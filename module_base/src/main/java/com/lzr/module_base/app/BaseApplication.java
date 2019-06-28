package com.lzr.module_base.app;

import android.app.Application;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzr.module_base.BuildConfig;

/**
 * 作者： 10302
 * 创建时间：2019/6/27
 */
public class BaseApplication extends Application {

    private static BaseApplication instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }


    public static BaseApplication getInstance() {
        return instance;
    }

}
