package com.example.staceylm.foodnutritionactivity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Exercise extends AppCompatActivity {
    Button button;
    Button click,finishbtn;
    TextView txtv;
    ImageView imgv;
    Dialog myDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    
        button = (Button) findViewById(R.id.btnClick);
    
        button.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View arg0) {
                myDialog = new Dialog(Exercise.this);
                myDialog.setContentView(R.layout.custom_dialog);
                myDialog.setTitle("Exercise is GRREAAAT :)");
                txtv=(TextView)myDialog.findViewById(R.id.tv);
                txtv.setText("Push yourself to be the best version of YOU that you can be! ");
                imgv=(ImageView)myDialog.findViewById(R.id.imgv);
                imgv.setImageResource(R.drawable.barbel);
                finishbtn=(Button)myDialog.findViewById(R.id.btnfinish);
                finishbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.cancel();
                    }
                });
                myDialog.show();
            }
        });
    }
}