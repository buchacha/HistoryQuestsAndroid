package com.example.android.historyquests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayersActivity extends AppCompatActivity {


    private ArrayList<String> players = new ArrayList<String>();
    private EditText playerNameEditText;
    private Button btnAddPlayer;
    private Button btnAddAllPlayers;
    private TextView playersShowTextView;
    private QuestMetaData questMetaData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.players);

        Intent intent = getIntent();
        questMetaData = (QuestMetaData) intent.getExtras().getSerializable("META_DATA");

        playerNameEditText = (EditText) findViewById(R.id.edit_player_name);
        btnAddPlayer = (Button) findViewById((R.id.button_add_player));
        btnAddAllPlayers = (Button) findViewById(R.id.button_add_all_players);
        playersShowTextView = (TextView) findViewById(R.id.show_players);

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = playerNameEditText.getText().toString();
                if (name != "") {
                    players.add(name);
                    playersShowTextView.append(name + "\n");
                    playerNameEditText.setText("");

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Введите имя",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btnAddAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = playerNameEditText.getText().toString();
                if (name != "") {
                    players.add(name);
                    playersShowTextView.append(name + "\n");
                }
                questMetaData.players = players;
                Intent goToRules = new Intent(PlayersActivity.this, RulesActivity.class);
                goToRules.putExtra("META_DATA", questMetaData);
                startActivity(goToRules);
            }
        });


    }

}
