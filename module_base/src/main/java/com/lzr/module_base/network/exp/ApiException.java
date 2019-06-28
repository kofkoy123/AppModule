package com.lzr.module_base.network.exp;


import com.lzr.module_base.network.constants.RespErrorAction;

/**
 * Created by Administrator on 2018/6/28.
 */

public class ApiException extends Exception {
    private final int code;
    public String msg;
    private Object object;
    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.msg = RespErrorAction.getMsgByCode(code);
    }

    public ApiException(Throwable throwable, int code, Object object) {
        super(throwable);
        this.code = code;
        this.msg = RespErrorAction.getMsgByCode(code);
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "\nCause：\n"+getCause()+"\ncode:"+code+"\nmsg:"+msg;
    }
}
