package com.wengmengfan.doutu.api.support;


import com.blankj.utilcode.utils.LogUtils;


public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        LogUtils.i("http : " + message);
    }
}
