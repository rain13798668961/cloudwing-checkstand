package com.cloudwing.checkstand.common.result;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;

/**
 * Create by cjf on 2019/2/22.
 */
public final class BaseResultUtil {

    /**
     * @param code      响应码
     * @param message   相应信息
     * @param any       返回的数据
     * @description     请求成功返回对象
     */
    public final BaseResult success(String code, String message, Object any) {
        return new BaseResult(code, message, any);
    }

    /**
     * @param any   返回的数据
     * @description 请求成功返回对象
     */
    public final BaseResult success(Object any) {
        String code = BaseStatusEnum.SUCCESS.getStatus();
        String message = BaseStatusEnum.SUCCESS.getMsg();
        return this.success(code, message, any);
    }

    /**
     * @description 请求成功返回对象
     */
    public final BaseResult success() {
        return this.success(null);
    }

    /**
     * @param baseStatusEnum  返回的响应码所对应的枚举类
     * @description         请求失败返回对象
     */
    public final BaseResult error(BaseStatusEnum baseStatusEnum) {
        return new BaseResult(baseStatusEnum.getStatus(), baseStatusEnum.getMsg(), null);
    }

    /**
     * @param code      响应码
     * @param message   相应信息
     * @description     请求失败返回对象
     */
    public final BaseResult error(String code, String message) {
        return new BaseResult(code, message, null);
    }
}
