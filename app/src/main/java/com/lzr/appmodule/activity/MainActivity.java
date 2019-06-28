package com.lzr.appmodule.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lzr.appmodule.R;
import com.lzr.appmodule.entity.MainChannel;
import com.lzr.module_base.provider.IHomeProvider;

import butterknife.BindView;


public class MainActivity extends AppCompatActivity {


    @Autowired(name = "/home/main")
    IHomeProvider mHomeProvider;


    private Fragment mHomeFragment;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        initView();

    }

    private void initView() {

        mTvTitle = findViewById(R.id.tv_title);

        if (mHomeProvider != null) {
            mHomeFragment = mHomeProvider.getMainHomeFragment();
        }

        if (mHomeFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_content, mHomeFragment, MainChannel.NEWS.name).commit();
            mTvTitle.setText("首页");
        }

    }
}
