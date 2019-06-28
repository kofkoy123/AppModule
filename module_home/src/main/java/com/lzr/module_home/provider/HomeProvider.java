package com.lzr.module_home.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lzr.module_base.provider.IHomeProvider;
import com.lzr.module_home.fragment.HomeFragment;

/**
 * 作者： 10302
 * 创建时间：2019/6/28
 */

@Route(path = "/home/main",name = "首页服务")
public class HomeProvider implements IHomeProvider {


    @Override
    public Fragment getMainHomeFragment() {
        return HomeFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
