package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.useraccount.userStorage;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNameLog;
    Button buttonLog;
    userStorage LocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        etNameLog = (EditText) findViewById(R.id.etNameLog);

        buttonLog = (Button)findViewById(R.id.buttonLog);

        buttonLog.setOnClickListener(this);

        LocalStore = new userStorage(this);

    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonLog)
        {
            LocalStore.clearUserData();
            LocalStore.setLogin(false);

            startActivity(new Intent(this, LogInActivity.class));
        }
    }
    }
