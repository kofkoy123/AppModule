package com.lzr.module_base.utils.nodoubleclick;

import java.util.Calendar;

/**
 *  判断下两次点击的时间
 */
public class OneClick {
    private String methodName;
    private static final int CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;

    public OneClick(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean check() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
}
