package com.example.staceylm.foodnutritionactivity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class Temperature_Rules extends Activity {

    ListView listView;
    EditText updateText;
    Button updateButton;
    final ArrayList<String> updateMessage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature__rules);

        final UpdateDataBaseHelper storedMessages = new UpdateDataBaseHelper(this);
        final SQLiteDatabase db = storedMessages.getWritableDatabase();

        Cursor results = db.query(false, storedMessages.getName(), new String[] {storedMessages.getKeyMessage()},
                null, null , null, null, null, null);

        if (results.moveToFirst()){
            do {
                updateMessage.add(results.getString(0));
            } while (results.moveToNext());
        }

        listView = findViewById(R.id.updateList);
        updateText = findViewById(R.id.updateText);
        updateButton = findViewById(R.id.updateButton);
        final ChatAdapter messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() //how many items to display
        {
            return updateMessage.size();
        }

        public String getItem(int position) {
            return updateMessage.get(position);
        }

        /*public View getView(int position, View convertView, ViewGroup parent)
        {

            LayoutInflater inflater = Temperature_Rules.this.getLayoutInflater();
            View result;

            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);


            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }
        */
    }

    public void onDestroy() {

        super.onDestroy();

    }
}
