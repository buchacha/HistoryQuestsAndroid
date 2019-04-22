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

    private String LOG_TAG = RulesActivity.class.getSimpleName();
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
                    Intent goToQuest = new Intent(RulesActivity.this, RoundInfoActivity.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                    return;
                }
                int nextRoundIdx = questMetaData.lastRoundNum + 1;
                Round nextRound = currentQuest.getRounds()[nextRoundIdx];

                if (nextRound.isRoute()) {
                    Intent goRoute = new Intent(RulesActivity.this, WhileGoingQr.class);
                    goRoute.putExtra("META_DATA", questMetaData);
                    startActivity(goRoute);
                } else if (nextRound.isQr()) {
                    Intent goQr = new Intent(RulesActivity.this, QrReadActivity.class);
                    goQr.putExtra("META_DATA", questMetaData);
                    startActivity(goQr);
                }
                else if (nextRound.isAddInfo()){
                    Intent goToQuest = new Intent(RulesActivity.this, RoundInfoActivity.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                } else {
                    switch (nextRound.getQuestionType()) {
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
                        case TemporaryQuests.EDIT_TEXT_TASK_TYPE:
                            goNextRound = new Intent(getApplicationContext(), EditTextActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean isQr() {
        int nextRoundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round nextRound = currentQuest.getRounds()[nextRoundIdx];

        boolean res = nextRound.isQr();

        return res;
    }

    private boolean isRoute() {
        int nextRoundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round nextRound = currentQuest.getRounds()[nextRoundIdx];

        boolean res = nextRound.isRoute();

        return res;
    }
}
