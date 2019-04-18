package com.example.android.historyquests;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class QrReadActivity extends AppCompatActivity {

    private Context context;
    private QuestMetaData questMetaData;
    private QuestMetaData qrQuestMetaData;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    SurfaceView sv;
    CameraSource cs;
    BarcodeDetector bd;
    private boolean read;

    private boolean isWrongQr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_read);

        context = getApplicationContext();
        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");
        isWrongQr = (boolean) intent.getBooleanExtra("WRONG_QR", false);

        read = false;
        initMetaData();
        initEquipment();

        /**
         * setting camera source
         */
        sv.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cs.start(holder);

                } catch (IOException e) {
                    Log.e(LOG_TAG, "Exception while create cameraSource and surface");
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cs.stop();
            }
        });

        /**
         * setting qr processor
         */
        bd.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                retrieveMetaData(qrCodes);

            }
        });

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup

        read = false;
        super.onResume();

    }

    private void goNextRound() {
//
        if (!questMetaData.questId.equals(TemporaryQuests.NULL_QUEST)) {
            if (qrQuestMetaData.questId.equals(questMetaData.questId) && qrQuestMetaData.lastRoundNum == questMetaData.lastRoundNum) {
                int nextRoundIdx = questMetaData.lastRoundNum + 1;
                Quest currentQuest = TemporaryQuests.questsHashMap.get(questMetaData.questId);

                Round nextRound = currentQuest.getRounds()[nextRoundIdx];
                String additionalInfo = nextRound.getAdditionalInfo();

                if (additionalInfo == null || additionalInfo == "") {
                    switch (nextRound.getQuestionType()) {
                        case TemporaryQuests.RADIO_BUTTON_TASK_TYPE:
                            Intent goNextRound = new Intent(QrReadActivity.this, RadioButtonActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        case TemporaryQuests.CHECK_BOX_TASK_TYPE:
                            goNextRound = new Intent(QrReadActivity.this, CheckBoxActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        case TemporaryQuests.LINK_VARIANTS_TASK_TYPE:
                            goNextRound = new Intent(QrReadActivity.this, LinkVariantsActivity.class);
                            goNextRound.putExtra("META_DATA", questMetaData);
                            startActivity(goNextRound);
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Данный тип вопроса в разработке", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Intent goToQuest = new Intent(QrReadActivity.this, RoundInfo.class);
                    goToQuest.putExtra("META_DATA", questMetaData);
                    startActivity(goToQuest);
                }

            } else {

                QrReadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "Что-то пошло не так и Вы считали неверный QR-код.\nПопробуйте еще раз", Toast.LENGTH_LONG).show();
                    }
                });
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                } finally {
                    read = false;
                }
            }
       }

        if (qrQuestMetaData.questId.equals(TemporaryQuests.WRONG_QUEST_ID) || qrQuestMetaData.lastRoundNum != -1) {

            QrReadActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(context, "Что-то пошло не так и Вы считали неверный QR-код.\n" +
                            "Попробуйте еще раз", Toast.LENGTH_LONG).show();
                }
            });
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            } finally {
                read = false;
            }
        } else if (questMetaData.questId.equals(TemporaryQuests.NULL_QUEST) && qrQuestMetaData.lastRoundNum == -1) {
            goPlayersActivity();
        }



    }

    private void goPlayersActivity(){
        QuestMetaData newQuestMetaData = new QuestMetaData(qrQuestMetaData.questId, qrQuestMetaData.lastRoundNum, qrQuestMetaData.lastRoundRight, qrQuestMetaData.level, qrQuestMetaData.players);
        Intent goToPlayersIntent = new Intent(QrReadActivity.this, PlayersActivity.class);
        goToPlayersIntent.putExtra("META_DATA", newQuestMetaData);
        startActivity(goToPlayersIntent);
    }

    private void vibrate(){
        Vibrator v = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }

    private void retrieveMetaData(SparseArray<Barcode> qrCodes){

        if (qrCodes.size() != 0 && !read) {
            read = true;
            vibrate();
            String qrString = qrCodes.valueAt(0).displayValue;
            String[] qrStringArray = qrString.split("x");
            try {
                qrQuestMetaData.questId = qrStringArray[0];
                qrQuestMetaData.lastRoundNum = Integer.parseInt(qrStringArray[1]) - 1;
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                Log.e(LOG_TAG, e.getMessage());
                qrQuestMetaData.questId = TemporaryQuests.WRONG_QUEST_ID;
            }
            goNextRound();
        }

    }

    private void initMetaData() {
        qrQuestMetaData = new QuestMetaData("0", -1,false, 0, null);
    }

    private void initEquipment() {
        sv = (SurfaceView)findViewById(R.id.camerapreview);
        bd = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();
        cs = new CameraSource.Builder(this, bd)
                .setRequestedPreviewSize(640, 480).build();
    }

}
