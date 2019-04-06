package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseQuestActivity extends AppCompatActivity {


    private Button btnUniversityQuest;
    private Button btnPalaceSquareQuest;
    private QuestMetaData questMetaData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_quest);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");// add key as string

        btnUniversityQuest = (Button) findViewById(R.id.button_spb_university);
        btnPalaceSquareQuest = (Button) findViewById(R.id.button_palace_square);

        btnUniversityQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questMetaData.questId = getString(R.string.id_university_quest);
                Intent goToPlayersIntent = new Intent(ChooseQuestActivity.this, PlayersActivity.class);
                goToPlayersIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToPlayersIntent);
            }
        });

        btnPalaceSquareQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questMetaData.questId = getString(R.string.id_palace_square_quest);
                Intent goToPlayersIntent = new Intent(ChooseQuestActivity.this, PlayersActivity.class);
                goToPlayersIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToPlayersIntent);
            }
        });

    }
}
