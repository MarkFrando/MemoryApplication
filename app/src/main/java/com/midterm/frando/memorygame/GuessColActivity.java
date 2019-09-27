package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GuessColActivity extends Activity {
    int[] numbers, colours;
    int[] guesses = {0, 0, 0, 0};
    boolean correct = false;
    String name;
    int index = 0;
    int points;
    private static final String NAME = "name";
    private static final String POINT = "point";
    private static final String COL = "coloured";
    private static final String NUM = "numbered";
    private static final String INDEX = "index";
    private static final String GUESS = "guess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_col);

        Intent get = getIntent();
        numbers = get.getIntArrayExtra("NUMS");
        colours = get.getIntArrayExtra("COLS");
        points = get.getIntExtra("POINTS", 0);
        name = get.getStringExtra("NAME");

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(NAME, "");
            points = savedInstanceState.getInt(POINT, 0);
            colours = savedInstanceState.getIntArray(COL);
            numbers = savedInstanceState.getIntArray(NUM);
            index = savedInstanceState.getInt(INDEX, 0);
            guesses = savedInstanceState.getIntArray(GUESS);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onSaveInstanceState");
        savedInstanceState.putString(NAME, name);
        savedInstanceState.putInt(POINT, points);
        savedInstanceState.putIntArray(COL, colours);
        savedInstanceState.putIntArray(NUM, numbers);
        savedInstanceState.putInt(INDEX, index);
        savedInstanceState.putIntArray(GUESS, guesses);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onRestoreInstanceState called");
        for(int i = 0; i < guesses.length; i++){
            switch(i){
                case 0:
                    switch(guesses[i]){
                        case 0:
                            break;
                        case 1:
                            findViewById(R.id.col1).setBackgroundColor(Color.parseColor("#ff0000"));
                            break;
                        case 2:
                            findViewById(R.id.col1).setBackgroundColor(Color.parseColor("#00ff00"));
                            break;
                        case 3:
                            findViewById(R.id.col1).setBackgroundColor(Color.parseColor("#0000ff"));
                            break;
                        case 4:
                            findViewById(R.id.col1).setBackgroundColor(Color.parseColor("#ffff00"));
                            break;
                    }
                    break;
                case 1:
                    switch(guesses[i]){
                        case 0:
                            break;
                        case 1:
                            findViewById(R.id.col2).setBackgroundColor(Color.parseColor("#ff0000"));
                            break;
                        case 2:
                            findViewById(R.id.col2).setBackgroundColor(Color.parseColor("#00ff00"));
                            break;
                        case 3:
                            findViewById(R.id.col2).setBackgroundColor(Color.parseColor("#0000ff"));
                            break;
                        case 4:
                            findViewById(R.id.col2).setBackgroundColor(Color.parseColor("#ffff00"));
                            break;
                    }
                    break;
                case 2:
                    switch(guesses[i]){
                        case 0:
                            break;
                        case 1:
                            findViewById(R.id.col3).setBackgroundColor(Color.parseColor("#ff0000"));
                            break;
                        case 2:
                            findViewById(R.id.col3).setBackgroundColor(Color.parseColor("#00ff00"));
                            break;
                        case 3:
                            findViewById(R.id.col3).setBackgroundColor(Color.parseColor("##0000ff"));
                            break;
                        case 4:
                            findViewById(R.id.col3).setBackgroundColor(Color.parseColor("#ffff00"));
                            break;
                    }
                    break;
                case 3:
                    switch(guesses[i]){
                        case 0:
                            break;
                        case 1:
                            findViewById(R.id.col4).setBackgroundColor(Color.parseColor("#ff0000"));
                            break;
                        case 2:
                            findViewById(R.id.col4).setBackgroundColor(Color.parseColor("#00ff00"));
                            break;
                        case 3:
                            findViewById(R.id.col4).setBackgroundColor(Color.parseColor("#0000ff"));
                            break;
                        case 4:
                            findViewById(R.id.col4).setBackgroundColor(Color.parseColor("##ffff00"));
                            break;
                    }
                    break;
            }
        }

    }

    public void onYellow(View view) {
        insertGuess("#ffff00");
        guesses[index] = 4;
        index++;
        pass();
    }

    public void onGreen(View view) {
        insertGuess("#00ff00");
        guesses[index] = 2;
        index++;
        pass();
    }

    public void onRed(View view) {
        insertGuess("#ff0000");
        guesses[index] = 1;
        index++;
        pass();
    }

    public void onBlue(View view) {
        insertGuess("#0000ff");
        guesses[index] = 3;
        index++;
        pass();
    }

    public void insertGuess(String colour){
        switch(index){
            case 0:
                findViewById(R.id.col1).setBackgroundColor(Color.parseColor(colour));
                break;
            case 1:
                findViewById(R.id.col2).setBackgroundColor(Color.parseColor(colour));
                break;
            case 2:
                findViewById(R.id.col3).setBackgroundColor(Color.parseColor(colour));
                break;
            case 3:
                findViewById(R.id.col4).setBackgroundColor(Color.parseColor(colour));
                break;
        }
    }

    public void pass(){
        if(index == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public boolean check(){
        boolean correct = false;
        for(int i = 0; i < guesses.length; i++){
            if(guesses[i] == colours[i]){
                correct = true;
            }else{
                correct = false;
                break;
            }
        }
        if(correct){
            points++;
        }
        return correct;
    }

    public void correct(){
        Intent next = new Intent(this, GameActivity.class);
        next.putExtra("POINTS", points);
        next.putExtra("NAME", name);
        startActivity(next);
    }

    public void incorrect(){
        Intent score = new Intent(this, ScoreActivity.class);
        score.putExtra("POINTS", points);
        score.putExtra("NAME", name);
        startActivity(score);
    }
}
