package com.example.staceylm.foodnutritionactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DrakeWin on 1/2/2018.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "Activities.db";
    final static int VERSION_NUM = 6;
    final static String KEY_ID = "id";
    final static String KEY_ACTIVITIES ="activities";
    final static String KEY_MINTUES = "mintues";
    final static String KEY_COMMENTS = "comments";
    final static String TABLE_NAME ="Activites";
    final static String KEY_DATE="date";
    static final String[] COLUMNS = {KEY_ID,KEY_ACTIVITIES,KEY_MINTUES,KEY_DATE,KEY_COMMENTS};

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME,null,VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper3", "Calling onCreate");
        String CREATE_ACTIVITES_DATABASE ="CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + KEY_ACTIVITIES + " TEXT ," + " " + KEY_MINTUES + " TEXT ," +  " " + KEY_DATE + " DATE ," + " " + KEY_COMMENTS + " TEXT )";
        db.execSQL(CREATE_ACTIVITES_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        // droping older table if message already exists
        Log.i("ChatDatabaseHelper3", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteItem(String id) {
        this.getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id);
    }
}


