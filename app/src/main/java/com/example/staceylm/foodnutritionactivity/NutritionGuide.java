package com.example.staceylm.foodnutritionactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class NutritionGuide extends AppCompatActivity {
    ProgressBar prgs;
    ProgressTask task = new ProgressTask();
    Button showProgressBtn;
    Button stopProgressBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_guide);
        WebView wv = (WebView) findViewById(R.id.webview);
        wv.setWebViewClient(new WebViewClient());    //the lines of code added
        wv.setWebChromeClient(new WebChromeClient()); //same as above
        wv.loadUrl("https://www.canada.ca/en/health-canada/services/food-nutrition/canada-food-guide/get-your-copy/eating-well-2007.html");
    
        prgs = (ProgressBar) findViewById(R.id.prgs);
        task.execute(10);
    }
    
    private class ProgressTask extends AsyncTask<Integer, Integer, Void> {
        
        protected void onPreExecute() {
            super.onPreExecute();
            prgs.setMax(100); // set maximum progress to 100.
        }
        
        protected void onCancelled() {
            prgs.setMax(0); // stop the progress
        }
        
        protected Void doInBackground(Integer... params) {
          
            int start = params[0];
            for (int i = start; i <= 100; i += 5) {
                try {
                    boolean cancelled = isCancelled();
                    if (!cancelled) {
                        // publishProgress(i);
                        prgs.setProgress(i);
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
            prgs.setProgress(5);
            Log.v("Progress", "Once");
        }
        
        protected void onPostExecute(Void result) {
            // async task finished
            Log.v("Progress", "Finished");
            prgs.setVisibility(View.INVISIBLE);
        }
        
    }
}
    
    //    public void showProgress() {
      //      task.execute(10);
            // start progress bar with initial progress 10
            ///////////////////task.execute(10,10,null);

        //}
    
    ///public void stopProgress() {
       // task.cancel(true);
   // }
    //}


