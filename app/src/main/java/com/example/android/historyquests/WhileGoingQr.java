package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class WhileGoingQr extends YouTubeBaseActivity {


    private ImageView imageView;
    private TextView info;

    private YouTubePlayerView youTubePlayerView;
    private Button btnPlay;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private String LOG_TAG = WhileGoingQr.class.getSimpleName();
    private QuestMetaData questMetaData;

    private Button endRouteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.while_going_to_qr);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        initViews();

        setViews();

        endRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();

            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                int curRoundIdx = questMetaData.lastRoundNum + 1;
                Quest quest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
                Round curRound = quest.getRounds()[curRoundIdx];
                if (curRound.getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
                    String currentUrl = curRound.getRouteInfo().getYoutubeLink();
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

    private void goNext() {

            int currentRoundIdx = questMetaData.lastRoundNum + 1;
            Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);

            if (currentRoundIdx == currentQuest.getCountRounds()) {
                Intent intent = new Intent(getApplicationContext(), WinActivity.class);
                startActivity(intent);
                return;
            }
            if (currentQuest == null) {
                Intent goNext = new Intent(WhileGoingQr.this, RoundInfoActivity.class);
                goNext.putExtra("META_DATA", questMetaData);
                startActivity(goNext);
            } else {
                Round currentRound = currentQuest.getRounds()[currentRoundIdx];

                if (currentRound.isQr()) {
                    Intent goQr = new Intent(WhileGoingQr.this, QrReadActivity.class);
                    goQr.putExtra("META_DATA", questMetaData);
                    startActivity(goQr);
                }
                else if (currentRound.isAddInfo()){
                    Intent goToQuest = new Intent(WhileGoingQr.this, RoundInfoActivity.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                } else {
                    switch (currentRound.getQuestionType()) {
                        case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                            Intent goNext = new Intent(WhileGoingQr.this, RadioButtonActivity.class);
                            goNext.putExtra("META_DATA", questMetaData);
                            startActivity(goNext);
                            break;
                        case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                            goNext = new Intent(WhileGoingQr.this, CheckBoxActivity.class);
                            goNext.putExtra("META_DATA", questMetaData);
                            startActivity(goNext);
                            break;
                        case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                            goNext = new Intent(WhileGoingQr.this, LinkVariantsActivity.class);
                            goNext.putExtra("META_DATA", questMetaData);
                            startActivity(goNext);
                            break;
                        case TemporaryQuests.EMPTY_TASK_TYPE:
                            goNext = new Intent(getApplicationContext(), AdvertActivity.class);
                            goNext.putExtra("META_DATA", questMetaData);
                            startActivity(goNext);
                            break;
                        case TemporaryQuests.EDIT_TEXT_TASK_TYPE:
                            Log.e(LOG_TAG, "HELLO FROM HERE 999");
                            goNext = new Intent(WhileGoingQr.this, EditTextActivity.class);
                            goNext.putExtra("META_DATA", questMetaData);
                            startActivity(goNext);
                            break;

                        default:
                            Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                    }
                }
            }


    }

    private void initViews() {
        info = findViewById(R.id.routeInfo);
        endRouteBtn = (Button)findViewById(R.id.btn_end_route);
        imageView = findViewById(R.id.imgView);
        youTubePlayerView = findViewById(R.id.youtubeView);
        btnPlay = findViewById(R.id.btnPlay);
    }

    private void setViews() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];

        info.setText(currentRound.getRouteInfo().getInfo());

        imageViewSet();
        videoViewSet();

    }

    private void imageViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (currentRound.getRouteInfo().getSourceType().equals(TemporaryQuests.IMAGE_TYPE)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentRound.getRouteInfo().getImgResourceId());

        }

    }

    private void videoViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (currentRound.getRouteInfo().getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
            youTubePlayerView.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.VISIBLE);

        }
    }

}
