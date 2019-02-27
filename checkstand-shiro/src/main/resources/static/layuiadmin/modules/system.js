/*
* 系统公用模块扩展
* */
layui.define(['index', 'setter', 'jquery', 'layer', 'table', 'element', 'laydate'], function (exports) {

    var $ = layui.$;
    var setter = layui.setter;
    var index = layui.index;
    // var conf = layui.conf;
    var layer = layui.layer;
    var table = layui.table;
    var element = layui.element;
    var laydate = layui.laydate;
    var system = {};

    //弹框（用于错误提示）
    system.alert = function (msg) {
        layer.msg(msg, {
            type: 1,
            anim: 6,
            time: 5000,
            shadeClose: true,
            closeBtn: 2
        });
    };
    //弹框（用于正常信息提示）
    system.alertM = function (msg) {
        layer.msg(msg, {
            offset: 'auto'
            ,icon: 1
        });
    };
    //弹框（用于正常错误（非10000）信息提示）
    system.alertF = function (msg) {
        layer.msg(msg, {
            offset: 'auto'
            ,icon: 2
        });
    };

    //loading
    var loadingStack = [];
    system.loading = function (show, option) {
        option = option || {};
        if (typeof(show) == "undefined" || show) {
            var index = layer.load(1, $.extend({}, {shade: 0.4}, option));
            loadingStack.push(index);
        } else {
            layer.close(loadingStack.pop());
        }
    };

    //progress
    var progressStack = [];
    system.progress = function (seconds) {
        //seconds传入进度条预估时间（秒），超过时间进度条卡在99%
        //传入false，关闭progress
        var flag = null;
        if (seconds === false) {
            element.progress('layui-system-progress', '99%');
            var data = progressStack.pop();
            clearInterval(data.flag);
            layer.close(data.index);
            return;
        }

        var index = layer.open({
            content: '<div class="layui-progress layui-progress-big" lay-filter="layui-system-progress" lay-showPercent="yes">' +
            '<div class="layui-progress-bar" lay-percent="3%"></div>' +
            '</div>',
            skin: 'layui-system-progress',
            anim: 5,
            area: ['90%', '18px'],
            shade: 0.4,
            title: false,
            closeBtn: false,
            btn: false,
            scrollbar: false,
            resize: false,
            success: function (layero, index) {
                element.render('progress');
                var current = 3;
                var surplus = 96;
                var beyond = seconds - 96;
                beyond = beyond > 0 ? beyond : 0;
                flag = setInterval(function () {
                    if (beyond > 0 && Math.random() > 0.5) {
                        beyond = beyond - 1;
                        seconds = seconds - 1;
                        return;
                    }
                    var step = parseInt(surplus / seconds);
                    step = seconds === 1 ? step : parseInt(parseInt(step / 2) + Math.random() * (parseInt(step / 2) + 1));
                    step = step > 1 ? step : 1;
                    current = current + step;
                    surplus = surplus - step;
                    seconds = seconds - 1;
                    element.progress('layui-system-progress', current + '%');
                    if (current >= 99) {
                        clearInterval(flag);
                    }
                }, 1000);
            }
        });
        progressStack.push({index: index, flag: flag});
    };

    //打开一个弹出的页面层
    system.open = function (option) {
        option = option ? option : {};
        option = $.extend({}, {
            type: 1
            , title: false
            , content: '<div></div>'
            , btn: ['确定', '取消']
            , closeBtn: 2
            , shade: 0.5
            , shadeClose: false
            , offset: '50px'
            , area: '600px'
            , id: ''
            , anim: 5
            , resize: false
            , yes: function (index, layero) {
                layer.close(index);
            }
            , btn2: function (index, layero) {
                layer.close(index);
            }
        }, option);

        return layer.open(option);
    };

    //获取cookie
    system.cookie = function (name) {
        var cookies = document.cookie;
        var list = cookies.split("; ");

        for (var i = 0; i < list.length; i++) {
            var arr = list[i].split("=");
            if (arr[0] == name) {
                return decodeURIComponent(arr[1]);
            }

        }
        return "";
    };

    //将时间字符串转为对象
    system.date = function (date) {
        date = new Date(date);
        return {
            year: date.getFullYear(),
            month: date.getMonth(),
            date: date.getDate(),
            hours: date.getHours(),
            minutes: date.getMinutes(),
            seconds: date.getSeconds()
        };
    };

    //获取url查询字符串，键对应的值，即问号 ? 之后的部分
    var query = null;
    system.getQuery = function (key) {
        if (query === null) {
            var search = location.search;
            search = search.substr(1);
            search = search.split('&');
            query = {};
            for (var i = 0; i < search.length; i++) {
                var s = search[i].split('=');
                query[s[0]] = s[1] ? s[1] : '';
            }
        }

        return query[key];
    };

    //localStorage操作，以当前url为表名，区分每个页面的值
    system.setData = function (key, value) {
        var table = location.pathname;
        layui.data(table, {key: key, value: value});
    };
    system.getData = function (key, defaultValue) {
        var table = location.pathname;
        var data = layui.data(table);
        return data[key] ? data[key] : defaultValue;
    };
    system.delData = function (key) {
        var table = location.pathname;
        layui.data(table, {key: key, remove: true});
    };

    //Ajax请求
    system.req = function (options) {
        var that = this
            , success = options.success
            , error = options.error
            , request = setter.request
            , response = setter.response
            , debug = function () {
            return setter.debug
                ? '<br><cite>URL：</cite>' + options.url
                : '';
        };

        options.data = options.data || {};
        options.headers = options.headers || {};

        if (request.tokenName) {
            var sendData = typeof options.data === 'string'
                ? JSON.parse(options.data)
                : options.data;

            //自动给参数传入默认 token
            options.data[request.tokenName] = request.tokenName in sendData
                ? options.data[request.tokenName]
                : (layui.data(setter.tableName)[request.tokenName] || '');

            //自动给 Request Headers 传入 token
            options.headers[request.tokenName] = request.tokenName in options.headers
                ? options.headers[request.tokenName]
                : (layui.data(setter.tableName)[request.tokenName] || '');
        }
        //CSRF
        if (request.csrfHeader) {
            options.headers[request.csrfHeader] = system.cookie(request.csrfCookie);
        }

        delete options.success;
        delete options.error;

        return $.ajax($.extend({
            type: 'post'
            , dataType: 'json'
            , success: function (res) {
                var statusCode = response.statusCode;

                //只有 response 的 code 一切正常才执行 done

                if (res[response.statusName] == statusCode.ok) {
                    typeof options.done === 'function' && options.done(res);
                }

                //登录状态失效，清除本地 access_token，并强制跳转到登入页
                else if (res[response.statusName] == statusCode.logout) {
                    location.href = res[response.dataName].url;
                }

                //其它异常
                else {
                    var error = [
                        '<cite>Error：</cite> ' + (res[response.msgName] || '返回状态码异常')
                        , debug()
                    ].join('');
                    system.alert(error);
                }

                //只要 http 状态码正常，无论 response 的 code 是否正常都执行 success
                typeof success === 'function' && success(res);
            }
            , error: function (e, code) {
                var error = [
                    '请求异常，请重试<br><cite>错误信息：</cite>' + code
                    , debug()
                ].join('');
                system.alert(error);

                typeof error === 'function' && error(res);
            }
        }, options));
    };

    //table组件全局配置
    var tableHeaders = {};
    // tableHeaders[setter.request.csrfHeader] = system.cookie(setter.request.csrfCookie);
    table.set({
        page: {
            limits: [10, 15, 20, 30]
            , layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
            , theme: '#1E9FFF'
        }
        , autoSort: false
        , method: 'post'
        // , headers: tableHeaders
        , parseData: function (res) {
            return {
                "code": res.status,
                "msg": res.msg,
                "count": res.data ? res.data.total : 0,
                // "data": res.data ? res.data.list : [],
                "data": res.data.list ? res.data.list : res.data,
                // "limit": res.data ? parseInt(res.data.per_page) : null
            };
        }
        , response: {
            statusCode: '10000'

        }
        // ,request:{
        //     pageName: 'pageNo'
        //     ,limitName: 'pageSize'
        // }
    });

    //laydate组件全局配置
    laydate.set({theme: '#1E9FFF'});

    //F5刷新当前子页面，而不刷新整个页面。（ctrl F5刷新整个页面）
    $('body').on('keydown', function (event) {
        if (event.keyCode == 116 && !event.ctrlKey) {
            event.preventDefault();
            if ($('iframe').length > 0) {
                top.layui.admin.events.refresh();
            } else {
                location.reload(true);
            }
        }
    });

    //打开标签页
    system.openTabsPage = function (url, text, param) {
        var query = '';
        if (param && typeof param === 'object') {
            query = query + '?';
            for (var k in param) {
                if (Array.isArray(param[k])) {
                    for (var i = 0; i < param[k].length; i++) {
                        query = query + k + '[]=' + param[k][i].toString() + '&';
                    }
                } else {
                    query = query + k + '=' + param[k].toString() + '&';
                }
            }
            query = query.substring(0, query.length - 1);
        }

        var topLayui = parent === self ? layui : top.layui;
        return topLayui.index.openTabsPage(url + query, text);
    };

    //在form的回调中关闭此form所在的layer弹层。可以在form里自由控制关闭
    system.layerFormClose = function (formDom) {
        var index = formDom.closest('.layui-layer.layui-layer-page');
        if (!index) {
            return;
        }
        index = parseInt(index.getAttribute('times'));
        if (!index) {
            return;
        }

        layer.close(index);
    };

    exports('system', system);
});