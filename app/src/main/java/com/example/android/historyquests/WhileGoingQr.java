package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WhileGoingQr extends AppCompatActivity {


    private String LOG_TAG = WhileGoingQr.class.getSimpleName();
    private QuestMetaData questMetaData;

    private Button endRouteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.while_going_to_qr);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        endRouteBtn = (Button)findViewById(R.id.btn_end_route);

        endRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChooseQuestIntent = new Intent(WhileGoingQr.this, QrReadActivity.class);
                goToChooseQuestIntent.putExtra("META_DATA", questMetaData);
                startActivity(goToChooseQuestIntent);

            }
        });


    }
}
