package com.wengmengfan.btwang.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.orhanobut.logger.Logger;
import com.wengmengfan.btwang.base.BaseApplication;
import com.wengmengfan.btwang.bean.DownVideoBean;
import com.wengmengfan.btwang.bean.MessageEventBean;
import com.wengmengfan.btwang.utils.DeviceUtils;
import com.wengmengfan.btwang.utils.NotificationHandler;
import com.wengmengfan.btwang.utils.PreferUtil;
import com.wengmengfan.btwang.view.CommonDialog;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

import org.greenrobot.eventbus.EventBus;

/**
 * sayid ....
 * Created by wengmf on 2018/3/14.
 */

public class DownTorrentVideoService extends Service {


    private String playPath, playTitle, videoPath, PlayimgUrl;


    private NotificationCompat.Builder nBuilder;
    private CommonDialog commonDialog;
    private NotificationHandler nHandler;
    private long taskId = -1;
    private boolean isDown = false;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                XLTaskInfo taskInfo = XLTaskHelper.instance().getTaskInfo(taskId);

                Logger.e("mFileName >>  "+taskInfo.mFileName+"\n"+
                                                  "mFileSize >>  "+taskInfo.mFileSize+"\n"+
                                                  "mDownloadSize >>  "+taskInfo.mDownloadSize);

                if (taskInfo.mFileSize > DeviceUtils.getSDFreeSize()) {
                    XLTaskHelper.deleteTask(taskId, videoPath);
                    nHandler.cancelNotification((int) taskId);
                    commonDialog.show();
                } else {
                    if (taskInfo.mDownloadSize > 0) {
                        isDown = true;
                        nHandler .updateProgressNotification(nBuilder, (int) (taskInfo.mDownloadSize * 100 / taskInfo.mFileSize), (int) taskId);
                    }
//                    mTaskStatus:当前状态，0连接中1下载中 2下载完成 3失败
                    switch (taskInfo.mTaskStatus){
                        case 0:
                            Logger.e("连接中");
                            break;
                        case 1:

                            handler.sendMessageDelayed(handler.obtainMessage(0, taskId), 1000);
                            MessageEventBean messageEventBean = new MessageEventBean();
                            messageEventBean.setmDownloadSize(taskInfo.mDownloadSize);
                            messageEventBean.setmFileName(taskInfo.mFileName);
                            messageEventBean.setmFileSize(taskInfo.mFileSize);
                            EventBus.getDefault().post(messageEventBean);

                            break;
                        case 2:
                            nHandler.cancelNotification((int) taskId);
                            Logger.e("下载完成");
                            break;

                        case 3:
                            nHandler.cancelNotification((int) taskId);
                            Logger.e("失败");
                            break;

                    }
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

        if (!isDown) {
            try {
                if(playPath.startsWith("thunder://")){
                    taskId = XLTaskHelper.instance().addThunderTask(playPath, videoPath, playTitle);
                }else {
                    taskId = XLTaskHelper.instance().addTorrentTask(playPath, videoPath, null);
                }
                Logger.e(String.valueOf(taskId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            nBuilder =   nHandler .createProgressNotification(DownTorrentVideoService.this, playTitle, (int) taskId);
            handler.sendMessage(handler.obtainMessage(0));
        }

        DownVideoBean downVideoBean = new DownVideoBean();
        downVideoBean.setPlayimgUrl(PlayimgUrl);
        downVideoBean.setPlayPath(playPath);
        downVideoBean.setPlayTitle(playTitle);
        if (taskId != -1)
            downVideoBean.setTaskId(taskId);
        BaseApplication.downVideoBeanList.add(downVideoBean);
        return super.onStartCommand(intent, flags, startId);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate() {
        super.onCreate();

        XLTaskHelper.init(getApplicationContext());
        nHandler  = NotificationHandler.getInstance(this);
        commonDialog = new CommonDialog(DownTorrentVideoService.this, "存储空间不足");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
