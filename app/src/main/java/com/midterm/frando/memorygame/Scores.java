package com.midterm.frando.memorygame;

public class Scores {
    String name;
    int score;

    public Scores(String name, int score){
        this.name = name;
        this.score = score;
    }

    public Scores(){}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }
}
