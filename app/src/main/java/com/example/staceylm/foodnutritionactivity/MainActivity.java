package com.example.staceylm.foodnutritionactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button foodButton = (Button) findViewById(R.id.foodButton);
        final TextView foodTrack = (TextView) findViewById(R.id.foodTrack);
        final TextView welcome = (TextView) findViewById(R.id.welcome);
       final ImageView heart = (ImageView) findViewById(R.id.heart);
        final ImageView fork = (ImageView) findViewById(R.id.fork);
    
        foodButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MainMenu mainMenu = new MainMenu();
                getSupportFragmentManager().beginTransaction().add(R.id.container, mainMenu).commit();
                foodButton.setVisibility(view.GONE);
                heart.setVisibility(view.GONE);
                welcome.setVisibility(view.GONE);
                fork.setVisibility(view.GONE);
                foodTrack.setVisibility(view.GONE);
              
            }
        });
    }
}