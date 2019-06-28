package com.lzr.module_base.network.exp;




import io.reactivex.Observable;
import rx.functions.Func1;

/**
 * 自定义Func，主要用于对抛出自定义异常
 * @param <T>
 */
public class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override
        public Observable<T> call(Throwable throwable) {
            //ExceptionEngine为处理异常的驱动器
            return Observable.error(ExceptionEngine.handleException(throwable));
        }
    }