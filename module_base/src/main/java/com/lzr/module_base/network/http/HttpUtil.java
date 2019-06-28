package com.lzr.module_base.network.http;//package com.ifeimo.audiorecord.network.http;
//
//
//import com.ifeimo.audiorecord.bean.ResultBean;
//import com.ifeimo.audiorecord.bean.audio.AudioRootBean;
//import com.ifeimo.audiorecord.bean.token.AudioToken;
//import com.ifeimo.audiorecord.bean.token.ImageToken;
//import com.ifeimo.audiorecord.bean.token.UploadCallback;
//import com.ifeimo.audiorecord.bean.user.UserRootBean;
//import com.ifeimo.audiorecord.bean.version.VersionRoot;
//import com.ifeimo.audiorecord.network.exp.HttpResponseFunc;
//import com.ifeimo.audiorecord.network.retrofit.ApiManager;
//
//import java.util.Map;
//
//import rx.Observable;
//import rx.schedulers.Schedulers;
//
//public class HttpUtil {
//
//
//    private static String target = "a_lyds";
//
//    /**
//     * 用户登录接口
//     * <p>
//     * isOpenId 是否为第三方登录，值为1/0 0：表示手机登录 1：表示第三方登录（必填）
//     * key      手机登录时，key=手机号； 第三方登录时，key=openid；（必填）
//     *
//     * @return
//     */
//    public static Observable<UserRootBean> login(Map<String, String> params) {
//        return ApiManager.getApiService().userLogin(params)
//                .onErrorResumeNext(new HttpResponseFunc<UserRootBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @param user_id   目标id
//     * @param member_id 访问者id （两者相等相当于查自己信息）
//     * @return
//     */
//    public static Observable<UserRootBean> loadUserInfo(String user_id, String member_id) {
//        return ApiManager.getApiService().loadUserInfo(user_id, member_id, target)
//                .onErrorResumeNext(new HttpResponseFunc<UserRootBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//
//    /**
//     * 修改用户资料
//     *
//     * @param params 用户资料的map 可修改有 id  用户id 必填 nickname 昵称 avatar   头像
//     *               location 用户地区 mobile 手机号sex 性别 qq  QQ号 email 邮箱 address  地址
//     * @return 用户信息
//     */
//    public static Observable<UserRootBean> modifyUserInfo(Map<String, String> params) {
//        return ApiManager.getApiService().modifyUserInfo(params)
//                .onErrorResumeNext(new HttpResponseFunc<UserRootBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 上传封面和头像获取token
//     *
//     * @param type      上传类型：值avatar代表头像；值cover代表封面
//     * @param member_id 用户id
//     * @return
//     */
//    public static Observable<ImageToken> getUpLoadImageToken(String type, String member_id) {
//        return ApiManager.getApiService().getUpLoadImageToken(type, member_id, target)
//                .onErrorResumeNext(new HttpResponseFunc<ImageToken>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 将图片保存服务器
//     *
//     * @param type      上传类型：值avatar代表头像；值cover代表封面
//     * @param member_id 用户ID
//     * @param name      上文件名称
//     * @return
//     */
//    public static Observable<ImageToken> saveImageToService(String type, String member_id, String name) {
//        return ApiManager.getApiService().saveImageToService(type, member_id, name, target)
//                .onErrorResumeNext(new HttpResponseFunc<ImageToken>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 获取上传音频的token
//     *
//     * @param member_id   用户ID
//     * @param audio_title 音频标题
//     * @param time_length 音频时长
//     * @return
//     */
//    public static Observable<AudioToken> getUpLoadAudioToken(String member_id, String type, String audio_title,
//                                                             String time_length, String size) {
//        return ApiManager.getApiService().getUpLoadAudioToken(member_id, type,audio_title, time_length,size, target)
//                .onErrorResumeNext(new HttpResponseFunc<AudioToken>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//
//    /**
//     * 音频上传完的回调
//     *
//     * @param member_id  用户ID
//     * @param audio_id   音频id
//     * @param is_success 是否上传成功，1为上传成功，0为上传失败
//     * @return
//     */
//    public static Observable<UploadCallback> saveAudioCallback(String member_id, String audio_id, String is_success) {
//        return ApiManager.getApiService().saveAudioCallback(member_id, audio_id, is_success, target)
//                .onErrorResumeNext(new HttpResponseFunc<UploadCallback>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @param member_id 目标id
//     * @param page      页数
//     * @return
//     */
//    public static Observable<AudioRootBean> loadAudioList(String member_id, int page) {
//        return ApiManager.getApiService().loadAudioList(member_id, String.valueOf(page), target)
//                .onErrorResumeNext(new HttpResponseFunc<AudioRootBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 绑定手机号
//     * @param member_id 用户id
//     * @param key 手机号
//     * @return
//     */
//    public static Observable<ResultBean> bindMobile(String key, String member_id) {
//        return ApiManager.getApiService().bindMobile(key,member_id,"1", target)
//                .onErrorResumeNext(new HttpResponseFunc<ResultBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 删除服务器音频
//     * @param qn_key 数组形式的地址
//     * @return
//     */
//    public static Observable<ResultBean> deleteAudio(String qn_key) {
//        return ApiManager.getApiService().deleteAudio(qn_key,target)
//                .onErrorResumeNext(new HttpResponseFunc<ResultBean>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//    /**
//     * 版本检测
//     * @param build 发行版本号
//     * @return
//     */
//    public static Observable<VersionRoot> getVersion(String build) {
//        return ApiManager.getApiService().getVersion(build,target)
//                .onErrorResumeNext(new HttpResponseFunc<VersionRoot>())//自定义捕获异常
//                .subscribeOn(Schedulers.io());//在IO线程中做网络请求
//    }
//
//
//
//}
