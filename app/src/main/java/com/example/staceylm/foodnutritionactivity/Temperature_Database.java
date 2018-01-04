package com.example.staceylm.foodnutritionactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rob on 2017-12-29.
 */

public class Temperature_Database extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "TemperatureTable";
    private static final String DATABASE_NAME = "Temperature.db";
    private static final int VERSION_NUM = 3;
    private static final String KEY_ID = "tempId";
    public static final String TEMP_TEMPERATURE = "temperature";
    public static final String TEMP_TIME = "time";
    public static final String TEMP_DAY = "day";

    public Temperature_Database TempDatabaseHelper;
    public SQLiteDatabase database;

    public Temperature_Database(Context ctx) {

        super(ctx, DATABASE_NAME, null, VERSION_NUM);
        database = getWritableDatabase();
    }

    public static String getTempTemperature(){

        return TEMP_TEMPERATURE;
    }

    public static String getTempTime(){

        return TEMP_TIME;
    }

    public static String getTempDay(){

        return TEMP_DAY;
    }

    public static String getTableName(){

        return TABLE_NAME;
    }

    public static String getKeyId(){
        return KEY_ID;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createDatabase = "CREATE TABLE " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEMP_DAY + " text not null, "
                + TEMP_TIME + " text not null, "
                + TEMP_TEMPERATURE  + " text not null);";

        db.execSQL(createDatabase);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

    public void close() {
        if (TempDatabaseHelper != null) {
            TempDatabaseHelper.close();
        }
    }

    public void deleteMessage(int id){
        // delete where KEY_ID = id
        database.delete(TABLE_NAME, "" + KEY_ID + " = " + id, null);
    }
}
