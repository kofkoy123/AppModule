package com.lzr.module_base.network.subscriber;





import com.lzr.module_base.BuildConfig;
import com.lzr.module_base.network.constants.RespCode;
import com.lzr.module_base.network.exp.ApiException;

import rx.Subscriber;

/**
 * 错误处理基类，主要把未捕获的异常转化为自定义异常{@link ApiException}
 * @param <T>
 */
public abstract class ErrorSubscriber<T> extends Subscriber<T> {
    public static final boolean DEBUG= BuildConfig.DEBUG;

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            onError(new ApiException(e, DEBUG ? RespCode.CODE_ERROR : RespCode.CODE_UNKNOWN));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}