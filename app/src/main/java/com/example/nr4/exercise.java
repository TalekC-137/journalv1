package com.example.nr4;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.nr4.OnTheFly;
import com.example.nr4.R;

public class exercise extends AppCompatDialogFragment {
private EditText ExerciseName;
private DialogListener listener;




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dodaj = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.addnew,null);
        dodaj.setView(view)
                .setTitle("Exercise")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
        .setPositiveButton("apply", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        String ExeName = ExerciseName.getText().toString();
        ExeName = ExeName.substring(0,1).toUpperCase()+ExeName.substring(1);

        listener.applyTexts(ExeName);




    }
    });

        ExerciseName = view.findViewById(R.id.edit_new_exercise);
return dodaj.create();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + "dodaj DialogListener ryju");
        }

    }

    public interface  DialogListener{
        void applyTexts(String ExeName);


    }

}
