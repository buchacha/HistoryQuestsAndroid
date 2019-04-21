package com.example.android.historyquests;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class CheckBoxActivity extends YouTubeBaseActivity {

    private QuestMetaData questMetaData;
    private Round currentRound;

    private LinearLayout taskLayout;
    private TextView title;
    private TextView question;
    private LinearLayout checkGroup;
    private ImageView imageView;
    private ImageView imageViewAfter;

    private YouTubePlayerView youTubePlayerView;
    private Button btnPlay;
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    private YouTubePlayerView youTubePlayerViewAfter;
    private Button btnPlayAfter;
    private YouTubePlayer.OnInitializedListener onInitializedListenerAfter;

    private TextView checkAnswer;
    private TextView getHint;

    private TextView darkerView;
    private LinearLayout afterTaskLayout;
    private ScrollView afterTaskScrollView;
    private TextView afterTaskText;
    private TextView afterTaskGoMain;
    private TextView afterTaskGoNext;
    private TextView afterTaskRepeat;

    private CheckBox answer1;
    private CheckBox answer2;
    private CheckBox answer3;
    private CheckBox answer4;
    private CheckBox answer5;
    private CheckBox answer6;

    private int[] answerIds = new int[6];

    private boolean iWasHere;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_check_box);

        iWasHere = false;
        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        initViews();
        setCurrentRound();
        setViews();



        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentAnswer = retrieveAnswer();
                String rightAnswer = currentRound.getAnswer();

                if (currentAnswer.equals("")) {
                    Toast.makeText(getApplicationContext(), "Введите Ваш ответ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (currentAnswer.equals(rightAnswer)) {
                    showAfterTask(true);
                } else {
                    showAfterTask(false);
                }

            }
        });

        afterTaskRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        afterTaskGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = NavUtils.getParentActivityIntent(CheckBoxActivity.this);
                startActivity(i);
            }
        });

        afterTaskGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNextRound();
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
                    String currentUrl = curRound.getYoutubeLink();
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

        onInitializedListenerAfter = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                int curRoundIdx = questMetaData.lastRoundNum + 1;
                Quest quest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
                Round curRound = quest.getRounds()[curRoundIdx];
                if (curRound.getAfterAnswer().getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
                    String currentUrl = curRound.getAfterAnswer().getYoutubeLink();
                    youTubePlayer.loadVideo(currentUrl);
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlayAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerViewAfter.initialize(YouTubeConf.getApiKey(), onInitializedListenerAfter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void goNextRound() {

        iWasHere = true;
        int nextRoundIdx = questMetaData.lastRoundNum + 2;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round nextRound = currentQuest.getRounds()[nextRoundIdx];

        if (nextRoundIdx == currentQuest.getCountRounds()) {
            Toast.makeText(getApplicationContext(), "Вы завершили квест полностью. С победой!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return;
        }
        if (currentQuest == null) {
            Intent goNextRound = new Intent(CheckBoxActivity.this, RoundInfoActivity.class);
            goNextRound.putExtra("META_DATA", questMetaData);
            startActivity(goNextRound);
        } else {

            questMetaData.lastRoundNum++;

            if (nextRound.isRoute()) {
                Intent goRoute = new Intent(CheckBoxActivity.this, WhileGoingQr.class);
                goRoute.putExtra("META_DATA", questMetaData);
                startActivity(goRoute);
            } else if (nextRound.isQr()) {
                Intent goQr = new Intent(CheckBoxActivity.this, QrReadActivity.class);
                goQr.putExtra("META_DATA", questMetaData);
                startActivity(goQr);
            }
            else if (nextRound.isAddInfo()){
                Intent goToQuest = new Intent(CheckBoxActivity.this, RoundInfoActivity.class);
                goToQuest.putExtra("META_DATA", questMetaData);
                startActivity(goToQuest);
            } else {
                switch (nextRound.getQuestionType()) {
                    case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                        Intent goNextRound = new Intent(CheckBoxActivity.this, RadioButtonActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                        goNextRound = new Intent(CheckBoxActivity.this, CheckBoxActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                        goNextRound = new Intent(CheckBoxActivity.this, LinkVariantsActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        if (iWasHere)
            questMetaData.lastRoundNum--;
        setClickable(taskLayout);
        darkerView.setVisibility(View.GONE);
        afterTaskScrollView.setVisibility(View.GONE);

    }

    private void setUnClickable(View view) {
        if (view != null) {
            view.setClickable(false);
            if (view instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup) view);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setUnClickable(vg.getChildAt(i));
                }
            }
        }
    }
    private void setClickable(View view) {
        if (view != null) {
            view.setClickable(true);
            if (view instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup) view);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setClickable(vg.getChildAt(i));
                }
            }
        }
    }

    private void showAfterTask(boolean isRight) {
        setUnClickable(taskLayout);
        darkerView.setVisibility(View.VISIBLE);
        afterTaskScrollView.setVisibility(View.VISIBLE);
        if (isRight)
            setAfterTaskRight();
        else
            setAfterTaskWrong();
    }

    private void setAfterTaskRight() {
        afterTaskText.setText(currentRound.getAfterAnswer().getTextIfRight());
        imageViewAfterSet();
        videoViewAfterSet();

    }

    private void setAfterTaskWrong(){
        afterTaskGoNext.setVisibility(View.GONE);
        afterTaskRepeat.setVisibility(View.VISIBLE);
        afterTaskText.setText(currentRound.getAfterAnswer().getTextIfWrong());


    }
    private void setViews() {
        int roundIdx = questMetaData.lastRoundNum+1;
        title.setText("Станция " + Integer.toString(roundIdx+1) + ". " + currentRound.getName());
        question.setText(currentRound.getQuestion());
        initCheckGroup();
        setupCheckGroup();
        imageViewSet();
        videoViewSet();
    }
    private void setupCheckGroup() {
        String[] variants = currentRound.getVariants();

        for (int i = 0; i < currentRound.getCountVariants(); i++) {
            CheckBox chb = findViewById(answerIds[i]);
            chb.setText(variants[i]);
            chb.setPadding(11, 11, 11, 11);
        }
        for (int i = currentRound.getCountVariants(); i < 6; i++) {
            CheckBox chb = findViewById(answerIds[i]);
            chb.setVisibility(View.GONE);
        }
    }
    private void initCheckGroup() {
        answer1 = findViewById(R.id.answer1);
        answerIds[0] = answer1.getId();
        answer2 = findViewById(R.id.answer2);
        answerIds[1] = answer2.getId();
        answer3 = findViewById(R.id.answer3);
        answerIds[2] = answer3.getId();
        answer4 = findViewById(R.id.answer4);
        answerIds[3] = answer4.getId();
        answer5 = findViewById(R.id.answer5);
        answerIds[4] = answer5.getId();
        answer6 = findViewById(R.id.answer6);
        answerIds[5] = answer6.getId();
    }
    private String retrieveAnswer() {
        String res = "";
        int numOfAnswers = currentRound.getCountVariants();
        for (int i = 0; i < numOfAnswers; i++) {
            CheckBox chb = findViewById(answerIds[i]);
            if (chb.isChecked()) {
                res += Integer.toString(i+1);
            }
        }

        return res;
    }

    private void setCurrentRound() {
        int roundIdx = questMetaData.lastRoundNum+1;
        currentRound = TemporaryQuests.questsHashMap.get(questMetaData.questId).getRounds()[roundIdx];
    }

    private void initViews() {
        taskLayout = findViewById(R.id.task_layout);
        title = findViewById(R.id.task_title);
        question = findViewById(R.id.task_question);
        checkGroup = findViewById(R.id.checkGroup);
        checkAnswer = findViewById(R.id.check_answer);
        getHint = findViewById(R.id.get_hint);

        darkerView = findViewById(R.id.darker_view);
        afterTaskLayout = findViewById(R.id.after_task_layout);
        afterTaskScrollView = findViewById(R.id.after_task_scroll_view);
        afterTaskText = findViewById(R.id.after_task_text);
        afterTaskGoMain = findViewById(R.id.after_task_go_main);
        afterTaskGoNext = findViewById(R.id.after_task_go_next);
        afterTaskRepeat = findViewById(R.id.after_task_repeat);

        imageView = findViewById(R.id.imgView);
        youTubePlayerView = findViewById(R.id.youtubeView);
        btnPlay = findViewById(R.id.btnPlay);

        imageViewAfter = findViewById(R.id.imgViewAfter);
        youTubePlayerViewAfter = findViewById(R.id.youtubeViewAfter);
        btnPlayAfter = findViewById(R.id.btnPlayAfter);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Вернуться назад без сохранения?");
        builder.setPositiveButton("Да", discardButtonClickListener);
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void imageViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (!currentRound.getSourceType().equals(TemporaryQuests.EMPTY_TASK_TYPE) && currentRound.getSourceType().equals(TemporaryQuests.IMAGE_TYPE)) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentRound.getImgResourceId());

        }

    }

    private void videoViewSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (!currentRound.getSourceType().equals(TemporaryQuests.EMPTY_TASK_TYPE) && currentRound.getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
            youTubePlayerView.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.VISIBLE);

        }
    }

    private void imageViewAfterSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (!currentRound.getAfterAnswer().getSourceType().equals(TemporaryQuests.EMPTY_TASK_TYPE) && currentRound.getAfterAnswer().getSourceType().equals(TemporaryQuests.IMAGE_TYPE)) {
            imageViewAfter.setVisibility(View.VISIBLE);
            imageViewAfter.setImageResource(currentRound.getImgResourceId());

        }

    }

    private void videoViewAfterSet() {
        int roundIdx = questMetaData.lastRoundNum + 1;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);
        Round currentRound = currentQuest.getRounds()[roundIdx];
        if (!currentRound.getAfterAnswer().getSourceType().equals(TemporaryQuests.EMPTY_TASK_TYPE) && currentRound.getAfterAnswer().getSourceType().equals(TemporaryQuests.VIDEO_TYPE)) {
            youTubePlayerViewAfter.setVisibility(View.VISIBLE);
            btnPlayAfter.setVisibility(View.VISIBLE);

        }
    }
}
