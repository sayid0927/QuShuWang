package com.wengmengfan.doutu.bean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/2/11.
 */

public class BookInfoBean {


    private  String bookName;
    private  String zuoZhe;
    private  String biaoQian;
    private String renqi;
    private String imgUrl;

    private List<BookChapterBean> bookChapterBeanList;


    public List<BookChapterBean> getBookChapterBeanList() {
        return bookChapterBeanList;
    }

    public void setBookChapterBeanList(List<BookChapterBean> bookChapterBeanList) {
        this.bookChapterBeanList = bookChapterBeanList;
    }

    public  static  class  BookChapterBean{
        private  String Url ;
        private  String num;

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRenqi() {
        return renqi;
    }

    public void setRenqi(String renqi) {
        this.renqi = renqi;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getZuoZhe() {
        return zuoZhe;
    }

    public void setZuoZhe(String zuoZhe) {
        this.zuoZhe = zuoZhe;
    }

    public String getBiaoQian() {
        return biaoQian;
    }

    public void setBiaoQian(String biaoQian) {
        this.biaoQian = biaoQian;
    }
}
