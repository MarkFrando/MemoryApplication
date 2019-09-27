package com.midterm.frando.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_SCORES = "scores";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORE_TABLE = "create table " + TABLE_SCORES + "(" +
                COLUMN_NAME + " text primary key, " +
                COLUMN_SCORE + " integer)";
        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SCORES);
        onCreate(db);
    }

    public void addScore(Scores score){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, score.getName());
        values.put(COLUMN_SCORE, score.getScore());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    public boolean deleteScore(String name){
        boolean result = false;
        String query = "select * from " + TABLE_SCORES +
                " where " + COLUMN_NAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Scores score = new Scores();
        if(cursor.moveToFirst()){
            db.delete(TABLE_SCORES, COLUMN_NAME + " = ?",
                    new String[] { String.valueOf(name)});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Scores findScore(String name){
        String query = "select * from " + TABLE_SCORES +
                " where " + COLUMN_NAME + " =\"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Scores score = new Scores();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            score.setName(cursor.getString(0));
            score.setScore(Integer.parseInt(cursor.getString(1)));
            cursor.close();
        }else{
            score = null;
        }
        db.close();
        return score;
    }
}
