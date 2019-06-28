package com.lzr.module_base.network.exp;



import com.google.gson.JsonParseException;
import com.lzr.module_base.BuildConfig;
import com.lzr.module_base.network.constants.RespCode;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;


/**
 * Created by Lzx on 2016/7/11.
 */

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){
        if(BuildConfig.DEBUG){
            e.printStackTrace();
        }

        ApiException ex;
        if (e instanceof HttpException){             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, RespCode.CODE_HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    if(BuildConfig.DEBUG){
                        ex.msg = httpException.getLocalizedMessage();
                    }else{
                        ex.msg ="网络错误";  //均视为网络错误
                    }
                    break;
            }
            return ex;
        } else if (e instanceof NetResultException){   //服务器返回的错误
            NetResultException resultException = (NetResultException) e;
            ex = new ApiException(resultException, resultException.errCode,resultException.getObject());
            ex.msg = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ApiException(e, RespCode.CODE_PARSE_ERROR);
            if(BuildConfig.DEBUG){
                ex.msg =  e.getLocalizedMessage();
            }else{
                ex.msg ="解析错误";  //均视为解析错误
            }

            return ex;
        }else if(e instanceof UnknownHostException
                ||e instanceof ConnectException
                || e instanceof SocketTimeoutException){
            ex = new ApiException(e, RespCode.CODE_NETWORD_ERROR);
            if(BuildConfig.DEBUG){
                ex.msg =  e.getLocalizedMessage();
            }else{
                ex.msg ="暂无网络";  //均视为网络错误
            }
            return ex;
        }else {
            ex = new ApiException(e, RespCode.CODE_UNKNOWN);
            ex.msg = "未知错误";          //未知错误
            return ex;
        }
    }
}

