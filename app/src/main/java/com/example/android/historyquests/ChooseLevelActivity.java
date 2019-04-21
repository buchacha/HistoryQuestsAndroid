package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseLevelActivity extends AppCompatActivity {

    private Button btnNew;
    private Button btnBeginner;
    private Button btnHistorian;
    private QuestMetaData questMetaData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level);

        btnBeginner =(Button) findViewById(R.id.button_beginner);
        btnNew =(Button) findViewById(R.id.button_new_player);
        btnHistorian =(Button) findViewById(R.id.button_historian);

        btnNew.setOnClickListener(new View.OnClickListener() {  // TODO: add level as string-int map
            @Override
            public void onClick(View v) {

                questMetaData = new QuestMetaData("0", -1,false, 0, null);
                questMetaData.level = 1;

                Intent goToChooseQuestIntent = new Intent(ChooseLevelActivity.this, ChooseQuestActivity.class);
                goToChooseQuestIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToChooseQuestIntent);

            }
        });

        btnBeginner.setOnClickListener(new View.OnClickListener() {  // TODO: add level as string-int map
            @Override
            public void onClick(View v) {

                questMetaData = new QuestMetaData("0", -1,false, 0, null);
                questMetaData.level = 2;

                Intent goToChooseQuestIntent = new Intent(ChooseLevelActivity.this, ChooseQuestActivity.class);
                goToChooseQuestIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToChooseQuestIntent);

            }
        });

        btnHistorian.setOnClickListener(new View.OnClickListener() { // TODO: add level as string-int map
            @Override
            public void onClick(View v) {

                questMetaData = new QuestMetaData("0", -1,false, 0, null);
                questMetaData.level = 3;

                Intent goToChooseQuestIntent = new Intent(ChooseLevelActivity.this, ChooseQuestActivity.class);
                goToChooseQuestIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToChooseQuestIntent);

            }
        });

    }
}
