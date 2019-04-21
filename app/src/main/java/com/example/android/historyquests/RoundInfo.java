package com.example.android.historyquests;

public class RoundInfo {

    private String title;
    private String info;
    private String sourceType;
    private String youtubeLink;
    private int imgResourceId;

    public RoundInfo(String title, String info, String sourceType, String youtubeLink, int imgResourceId) {
        this.title = title;
        this.info = info;
        this.sourceType = sourceType;
        this.youtubeLink = youtubeLink;
        this.imgResourceId = imgResourceId;
    }

    public String getTitle() {
        return title;
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
