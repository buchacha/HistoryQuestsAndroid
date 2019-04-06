package com.example.android.historyquests;

public class Round {
    private int roundNum;
    private String question;
    private int countVariants;
    private String[] variants;
    private String[] questionVariants;
    private int sourceType;
    private String sourceLink;
    private int answer;
    private String questionType;
    private String additionalInfo;
    private String name;
    private AfterAnswer afterAnswer;

    public Round(int roundNum, String name, String additionalInfo, String questionType, String question, int countVariants,
                 String[] variants, String[] questionVariants, int answer, int sourceType, String sourceLink,
                 AfterAnswer afterAnswer) {
        this.roundNum = roundNum;
        this.question = question;
        this.countVariants = countVariants;
        this.variants = variants;
        this.questionVariants = questionVariants;
        this.sourceType = sourceType;
        this.sourceLink = sourceLink;
        this.answer = answer;
        this.questionType = questionType;
        this.additionalInfo = additionalInfo;
        this.name = name;
        this.afterAnswer = afterAnswer;
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

    public int getSourceType() {
        return sourceType;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public int getAnswer() {
        return answer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
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
}
