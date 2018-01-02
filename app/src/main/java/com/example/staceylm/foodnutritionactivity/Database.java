package com.example.staceylm.foodnutritionactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

/**
 * Created by StaceyLM on 2017-12-21.
 */

public class Database extends AppCompatActivity {
    foodDatabaseHelper myDb;
    Time today = new Time(Time.getCurrentTimezone());
    EditText foodUser, calorUser, fatsUser, carbsUser;
    Button buttonAdd, buttonDel, countcals;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_food);
        
        
        foodUser = (EditText) findViewById(R.id.foodUser);
        calorUser = (EditText) findViewById(R.id.calorUser);
        fatsUser = (EditText) findViewById(R.id.fatsUser);
        carbsUser = (EditText) findViewById(R.id.carbsUser);
        TextView dateText = (TextView) findViewById(R.id.dateText);
    
        countcals = (Button) findViewById(R.id.countCals);
    
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        dateText.setText(dateString);
        
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonDel = (Button) findViewById(R.id.delButton);
    
        listViewItemClick();
        listviewItemLongClick();
        openData();
        addData();
        viewAll();
        deleteAllRows();
        countCals();
    
    }
    
    private void openData() {
        myDb = new foodDatabaseHelper(this);
        myDb.openDB();
    }
    
    public void addData() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            
                // today.setToNow();
                //  String timestamp = today.format("%Y-%m-%d  %H:%M:%S");
            
                myDb.insertRow(foodUser.getText().toString(),
                        calorUser.getText().toString(),
                        fatsUser.getText().toString(),
                        carbsUser.getText().toString());
                viewAll();
            
            }
        });
    
    }
    
    public void viewAll() {
        Cursor res = myDb.getAllRows();
        String[] foodInfo = new String[]{foodDatabaseHelper.KEY_ID, foodDatabaseHelper.COL2, foodDatabaseHelper.COL3, foodDatabaseHelper.COL4, foodDatabaseHelper.COL5};
        
        int[] toViewIDs = new int[]{R.id.lv1, R.id.lv2, R.id.lv3, R.id.lv4, R.id.lv5};
        
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout, res, foodInfo, toViewIDs, 0);
        
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(myCursorAdapter);
    
        myCursorAdapter.notifyDataSetChanged();
    }
    
    public void countCals(){
        final ListView lv = (ListView) findViewById(R.id.listView);
        
        countcals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Total number of Food Items Added to the list this session :" + lv.getAdapter().getCount(), Toast.LENGTH_LONG).show();
            }

        });

 
       
    }
    
    private void updateTask(long id) {
        Cursor res = myDb.getRow(id);
        if (res.moveToFirst()) {
            String foodtype = foodUser.getText().toString();
            String cals = calorUser.getText().toString();
            String fats = fatsUser.getText().toString();
            String carbs = carbsUser.getText().toString();
            //today.setToNow();
            //String timestamp = today.format("%Y-%m-%d  %H:%M:%S");
            myDb.updateRow(id, foodtype, cals, fats, carbs);
        }
        res.close();
    }
    
    private void listViewItemClick() {
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                updateTask(id);
                viewAll();
            }
        });
        
    }
    
    private void deleteAllRows() {
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.deleteAll();
                viewAll();
            }
        });
    }
    
    private void listviewItemLongClick() {
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                myDb.deleteRow(id);
                viewAll();
                return false;
            }
        });
    }
}