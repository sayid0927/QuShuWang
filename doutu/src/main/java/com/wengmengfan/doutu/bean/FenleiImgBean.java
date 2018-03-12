package com.wengmengfan.doutu.bean;

/**
 * sayid ....
 * Created by wengmf on 2018/2/9.
 */

public class FenleiImgBean  {

    private  String url;
    private  String imgUrl;
    private  String bookName;
    private  String bookNum;

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
