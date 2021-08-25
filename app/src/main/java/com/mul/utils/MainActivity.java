package com.mul.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.mul.utils.log.LogUtil;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView text;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        text.setText(NumberUtils.divide(0.00002, "0.01", 3));
        LogUtil.i("测试一下是否好使了");
    }
}