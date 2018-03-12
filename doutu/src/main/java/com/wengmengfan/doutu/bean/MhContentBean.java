package com.wengmengfan.doutu.bean;

/**
 * sayid ....
 * Created by wengmf on 2018/2/23.
 */

public class MhContentBean {

    private  String imgSrc;
    private   String dataSrc;
    private  String dataImageId;
    private  String Type;


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(String dataSrc) {
        this.dataSrc = dataSrc;
    }

    public String getDataImageId() {
        return dataImageId;
    }

    public void setDataImageId(String dataImageId) {
        this.dataImageId = dataImageId;
    }
}
