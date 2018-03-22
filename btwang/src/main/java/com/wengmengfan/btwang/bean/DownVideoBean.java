package com.wengmengfan.btwang.bean;

/**
 * sayid ....
 * Created by wengmf on 2018/3/22.
 */

public class DownVideoBean {
    private String playPath;
    private String playTitle;
    private String PlayimgUrl;
    private  long taskId;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getPlayPath() {
        return playPath;
    }

    public void setPlayPath(String playPath) {
        this.playPath = playPath;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public String getPlayimgUrl() {
        return PlayimgUrl;
    }

    public void setPlayimgUrl(String playimgUrl) {
        PlayimgUrl = playimgUrl;
    }
}
