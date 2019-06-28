package com.lzr.module_base.network.subscriber;



import com.lzr.module_base.network.constants.RespCode;
import com.lzr.module_base.network.constants.RespErrorAction;
import com.lzr.module_base.network.exp.ApiException;

import java.util.Locale;

/**
 * Created by Administrator on 2018/6/28.
 */

public abstract class BaseSubscriber<T> extends ErrorSubscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onFinish(RespCode.CODE_SUCCESS, RespErrorAction.getMsgByCode(RespCode.CODE_SUCCESS), t);
    }

    @Override
    protected void onError(ApiException ex) {
        if (ex != null) {
            ex.printStackTrace();
        }
        int respCode = ex == null ? RespCode.CODE_UNKNOWN : ex.getCode();
        String msg = ex == null ? RespErrorAction.getMsgByCode(RespCode.CODE_UNKNOWN) : ex.msg;
        if(DEBUG)
            msg = String.format(Locale.CHINA,"%1$s(%2$d)",msg,respCode);
        /**
         * 此处data为请求返回的json数据（字符串形式）
         */
        Object data = ex == null ? null : ex.getObject();

        if (RespErrorAction.isLoginError(respCode)) {//登录问题
            onLoginExpired(respCode, msg, data);
        }else{
            onError(respCode, msg, data);//数据异常
        }
        onFinish(respCode, msg, data);
    }

    public abstract void onSuccess(T t);//如果是列表数据，在onSuccess里面拿相应集合数据去判断了

    public abstract void onError(int code, String msg, Object data);

    public abstract void onFinish(int code, String msg, Object data);

    public abstract void onLoginExpired(int code, String msg, Object data);
}
