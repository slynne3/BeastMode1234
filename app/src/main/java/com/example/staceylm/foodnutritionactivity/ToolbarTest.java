package com.example.staceylm.foodnutritionactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class ToolbarTest extends AppCompatActivity {
    String responseText = "You pressed yes";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity);
        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_one:
               // Button bActivityTracker = findViewById(R.id.bActivityTracker);
                break;
            case R.id.action_two:
                        Intent f = new Intent(ToolbarTest.this, MainActivity.class);
                        startActivityForResult(f, 100);
                
                break;
            case R.id.action_three:
                //Button bThermostat = findViewById(R.id.bThermostat);
                
                               break;
            case R.id.action_four:
                //Button bAutomobile = findViewById(R.id.bAutomobile);
            case R.id.action_about:
                Toast t2 = Toast.makeText(ToolbarTest.this, "Version 1.0 BeastMode Applications", Toast.LENGTH_LONG);
                t2.show();
                break;
        }
        return true;
    }
    
    
}

