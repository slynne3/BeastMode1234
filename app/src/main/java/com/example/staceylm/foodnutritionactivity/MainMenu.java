package com.example.staceylm.foodnutritionactivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenu extends Fragment {


    public MainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        String[] menuItems = {"Calories",
                "Total Fat",
                "Total Carbohydrates"
        };

        ListView listView = (ListView) view.findViewById(R.id.mainMenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Toast.makeText(getActivity(), "You clicked the first item.", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getActivity(), Calorie_Count.class);
                    startActivity(intent1);
                }
                else if (position == 1) {
                    Toast.makeText(getActivity(), "You clicked the second item.", Toast.LENGTH_SHORT).show();
                }
                else if (position ==2){
                    Toast.makeText(getActivity(), "You clicked the third item.", Toast.LENGTH_SHORT).show();
                }
               }});

        return view;


    }

}
