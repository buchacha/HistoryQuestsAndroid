package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RulesActivity extends AppCompatActivity {

    private Button btnGoToQuest;
    private QuestMetaData questMetaData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        btnGoToQuest = (Button) findViewById(R.id.button_procced_from_rules);


        btnGoToQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
                if (currentQuest == null) {
                    Intent goToQuest = new Intent(RulesActivity.this, RoundInfo.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                } else {
                    Round currentRound = currentQuest.getRounds()[0];
                    String additionalInfo = currentRound.getAdditionalInfo();
                    if (additionalInfo == null || additionalInfo == "") {
                        Intent goToQuest = new Intent(RulesActivity.this, RadioButtonActivity.class);
                        goToQuest.putExtra("META_DATA", questMetaData);
                        startActivity(goToQuest);
                    } else {
                        Intent goToQuest = new Intent(RulesActivity.this, RoundInfo.class);
                        goToQuest.putExtra("META_DATA", questMetaData);
                        startActivity(goToQuest);
                    }
                }
            }
        });
    }
}
