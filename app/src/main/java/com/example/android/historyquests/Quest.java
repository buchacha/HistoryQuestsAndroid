package com.example.android.historyquests;

public class Quest {

    private String id;
    private String name;
    private String date;
    private int countRounds;
    private Round[] rounds;


    public Quest(String id, String name, String date, int countRounds, Round[] rounds) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.countRounds = countRounds;
        this.rounds = rounds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getCountRounds() {
        return countRounds;
    }

    public Round[] getRounds() {
        return rounds;
    }
}


