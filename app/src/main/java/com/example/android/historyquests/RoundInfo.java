package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RoundInfo extends AppCompatActivity {

    private QuestMetaData questMetaData;
    private TextView title;
    private TextView description;
    private Quest quest;
    private int currentRoundIdx;
    private TextView goToTaskTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        title = (TextView) findViewById(R.id.round_info_title);
        description = (TextView) findViewById(R.id.round_info_description);
        goToTaskTextView = (TextView) findViewById(R.id.go_to_task);

        quest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        if (quest == null) {
            LinearLayout fullInfoView = (LinearLayout) findViewById(R.id.info_quest_activity);
            fullInfoView.setVisibility(View.GONE);
            TextView emptyView = (TextView) findViewById(R.id.empty_view_quest_info);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            currentRoundIdx = questMetaData.lastRoundNum + 1;
            Round currentRound = quest.getRounds()[currentRoundIdx];
            title.setText("Станция " + Integer.toString(currentRoundIdx+1) + ". " + currentRound.getName());
            description.setText(currentRound.getAdditionalInfo());
        }

        goToTaskTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
                int nextRoundIdx = questMetaData.lastRoundNum + 1;
                Round nextRound = currentQuest.getRounds()[nextRoundIdx];
                Intent goNextRound;
                switch (nextRound.getQuestionType()) {
                    case "0":
                        goNextRound = new Intent(RoundInfo.this, RadioButtonActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case "3":
                        goNextRound = new Intent(RoundInfo.this, LinkVariantsActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
