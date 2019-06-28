package com.lzr.module_base.utils;


import com.lzr.module_base.network.retrofit.ApiManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by Administrator on 2017/5/8.
 */

public class LogUtil {


    private static boolean isShowLog;
    private static String TAG = "lzr";

    public static void initLogger(boolean isShow) {
        isShowLog = isShow;
        //网络请求打印
        ApiManager.setDebug(isShow);
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag(TAG)   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        //显示就调用一下方法
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }


    public static void v(String msg) {
        if (isShowLog)
            Logger.v(msg);
    }

    public static void d(String msg) {
        if (isShowLog)
            Logger.d(msg);
    }

    public static void i(String msg) {
        if (isShowLog)
            Logger.i(msg);
    }

    public static void w(String msg) {
        if (isShowLog)
            Logger.w(msg);
    }

    public static void e(String msg) {
        if (isShowLog)
            Logger.e(msg);
    }

    public static void e(String tag, String msg){
        if (isShowLog)
            Logger.t(tag).e(msg);
    }

    public static void json(String msg) {
        if (isShowLog)
            Logger.json(msg);
    }

    public static void xml(String msg) {
        if (isShowLog)
            Logger.xml(msg);
    }

    /**
     * 打印其他对象,map list set
     *
     * @param obj
     */
    public static void other(Object obj) {
        if (isShowLog)
            Logger.d(obj);
    }

}
