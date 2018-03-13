package com.ManHuan.manhuan.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class ManHuan_Name_List {


    /**
     * res : 00000
     * message : 查询成功
     * currentTimes : 1.509685623608E9
     * data : [{"book_cover_ailiyunImgPath":"manhua/校园类/我的青春恋爱喜剧果然有问题/我的青春恋爱喜剧果然有问题.jpg","manhun_book_name":"我的青春恋爱喜剧果然有问题","book_author":"佳月玲茅","manhun_book_list_id":1,"book_profile":"故事简介：我的青春恋爱喜剧果然有问题漫画 ，如果失败真能成为是青春的象征的话,可谓是充满青春也不足为奇吧....TV动画化决定!!备受瞩目的作品新连载开始!!!!我的青春恋爱喜剧果然有问题~\u2026","book_cover":"http://rs.sfacg.com/web/comic/images/Logo/201401/1f271a6b-1a2c-4b7d-b768-623830b98f51.jpg","id":1},{"book_cover_ailiyunImgPath":"manhua/校园类/学园奶爸/学园奶爸.jpg","manhun_book_name":"学园奶爸","book_author":"时计野哈里","manhun_book_list_id":1,"book_profile":"故事简介：学园奶爸漫画 ，因为空难事件失去双亲的龙一和年幼的弟弟虎太郎，两兄弟虽然被森之宫学园的理事长收养，但交换条件却是「要在学园的保母室当保母」！面对这些活力十足的小朋友们，龙一要怎麽办呢\u2026？超人气！学园「育婴」紧急行动。\u2026","book_cover":"http://rs.sfacg.com/web/comic/images/Logo/201209/b0144ebe-4f17-41f0-b563-8c341839b8fd.jpg","id":2},{"book_cover_ailiyunImgPath":"manhua/校园类/一弦定音/一弦定音.jpg","manhun_book_name":"一弦定音","book_author":"アミュー","manhun_book_list_id":1,"book_profile":"故事简介：一弦定音！漫画 ，是无可替代的容身之处\u2014\u2014愿将此音\u2014\u2014传达\u2014\u2014给你\u2014\u2014\u2026","book_cover":"http://rs.sfacg.com/web/comic/images/Logo/201304/475c5269-f09c-40ff-8748-312d6e6db9da.jpg","id":3},{"book_cover_ailiyunImgPath":"manhua/校园类/初瓣外传/初瓣外传.jpg","manhun_book_name":"初瓣外传","book_author":"桐原いづみ","manhun_book_list_id":1,"book_profile":"故事简介：初瓣外传沿续了初瓣的故事，讲述了主人公是15岁的熊鹰艺术学院新生麻井麦一个非常不善于和别人说话的女生，在充满个性的前辈的鼓励下麻井麦向着世界第一的女演员的目标而努力的故事。\u2026","book_cover":"http://mh.sfacg.com/Logo/201103/bdfae962-e3fc-49c9-b032-7b29fc856dcc.jpg","id":4},{"book_cover_ailiyunImgPath":"manhua/校园类/王样老师/王样老师.jpg","manhun_book_name":"王样老师","book_author":"椿泉","manhun_book_list_id":1,"book_profile":"故事简介：黒崎真冬曾经是称霸埼玉的不良少女。因为打架而被退学，之后转学到绿丘学园，她决定洗心革面，当个青春洋溢的高中生，要过闪亮的高中生活！却变成每天被儿时玩伴兼导师的鹰臣捉弄在鹰臣的计策下，真东与朋友早坂加入风纪部！第一项社团活动的主题，就是打倒老大！目标，令人向往的高中生活\u2026","book_cover":"http://rs.sfacg.com/web/comic/images/Logo/201103/27ccd9a2-44f8-4bc0-bdd5-f5effc91b753.jpg","id":5}]
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
         * book_cover_ailiyunImgPath : manhua/校园类/我的青春恋爱喜剧果然有问题/我的青春恋爱喜剧果然有问题.jpg
         * manhun_book_name : 我的青春恋爱喜剧果然有问题
         * book_author : 佳月玲茅
         * manhun_book_list_id : 1
         * book_profile : 故事简介：我的青春恋爱喜剧果然有问题漫画 ，如果失败真能成为是青春的象征的话,可谓是充满青春也不足为奇吧....TV动画化决定!!备受瞩目的作品新连载开始!!!!我的青春恋爱喜剧果然有问题~…
         * book_cover : http://rs.sfacg.com/web/comic/images/Logo/201401/1f271a6b-1a2c-4b7d-b768-623830b98f51.jpg
         * id : 1
         */

        private String book_cover_ailiyunImgPath;
        private String manhun_book_name;
        private String book_author;
        private int manhun_book_list_id;
        private String book_profile;
        private String book_cover;
        private int id;

        public String getBook_cover_ailiyunImgPath() {
            return book_cover_ailiyunImgPath;
        }

        public void setBook_cover_ailiyunImgPath(String book_cover_ailiyunImgPath) {
            this.book_cover_ailiyunImgPath = book_cover_ailiyunImgPath;
        }

        public String getManhun_book_name() {
            return manhun_book_name;
        }

        public void setManhun_book_name(String manhun_book_name) {
            this.manhun_book_name = manhun_book_name;
        }

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }

        public int getManhun_book_list_id() {
            return manhun_book_list_id;
        }

        public void setManhun_book_list_id(int manhun_book_list_id) {
            this.manhun_book_list_id = manhun_book_list_id;
        }

        public String getBook_profile() {
            return book_profile;
        }

        public void setBook_profile(String book_profile) {
            this.book_profile = book_profile;
        }

        public String getBook_cover() {
            return book_cover;
        }

        public void setBook_cover(String book_cover) {
            this.book_cover = book_cover;
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
                    "book_cover_ailiyunImgPath='" + book_cover_ailiyunImgPath + '\'' +
                    ", manhun_book_name='" + manhun_book_name + '\'' +
                    ", book_author='" + book_author + '\'' +
                    ", manhun_book_list_id=" + manhun_book_list_id +
                    ", book_profile='" + book_profile + '\'' +
                    ", book_cover='" + book_cover + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ManHuan_Name_List{" +
                "res='" + res + '\'' +
                ", message='" + message + '\'' +
                ", currentTimes=" + currentTimes +
                ", data=" + data +
                '}';
    }
}
