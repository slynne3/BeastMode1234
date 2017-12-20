package com.example.staceylm.foodnutritionactivity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {
    Toolbar toolbar;
    TextView textAbout;
    RelativeLayout layoutC;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initToolBar();
        textAbout = (TextView) findViewById(R.id.textAbout);
        textAbout.setVisibility(View.INVISIBLE);
    }
    
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("All About Us");
        setSupportActionBar(toolbar);
        
        toolbar.setNavigationIcon(R.drawable.cad);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AboutUs.this, "Thanks for Using Food Tracker", Toast.LENGTH_SHORT).show();
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.layoutC), "Author: Stacey Mulder.  Version 1.0.", Snackbar.LENGTH_INDEFINITE);
                        textAbout.setVisibility(View.VISIBLE);
                        mySnackbar.show();
                        mySnackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
    
                        });
                    }
                    });
    
                    }
                }
    
