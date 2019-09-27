package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends Activity {
    int points = 0;
    EditText nameBox;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        nameBox = (EditText) findViewById(R.id.name);
    }

    public void onPlay(View view) {
        name = nameBox.getText().toString();
        if(name.equalsIgnoreCase("")){
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
        }else{
            Intent play = new Intent(this, GameActivity.class);
            play.putExtra("POINTS", points);
            play.putExtra("NAME", name);
            startActivity(play);
        }
    }

    public void onScores(View view) {
        Intent scores = new Intent(this, HiscoresActivity.class);
        startActivity(scores);
    }
}
