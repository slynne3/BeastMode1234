package com.example.staceylm.foodnutritionactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        // Button bActivityTracker = findViewById(R.id.bActivityTracker);
        Button bFoodTracker = (Button) findViewById(R.id.bFoodTracker);
        // Button bThermostat = findViewById(R.id.bThermostat);
        // Button bAutomobile = findViewById(R.id.bAutomobile);

        bFoodTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent f = new Intent(StartActivity.this, MainActivity.class);
               startActivityForResult(f, 100);
            }
        });

    }
}

