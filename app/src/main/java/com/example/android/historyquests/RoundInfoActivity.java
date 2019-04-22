package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class RoundInfoActivity extends YouTubeBaseActivity {

    private String LOG_TAG = RoundInfoActivity.class.getSimpleName();
    private ImageView imageView;

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
                Intent goNextRound;
                Log.e(LOG_TAG, "HELLO FROM HERE 66");
                switchNextRound(curRound.getQuestionType());
            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                int curRoundIdx = questMetaData.lastRoundNum + 1;
                Quest quest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
                Round curRound = quest.getRounds()[curRoundIdx];
                if (curRound.getRoundInfo().getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
                    String currentUrl = curRound.getRoundInfo().getYoutubeLink();
                    youTubePlayer.loadVideo(currentUrl);
                }

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
            case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                goNextRound = new Intent(RoundInfoActivity.this, RadioButtonActivity.class);
                goNextRound.putExtra("META_DATA", questMetaData);
                startActivity(goNextRound);
                break;
            case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                goNextRound = new Intent(RoundInfoActivity.this, CheckBoxActivity.class);
                goNextRound.putExtra("META_DATA", questMetaData);
                startActivity(goNextRound);
                break;
            case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                goNextRound = new Intent(RoundInfoActivity.this, LinkVariantsActivity.class);
                goNextRound.putExtra("META_DATA", questMetaData);
                startActivity(goNextRound);
                break;
            case TemporaryQuests.EMPTY_TASK_TYPE:
                goNextRound = new Intent(RoundInfoActivity.this, AdvertActivity.class);
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
    private void extractIntentData() {
        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");
    }
    private void initViews() {
        title = (TextView) findViewById(R.id.round_info_title);
        description = (TextView) findViewById(R.id.round_info_description);
        goToTaskTextView = (TextView) findViewById(R.id.go_to_task);
        btnPlay = findViewById(R.id.btnPlay);
        youTubePlayerView = findViewById(R.id.youtubeView);

        imageView = findViewById(R.id.imgView);
        youTubePlayerView = findViewById(R.id.youtubeView);
        btnPlay = findViewById(R.id.btnPlay);

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
            title.setText(currentRound.getName());
            description.setText(currentRound.getRoundInfo().getInfo());
            imageViewSet();
            videoViewSet();
        }



    }

    private void imageViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (currentRound.getRoundInfo().getSourceType().equals(TemporaryQuests.IMAGE_TYPE)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentRound.getRoundInfo().getImgResourceId());
        }

    }

    private void videoViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (currentRound.getRoundInfo().getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
            youTubePlayerView.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.VISIBLE);

        }
    }
}
