package com.lzr.module_base.network.subscriber;

/**
 * Created by Administrator on 2018/6/28.
 */

public abstract class NetSubscriber<T> extends BaseSubscriber<T>{

    @Override
    public abstract void  onSuccess(T t);

    @Override
    public void onError(int code, String msg, Object data) {

    }

    @Override
    public void onFinish(int code, String msg, Object data) {

    }

    @Override
    public void onLoginExpired(int code, String msg, Object data) {

    }
}

