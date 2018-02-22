package com.qushuwang.qushuwang.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class UmengUtil {

    private  static  UmengUtil umengUtil;

    public static UmengUtil getUmengUtil(){
        if (umengUtil==null){
            umengUtil = new UmengUtil();
        }else {
            return umengUtil;
        }
        return umengUtil;
    }

    public static   String getVersion(Context context) {
        String version="";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    public static   void   onEvent(Context context , String EventsName, HashMap<String,String> map){
        if(map!=null){
            map.put("Android版本",getVersion(context));
            MobclickAgent.onEvent(context,EventsName,map);
        }else {
            HashMap<String, String> maps = new HashMap<String, String>();
            maps.put("Android版本", getVersion(context));
            MobclickAgent.onEvent(context, EventsName, maps);
        }
    }

}
