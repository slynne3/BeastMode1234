package com.example.staceylm.foodnutritionactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button bActivityTracker = findViewById(R.id.bActivityTracker);
        Button bFoodTracker = (Button) findViewById(R.id.bFoodTracker);
        Button bThermostat = findViewById(R.id.bThermostat);
        Button bAutomobile = findViewById(R.id.bAutomobile);

        bFoodTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent foodTracker = new Intent(getApplicationContext(), Thermostat.class);
              //  startActivity(foodTracker);
                startActivityForResult(foodTracker, 10);
            }
       });

    }

}
