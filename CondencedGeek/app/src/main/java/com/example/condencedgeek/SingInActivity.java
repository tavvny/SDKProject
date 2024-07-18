package com.example.condencedgeek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SingInActivity extends AppCompatActivity {

    Button button;
    EditText email, password;
    ImageView backbtn;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        button = findViewById(R.id.btnlgin);
        email = findViewById(R.id.emali);
        password = findViewById(R.id.pasword);
        backbtn = findViewById(R.id.imageView4);




        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtEmail = email.getText().toString();
                String edtPass = password.getText().toString();

                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String pochta = edtEmail;
                editor.putString("pochta", pochta);
                editor.apply();



                auth.signInWithEmailAndPassword(edtEmail, edtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {



                            Intent intent = new Intent(SingInActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SingInActivity.this, "Sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingInActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}