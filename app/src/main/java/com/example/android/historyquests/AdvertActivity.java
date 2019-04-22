package com.example.android.historyquests;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;

public class AdvertActivity extends YouTubeBaseActivity

{

    private String LOG_TAG = AdvertActivity.class.getSimpleName();
    private TextView info;
    private Button btnDonate;
    private TextView goNextTextView;
    private TextView title;

    private QuestMetaData questMetaData;
    private Round currentRound;

    private boolean iWasHere;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        iWasHere = false;
        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        info = findViewById(R.id.advert_info);
        title = findViewById(R.id.advert_title);
        btnDonate = findViewById(R.id.button_donate);
        goNextTextView = findViewById(R.id.go_next);

        int currentRoundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        currentRound = currentQuest.getRounds()[currentRoundIdx];

        title.setText(currentRound.getAd().getTitle());
        info.setText(currentRound.getAd().getInfo());

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentRound.getAd().getWebsiteLink()));
                startActivity(browserIntent);
            }
        });



        goNextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNextRound();

            }
        });

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        if (iWasHere)
            questMetaData.lastRoundNum--;

    }

    private void goNextRound() {

        iWasHere = true;
        int nextRoundIdx = questMetaData.lastRoundNum + 2;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);

        if (nextRoundIdx == currentQuest.getCountRounds()) {
            Intent intent = new Intent(getApplicationContext(), WinActivity.class);
            startActivity(intent);
            return;
        }
        if (currentQuest == null) {
            Intent goNextRound = new Intent(AdvertActivity.this, RoundInfoActivity.class);
            goNextRound.putExtra("META_DATA", questMetaData);
            startActivity(goNextRound);
        } else {
            Round nextRound = currentQuest.getRounds()[nextRoundIdx];
            questMetaData.lastRoundNum++;

            if (nextRound.isRoute()) {
                Intent goRoute = new Intent(AdvertActivity.this, WhileGoingQr.class);
                goRoute.putExtra("META_DATA", questMetaData);
                startActivity(goRoute);
            } else if (nextRound.isQr()) {
                Intent goQr = new Intent(AdvertActivity.this, QrReadActivity.class);
                goQr.putExtra("META_DATA", questMetaData);
                startActivity(goQr);
            }
            else if (nextRound.isAddInfo()){
                Intent goToQuest = new Intent(AdvertActivity.this, RoundInfoActivity.class);
                goToQuest.putExtra("META_DATA", questMetaData);
                startActivity(goToQuest);
            } else {
                switch (nextRound.getQuestionType()) {
                    case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                        Intent goNextRound = new Intent(AdvertActivity.this, RadioButtonActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                        goNextRound = new Intent(AdvertActivity.this, CheckBoxActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                        goNextRound = new Intent(AdvertActivity.this, LinkVariantsActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case TemporaryQuests.EMPTY_TASK_TYPE:
                        goNextRound = new Intent(getApplicationContext(), AdvertActivity.class);
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

    }


}
