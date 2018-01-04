package com.example.staceylm.foodnutritionactivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


/**
 * Created by DrakeWin on 1/2/2018.
 */

public class ShowActivityList extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_list);


        ActivitesDetailsFragment messageFragment = new ActivitesDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", getIntent().getIntExtra("position",0));
        bundle.putString("messageID",getIntent().getStringExtra("messageID"));
        messageFragment.setArguments(bundle);

        // making the FragmentTrasncation

        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.relativeLayout, messageFragment).commit();



    }

}
