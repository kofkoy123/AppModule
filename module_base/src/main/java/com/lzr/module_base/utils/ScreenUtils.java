package com.lzr.module_base.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by y on 2016/4/28.
 */

/*
 * 屏幕像素转换工具类
 */
public class ScreenUtils {

    private static int baseWidth = 720;
    private static int baseHeight = 1280;
    private static float scalePercent = 1;
    private static float yScalePercent = 1;
    private static int screenHeight;
    private static int screenWidth;

    public static void init(Activity activity) {
        if (screenHeight == 0 || screenWidth == 0) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            screenHeight = metrics.heightPixels;
            screenWidth = metrics.widthPixels;
            yScalePercent = (float) screenHeight / baseHeight;
            scalePercent = (float) screenWidth / baseWidth;
        }
    }

    public static int getScaleValue(float value) {
        return (int) (value * scalePercent);
    }

    public static int getScaleY(float y) {
        return (int) (y * yScalePercent);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成sp
     */
    public int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    //获取屏幕宽度(px)
    public static int getScreenWidth() {
        return screenWidth;
    }

    //获得屏幕高度（px）
    public static int getScreenHeight() {
        return screenHeight;
    }

    //获取屏幕宽度(px)
    public static int getBaseWidth() {
        return baseWidth;
    }

    //获得屏幕高度（px）
    public static int getBaseHeight() {
        return baseHeight;
    }
}
