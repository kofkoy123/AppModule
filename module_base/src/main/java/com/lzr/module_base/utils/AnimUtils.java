package com.lzr.module_base.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.View;


import com.lzr.module_base.widget.KickBackAnimator;

import java.util.List;

/**
 * 作者： 10302
 * 创建时间：2019/4/18
 */

public class AnimUtils {


    private AnimUtils() {
    }

    private static class AnimUtilsInstance {
        private static final AnimUtils INSTANCE = new AnimUtils();
    }

    public static AnimUtils getInstance() {
        return AnimUtils.AnimUtilsInstance.INSTANCE;
    }


    /**
     * 逐个跳出动画
     * @param childViewList
     */
    public void showAnimation(List<View> childViewList) {
        for (int i = 0; i < childViewList.size(); i++) {
            final View child = childViewList.get(i);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 200, 0);
                    fadeAnim.setDuration(600);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(600);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 100);
        }
    }


    //显示动画从小变大
    public void showScaleAnim(final View view, Animator.AnimatorListener listener) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "ScaleX", 0f, 1f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (Float) valueAnimator.getAnimatedValue();
                view.setScaleX(val);
                view.setScaleY(val);
            }
        });
        if (listener != null) {
            anim.addListener(listener);
        }
        anim.setDuration(500);
        anim.start();
    }

    //隐藏动画 从大变小
    public void hideScaleAnim(final View view, Animator.AnimatorListener listener) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "ScaleX", 1f, 0f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (Float) valueAnimator.getAnimatedValue();
                view.setScaleX(val);
                view.setScaleY(val);
            }
        });
        if (listener != null) {
            anim.addListener(listener);
        }
        anim.setDuration(500);
        anim.start();
    }


    /**
     * 下移动画
     */
    public void translationDownAnim(final View view) {
        int height = view.getHeight();
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(view, "translationY", 0, height);
        translationY.setDuration(300);  //设置动画时间
        translationY.start(); //启动
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 上移动画
     */
    public void translationUpAnim(View view) {
        int height = view.getHeight();
        if (height == 0){
            height = DisplayUtil.dip2px(view.getContext(),80);
        }
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(view, "translationY", height, 0);
        translationY.setDuration(300);  //设置动画时间
        translationY.start(); //启动
    }


    /**
     * 渐变动画
     * @param view
     */
    public void alphaAnim(View view){
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1f);
        anim.setDuration(800);// 动画持续时间
        anim.start();
    }
}
