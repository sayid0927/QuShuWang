package com.wengmengfan.btwang.bean;

/**
 * sayid ....
 * Created by wengmf on 2018/3/21.
 */

public class VideoDetailsBean {
//    类型：
//    地区：
//    语言：
//    片长：
//    上映日期：
//    资源更新：
//    豆瓣评分：
//    预告片：
//    导演：

    String Type;
    String area;
    String language;
    String length;
    String releaseDate;
    String resourceUpdate;
    String watercressScore;
    String trailer;
    String director;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getResourceUpdate() {
        return resourceUpdate;
    }

    public void setResourceUpdate(String resourceUpdate) {
        this.resourceUpdate = resourceUpdate;
    }

    public String getWatercressScore() {
        return watercressScore;
    }

    public void setWatercressScore(String watercressScore) {
        this.watercressScore = watercressScore;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
