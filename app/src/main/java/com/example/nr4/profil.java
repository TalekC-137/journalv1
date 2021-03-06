package com.example.nr4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class profil extends AppCompatActivity {

ImageView Settings,menu, history, PRs;
Button btn_login, out;
TextView mail, name;
ImageView avatar;
View kreska;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        kreska = findViewById(R.id.kreska);
        history = findViewById(R.id.imageView_history);
        PRs = findViewById(R.id.imageView_PRs);
        Settings = findViewById(R.id.iv_settings);
        menu = findViewById(R.id.img_menu);
        btn_login = findViewById(R.id.btn_login);
        mail = findViewById(R.id.tv_email_p);
        name = findViewById(R.id.tv_name_p);
        avatar = findViewById(R.id.iv_avatar_p);
        out = findViewById(R.id.btn_logout_p);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId(); //ID nie użyte ale przyda się na później
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            mail.setText(personEmail);
            out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {

                        case R.id.btn_logout_p:
                            signOut();
                            break;

                    }
                }
            });

            Glide.with(this).load(String.valueOf(personPhoto)).into(avatar);
        }
        //sprawdzdanie czy użytkownik jest zalogowany
        //jest nie jest
        if(mail.getText().toString().isEmpty()){
            avatar.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            mail.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
            out.setVisibility(View.GONE);
            kreska.setVisibility(View.GONE);
        }else{
            //jesli jest zalogowany

            avatar.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);
            out.setVisibility(View.VISIBLE);
            kreska.setVisibility(View.VISIBLE);
        }


       history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        PRs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profil.this, "it works!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(profil.this, Login.class);
                startActivity(i);
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(profil.this, Settings.class);
                try {

    startActivity(i);
} catch (Exception e){
    Toast.makeText(profil.this, "error",Toast.LENGTH_LONG).show();
}


            }
        });

menu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(profil.this, MainActivity.class);
        startActivity(i);
    }
});
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(profil.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }



}