package com.example.android.historyquests;

public class Round {
    private int roundNum;
    private String question;
    private int countVariants;
    private String[] variants;
    private String[] questionVariants;
    private String sourceType;
    private String youtubeLink;
    private int imgResourceId;
    private String answer;
    private String questionType;
    private RoundInfo roundInfo;
    private String name;
    private AfterAnswer afterAnswer;
    private RouteInfo routeInfo;
    private boolean isQr;
    private boolean isRoute;
    private boolean addInfo;

    public Round(int roundNum, String name, RoundInfo roundInfo, String questionType, String question, int countVariants,
                 String[] variants, String[] questionVariants, String answer, String sourceType, String youtubeLink, int imgResourceId,
                 AfterAnswer afterAnswer, RouteInfo routeInfo, boolean isQr, boolean isRoute, boolean addInfo) {
        this.roundNum = roundNum;
        this.question = question;
        this.countVariants = countVariants;
        this.variants = variants;
        this.questionVariants = questionVariants;
        this.sourceType = sourceType;
        this.youtubeLink = youtubeLink;
        this.imgResourceId = imgResourceId;
        this.answer = answer;
        this.questionType = questionType;
        this.roundInfo = roundInfo;
        this.name = name;
        this.afterAnswer = afterAnswer;
        this.routeInfo = routeInfo;
        this.isQr = isQr;
        this.isRoute = isRoute;
        this.addInfo = addInfo;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public String getQuestion() {
        return question;
    }

    public int getCountVariants() {
        return countVariants;
    }

    public String[] getVariants() {
        return variants;
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
    public String getAnswer() {
        return answer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public RoundInfo getAdditionalInfo() {
        return roundInfo;
    }

    public String getName() {
        return name;
    }

    public String[] getQuestionVariants() {
        return questionVariants;
    }

    public AfterAnswer getAfterAnswer() {
        return afterAnswer;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public RoundInfo getRoundInfo() {
        return roundInfo;
    }

    public boolean isQr() {
        return isQr;
    }

    public boolean isRoute() {
        return isRoute;
    }

    public boolean isAddInfo() {
        return addInfo;
    }
}
