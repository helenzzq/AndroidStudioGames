package com.example.myapplication.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonReg;
    EditText etNameReg;
    EditText etPasswordReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNameReg = (EditText) findViewById(R.id.etNameReg);
        etPasswordReg = (EditText) findViewById(R.id.etPasswordReg);

    }

    @Override
    public void onClick(View v) {

    }
}
