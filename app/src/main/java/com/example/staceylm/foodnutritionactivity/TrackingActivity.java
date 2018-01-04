package com.example.staceylm.foodnutritionactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

;

public class TrackingActivity extends AppCompatActivity {

    //private ListView activites
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        ForeCastQuery forecast = new ForeCastQuery();
        forecast.execute();



        android.os.SystemClock.sleep(600);
        // Get The Refference Of Button
        Button btnShowList = (Button) findViewById(R.id.butttonShowList);
        Button btnShowAct = (Button) findViewById(R.id.buttonshowActivities);

        // Set OnClick Listener on  button  and start AnimalListActivity when clicked on Button
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityFragment af = new ActivityFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.linearLayout, af).commit();

            }
        });

        // Set OnClick Listener on  button  and start AnimalListActivity when clicked on Button
        btnShowAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivitesListFragment af = new ActivitesListFragment();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.linearLayout, af).commit();

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        if (id == R.id.help) {
            Toast toast = Toast.makeText(getApplicationContext(), "Version 1.0, by Drake Cassidy" ,Toast.LENGTH_LONG);
            toast.show();
        }
        return true;
    }

    public class ForeCastQuery extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer ... value) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);
        }

        @Override
        protected void onPostExecute(String Result) {
            progressBar.setVisibility(View.INVISIBLE);

        }
    }



}
