package com.wengmengfan.a80sttwang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xunlei.downloadlib.XLTaskHelper;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XLTaskHelper.init(getApplicationContext());

        long dd = 0;
        try {
            dd = XLTaskHelper.instance().addThunderTask("thunder://QUFodHRwOi8vZGwxMDcuODBzLmNvbS5jbzo5OTkvMTgwMy/ph5Fz5qKm5LmhL+mHkXPmoqbkuaFfYmQubXA0Wlo=","/sdcard/" , null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("DD >>  " , String.valueOf(dd));
    }
}
