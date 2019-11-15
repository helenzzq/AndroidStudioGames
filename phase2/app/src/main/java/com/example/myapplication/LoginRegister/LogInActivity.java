package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class LogInActivity extends AppCompatActivity{


    /*BASED ON: hhttps://www.youtube.com/watch?v=fI9UTA-NaO4
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.etName);
        final EditText Password = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        final TextView tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String password = Password.getText().toString();

                SharedPreferences preferences = getSharedPreferences("USER", MODE_PRIVATE);

                String userDetails = preferences.getString(user + password + "data", "User or password is incorrect");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("MainMenuActivity", userDetails);
                editor.commit();

                Intent displayScreen = new Intent(LogInActivity.this, MainMenuActivity.class);
                startActivity(displayScreen);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(registerScreen);
            }
        });
    }

}
