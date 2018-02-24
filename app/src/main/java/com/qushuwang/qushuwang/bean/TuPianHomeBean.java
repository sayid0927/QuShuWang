package com.qushuwang.qushuwang.bean;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class TuPianHomeBean {

    private  String Url ;
    private  String Title;
    private String ImgUrl;

    private int id;

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
