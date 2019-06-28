package com.lzr.module_base.network.constants;

/**
 * Created by Nowy on 2016/10/14.
 * 服务的响应码对照表
 */
public interface RespCode {
    /**
     * 本地异常码，表示网络访问失败
     */
    int CODE_NATIVE_NET_ERROR = -1000;//

    /**
     * 未知错误
     */
//    int CODE_UNKNOWN = -10096;
    int CODE_UNKNOWN = 20000;
    /**
     * 解析错误
     */
    int CODE_PARSE_ERROR = -10010;
    /**
     * 网络错误
     */
    int CODE_NETWORD_ERROR = -10020;
    /**
     * 协议出错
     */
    int CODE_HTTP_ERROR = -10030;



    /**
     * 代码异常
     */
    int CODE_ERROR = -10097;


    /*-------------------------系统-----------------------------*/
    /**
     * 成功
     */
    int CODE_SUCCESS = 1;


    //----------------------------服务器没有明确说明，只是备用----------
    /**
     * 登录状态异常
     */
    int CODE_LOGIN_ERROR = 9993;

    /**
     * token失效
     */
    int CODE_TOKEN_EXPIRED = 9991;


}
