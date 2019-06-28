package com.lzr.module_base.network.api;



import com.lzr.module_base.bean.BaseResponse;
import com.lzr.module_base.bean.user.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiService {

    /**
     * 用户登录接口
     * <p>
     * isOpenId 是否为第三方登录，值为1/0 0：表示手机登录 1：表示第三方登录（必填）
     * key      手机登录时，key=手机号； 第三方登录时，key=openid；（必填）
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Api.login)
    Observable<BaseResponse<User>> userLogin(@FieldMap Map<String, String> params);


    /**
     * 获取用户信息
     *
     * @return
     */
    @GET(Api.loadUserInfo)
    Observable<BaseResponse<User>> loadUserInfo(@QueryMap Map<String, String> params);



}
