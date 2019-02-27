//测试环境
//var domain = "http://127.0.0.1/layui-template";

//测试环境 虚拟域名 将项目打包成ROOT.war发布
//var domain = "http://card.yuzfeng.com";

//正式环境
//var domain = "https://cus-seion.yunyitg.com";

//根据tomcat/myapps-config 的配置 动态生成domain 路径
var domain = "";

var Routes = {
    //主页
    "domain" : domain + "/index",
    //登陆验证请求url
    "loginUrl" : domain + "/account/login",
    //登陆界面
    "loginHtml" : domain + "/login",
    //登出请求url
    "logoutUrl" : domain + "/account/logout",
    //修改密码请求
    //"passwordChangeUrl" : domain + "/ajax/user/password/change",
    //初始化数据
    "getInitDataUrl" : domain + "/account/getInitData",
    //获取导航栏数据
    // "getNavDataUrl" : domain + "/ajax/user/getNavData",
    //下面为业务请求
    //订单列表加载
    "orderListUrl" : domain + "/order/list",
    //订单新增请求
    "orderPlaceUrl" : domain + "/order/place",
    //订单支付请求
    "orderSubmitUrl" : domain + "/order/submit",


    //商户列表加载
    "mchListUrl" : domain + "/mch/list",


    //现场列表加载
    "officeListUrl" : domain + "/office/list",
    //现场新增请求
    "officeCreateUrl" : domain + "/office/create",
    //现场关联商户加载
    "officeMchRelatedUrl" : domain + "/office/mch/list",

    // 用户列表加载
    "userListUrl" : domain + "/user/list",
    // 用户列表更新
    "userUpdateUrl" : domain + "/user/update",
    // 用户赋权
    "userOperateUrl" : domain + "/user/operate",
    // 用户赋权关联
    "userOperateUpdateUrl" : domain + "/user/operate/update",
    //用户关联现场加载
    "userOfficelistUrl" : domain + "/order/office/list",
    //用户关联商户加载
    "userMerchantlistUrl" : domain + "/order/mch/list",



 };
