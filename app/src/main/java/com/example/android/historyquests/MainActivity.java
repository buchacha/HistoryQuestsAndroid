package com.example.android.historyquests;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.historyquests.R;

public class MainActivity extends AppCompatActivity {


    private TextView qrCodeTextView;
    private TextView aboutTextView;
    private TextView startQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrCodeTextView = (TextView)findViewById(R.id.start_qr_code);
        aboutTextView = (TextView)findViewById(R.id.start_info);
        startQuest = (TextView)findViewById(R.id.start_quest);

        initExistingQuests();

        qrCodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: open qr reader and then according to qr-code information start quest
            }
        });

        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.site_url)));
                startActivity(browserIntent);

            }
        });

        startQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questIntent = new Intent(MainActivity.this, ChooseLevelActivity.class);
                startActivity(questIntent);
            }
        });

    }

    private  void initExistingQuests(){
        TemporaryQuests.questsHashMap.put(getString(R.string.id_university_quest), TemporaryQuests.questPetersburgUniversity);
        TemporaryQuests.questsHashMap.put(getString(R.string.id_palace_square_quest), null);

    }









}
