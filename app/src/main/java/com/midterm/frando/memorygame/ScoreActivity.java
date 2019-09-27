package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends Activity {
    SharedPreferences prefs;
    TextView score, hiscore;
    String name;
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        prefs = getPreferences(MODE_PRIVATE);

        Intent get = getIntent();
        points = get.getIntExtra("POINTS", 0);
        name = get.getStringExtra("NAME");

        score = (TextView) findViewById(R.id.score);
        hiscore = (TextView) findViewById(R.id.hiscore);
        add();
        read();

    }

    public void save() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("HNAME", name);
        editor.putInt("HSCORE", points);
        editor.commit();
    }

    public void read(){
        String readName = prefs.getString("HNAME", "");
        int readPoints = prefs.getInt("HSCORE", -1);
        if(points > readPoints){
            save();
            score.setText("New Hiscore!");
            hiscore.setText("The current Hiscore is held by " + name + " at " + points + " points.");
        }else{
            score.setText("Thank you for playing " + name + "\nYour Score is: \n" + points);
            hiscore.setText("The current Hiscore is held by " + readName + " at " + readPoints + " points.");
        }
    }

    public void onReplay(View view) {
        Intent replay = new Intent(this, NameActivity.class);
        startActivity(replay);
    }

    public void add(){
        DatabaseOpenHelper dbHandler = new DatabaseOpenHelper(this, null, null, 1);
        Scores score = new Scores(name, points);
        dbHandler.addScore(score);
    }
}
