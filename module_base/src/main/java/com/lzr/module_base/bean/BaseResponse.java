package com.lzr.module_base.bean;

public class BaseResponse<T> {


    private boolean result;
    private String code;
    private String msg;
    private String OData;
    private T AData;
    private T data;


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getOData() {
        return OData;
    }

    public void setOData(String OData) {
        this.OData = OData;
    }

    public T getAData() {
        return AData;
    }

    public void setAData(T AData) {
        this.AData = AData;
    }
}