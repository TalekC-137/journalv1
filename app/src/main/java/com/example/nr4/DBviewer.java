package com.example.nr4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;

public class DBviewer extends AppCompatActivity {

ListView lv_exer_list;
SearchView sv_exercises;
    ArrayAdapter exerciseArrayAdapter;
    DataBasaHelper dataBasaHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_bviewer);
       dataBasaHelper = new DataBasaHelper(DBviewer.this);
    sv_exercises = findViewById(R.id.sv_exercies);
   lv_exer_list = findViewById(R.id.lv_db_exercises);
        exerciseArrayAdapter = new ArrayAdapter<ExerciseModel>(DBviewer.this, R.layout.white,  dataBasaHelper.getExercise());
        lv_exer_list.setAdapter(exerciseArrayAdapter);


  /* lv_exer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           ExerciseModel clickedExer = (ExerciseModel) parent.getItemAtPosition(position);
           dataBasaHelper.deleteOne(clickedExer);
           exerciseArrayAdapter = new ArrayAdapter<ExerciseModel>(DBviewer.this, android.R.layout.simple_list_item_1, dataBasaHelper.getExercise());
           lv_exer_list.setAdapter(exerciseArrayAdapter);
           Toast.makeText(DBviewer.this, "exercise: " + clickedExer + " deleted", Toast.LENGTH_SHORT).show();
       }
   }); */

   lv_exer_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
       public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

           ExerciseModel clickedExer = (ExerciseModel) parent.getItemAtPosition(position);
           dataBasaHelper.deleteOne(clickedExer);
           exerciseArrayAdapter = new ArrayAdapter<ExerciseModel>(DBviewer.this, R.layout.white, dataBasaHelper.getExercise());
           lv_exer_list.setAdapter(exerciseArrayAdapter);
           if (position == 0 || position == 1 || position == 2 || position == 3 || position == 4 || position == 5 ){
               Toast.makeText(DBviewer.this, "You can't delete " + clickedExer, Toast.LENGTH_LONG).show();
           }
                else {

               Toast.makeText(DBviewer.this, "exercise: " + clickedExer + " deleted", Toast.LENGTH_SHORT).show();
           }
           return false;
       }
   });

   sv_exercises.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String query) {

         DBviewer.this.exerciseArrayAdapter.getFilter().filter(query);

           return false;
       }

       @Override
       public boolean onQueryTextChange(String newText) {
           DBviewer.this.exerciseArrayAdapter.getFilter().filter(newText);
          return false;
       }
   });


    }
}