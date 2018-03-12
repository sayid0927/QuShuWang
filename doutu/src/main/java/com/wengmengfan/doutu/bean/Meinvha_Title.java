package com.wengmengfan.doutu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class Meinvha_Title {


    /**
     * res : 00000
     * message : 查询成功
     * currentTimes : 1.509781898332E9
     * data : [{"dir_id":1,"title":"【推女郎】大眼美女妹妹闺房半裸真空性感写真","title_id":1,"img_url":"http://img.meinvha.com/uploads/allimg/171030/hv4fgvc5mkq1182.jpg","id":1},{"dir_id":1,"title":"【丝魅VIP】巨乳女神陈思琪私房粉色情趣诱惑写真","title_id":2,"img_url":"http://img.meinvha.com/uploads/allimg/171030/lxvjrw4wzzf1189.jpg","id":2},{"dir_id":1,"title":"【模范学院】粉红唯美女郎艾比利私房比基尼诱惑写真","title_id":3,"img_url":"http://img.meinvha.com/uploads/allimg/171030/z0ot1ybbaoa1175.jpg","id":3},{"dir_id":1,"title":"【4K-STAR】妩媚女神可可COCO性感翘臀私房情趣内衣写真","title_id":4,"img_url":"http://img.meinvha.com/uploads/allimg/171031/ukorynomven2077.jpg","id":4},{"dir_id":1,"title":"【AISS爱丝】美臀女神猩一娇艳惊人私房情趣皮衣写真","title_id":5,"img_url":"http://img.meinvha.com/uploads/allimg/171031/puaptzdjn3b2082.jpg","id":5},{"dir_id":1,"title":"【丝雅写真】性感美女顽皮猫baby私房半裸撩人诱惑写真","title_id":6,"img_url":"http://img.meinvha.com/uploads/allimg/171030/ic4ygwadkpx1168.jpg","id":6},{"dir_id":1,"title":"【TBA高清套图】推女郎性感女神陈子睿私房大胸诱惑妖娆写真","title_id":7,"img_url":"http://img.meinvha.com/uploads/allimg/171031/lh4lmqx040w2090.jpg","id":7},{"dir_id":1,"title":"【Tyingart】韩国风俗媚娘清纯甜美性感写真集","title_id":8,"img_url":"http://img.meinvha.com/uploads/allimg/171101/xebcoj4poxs2093.jpg","id":8},{"dir_id":1,"title":"【4K-STAR】瘦瘦的性感锁骨大胸时尚美女阳光靓丽清纯动人","title_id":9,"img_url":"http://img.meinvha.com/uploads/allimg/171101/p2snrgl3jt42104.jpg","id":9},{"dir_id":1,"title":"【尤物馆】韩国娇艳风骚高跟鞋美女露乳沟性感写真","title_id":10,"img_url":"http://img.meinvha.com/uploads/allimg/171101/r13sigdtim42119.jpg","id":10}]
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
         * dir_id : 1
         * title : 【推女郎】大眼美女妹妹闺房半裸真空性感写真
         * title_id : 1
         * img_url : http://img.meinvha.com/uploads/allimg/171030/hv4fgvc5mkq1182.jpg
         * id : 1
         */

        private int dir_id;
        private String title;
        private int title_id;
        private String img_url;
        private int id;

        public int getDir_id() {
            return dir_id;
        }

        public void setDir_id(int dir_id) {
            this.dir_id = dir_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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
                    "dir_id=" + dir_id +
                    ", title='" + title + '\'' +
                    ", title_id=" + title_id +
                    ", img_url='" + img_url + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Meinvha_Dir{" +
                "res='" + res + '\'' +
                ", message='" + message + '\'' +
                ", currentTimes=" + currentTimes +
                ", data=" + data +
                '}';
    }
}
