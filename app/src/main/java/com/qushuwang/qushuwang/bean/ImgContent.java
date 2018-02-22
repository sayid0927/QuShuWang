package com.qushuwang.qushuwang.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class ImgContent {


    /**
     * res : 00000
     * message : 查询成功
     * currentTimes : 1.509791510238E9
     * data : [{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/rpck4dkorgq1188.jpg","id":2},{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/lxvjrw4wzzf1189.jpg","id":109},{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/ak5ugsh3fjt1191.jpg","id":111},{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/obbjzwf5wuq1193.jpg","id":112},{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/hvdgtqkraqs1192.jpg","id":113},{"title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/x5kpqyl5h5w1190.jpg","id":117}]
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
         * title_id : 2
         * img_url : http://img.meinvha.com/uploads/allimg/171030/rpck4dkorgq1188.jpg
         * id : 2
         */

        private int title_id;
        private String img_url;
        private int id;

        public int getTitle_id() {
            return title_id;
        }

        public void setTitle_id(int title_id) {
            this.title_id = title_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title_id=" + title_id +
                    ", img_url='" + img_url + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImgContent{" +
                "res='" + res + '\'' +
                ", message='" + message + '\'' +
                ", currentTimes=" + currentTimes +
                ", data=" + data +
                '}';
    }
}
