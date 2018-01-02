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

public class MainMenu extends Fragment {
    
    public MainMenu() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
    
        final ListView listView = (ListView) view.findViewById(R.id.mainMenu);
        listView.setVisibility(view.VISIBLE);
        

        String[] menuItems = {getString(R.string.listView1),
                getString(R.string.listView2),
                getString(R.string.listView3),
                getString(R.string.listView4)
        };

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
                    Intent intent1 = new Intent(getActivity(),  Database.class);
                    startActivity(intent1);
                }
                else if (position == 1) {
                    Toast.makeText(getActivity(), "You clicked the second item.", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getActivity(),  NutritionGuide.class);
                    startActivity(intent2);
                }
                else if (position ==2){
                    Toast.makeText(getActivity(), "You clicked the third item.", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getActivity(),  Exercise.class);
                    startActivity(intent3);
                }
                else if (position ==3){
                    Toast.makeText(getActivity(), "You clicked the fourth item.", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getActivity(),  AboutUs.class);
                    startActivity(intent3);
                }
               }});

        return view;


    }

}
