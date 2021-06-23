package com.mul.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mul.utils.log.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i("测试一下是否好使了");
    }
}