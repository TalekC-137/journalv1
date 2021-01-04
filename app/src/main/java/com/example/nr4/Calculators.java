package com.example.nr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calculators extends AppCompatActivity {
Button btn_bmi;
Button btn_1rp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculators);
        btn_bmi = findViewById(R.id.btn_BMI_1);
        btn_1rp = findViewById(R.id.btn_1rp);



        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calculators.this, bmi.class);
                startActivity(i);
            }
        });
  btn_1rp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Calculators.this, OneRep.class);
        startActivity(i);
      }
  });


    }
}