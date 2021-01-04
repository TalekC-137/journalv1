package com.example.nr4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.BufferUnderflowException;


public class bmi extends AppCompatActivity {
    EditText weight, height,height_ft, height_inch;
    ImageView lbs, kg, celownik;
    TextView BMI_Value;
    Button btn_bmi_show;
   Switch ToImperial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        BMI_Value = findViewById(R.id.TextView_bmi);
        btn_bmi_show = findViewById(R.id.btn_bmi_show);
        weight = findViewById(R.id.EditText_weight);
        height = findViewById(R.id.EditText_height);
        ToImperial = findViewById(R.id.sw_ToImperial);
        height_ft = findViewById(R.id.et_ft);
        height_inch = findViewById(R.id.et_inch);
        lbs = findViewById(R.id.iv_bmi_lbs);
        kg = findViewById(R.id.iv_bmi_kg);
        celownik =findViewById(R.id.iv_celownik);

        kg.setVisibility(View.GONE);
        lbs.setVisibility(View.GONE);
        celownik.setVisibility(View.GONE);



        final SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        ToImperial.setChecked(sharedPreferences.getBoolean("value",true));

        if(weight ==null || height == null){
            Toast.makeText(bmi.this,"error",Toast.LENGTH_LONG).show();
        }
//jest w imperialnym
        if (ToImperial.isChecked()){
            ToImperial.setChecked(true);
            ToImperial.setText("Switch to Metric   ");
            weight.setHint("weight in lbs");
            height.setVisibility(View.GONE);
            height_ft.setVisibility(View.VISIBLE);
            height_inch.setVisibility(View.VISIBLE);
            height_ft.setHint("ft");
            height_inch.setHint("inches");


        }

        ToImperial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    //jesli jest w imperialnym
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    ToImperial.setChecked(true);
                    ToImperial.setText("Switch to Metric   ");
                    weight.setHint("weight in lbs");
                    height.setVisibility(View.GONE);
                    height_ft.setVisibility(View.VISIBLE);
                    height_inch.setVisibility(View.VISIBLE);
                    height_ft.setHint("ft");
                    height_inch.setHint("inches");






                }else{
                    SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    ToImperial.setChecked(false);
                   ToImperial.setText("Switch to Imperial");
                   weight.setHint("Weight in kg");
                    height.setVisibility(View.VISIBLE);
                    height_ft.setVisibility(View.GONE);
                    height_inch.setVisibility(View.GONE);

     }
            }
        });


        btn_bmi_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                celownik.setVisibility(View.VISIBLE);

                if (ToImperial.isChecked()) {
                            //funty



                            lbs.setVisibility(View.VISIBLE);
                            kg.setVisibility(View.GONE);
                            Double weightValue = Double.parseDouble(weight.getText().toString());
                            Double heightValue = Double.parseDouble(height_ft.getText().toString());
                           Double heightValueInch = Double.parseDouble(height_inch.getText().toString());
                            Double totalInches = heightValue * 12 + heightValueInch;

                            Double Bmi_Index = weightValue / (totalInches * totalInches) * 703;
                            Double rounded = new BigDecimal(Bmi_Index).setScale(2, RoundingMode.HALF_UP).doubleValue();
                            BMI_Value.setText(rounded.toString());

                            float y = celownik.getY();
                    float heightVal_ft = Float.valueOf(height_ft.getText().toString());
                    float heightVal_inch = Float.valueOf(height_inch.getText().toString());
                   float total_in_cm = (heightVal_ft *12 + heightVal_inch)*2.54f;
                   y  = (y + total_in_cm - 140) * -10f;
              //     celownik.setY(y);
                    float x = celownik.getX();
                    float weightVal = Float.valueOf(weight.getText().toString());
                    float weightVal_kg = weightVal/2.2f;
                    x = (x + weightVal_kg - 40) * 9.5f;
               //     celownik.setX(x);

                    //120 cali

                } else {
                    //kg

                    float y = celownik.getY();
                    float heightVal = Float.valueOf(height.getText().toString());
                    y = (y + heightVal - 140) * -10;
                    celownik.setY(y);
                    float x = celownik.getX();
                    float weightVal = Float.valueOf(weight.getText().toString());

                  x = (x + weightVal - 40) * 9.5f;
             //     celownik.setX(x);


                    lbs.setVisibility(View.GONE);
                    kg.setVisibility(View.VISIBLE);

                    Double weightValue = Double.parseDouble(weight.getText().toString());
                    Double heightValue = Double.parseDouble(height.getText().toString());

                    Double Bmi_Index = weightValue / (heightValue * heightValue) * 10000;
                    Double rounded = new BigDecimal(Bmi_Index).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    BMI_Value.setText(rounded.toString());
                }
                }catch (Exception e)
                    { celownik.setVisibility(View.GONE);

                    if(height_inch.getText().toString().length() ==0){
                   //kod który jest zbyt skomplikowany niż powonien być ale jestem nie wypany i jest taki bo tak.

                        lbs.setVisibility(View.VISIBLE);
                        kg.setVisibility(View.GONE);
                        Double weightValue = Double.parseDouble(weight.getText().toString());
                        Double heightValue = Double.parseDouble(height_ft.getText().toString());
                        Double heightValueInch = 0.0;
                        Double totalInches = heightValue * 12 + heightValueInch;

                        Double Bmi_Index = weightValue / (totalInches * totalInches) * 703;
                        Double rounded = new BigDecimal(Bmi_Index).setScale(2, RoundingMode.HALF_UP).doubleValue();
                        BMI_Value.setText(rounded.toString());

                    }else
                        Toast.makeText(bmi.this,"put in your measurements",Toast.LENGTH_LONG).show();
                }
       }
        });

    }


}