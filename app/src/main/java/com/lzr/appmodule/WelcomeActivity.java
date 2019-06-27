package com.lzr.appmodule;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;


import com.alibaba.android.arouter.launcher.ARouter;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build("/user/LoginActivity").navigation();
            }
        },2000);

    }
}
