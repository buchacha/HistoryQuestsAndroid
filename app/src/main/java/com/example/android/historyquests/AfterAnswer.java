package com.example.android.historyquests;

public class AfterAnswer {
    private String textIfRight;
    private String textIfWrong;
    private String sourceType;
    private String youtubeLink;
    private int imgResourceId;

    public AfterAnswer(String textIfRight, String textIfWrong, String sourceType, String youtubeLink, int imgResourceId) {
        this.textIfRight = textIfRight;
        this.textIfWrong = textIfWrong;
        this.sourceType = sourceType;
        this.youtubeLink = youtubeLink;
        this.imgResourceId = imgResourceId;
    }

    public String getTextIfRight() {
        return textIfRight;
    }

    public String getTextIfWrong() {
        return textIfWrong;
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
