package com.example.staceylm.foodnutritionactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by StaceyLM on 2017-12-21.
 */

public class foodDatabaseHelper {
    
    public static final String DATABASE_NAME = "Food.db";
    public static final String TABLE_NAME = "food";
    public final static String KEY_ID = "_id";
    public static final String COL2 = "foodtype";
    public static final String COL3 = "cals";
    public static final String COL4 = "fats";
    public static final String COL5 = "carbs";
   // public static final String COL6 = "date";
    private static int VERSION_NUM = 27;
    
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + " ( "
            + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL2
            + " TEXT NOT NULL,"
            + COL3
            + " TEXT NOT NULL,"
            + COL4
            + " TEXT NOT NULL,"
            + COL5
            + " TEXT NOT NULL ) ";
          //  + COL6
           // + " TEXT NOT NULL ) ";
            
    
    private Context ctx;
    private DatabaseHelper myDbHelper;
    private SQLiteDatabase db;
    
    
    public foodDatabaseHelper(Context ctx) {
        this.ctx = ctx;
        myDbHelper = new DatabaseHelper(ctx);
    }
    
    public foodDatabaseHelper openDB() {
        db = myDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        myDbHelper.close();
    }
    
    
    public long insertRow(String foodtype, String cals, String fats, String carbs) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, foodtype);
        contentValues.put(COL3, cals);
        contentValues.put(COL4, fats);
        contentValues.put(COL5, carbs);
       // contentValues.put(COL6, date);
    
        return db.insert(TABLE_NAME, null, contentValues);
         //db.update(TABLE_NAME, contentValues, "ID = ? ", new String[] { KEY_ID } );
    }
    
    public boolean deleteRow(long rowId){
        String where = KEY_ID + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) !=0;
    }
    
    public void deleteAll(){
        Cursor res = getAllRows();
        long rowId = res.getColumnIndexOrThrow(KEY_ID);
        if (res.moveToFirst()) {
            do {
                deleteRow(res.getLong((int) rowId));
            }while (res.moveToNext());
            }
            res.close();
        }
        
        public Cursor getRow(long rowId){
            String where = KEY_ID + "=" + rowId;
        Cursor res = db.query(false, TABLE_NAME, new String[] {KEY_ID, COL2, COL3, COL4, COL5}, where, null, null, null, null, null, null);
            if (res != null) {
                res.moveToFirst();
            }
            return res;
    }
    
    public boolean updateRow(long rowId, String foodtype, String cals, String fats, String carbs) {
        String where = KEY_ID + "=" + rowId;
    
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, foodtype);
        contentValues.put(COL3, cals);
        contentValues.put(COL4, fats);
        contentValues.put(COL5, carbs);
        //contentValues.put(KEY_DATE, timestamp);
    
        return db.update(TABLE_NAME, contentValues, where , null) != 0;
        
    }
    
    
    public Cursor getAllRows() {
        
        Cursor res = db.query(true, TABLE_NAME, new String[] {KEY_ID, COL2, COL3, COL4, COL5}, null, null, null, null, null, null);
        if (res != null) {
            res.moveToFirst();
        }
        return res;
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper {
        
        public DatabaseHelper(Context ctx) {
            super(ctx, DATABASE_NAME, null, VERSION_NUM);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
           
            onCreate(db);
    
            if (oldVersion == 27)
                db.execSQL("ALTER TABLE "+ TABLE_NAME +" ADD "+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT");
        }
        }
    }
