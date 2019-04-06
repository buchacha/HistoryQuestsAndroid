package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {

    private QuestMetaData questMetaData;
    private Round currentRound;

    private LinearLayout taskLayout;
    private TextView title;
    private TextView question;
    private RadioGroup radioGroup;

    private TextView checkAnswer;
    private TextView getHint;

    private TextView darkerView;
    private LinearLayout afterTaskLayout;
    private ScrollView afterTaskScrollView;
    private TextView afterTaskText;
    private TextView afterTaskGoMain;
    private TextView afterTaskGoNext;
    private TextView afterTaskRepeat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_radio_button);



        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        initViews();
        setCurrentRound();
        setViews();

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentAnswer = retrieveAnswer();
                int rightAnswer = currentRound.getAnswer();

                if (currentAnswer == -1) {
                    Toast.makeText(getApplicationContext(), "Введите ответ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (currentAnswer == rightAnswer) {
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
                Intent i = NavUtils.getParentActivityIntent(RadioButtonActivity.this);
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

    private void goNextRound() {


        int nextRoundIdx = questMetaData.lastRoundNum + 2;
        Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);

        if (nextRoundIdx == currentQuest.getCountRounds()) {
            Toast.makeText(getApplicationContext(), "Вы завершили квест полностью. С победой!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (currentQuest == null) {
            Intent goNextRound = new Intent(RadioButtonActivity.this, RoundInfo.class);
            goNextRound.putExtra("META_DATA", questMetaData);
            startActivity(goNextRound);
        } else {

            Round nextRound = currentQuest.getRounds()[nextRoundIdx];
            String additionalInfo = nextRound.getAdditionalInfo();
            if (additionalInfo == null || additionalInfo == "") {
                questMetaData.lastRoundNum++;
                switch (nextRound.getQuestionType()) {
                    case "0":
                        Intent goNextRound = new Intent(RadioButtonActivity.this, RadioButtonActivity.class);
                        goNextRound.putExtra("META_DATA", questMetaData);
                        startActivity(goNextRound);
                        break;
                    case "1":
                        Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                }

            } else {
                questMetaData.lastRoundNum++;
                Intent goToQuest = new Intent(RadioButtonActivity.this, RoundInfo.class);
                goToQuest.putExtra("META_DATA", questMetaData);
                startActivity(goToQuest);
            }
        }

    }

    private void setClickable(View view) {
        if (view != null) {
            view.setClickable(false);
            if (view instanceof ViewGroup) {
                ViewGroup vg = ((ViewGroup) view);
                for (int i = 0; i < vg.getChildCount(); i++) {
                    setClickable(vg.getChildAt(i));
                }
            }
        }
    }

    private void showAfterTask(boolean isRight) {
        setClickable(taskLayout);
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
        setupRadioGroup();
    }

    private int retrieveAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        return selectedId;
    }
    private void setupRadioGroup() {
        String[] variants = currentRound.getVariants();
        RadioButton button;
        radioGroup.removeAllViews();
        for (int i = 0; i < currentRound.getCountVariants(); i++) {
            button = new RadioButton(this);
            button.setText(variants[i]);
            button.setPadding(11, 11, 11, 11);
            button.setId(i);
            radioGroup.addView(button);
        }
    }

    private void setCurrentRound() {
        int roundIdx = questMetaData.lastRoundNum+1;
        currentRound = TemporaryQuests.questsHashMap.get(questMetaData.questId).getRounds()[roundIdx];
    }

    private void initViews() {
        taskLayout = findViewById(R.id.task_layout);
        title = findViewById(R.id.task_title);
        question = findViewById(R.id.task_question);
        radioGroup = findViewById(R.id.task_radio_group);
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
}
