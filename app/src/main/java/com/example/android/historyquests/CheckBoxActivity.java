package com.example.android.historyquests;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {

    private QuestMetaData questMetaData;
    private Round currentRound;

    private LinearLayout taskLayout;
    private TextView title;
    private TextView question;
    private LinearLayout checkGroup;

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

        if (nextRoundIdx == currentQuest.getCountRounds()) {
            Toast.makeText(getApplicationContext(), "Вы завершили квест полностью. С победой!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return;
        }
        if (currentQuest == null) {
            Intent goNextRound = new Intent(CheckBoxActivity.this, RoundInfo.class);
            goNextRound.putExtra("META_DATA", questMetaData);
            startActivity(goNextRound);
        } else {

            questMetaData.lastRoundNum++;
            Intent goToChooseQuestIntent = new Intent(CheckBoxActivity.this, WhileGoingQr.class);
            goToChooseQuestIntent.putExtra("META_DATA", questMetaData);
            startActivity(goToChooseQuestIntent);
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
}
