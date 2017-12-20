package com.example.staceylm.foodnutritionactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Thermostat extends AppCompatActivity {
    Button addTempButton;
    Button weeklyTempButton;
    Button aboutButton;
    ListView tempList;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        addTempButton = (Button) findViewById(R.id.addTempButton);

        addTempButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("AddTemp", "User clicked Add temperature");
                Intent chatIntent = new Intent(getApplicationContext(), DateTimeTempSpinner.class);
                startActivityForResult(chatIntent, 10);
            }

        });

    }
}