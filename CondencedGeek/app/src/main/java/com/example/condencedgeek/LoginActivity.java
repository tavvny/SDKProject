package com.example.condencedgeek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseUser;


import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnSignUp;
    EditText name, email, password;
    TextView AccountExists;
    ImageView backbutton;

    FirebaseAuth auth;
    FirebaseDatabase database;
    AlertDialog.Builder ProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        btnSignUp=findViewById(R.id.singUP);
        name = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        AccountExists = findViewById(R.id.lin);
        backbutton = findViewById(R.id.imageView);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        ProgressDialog = new AlertDialog.Builder(LoginActivity.this);
        ProgressDialog.setMessage("Подождите, идёт создание аккаунта");


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.show();

                String edtName = name.getText().toString();
                String edtEmail = email.getText().toString();
                String edtPassword = password.getText().toString();

                auth.createUserWithEmailAndPassword(edtEmail, edtPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                String pochta = edtEmail;
                                editor.putString("pochta", pochta);
                                editor.apply();

                                if (task.isSuccessful()) {
                                    UserModel model = new UserModel(edtName, edtEmail, edtPassword);
                                    FirebaseUser user = task.getResult().getUser();
                                    if (user != null) {
                                        String id = user.getUid();
                                        database.getReference().child("Users").child(id).setValue(model);

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Exception exception = task.getException();
                                    if (exception != null && exception.getMessage() != null && exception.getMessage().contains("email address is already in use")) {
                                        // Почта уже есть в системе
                                        Toast.makeText(LoginActivity.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Handle other errors
                                        Toast.makeText(LoginActivity.this, "Failed to create user: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SingInActivity.class);
                startActivity(intent);
            }



        });

        AccountExists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SingInActivity.class);
                startActivity(intent);
            }
        });

    }


}