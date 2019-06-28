package com.lzr.module_base.network.retrofit;

import android.util.Log;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lzr.module_base.network.api.Api;
import com.lzr.module_base.network.api.ApiService;
import com.lzr.module_base.network.interceptor.BasicParamsInterceptor;
import com.lzr.module_base.utils.UnicodeUtil;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/6/28.
 */

public class ApiManager {
    private static ApiService mApiService;
    private static boolean isShowLog;
    private static final long TIME_OUT_CONNECT = 25;//秒
    private static final long TIME_OUT_READ = 30;//秒

    public static ApiService getApiService() {
        if (mApiService == null) {
            mApiService = createService(ApiService.class);
        }
        return mApiService;
    }

    public static <S> S createService(Class<S> serviceClass) {
        return getRetrofit(Api.BASE_API).create(serviceClass);
    }

    public static void setDebug(boolean isShow) {
        isShowLog = isShow;
    }

    /**
     * 默认Retrofit实例，用于普通网络请求
     * 添加了超时机制，采用默认的超时处理
     *
     * @param baseUrl
     * @return
     */
    private static Retrofit getRetrofit(String baseUrl) {
        OkHttpClient client = buildOkhttp()
                .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                //.retryOnConnectionFailure() //是否超时重连
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0,
                .build();
    }

    /**
     * 构建基础okHttp实例
     * 设置日志
     * 设置请求头
     * //后期可能设置cookie
     *
     * @return
     */
    private static OkHttpClient.Builder buildOkhttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        CookieHandler cookieHandler = new CookieManager();
        builder.cookieJar(new JavaNetCookieJar(cookieHandler));
        builder.addInterceptor(createHttpLog());

        builder.addNetworkInterceptor(
                new BasicParamsInterceptor.Builder()
                        //-----------------------------添加头部-----------------------
                        //.addHeaderParam(ReqHeader.Key.HTTP_PHONETYPE, "android")
                        //-----------------------------添加固定参数-----------------------
//                        .addParam("oauth_token", "b0f8ce9c3939b7d543c2e776d9af7d65")
//                        .addParam("oauth_token_secret","b7a9144c245ffe0be4088f95b5d009a9")
//                        .addParam("api_type","22222222")
//                        .addQueryParam("target","a_lyds")
//                        .addParam("target","a_lyds")//固定参数 统计哪一个端的
//                        .addParam("current_version", Constants.REQUEST_VSERSION_NAME)
                        .build());

        return builder;
    }

    /**
     * 打印日志
     *
     * @return
     */
    private static HttpLoggingInterceptor createHttpLog() {
        final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("返回的数据:", UnicodeUtil.decode(message));
            }
        });

        logInterceptor.setLevel(isShowLog ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logInterceptor;
    }

    /**
     * header的中文转码
     *
     * @param headInfo
     * @return
     */
    private static String encodeHeadInfo(String headInfo) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append(String.format("\\u%04x", (int) c));
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }


}
