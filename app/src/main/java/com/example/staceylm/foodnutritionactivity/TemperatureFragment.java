package com.example.staceylm.foodnutritionactivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TemperatureFragment extends Fragment {

    private Context parent;
    final int resultCodeDelete = 2;

    public void onAttach(Context activity) {
        super.onAttach(activity);

        parent = activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passInfo = getArguments();

        long id = 0;
        if (passInfo != null) {
            id = passInfo.getLong("ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Bundle passInfo = getArguments();
        // Inflate the xml file for the fragment
        View v = inflater.inflate(R.layout.activity_temperature_fragment, null);

        final long id = passInfo.getLong("id");
        final int position = passInfo.getInt("position");
        final String message = passInfo.getString("Message");

        TextView messageDetails = (TextView) v.findViewById(R.id.tempFragDetails);
        messageDetails.setText(message);

        Button deleteButton = (Button) v.findViewById(R.id.tempDeleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actBundle = new Intent();
                actBundle.putExtra("id", id);
                actBundle.putExtra("position", position);
                getActivity().setResult(resultCodeDelete, actBundle);
                getActivity().finish();
            }
        });
        return v;
    }

}
