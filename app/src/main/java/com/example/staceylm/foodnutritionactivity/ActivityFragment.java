package com.example.staceylm.foodnutritionactivity;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {

    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> activites = new ArrayList<>();
    Button btnSend;
    EditText chatBox;
    ChatDatabaseHelper dbHelper;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    double mintues;
    String comments;




    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container,false);
        dbHelper = new ChatDatabaseHelper(getActivity());

        // creating the generic Activities
        activites.add("Running");
        activites.add("Walking");
        activites.add("Biking");
        activites.add("Swimming");
        activites.add("Skating");
/*
       // creating the chatBox
        btnSend = (Button) view.findViewById(R.id.btnSend);
        chatBox = (EditText) view.findViewById(R.id.chatBox);
*/
        db = dbHelper.getWritableDatabase();
        db2 = dbHelper.getReadableDatabase();

        cursor = db2.query(dbHelper.TABLE_NAME,dbHelper.COLUMNS,null,null,null,null,null);


        // Array Adapter for number of activites
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data
        );

        // Array Adapter for the generic activites
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                activites
        );


        ListView listView = (ListView) view.findViewById(R.id.listViewActivity);
        listView.setAdapter(adapter1);

        // what happens when an item in the listview gets tagged
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, final int position, long arg3)
            {
               final String selectedAct= activites.get(position);
               final String builderTitle = getString(R.string.newMessage_Title) + " " + selectedAct + " " + getString(R.string.Activity);
               //final long id = getItemId(position);

                // making the dialog pop up

                // setting up the inflater
                LayoutInflater inflater = getLayoutInflater();
                LinearLayout rootTag = (LinearLayout)inflater.inflate(R.layout.dialog_message,null);
                final EditText newTitle = (EditText) rootTag.findViewById(R.id.etxtTitle);
                final EditText newMintues = (EditText) rootTag.findViewById(R.id.mintues);
                final EditText newComments = (EditText) rootTag.findViewById(R.id.etxtComments);
                final EditText newDate = (EditText) rootTag.findViewById(R.id.etxtDate);

                // making the alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(builderTitle);

                // Adding the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Create Intent for AnimalListActivity and Start The Activity

                        //adding the content into the database
                        ContentValues values = new ContentValues();
                        values.put(dbHelper.KEY_ACTIVITIES,newTitle.getText().toString());
                        values.put(dbHelper.KEY_MINTUES,newMintues.getText().toString());
                        values.put(dbHelper.KEY_COMMENTS,newComments.getText().toString());
                        values.put(dbHelper.KEY_DATE,newDate.getText().toString());
                        db.insert(dbHelper.TABLE_NAME,null,values);

                        // making the cursors
                        cursor.moveToPosition(position);
                        Long id = cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_ID));


                        Intent intent=new Intent(getActivity(),ShowActivityList.class);
                        intent.putExtra("position",position);
                        intent.putExtra("messageID",id.toString());
                        startActivity(intent);
                    }
                });


                builder.setView(rootTag);

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // making a toast pop up to tell which activity was selected
                        Toast.makeText(getContext(), R.string.actSelect +selectedAct,   Toast.LENGTH_LONG).show();
                        android.os.SystemClock.sleep(300);
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
/*

*/
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

    public long getItemId(int position) {
        cursor.moveToPosition(position);
        long dbID =0;
        //return cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_ID));
        if (cursor.getCount() > position){
            dbID = cursor.getLong(0);
        }
        return dbID;
    }

}
