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
                    return;
                }
                Round currentRound = currentQuest.getRounds()[0];
                String additionalInfo = currentRound.getAdditionalInfo();

                if (additionalInfo == null || additionalInfo == "") {
                    switch (currentRound.getQuestionType()) {
                        case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                            Intent goNextRound = new Intent(RulesActivity.this, RadioButtonActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                            goNextRound = new Intent(RulesActivity.this, CheckBoxActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                            goNextRound = new Intent(RulesActivity.this, LinkVariantsActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent goToQuest = new Intent(RulesActivity.this, RoundInfo.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                }
            }
        });
    }
}
