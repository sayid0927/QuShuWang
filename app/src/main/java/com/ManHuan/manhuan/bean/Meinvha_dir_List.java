package com.ManHuan.manhuan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class Meinvha_dir_List {


    /**
     * res : 00000
     * message : 查询成功
     * currentTimes : 1.509774625181E9
     * data : [{"id":1,"dir":"性感美女"},{"id":2,"dir":"模特美女"}]
     */

    private String res;
    private String message;
    private double currentTimes;
    private List<DataBean> data;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getCurrentTimes() {
        return currentTimes;
    }

    public void setCurrentTimes(double currentTimes) {
        this.currentTimes = currentTimes;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * dir : 性感美女
         */

        private int id;
        private String dir;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }
}
