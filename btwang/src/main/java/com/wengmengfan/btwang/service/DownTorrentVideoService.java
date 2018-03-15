package com.wengmengfan.btwang.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.blankj.utilcode.utils.ConvertUtils;
import com.orhanobut.logger.Logger;
import com.wengmengfan.btwang.R;
import com.wengmengfan.btwang.base.BaseApplication;
import com.wengmengfan.btwang.utils.DeviceUtils;
import com.wengmengfan.btwang.utils.ImgLoadUtils;
import com.wengmengfan.btwang.utils.PreferUtil;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

import java.util.concurrent.TimeUnit;

import static com.wengmengfan.btwang.tinker.SampleApplicationContext.context;
import static com.wengmengfan.btwang.utils.DeviceUtils.getPlayStart;
import static xllib.FileUtils.convertFileSize;

/**
 * sayid ....
 * Created by wengmf on 2018/3/14.
 */

public class DownTorrentVideoService extends Service {


    private String playPath, playTitle, videoPath,PlayimgUrl;

    private int preProgress = 0;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;


    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                long taskId = (long) msg.obj;
                XLTaskInfo taskInfo = XLTaskHelper.instance().getTaskInfo(taskId);

                Logger.e("fileSize:" + taskInfo.mFileSize + "\n" +
                        " downSize:" + taskInfo.mDownloadSize
                        + "/sdcdnSpeed:" + convertFileSize(taskInfo.mAdditionalResDCDNSpeed)
                        + "/s filePath:" + videoPath);

                if (taskInfo != null && taskInfo.mDownloadSize > 0)
                    updateNotification(taskInfo.mDownloadSize * 100 / taskInfo.mFileSize);

                handler.sendMessageDelayed(handler.obtainMessage(0, taskId), 1000);
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        XLTaskHelper.init(getApplicationContext());
        playPath = PreferUtil.getInstance().getPlayPath();
        playTitle = PreferUtil.getInstance().getPlayTitle();
        PlayimgUrl = PreferUtil.getInstance().getPlayimgUrl();
        videoPath = DeviceUtils.getSDVideoPath(playTitle);


        BaseApplication.MAIN_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                long taskId = 0;
                try {
                    taskId = XLTaskHelper.instance().addTorrentTask(playPath, videoPath, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                initNotification();
                handler.sendMessage(handler.obtainMessage(0, taskId));
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 初始化Notification通知
     */
    public void initNotification() {

        builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.mm)
                .setContentText("0%")
                .setContentTitle(playTitle)
                .setProgress(100, 0, false);
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 更新通知
     */
    public void updateNotification(long progress) {
        int currProgress = (int) progress;
        if (preProgress < currProgress) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, (int) progress, false);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
        preProgress = (int) progress;
    }
}
