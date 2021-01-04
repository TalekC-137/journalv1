package com.example.nr4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.ULocale;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class OnTheFly extends AppCompatActivity  implements exercise.DialogListener {
Button btn_add_w, btn_poka, btn_add_new,cancel, dbv;
Button btn_subtract_w, btn_saveprogress;
Button btn_subtract_r;
Button btn_add_r;
TextView tv_weight_num, tv_reps_num;
Integer weight = 50;
Integer reps = 0;
ListView SetsReps;

AutoCompleteTextView actv;
FloatingActionButton AB_add;
ConstraintLayout dodawanie;
    DataBasaHelper dataBasaHelper;
    ArrayList<String> Exercises = new ArrayList<>();


ArrayList<String> ListViewPlan = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_the_fly);
        actv = findViewById(R.id.actv);



        dataBasaHelper = new DataBasaHelper(OnTheFly.this);
   //     Spinner spinner = findViewById(R.id.spin_log);
        // wybieranie cwicznia
        arrayAdapter = new ArrayAdapter<>(OnTheFly.this, android.R.layout.simple_spinner_dropdown_item, dataBasaHelper.getExercise());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actv.setAdapter(arrayAdapter);

      /*  spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/
AB_add = findViewById(R.id.Action_add_exe);
cancel = findViewById(R.id.btn_cancel);
        btn_saveprogress = findViewById(R.id.btn_saveprogress);
        btn_add_w = findViewById(R.id.btn_add_w);
        btn_subtract_w = findViewById(R.id.btn_subtract_w);
        btn_add_r = findViewById(R.id.btn_add_r);
        btn_subtract_r = findViewById(R.id.btn_subtract_r);
        tv_weight_num = findViewById(R.id.tv_weight_num);
        tv_reps_num = findViewById(R.id.tv_reps_num);
        btn_add_new = findViewById(R.id.btn_add_new);
        dbv = findViewById(R.id.btn_db);
           /* AutoCompleteTextView Select = findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Exercises);
        Select.setAdapter(adapter);*/

        dodawanie = findViewById(R.id.dodawanie);
        btn_poka = findViewById(R.id.btn_poka);
        SetsReps = findViewById(R.id.lv_setsReps);
dodawanie.setVisibility(View.GONE);
        btn_saveprogress.setVisibility(View.GONE);
        tv_weight_num.setText(weight.toString());
        tv_reps_num.setText(reps.toString());

       btn_add_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight +=2;
                tv_weight_num.setText(weight.toString());
            }
        });
AB_add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dodawanie.setVisibility(View.VISIBLE);
        AB_add.setVisibility(View.GONE);
    }
});

dbv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(OnTheFly.this, DBviewer.class);
        startActivity(i);
    }
});

btn_subtract_w.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        weight -=2;
        if(weight>=0) {
            tv_weight_num.setText(weight.toString());
        }else{
            tv_weight_num.setText("0");
        }
    }
});


btn_add_r.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        reps += 1;
        tv_reps_num.setText(reps.toString());
    }
});

btn_subtract_r.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        reps-=1;
        if(reps>=0) {
            tv_reps_num.setText(reps.toString());
        }else{

            tv_reps_num.setText("0");
        }

        }



});

actv.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        actv.showDropDown();
        return false;
    }
});

btn_add_new.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openAdding();

    }
});
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dodawanie.setVisibility(View.GONE);
        AB_add.setVisibility(View.VISIBLE);

    }
});

btn_poka.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String text = actv.getText().toString();
            if(text.length() != 0) {

                ListViewPlan.add(text + " reps: " + reps.toString() + " Weight: " + weight.toString());
                arrayAdapter = new ArrayAdapter(OnTheFly.this, R.layout.white, ListViewPlan);
                SetsReps.setAdapter(arrayAdapter);


                dodawanie.setVisibility(View.GONE);
                AB_add.setVisibility(View.VISIBLE);
                if(!arrayAdapter.isEmpty()){

                    btn_saveprogress.setVisibility(View.VISIBLE);
                }
            }else{

                Toast.makeText(OnTheFly.this, "select an exercise from the list", Toast.LENGTH_SHORT).show();
            }
    }
});
        SetsReps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OnTheFly.this,"clicked Item = " + ListViewPlan.get(position) +
                        " reps: " + reps.toString()+ " Weight: "+ weight.toString(),Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void openAdding(){
  exercise exer = new exercise();
  exer.show(getSupportFragmentManager(),  "Exercise");
    }

    @Override
    public void applyTexts(String ExeName) {
        ExerciseModel exerciseModel;
         try {
            exerciseModel = new ExerciseModel(-1, ExeName);

             Toast.makeText(this, exerciseModel.toString(), Toast.LENGTH_SHORT).show();
         }catch (Exception e){
             Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
             exerciseModel = new ExerciseModel(-1, "error");

         }

        DataBasaHelper dataBasaHelper = new DataBasaHelper(this);

        boolean success = dataBasaHelper.addOne(exerciseModel);
        Toast.makeText(this, "success= " + success, Toast.LENGTH_SHORT).show();


    }





// To jest usunięta wersja ze spinnerrem zamiast AutoCOmpleteTextView
 /*   @Override
    public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
        final String text = parent.getItemAtPosition(position).toString();


        btn_poka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneSet.add(text + " " + reps.toString());
                arrayAdapter = new ArrayAdapter(OnTheFly.this,android.R.layout.simple_list_item_1,OneSet);
                SetsReps.setAdapter(arrayAdapter);
            }
        });




        SetsReps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OnTheFly.this,"clicked Item = " + OneSet.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/


}
