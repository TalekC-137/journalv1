package com.example.nr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {
    Switch metric;
    TextView elo;
    Button apply;
    String TextS = "Metric";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        metric = findViewById(R.id.sw_metric);
        elo = findViewById(R.id.elo);
        apply = findViewById(R.id.btn_apply);



     final SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
     metric.setChecked(sharedPreferences.getBoolean("value",true));





        metric.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    metric.setChecked(true);
                    elo.setText("metric");






                }else{
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    metric.setChecked(false);
                    elo.setText("imperial");





                }
            }
        });





    }





}
