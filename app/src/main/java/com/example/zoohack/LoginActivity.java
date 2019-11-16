package com.example.zoohack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {



    private String mLogin;
    private String mPassword;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            setContentView(R.layout.activity_login);
            Button singIn = (Button) findViewById(R.id.signIn); // кнопка авторизации
            EditText login = (EditText) findViewById(R.id.username), password = (EditText) findViewById(R.id.password);

            singIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLogin = ((EditText) findViewById(R.id.username)).getText().toString();
                    mPassword = ((EditText) findViewById(R.id.password)).getText().toString();
                    if ("".equals(mLogin) || "".equals(mPassword)) {
                        Toast.makeText(getApplicationContext(), "Одно из полей не заполненно. Пожалуйста, заполните все поля и повторите отправку", Toast.LENGTH_LONG).show();
                    } else {
                        singInUser();
                    }

                }
            });

            Button singUp = (Button) findViewById(R.id.signUp); // кнопка регистрации
            singUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void singInUser() {
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // sing in

                } else {
                    //sing out
                }
            }
        };

        mAuth.signInWithEmailAndPassword(mLogin, mPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()  {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(), "Авторизация успешна", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    intent.putExtra("PARAM", 1);

                    startActivity(intent);
                    finish();
                    /*Fragment fragment = new Tasks();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);*/

                } else {
                    Toast.makeText(getApplicationContext(), "Авторизация провалена", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}