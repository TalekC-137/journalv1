package com.example.nr4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.invoke.MethodHandleInfo;
import java.util.ArrayList;
import java.util.List;

public class DataBasaHelper extends SQLiteOpenHelper {

     public static final String EXERCISE_TABLE = "EXERCISE_TABLE";
    public static final String COLUMN_EXERCISE_NAME = "EXERCISE_NAME";
    private static final String COLUMN_ID = "ID";

    public DataBasaHelper(@Nullable Context context) {
        super(context, "exercise.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String createTableStatement = "CREATE TABLE " + EXERCISE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_EXERCISE_NAME + " TEXT )";
            db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ExerciseModel exerciseModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EXERCISE_NAME, exerciseModel.getName());


       long insert =  db.insert(EXERCISE_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }


    public boolean deleteOne(ExerciseModel exerciseModel){
//usuwanie cwiczenia z bazy danych
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString  = "DELETE FROM " + EXERCISE_TABLE + " WHERE " + COLUMN_ID + " = " + exerciseModel.getId();
       Cursor cursor=  db.rawQuery(queryString, null);

       if(cursor.moveToFirst()){
           return true;

       }else {
           return false;
       }
    }



public List<ExerciseModel> getExercise(){
        List<ExerciseModel> returnList = new ArrayList<>();
        //pobierz z bazy danych
        String queryString = "SELECT * FROM " + EXERCISE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();




       Cursor cursor = db.rawQuery(queryString, null);



        ExerciseModel bench = new ExerciseModel( 1 , "Bench Press");
        returnList.add(bench);

    ExerciseModel Deadlift = new ExerciseModel( 2 , "Dead Lift");
    returnList.add(Deadlift);

    ExerciseModel squat = new ExerciseModel( 3 , "Squat");
    returnList.add(squat);

    ExerciseModel pull = new ExerciseModel( 4 , "Pull-Ups");
    returnList.add(pull);

    ExerciseModel dip = new ExerciseModel( 5 , "Dips");
    returnList.add(dip);

    ExerciseModel push = new ExerciseModel( 6 , "Push-Ups");
    returnList.add(push);




  if (cursor.moveToFirst()) {
        do {
            //dodaje nowy element do arraya / bazy danych ćwiczeń
            int exerciseID = cursor.getInt(0);
            String exerciseName = cursor.getString(1);

            ExerciseModel newExercise = new ExerciseModel(exerciseID, exerciseName);
            returnList.add(newExercise);



        } while (cursor.moveToNext());
    } else {
        //nic sie nie dodaje

    }

       cursor.close();
       db.close();

    return returnList;
    }


}
