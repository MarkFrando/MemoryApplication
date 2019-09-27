package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity {
    Random rand = new Random();
    String colour, name;
    int points;
    int[] colours, numbers;
    TextView num1, num2, num3, num4;
    private static final String NAME = "name";
    private static final String POINT = "point";
    private static final String COL = "coloured";
    private static final String NUM = "numbered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent get = getIntent();
        points = get.getIntExtra("POINTS", 0);
        name = get.getStringExtra("NAME");

        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);
        num3 = (TextView) findViewById(R.id.num3);
        num4 = (TextView) findViewById(R.id.num4);
        numbers = createNumbers();
        colours = createColourID();

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(NAME, "");
            points = savedInstanceState.getInt(POINT, 0);
            colours = savedInstanceState.getIntArray(COL);
            numbers = savedInstanceState.getIntArray(NUM);
        }

        num1.setText(String.valueOf(numbers[0]));
        num2.setText(String.valueOf(numbers[1]));
        num3.setText(String.valueOf(numbers[2]));
        num4.setText(String.valueOf(numbers[3]));
        num1.setBackgroundColor(Color.parseColor(createColour(colours[0])));
        num2.setBackgroundColor(Color.parseColor(createColour(colours[1])));
        num3.setBackgroundColor(Color.parseColor(createColour(colours[2])));
        num4.setBackgroundColor(Color.parseColor(createColour(colours[3])));

        if(points >= 1) Toast.makeText(this, "Points: " + points, Toast.LENGTH_SHORT).show();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onSaveInstanceState");
        savedInstanceState.putString(NAME, name);
        savedInstanceState.putInt(POINT, points);
        savedInstanceState.putIntArray(COL, colours);
        savedInstanceState.putIntArray(NUM, numbers);
    }

    public int[] createNumbers(){
        int[] nums = {(rand.nextInt(10)), (rand.nextInt(10)), (rand.nextInt(10)), (rand.nextInt(10))};
        return nums;
    }

    public int[] createColourID(){
        int[] colourID = {(int)(Math.random() * 4 + 1), (int)(Math.random() * 4 + 1), (int)(Math.random() * 4 + 1), (int)(Math.random() * 4 + 1)};
        return colourID;
    }

    public String createColour(int id){
        switch(id){
            case 1:
                colour = "#ff0000";
                break;
            case 2:
                colour = "#00ff00";
                break;
            case 3:
                colour = "#0000ff";
                break;
            case 4:
                colour = "#ffff00";
                break;
        }
        return colour;
    }

    public void onMemorized(View view) {
        int activity = (int)(Math.random() * 2 + 1);
        Intent memorize;
        if(activity == 1){
            memorize = new Intent(this, GuessNumActivity.class);
        }else{
            memorize = new Intent(this, GuessColActivity.class);
        }
        memorize.putExtra("NUMS", numbers);
        memorize.putExtra("COLS", colours);
        memorize.putExtra("POINTS", points);
        memorize.putExtra("NAME", name);
        startActivity(memorize);
    }
}
