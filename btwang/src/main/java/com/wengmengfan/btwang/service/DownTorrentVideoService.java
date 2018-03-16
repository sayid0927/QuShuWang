package com.wengmengfan.btwang.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.wengmengfan.btwang.utils.DeviceUtils;
import com.wengmengfan.btwang.utils.NotificationHandler;
import com.wengmengfan.btwang.utils.PreferUtil;
import com.wengmengfan.btwang.view.CommonDialog;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

/**
 * sayid ....
 * Created by wengmf on 2018/3/14.
 */

public class DownTorrentVideoService extends Service {


    private String playPath, playTitle, videoPath, PlayimgUrl;

    private NotificationHandler nHandler;
    private NotificationCompat.Builder nBuilder;
    private CommonDialog commonDialog;

    private long taskId = 0;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                XLTaskInfo taskInfo = XLTaskHelper.instance().getTaskInfo(taskId);
                if(taskInfo.mFileSize>DeviceUtils.getSDFreeSize()){
                    XLTaskHelper.deleteTask(taskId,videoPath);
                    nHandler.cancelNotification((int)taskId);
                    commonDialog.show();
                }else {
                    if (taskInfo.mDownloadSize > 0) {
                        nHandler.updateProgressNotification(nBuilder, (int) (taskInfo.mDownloadSize * 100 / taskInfo.mFileSize), (int) taskId);
                    }
                    handler.sendMessageDelayed(handler.obtainMessage(0, taskId), 1000);
                }
            }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        playPath = PreferUtil.getInstance().getPlayPath();
        playTitle = PreferUtil.getInstance().getPlayTitle();
        PlayimgUrl = PreferUtil.getInstance().getPlayimgUrl();
        videoPath = DeviceUtils.getSDVideoPath(playTitle);
        try {
            taskId = XLTaskHelper.instance().addTorrentTask(playPath, videoPath, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        nBuilder = nHandler.createProgressNotification(DownTorrentVideoService.this, playTitle, (int) taskId);
        handler.sendMessage(handler.obtainMessage(0));
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        XLTaskHelper.init(getApplicationContext());
        nHandler = NotificationHandler.getInstance(this);
        commonDialog = new CommonDialog(DownTorrentVideoService.this,"存储空间不足");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
