package com.example.staceylm.foodnutritionactivity;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TemperatureDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_details);
            //Bundle info being passed to the Fragment.
            Bundle info = getIntent().getExtras();
            FragmentTransaction ft =  getFragmentManager().beginTransaction();
            TemperatureFragment messageFrag = new TemperatureFragment();
            messageFrag.setArguments(info);
            ft.add(R.id.emptyTempFragment, messageFrag).commit();
        }
    }

