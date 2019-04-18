package com.example.android.historyquests;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.historyquests.R;

public class MainActivity extends AppCompatActivity {


    private TextView qrCodeTextView;
    private TextView aboutTextView;
    private TextView startQuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
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

    @Override
    public void onBackPressed() {

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                        System.exit(0);
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void initViews() {
        qrCodeTextView = (TextView)findViewById(R.id.start_qr_code);
        aboutTextView = (TextView)findViewById(R.id.start_info);
        startQuest = (TextView)findViewById(R.id.start_quest);
    }
    private  void initExistingQuests(){
        TemporaryQuests.questsHashMap.put(TemporaryQuests.ID_UNIVERSITY_QUEST, TemporaryQuests.questPetersburgUniversity);
        TemporaryQuests.questsHashMap.put(TemporaryQuests.ID_PALCE_SQUARE_QUEST, null);

    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Закрыть приложение Исторический MAXIMUM?");
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
