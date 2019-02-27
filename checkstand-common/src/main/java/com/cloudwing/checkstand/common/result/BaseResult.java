package com.cloudwing.checkstand.common.result;

import com.cloudwing.checkstand.common.enums.BaseStatusEnum;

import java.io.Serializable;

/**
 * 基础返回结果对象
 * @param <T>
 */
public class BaseResult<T>  implements Serializable {

    private String status;

    private String msg;

    private T data;

    public BaseResult() {
    }

    public BaseResult(BaseStatusEnum statusEnum) {
        this.status = statusEnum.getStatus();
        this.msg = statusEnum.getMsg();
    }

    public BaseResult(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BaseResult(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
