package com.example.android.historyquests;

public class Advert {

    private  String title;
    private  String info;
    private String resourceType;
    private String youtubeLink;
    private int imgResourceId;
    private String websiteLink;
    private boolean isDonateble;

    public Advert(String title, String info, String resourceType, String youtubeLink, int imgResourceId, String websiteLink, boolean isDonateble) {
        this.title = title;
        this.info = info;
        this.resourceType = resourceType;
        this.youtubeLink = youtubeLink;
        this.imgResourceId = imgResourceId;
        this.websiteLink = websiteLink;
        this.isDonateble = isDonateble;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public boolean isDonateble() {
        return isDonateble;
    }
}
