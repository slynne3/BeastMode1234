package com.example.staceylm.foodnutritionactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rob on 2017-12-01.
 */

public class UpdateDataBaseHelper extends SQLiteOpenHelper {

    public final static String name = "MyTable";
    private static final String DATABASE_NAME = "Updates.db";
    private static final int VERSION_NUM = 1;
    private static final String KEY_ID = "id";
    private static final String KEY_MESSAGE = "updates";

    public UpdateDataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getName() {
        return name;
    }

    public static String getKeyMessage() {
        return KEY_MESSAGE;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String createDatabase = "CREATE TABLE " + name + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MESSAGE + " TEXT" + " ) ";

        db.execSQL(createDatabase);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + name);

        onCreate(db);
    }

}
