package com.example.android.historyquests;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestMetaData implements Serializable {

    public String questId;
    public int lastRoundNum;
    public boolean lastRoundRight;
    public int level;
    public ArrayList<String> players;

    public QuestMetaData(String questId, int lastRoundNum, boolean lastRoundRight, int level, ArrayList<String> players) {
        this.questId = questId;
        this.lastRoundNum = lastRoundNum;
        this.lastRoundRight = lastRoundRight;
        this.level = level;
        this.players = players;
    }
}
