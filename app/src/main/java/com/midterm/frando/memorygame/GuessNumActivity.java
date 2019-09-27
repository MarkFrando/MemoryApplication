package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GuessNumActivity extends Activity {
    TextView guessBox;
    int[] numbers, colours;
    int[] guesses = new int[4];
    boolean correct = false;
    String guess, name;
    int index = 0;
    int points;
    private static final String NAME = "name";
    private static final String POINT = "point";
    private static final String COL = "coloured";
    private static final String NUM = "numbered";
    private static final String GUESS = "guess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        guessBox = (TextView) findViewById(R.id.guess);

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
            guess = savedInstanceState.getString(GUESS, "");
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onSaveInstanceState");
        savedInstanceState.putString(NAME, name);
        savedInstanceState.putInt(POINT, points);
        savedInstanceState.putIntArray(COL, colours);
        savedInstanceState.putIntArray(NUM, numbers);
        savedInstanceState.putString(GUESS, guess);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onRestoreInstanceState called");
        guessBox.setText(guess);
    }

    public void onOne(View view) {
        guess = guessBox.getText().toString() + "1";
        guesses[index] = 1;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onTwo(View view) {
        guess = guessBox.getText().toString() + "2";
        guesses[index] = 2;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onThree(View view) {
        guess = guessBox.getText().toString() + "3";
        guesses[index] = 3;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onFour(View view) {
        guess = guessBox.getText().toString() + "4";
        guesses[index] = 4;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onFive(View view) {
        guess = guessBox.getText().toString() + "5";
        guesses[index] = 5;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onSix(View view) {
        guess = guessBox.getText().toString() + "6";
        guesses[index] = 6;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onSeven(View view) {
        guess = guessBox.getText().toString() + "7";
        guesses[index] = 7;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onEight(View view) {
        guess = guessBox.getText().toString() + "8";
        guesses[index] = 8;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onNine(View view) {
        guess = guessBox.getText().toString() + "9";
        guesses[index] = 9;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
            correct = check();
            if(correct){
                correct();
            }else{
                incorrect();
            }
        }
    }

    public void onZero(View view) {
        guess = guessBox.getText().toString() + "0";
        guesses[index] = 0;
        index++;

        guessBox.setText(guess);
        if(guess.length() == 4){
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
            if(guesses[i] == numbers[i]){
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
