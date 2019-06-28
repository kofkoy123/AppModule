package com.lzr.module_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lzr.module_home.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        mHomeFragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mHomeFragment, "11").commit();

    }
}
