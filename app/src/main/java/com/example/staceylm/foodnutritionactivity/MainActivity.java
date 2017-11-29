package com.example.staceylm.foodnutritionactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button foodButton = (Button) findViewById(R.id.foodButton);
        final TextView tracker = (TextView) findViewById(R.id.tracker);
        final TextView welcome = (TextView) findViewById(R.id.welcome);
        final TextView text3 = (TextView) findViewById(R.id.text3);

        foodButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MainMenu mainMenu = new MainMenu();
                getSupportFragmentManager().beginTransaction().add(R.id.container, mainMenu).commit();
                foodButton.setVisibility(view.GONE);
                tracker.setVisibility(view.GONE);
                welcome.setVisibility(view.GONE);
                text3.setVisibility(view.GONE);
            }
        });
    }
}