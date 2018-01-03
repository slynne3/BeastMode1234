package com.example.staceylm.foodnutritionactivity;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WeeklyTemp extends AppCompatActivity {
    ProgressBar webPageProgress;
    ProgressTask task = new ProgressTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_temp);

        WebView weeklyWeb = (WebView) findViewById(R.id.weeklyWeb);

        weeklyWeb.setWebViewClient(new WebViewClient());    //the lines of code added
        weeklyWeb.setWebChromeClient(new WebChromeClient()); //same as above
        weeklyWeb.loadUrl("https://m.accuweather.com/en/ca/ottawa/k1y/extended-weather-forecast/55487");

        webPageProgress = (ProgressBar) findViewById(R.id.webProgress);
        task.execute(10);
    }

    private class ProgressTask extends AsyncTask<Integer, Integer, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            webPageProgress.setMax(100); // set maximum progress to 100.
        }

        protected void onCancelled() {
            webPageProgress.setMax(0); // stop the progress
        }

        protected Void doInBackground(Integer... params) {

            int start = params[0];
            for (int i = start; i <= 100; i += 25) {
                try {
                    boolean cancelled = isCancelled();
                    if (!cancelled) {
                        // publishProgress(i);
                        webPageProgress.setProgress(i);
                        Log.v("Progress", "increment " + i);
                        //onProgressUpdate(i);
                        SystemClock.sleep(1000);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }
            return null;
        }

        protected void onProgressUpdate(Integer... values) {
            webPageProgress.setProgress(25);
            Log.v("Progress", "Once");
        }

        protected void onPostExecute(Void result) {
            // async task finished
            Log.v("Progress", "Finished");
            webPageProgress.setVisibility(View.INVISIBLE);
        }

    }
}


