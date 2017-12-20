package com.example.staceylm.foodnutritionactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DateTimeTempSpinner extends Activity {
    public Spinner daySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_temp_spinner);

        String dateMenu [] = new String[]{"Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday"};

    }

    public void addItemsToSpinner(){
        daySpinner = (Spinner) findViewById(R.id.weekdaySpinner);

        List<String> list = new ArrayList<String>();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dataAdapter);
    }
}
