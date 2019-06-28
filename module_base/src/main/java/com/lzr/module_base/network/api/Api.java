package com.lzr.module_base.network.api;


import com.lzr.module_base.commen.Constants;

/**
 * Created by 10302 on 2018/10/30.
 */

public interface Api {

    String BASE_API = Constants.BASE_URL;

    //用户登录接口
    String login = "Member/login";

    //查询用户资料
    String loadUserInfo = "Userprofile/personalInformation";

}
