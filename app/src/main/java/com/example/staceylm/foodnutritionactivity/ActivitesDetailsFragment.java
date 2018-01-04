package com.example.staceylm.foodnutritionactivity;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ActivitesDetailsFragment extends android.app.Fragment {

    ChatDatabaseHelper dbHelper;
    SQLiteDatabase db;
    SQLiteDatabase db2;
    Cursor cursor;
    ArrayList<String> data;
    ArrayList<String> mins;
    ArrayList<String> comments;
    ArrayList<Date> dates;
    Context context;
    EditText newTitle;
    EditText newMintues;
    EditText newComments;
    EditText newDate;

    TextView txtTitle;
    TextView txtMins;
    TextView txtComments;
    CalendarView vDate;
    Date defaultDate = new Date();
    int listPostion;
    public ActivitesDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activites_details, container, false);

        data = new ArrayList<String>();
        mins = new ArrayList<String>();
        comments = new ArrayList<String>();
        dates =new ArrayList<Date>();

        //setting up Database
        dbHelper = new ChatDatabaseHelper(getActivity());
        db = dbHelper.getWritableDatabase();
        db2 = dbHelper.getReadableDatabase();

        // setting up textfields
        txtTitle = (TextView) view.findViewById(R.id.txtActTitle);
        txtMins = (TextView) view.findViewById(R.id.txtActMins);
        txtComments = (TextView) view.findViewById(R.id.txtActComments);
        vDate = (CalendarView) view.findViewById(R.id.calendarView);
        // setting up the two buttons
        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);
        Button btnDelete = (Button) view.findViewById(R.id.btnDelete);

        // Putting the correct values into serparte Array Lists for

        cursor = db2.query(dbHelper.TABLE_NAME,dbHelper.COLUMNS,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGES)));
                data.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_ACTIVITIES)));
                mins.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MINTUES)));
                comments.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_COMMENTS)));

                try {
                    dates.add(new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_DATE))));
                } catch (ParseException e){
                    dates.add(defaultDate);
                }
                cursor.moveToNext();
            }
        }

        listPostion = getArguments().getInt("position");
        final String title = data.get(listPostion);
        final String mintues = mins.get(listPostion);
        final String strComments = comments.get(listPostion);
        final long date = dates.get(listPostion).getTime();

        // putting the correct values into the txt fields
        writeText(title,mintues,strComments,date);
        // button events

        // Edit button
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view){
                final String selectedAct= "TBD";
                final String builderTitle = getString(R.string.newMessage_Title) + " " + selectedAct + " " + getString(R.string.Activity);

                Log.i("TESTING THE BUILDERTITL",builderTitle);
                // making the dialog pop up

                // setting up the inflater
                context = getContext();
               LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

                LinearLayout rootTag = (LinearLayout)inflater.inflate(R.layout.dialog_message,null);
                newTitle = (EditText) rootTag.findViewById(R.id.etxtTitle);
                newMintues = (EditText) rootTag.findViewById(R.id.mintues);
                newComments = (EditText) rootTag.findViewById(R.id.etxtComments);
                newDate = (EditText) rootTag.findViewById(R.id.etxtDate);


                // setting up the edit texts
                newTitle.setText(title);
                newMintues.setText(mintues);
                newComments.setText(strComments);
                newDate.setText("");
                   // writeText(title,mintues,strComments);
                // making the alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(builderTitle);

                // Adding the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String newT = newTitle.getText().toString();
                        String newM = newMintues.getText().toString();
                        String newC = newComments.getText().toString();
                        Date dateNewD;
                        try {
                            dateNewD = (new SimpleDateFormat("dd/MM/yyyy").parse(newDate.getText().toString()));
                        } catch (ParseException e){
                            dateNewD = defaultDate;
                        }

                        long newD = dateNewD.getTime();
                        String id = getArguments().getString("messageID");
                        Log.i("Editing ID",id);
                        ContentValues values = new ContentValues();
                        values.put(dbHelper.KEY_ACTIVITIES,newT);
                        values.put(dbHelper.KEY_MINTUES,newM);
                        values.put(dbHelper.KEY_COMMENTS,newC);
                        values.put(dbHelper.KEY_DATE,newDate.getText().toString());
                       // Log.i("Testing Database",getArguments().getString("messageID"));
                        db.update(dbHelper.TABLE_NAME,values,"id=" + id,null);

                        writeText(newT,newM,newC,newD);
                        startTrackingAct();
                    }
                });

                builder.setView(rootTag);

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // making a toast pop up to tell which activity was selected
                        Toast.makeText(getContext(), "Edit was canceled",   Toast.LENGTH_LONG).show();
                        android.os.SystemClock.sleep(300);
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = getArguments().getString("messageID");
                Log.i("Delete ID", id);
                dbHelper.deleteItem(id);
                startTrackingAct();

            }
        });
        return view;
    }

    public void writeText(String title,String mins,String comments,long date) {
        txtTitle.setText(title);
        txtMins.setText(mins);
        txtComments.setText(comments);
        vDate.setDate(date);

    }

    public void startTrackingAct() {
        Intent intent=new Intent(getActivity(),TrackingActivity.class);
        intent.putExtra("position",listPostion);
        startActivity(intent);
    }
}
