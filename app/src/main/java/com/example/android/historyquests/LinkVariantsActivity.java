package com.example.android.historyquests;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LinkVariantsActivity extends AppCompatActivity {

    private String currentAnswerState = "";
    private int currentRoundIdx;

    private QuestMetaData questMetaData;
    private Round currentRound;

    private ScrollView mainScrollView;
    private LinearLayout taskLayout;
    private TextView title;
    private TextView question;

    private TextView checkAnswer;
    private TextView resetAnswer;
    private TextView getHint;

    private TextView darkerView;
    private LinearLayout afterTaskLayout;
    private ScrollView afterTaskScrollView;
    private TextView afterTaskText;
    private TextView afterTaskGoMain;
    private TextView afterTaskGoNext;
    private TextView afterTaskRepeat;

    private LinearLayout variantsLayout;
    private LinearLayout answersLayout;

    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView answer5;
    private TextView answer6;

    private int[] answerIds = new int[6];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_link_variants);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        currentRoundIdx = questMetaData.lastRoundNum+1;
        currentRound = TemporaryQuests.questsHashMap.get(questMetaData.questId).getRounds()[currentRoundIdx];

        initViews();
        setupViews();
        setListeners();



        resetAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAnswerState = "";
                int numOfVariants = currentRound.getCountVariants();
                answersLayout.removeAllViews();
                setupAnswers(numOfVariants);
            }
        });

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rightAnswer = Integer.toString(currentRound.getAnswer());
                if (rightAnswer.equals(currentAnswerState)){
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
                Intent i = NavUtils.getParentActivityIntent(LinkVariantsActivity.this);
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
            Intent i = NavUtils.getParentActivityIntent(LinkVariantsActivity.this);
            startActivity(i);
            return;
        }


        Round nextRound = currentQuest.getRounds()[nextRoundIdx];
        String additionalInfo = nextRound.getAdditionalInfo();
        if (additionalInfo == null || additionalInfo == "") {
            questMetaData.lastRoundNum++;
            switch (nextRound.getQuestionType()) {
                case "0":
                    Intent goNextRound = new Intent(LinkVariantsActivity.this, RadioButtonActivity.class);
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
            Intent goToQuest = new Intent(LinkVariantsActivity.this, RoundInfo.class);
            goToQuest.putExtra("META_DATA", questMetaData);
            startActivity(goToQuest);
        }


    }

    private void showAfterTask(boolean isRight) {
        setClickable(taskLayout);
        darkerView.setVisibility(View.VISIBLE);
        afterTaskScrollView.setVisibility(View.VISIBLE);
        mainScrollView.scrollTo(0, 0);
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

    private void setupViews() {
        title.setText("Станция " + Integer.toString(currentRoundIdx+1) + ". " + currentRound.getName());
        question.setText(currentRound.getQuestion());

        int currentRoundIdx = questMetaData.lastRoundNum+1;

        int numOfVariants = currentRound.getCountVariants();
        setupVariants(numOfVariants);
        setupAnswers(numOfVariants);
    }
    private void setupVariants(int numOfVariants) {
        answersLayout.removeAllViews();
        variantsLayout.removeAllViews();
        String[] variants = currentRound.getQuestionVariants();
        for (int i = 0; i < numOfVariants; i++) {
            TextView variantView = new TextView (getApplicationContext());
            variantView.setText(variants[i]);
            variantView.setTextColor(Color.GRAY);
            variantView.setTextSize(18);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 200 );
            variantView.setLayoutParams(lp);
            variantView.setPadding(11,11,11,11);
            variantsLayout.addView(variantView);
        }
    }

    private void setupAnswers(int numOfAnswers) {
        String[] answers = currentRound.getVariants();
        for (int i = 0; i<numOfAnswers; i++) {
            TextView answerView = findViewById(answerIds[i]);
            answerView.setText(answers[i]);
            answerView.setVisibility(View.VISIBLE);
        }

        for (int i = numOfAnswers; i<answers.length; i++) {
            TextView answerView = findViewById(answerIds[i]);
            answerView.setVisibility(View.GONE);
        }
    }

    private void setListeners() {
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(0);
                answer1.setVisibility(View.GONE);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(1);
                answer2.setVisibility(View.GONE);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(2);
                answer3.setVisibility(View.GONE);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(3);
                answer4.setVisibility(View.GONE);
            }
        });

        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(4);
                answer5.setVisibility(View.GONE);
            }
        });

        answer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToAnswersLayout(5);
                answer6.setVisibility(View.GONE);
            }
        });


    }

    private  void addToAnswersLayout(int idx) {
        String[] answers = currentRound.getVariants();
        TextView answerView = new TextView(getApplicationContext());
        answerView.setText(answers[idx]);
        answerView.setTextColor(Color.DKGRAY);
        answerView.setTextSize(18);
        answerView.setPadding(11,11,11,11);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 200);
        answerView.setLayoutParams(lp);
        answersLayout.addView(answerView);
        currentAnswerState += Integer.toString(idx+1);
        Log.e("FROM HERE 3", currentAnswerState);
    }
    private void initViews() {
        mainScrollView = findViewById(R.id.main_scroll_view);
        taskLayout = findViewById(R.id.task_layout);
        title = findViewById(R.id.task_title);
        question = findViewById(R.id.task_question);
        checkAnswer = findViewById(R.id.check_answer);
        getHint = findViewById(R.id.get_hint);
        resetAnswer = findViewById(R.id.reset_answer);

        darkerView = findViewById(R.id.darker_view);
        afterTaskLayout = findViewById(R.id.after_task_layout);
        afterTaskScrollView = findViewById(R.id.after_task_scroll_view);
        afterTaskText = findViewById(R.id.after_task_text);
        afterTaskGoMain = findViewById(R.id.after_task_go_main);
        afterTaskGoNext = findViewById(R.id.after_task_go_next);
        afterTaskRepeat = findViewById(R.id.after_task_repeat);

        variantsLayout = (LinearLayout) findViewById(R.id.variants_layout);
        answersLayout = (LinearLayout) findViewById(R.id.answers_layout);

        answer1 = (TextView) findViewById(R.id.answer1);
        answerIds[0] = answer1.getId();
        answer2 = (TextView) findViewById(R.id.answer2);
        answerIds[1] = answer2.getId();
        answer3 = (TextView) findViewById(R.id.answer3);
        answerIds[2] = answer3.getId();
        answer4 = (TextView) findViewById(R.id.answer4);
        answerIds[3] = answer4.getId();
        answer5 = (TextView) findViewById(R.id.answer5);
        answerIds[4] = answer5.getId();
        answer6 = (TextView) findViewById(R.id.answer6);
        answerIds[5] = answer6.getId();
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

}
