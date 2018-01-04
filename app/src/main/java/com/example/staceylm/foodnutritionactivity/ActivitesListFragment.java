package com.example.staceylm.foodnutritionactivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitesListFragment extends Fragment {

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> activites = new ArrayList<>();
    Button btnTotal;
    Button btnLast;
    EditText chatBox;
    ChatDatabaseHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    double mintues;
    String comments;



    public ActivitesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_activites_list, container, false);

        dbHelper = new ChatDatabaseHelper(getActivity());


        // creating the chatBox
        btnTotal = (Button) view.findViewById(R.id.btnTotal);
        btnLast = (Button) view.findViewById(R.id.btnLast);
       // chatBox = (EditText) view.findViewById(R.id.chatBox);

        db = dbHelper.getWritableDatabase();
        db2 = dbHelper.getReadableDatabase();

        cursor = db2.query(dbHelper.TABLE_NAME,dbHelper.COLUMNS,null,null,null,null,null);

        // Array Adapter for number of activites
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data
        );

        final ListView listView = (ListView) view.findViewById(R.id.listViewActivity);
        listView.setAdapter(adapter);

        // what happens when an item in the listview gets tagged
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                final String selectedAct= data.get(position);


               Toast.makeText(getContext(), "Activity Selected : "+selectedAct,   Toast.LENGTH_LONG).show();
              //  SnackBar snackBar = Snackbar.make(view,,Snackbar.LENGTH_LONG).setAction("Action",null).show();


                cursor.moveToPosition(position);
                Long id = cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_ID));


                Log.i("OMMMMMMMMMG",id.toString());
                android.os.SystemClock.sleep(600);
                Intent intent=new Intent(getActivity(),ShowActivityList.class);
                intent.putExtra("position",position);
                intent.putExtra("messageID",id.toString());
                startActivity(intent);

            }
        });

        writeMessage();
        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMonths();
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMonths();
            }
        });


        return view;

    }


    public void writeMessage(){
        data.clear();
        cursor = db2.query(dbHelper.TABLE_NAME,dbHelper.COLUMNS,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGES)));
                data.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_ACTIVITIES)));
                cursor.moveToNext();
            }
        }
    }

    public void displayMonths() {

        final ArrayList<String> months = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout rootTag = (LinearLayout)inflater.inflate(R.layout.dialog_calender,null);

        // creating the months
        months.add("January ");
        months.add("February");
        months.add("March ");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                months
        );

        // making the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.monthTitle);

        ListView listView = (ListView) rootTag.findViewById(R.id.calenderListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                calculateMins(position);
            }
        });
        // Adding the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Create Intent for AnimalListActivity and Start The Activity

            }
        });


        builder.setView(rootTag);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // making a toast pop up to tell which activity was selected
                Snackbar.make(getView(), R.string.monthCancel, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void calculateMins(int month) {
        int total = 0;
        ArrayList<Date> dates = new ArrayList<>();
        ArrayList<String> mins = new ArrayList<>();
        // Putting the correct values into serparte Array Lists for
        cursor = db2.query(dbHelper.TABLE_NAME, dbHelper.COLUMNS, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                mins.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MINTUES)));
                try {
                    dates.add(new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_DATE))));

                } catch (ParseException e) {
                    dates.add(new Date());
                    // getting the mintues

                }
                cursor.moveToNext();
            }

            for (int i = 0; i < dates.size(); i++) {

                int d = Integer.parseInt(new SimpleDateFormat("MM").format(dates.get(i)));
                d--;
                Log.i("What is happening", Integer.toString(mins.size()));
                if (d == month) {

                    try {

                        total += Integer.parseInt(mins.get(i));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            Toast.makeText(getContext(), "Total Mintues for this month = " + total, Toast.LENGTH_LONG).show();
            android.os.SystemClock.sleep(300);




        }
    }
}
