package com.example.android.historyquests;

public class RouteInfo {

    private String info;
    private String sourceType;
    private String youtubeLink;
    private int imgResourceId;

    public RouteInfo(String info, String sourceType, String youtubeLink, int imgResourceId) {
        this.info = info;
        this.sourceType = sourceType;
        this.youtubeLink = youtubeLink;
        this.imgResourceId = imgResourceId;
    }

    public String getInfo() {
        return info;
    }

    public String getSourceType() {
        return sourceType;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }
}
