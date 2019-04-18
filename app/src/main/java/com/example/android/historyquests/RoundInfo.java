package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

public class RoundInfo extends YouTubeBaseActivity {

    private YouTubePlayerView youTubePlayerView;
    private Button btnPlay;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private QuestMetaData questMetaData;
    private TextView title;
    private TextView description;
    private Quest quest;

    private TextView goToTaskTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_info);

        extractIntentData();
        initViews();
        quest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        setViews();

        goToTaskTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curRoundIdx = questMetaData.lastRoundNum + 1;
                Round curRound = quest.getRounds()[curRoundIdx];
                switchNextRound(curRound.getQuestionType());
            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                int curRoundIdx = questMetaData.lastRoundNum + 1;
                Round curRound = quest.getRounds()[curRoundIdx];
                String currentUrl = curRound.getSourceLink();
                if (curRound.getSourceType().equals(TemporaryQuests.VIDEO_TYPE))
                    youTubePlayer.loadVideo(currentUrl);
                else if (curRound.getSourceType().equals(TemporaryQuests.IMAGE_TYPE))
                    Toast.makeText(getApplicationContext(), "Здесь должна высветиться картинка", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "В этом раунде нет картинки или видео для показа", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(YouTubeConf.getApiKey(), onInitializedListener);
            }
        });



    }

    private void switchNextRound(String questionType) {
        Intent goNextRound;
        switch (questionType) {
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
    private void extractIntentData() {
        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");
    }
    private void initViews() {
        title = (TextView) findViewById(R.id.round_info_title);
        description = (TextView) findViewById(R.id.round_info_description);
        goToTaskTextView = (TextView) findViewById(R.id.go_to_task);
        btnPlay = findViewById(R.id.btnPlay);
        youTubePlayerView = findViewById(R.id.youtubePlayView);

    }
    private void setViews() {
        if (quest == null) {
            LinearLayout fullInfoView = (LinearLayout) findViewById(R.id.info_quest_activity);
            fullInfoView.setVisibility(View.GONE);
            TextView emptyView = (TextView) findViewById(R.id.empty_view_quest_info);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            int currentRoundIdx = questMetaData.lastRoundNum + 1;
            Round currentRound = quest.getRounds()[currentRoundIdx];
            title.setText("Станция " + Integer.toString(currentRoundIdx+1) + ". " + currentRound.getName());
            description.setText(currentRound.getAdditionalInfo());
        }
    }
}
