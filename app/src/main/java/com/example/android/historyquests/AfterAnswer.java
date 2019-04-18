package com.example.android.historyquests;

public class AfterAnswer {
    private String textIfRight;
    private String textIfWrong;
    private String sourceType;
    private String sourceLink;

    public AfterAnswer(String textIfRight, String textIfWrong, String sourceType, String sourceLink) {
        this.textIfRight = textIfRight;
        this.textIfWrong = textIfWrong;
        this.sourceType = sourceType;
        this.sourceLink = sourceLink;
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

    public String getSourceLink() {
        return sourceLink;
    }
}
