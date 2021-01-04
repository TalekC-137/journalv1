package com.example.nr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Start_Logging extends AppCompatActivity {
ImageView btn_start,btn_build,btn_follow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__logging);
        btn_start = findViewById(R.id.imageView_start_log);
        btn_build = findViewById(R.id.imageView_buildPlan);
        btn_follow = findViewById(R.id.imageView_follow);




        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_Logging.this, OnTheFly.class);
                startActivity(i);

            }
        });
 /* btn_build.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent i = new Intent (Start_Logging.this, PlanLists.class);
          startActivity(i);
      }
  }); */


    }
}