package com.example.staceylm.foodnutritionactivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Thermostat extends AppCompatActivity {
    /*
    Declaring the elements of the Temperature UI.
     */
    public EditText editDay;
    public EditText editTime;
    public EditText editTemp;

    public ListView tempList;

    public Button addTempButton;
    public Button weeklyTempButton;
    public Button aboutButton;

    public TemperatureAdapter tempAdapter; // Declaring the ArrayAdapter.
    public ArrayList<String> tempArray; //String ArrayList to hold the values in the database.
    private Temperature_Database dbHelper; //Declaring the Database.
    private Cursor cursor;//Declaring the cursor object for the Database query.

    public long id; // Id variable used to track the bundle message.
    final int requestCodeDetails = 1; //Control request code.
    final int resultCodeDelete = 2;//Delete request code.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        tempArray = new ArrayList<String>(); //ArrayList Initiation.

        /*
        Initiation of the UI elements. Assigned to the specific UI element.
         */
        editDay = (EditText) findViewById(R.id.tempDay);
        editTime = (EditText) findViewById(R.id.tempText);
        editTemp = (EditText) findViewById(R.id.timeText);
        tempList = (ListView) findViewById(R.id.tempList);

        tempAdapter = new TemperatureAdapter( this );//Adapter initiation.
        tempList.setAdapter(tempAdapter);//setting the Adapter to the ListView.

        //Database Instantiation
        dbHelper = new Temperature_Database(this);
        final SQLiteDatabase tempDatabase;
        tempDatabase = dbHelper.getReadableDatabase();

        /*
        Query that queries the Day/Time/Temp/ID for the user created rules
         */

        cursor = tempDatabase.query(false, dbHelper.getTableName(), new String[] {dbHelper.getTempDay(), dbHelper.getTempTemperature(), dbHelper.getTempTime(), dbHelper.getKeyId()},
                null, null , null, null, dbHelper.getTempDay() + " DESC," + dbHelper.getTempTime() + " ASC" , null);
   
        /*
        Creates a String from the DataBaseQuery and adds the String to the ArrayList
         */
        if (cursor.moveToFirst()){
            do {
                String dbInfo = "Day: " + cursor.getString(0) + " Time: " + cursor.getString(1) + " Temperature: " + cursor.getString(2) + "°C";
                tempArray.add(dbInfo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //On Click listener for the ListView.
        tempList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    final Bundle info = new Bundle(); // Bundle for the info to be passed to the Fragment.
                    Intent tempDetailsIntent = new Intent(Thermostat.this, TemperatureDetails.class);
                    String passedMessage = tempAdapter.getItem(position); //Saving ArrayList info as a String.
                    /*
                    Inserting the information into the Bundle.
                     */
                    info.putInt("position", position);
                    info.putLong("id", id);
                    info.putString("Message", passedMessage);
                    tempDetailsIntent.putExtras(info);
                    startActivityForResult(tempDetailsIntent, requestCodeDetails);

            }
        });

        addTempButton = (Button) findViewById(R.id.addTempButton);

        addTempButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { //On Click Listener for the Add Button.
                Log.i("AddTemp", "User clicked Add temperature");
                /*
                Gets the text that is in the EditText fields and saves it into a string.
                 */
                String timeInput = editTime.getText().toString().trim();
                String tempInput = editTemp.getText().toString().trim();
                String dayInput = editDay.getText().toString().trim();

                /*
                Adds these values into the appropriate columns in the table.
                 */
                ContentValues tempValues = new ContentValues();
                tempValues.put(Temperature_Database.TEMP_TIME, timeInput);
                tempValues.put(Temperature_Database.TEMP_TEMPERATURE, tempInput);
                tempValues.put(Temperature_Database.getTempDay(), dayInput);
                tempDatabase.insert(Temperature_Database.TABLE_NAME, null, tempValues);
                tempAdapter.notifyDataSetChanged();
                editTime.setText("");
                editTemp.setText("");
                editDay.setText("");

                /*
                Snackbar notification about adding the the userinput to the DataBase
                 */
                View snackView = findViewById(R.id.thermostat_main_id);
                String message = "You have successfully added a temperature rule";
                int duration = Snackbar.LENGTH_SHORT;
                showSnackbar(snackView, message, duration);

            }

        });

        weeklyTempButton = (Button) findViewById(R.id.weeklyTempButton);
        //On Click Listener for the weekly temperature button.
        weeklyTempButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("WeeklyWeather", "User clicked Weekly Weather");
                Intent weeklyIntent = new Intent(getApplicationContext(), WeeklyTemp.class);
                startActivityForResult(weeklyIntent, 10);
            }

        });

        aboutButton = (Button) findViewById(R.id.aboutButton);
        //On Click Listener for the About Information button
        aboutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("AboutGuide", "User clicked About Button");
                Intent aboutIntent = new Intent(getApplicationContext(), about_temperature.class);
                startActivityForResult(aboutIntent, 10);
            }

        });

    }

    /*
    The activity result for when an entry is deleted from the database.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCodeDetails && resultCode == resultCodeDelete) {
            //Toast notification once an entry is deleted
            Toast toast = Toast.makeText(this, "The Temperature rule has been deleted!",
                    Toast.LENGTH_LONG);
            long id = data.getLongExtra("id", 0);
            int position = data.getIntExtra("position", 0);
            dbHelper.deleteMessage((int) id);
            tempArray.remove(position);
            tempAdapter.notifyDataSetChanged();
            toast.show();
        }
    }

    //Method to show shackbars
    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).show();
    }

    /*
    Array adapter to manage the ArrayList.
     */
    private class TemperatureAdapter extends ArrayAdapter<String> {

        public TemperatureAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount(){
            return tempArray.size();
        }

        @Override
        public String getItem(int position){
            return tempArray.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = Thermostat.this.getLayoutInflater();
            View result;

            result = inflater.inflate(R.layout.temp_listview, null);
            TextView temperatureDetails = (TextView)result.findViewById(R.id.tempListDetails);

            temperatureDetails.setText(getItem(position));

            return result;
        }
    }
}
