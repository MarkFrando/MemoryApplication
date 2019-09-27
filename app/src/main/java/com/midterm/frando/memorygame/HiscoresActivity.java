package com.midterm.frando.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HiscoresActivity extends Activity {
    EditText _name;
    TextView _score;
    String name = "";
    int score = 0;
    private final static String NAME = "name";
    private final static String SCORE = "score";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscores);
        _name = (EditText) findViewById(R.id.name);
        _score = (TextView) findViewById(R.id.score);

        if (savedInstanceState != null) {
            name = savedInstanceState.getString(NAME, "");
            score = savedInstanceState.getInt(SCORE, 0);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onSaveInstanceState");
        savedInstanceState.putString(NAME, name);
        savedInstanceState.putInt(SCORE, score);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Memory", "onRestoreInstanceState called");
        _name.setText(name);
        _score.setText(String.valueOf(score));
    }

    public void onFind(View view) {
        DatabaseOpenHelper dbHandler = new DatabaseOpenHelper(this, null, null, 1);
        Scores scores = dbHandler.findScore(_name.getText().toString());
        if(scores != null){
            _name.setText(String.valueOf(scores.getName()));
            score = scores.getScore();
            _score.setText(String.valueOf(score));

        }else{
            Toast.makeText(this, _name.getText().toString() + " not found.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelete(View view) {
        DatabaseOpenHelper dbHandler = new DatabaseOpenHelper(this, null, null, 1);
        boolean result = dbHandler.deleteScore(_name.getText().toString());
        if(result){
            Toast.makeText(this, _name.getText().toString() + " deleted.", Toast.LENGTH_SHORT).show();
            _name.setText("");
            _score.setText("");
        }else{
            _score.setText("");
            Toast.makeText(this, _name.getText().toString() + " not found.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBack(View view) {
        Intent back = new Intent(this, NameActivity.class);
        startActivity(back);
    }
}
