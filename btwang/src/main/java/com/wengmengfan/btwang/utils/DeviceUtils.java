package com.wengmengfan.btwang.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.wengmengfan.btwang.base.Constant;
import com.wengmengfan.btwang.contentprovider.PlayController;
import com.wengmengfan.btwang.contentprovider.PlayProvider;

import java.io.File;
import java.io.IOException;

/**
 * Created by zs on 2016/7/7.
 */
public class DeviceUtils {
    /*
     * 获取应用名
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            //获取包管理者
            PackageManager pm = context.getPackageManager();
            //获取packageInfo
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            //获取versionName
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionName;
    }

    /*
     * 获取应用版本
     */
    public static int getVersionCode(Context context) {

        int versionCode = 0;
        try {
            //获取包管理者
            PackageManager pm = context.getPackageManager();
            //获取packageInfo
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            //获取versionCode
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }


    public static String getSDPath() {
        String sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                    .separator + Constant.FILEPATH;
        } else {
            File file = new File("/mnt/sdcard/" + Constant.FILEPATH);//创建文件
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    sdDir = file.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file.getAbsolutePath();
            } else {
                sdDir = file.getAbsolutePath();
            }
            return sdDir;
        }
        return sdDir;
    }

    public static String getSDPath(String fName) {
        String sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                    .separator + Constant.FILEPATH+"/"+fName;
        } else {
            File file = new File("/mnt/sdcard/" + Constant.FILEPATH+"/"+fName);//创建文件
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    sdDir = file.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file.getAbsolutePath();
            } else {
                sdDir = file.getAbsolutePath();
            }
            return sdDir;
        }
        return sdDir;
    }
    public static String getSDVideoPath(String fName) {
        String sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                    .separator + "Video"+"/"+fName;
        } else {
            File file = new File("/mnt/sdcard/" + "Video"+"/"+fName);//创建文件
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    sdDir = file.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file.getAbsolutePath();
            } else {
                sdDir = file.getAbsolutePath();
            }
            return sdDir;
        }
        return sdDir;
    }


    public static PlayController getPlayStart(Context context, int id) {

        PlayController playController = new PlayController();
        Uri playUri = PlayProvider.PLAY_CONTENT_URI;
        Cursor playCursor = context.getContentResolver().query(playUri, new String[]{"_id", "type", "isPlay"}, null, null, null);

        if (playCursor != null) {
            while (playCursor.moveToNext()) {
                if (playCursor.getInt(0) == id) {
                    playController.Id = playCursor.getInt(0);
                    playController.type = playCursor.getString(1);
                    playController.isPlay = playCursor.getString(2);
                }
            }
            playCursor.close();
        }
        return playController;
    }

    public static void updatePlayStart(Context context, int id, String falg) {
        Uri bookUri = PlayProvider.PLAY_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put("isPlay", falg);
        try {
            int c = context.getContentResolver().update(bookUri, values, "_id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.toString();
        }
    }
}


