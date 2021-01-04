package com.example.nr4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Logowanie extends AppCompatActivity {
ImageView iv_avatar;
Button btn_logOut;
TextView tv_name, tv_email, tv_id;
GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        iv_avatar = findViewById(R.id.iv_avatar);
        btn_logOut = findViewById(R.id.btn_logOut);
        tv_name = findViewById(R.id.tV_name);
        tv_email = findViewById(R.id.tV_email);
        tv_id  =findViewById(R.id.tV_id);



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            tv_name.setText(personName);
            tv_email.setText(personEmail);
            tv_id.setText(personId);

            btn_logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {

                        case R.id.btn_logOut:
                            signOut();
                            break;

                    }
                }
            });


            Glide.with(this).load(String.valueOf(personPhoto)).into(iv_avatar);
        }

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Logowanie.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                            finish();
                    }
                });
    }



}