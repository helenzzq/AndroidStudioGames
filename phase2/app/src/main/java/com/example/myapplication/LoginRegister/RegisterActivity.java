package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

public class RegisterActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText userName = (EditText) findViewById(R.id.etNewName);
        final EditText password = (EditText) findViewById(R.id.etNewPassword);
        Button btnRegister = (Button) findViewById(R.id.btnNewRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(newUser + newPassword + "data",newUser);
                editor.commit();

                Intent loginScreen = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(loginScreen);
            }
        });
    }
    /*Button buttonReg;
    EditText etNameReg;
    EditText etPasswordReg;
    userStorage LocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNameReg = (EditText) findViewById(R.id.etNewName);
        etPasswordReg = (EditText) findViewById(R.id.etNewPassword);
        Button buttonReg = (Button) findViewById(R.id.btnNewRegister);

        final String username = etNameReg.getText().toString();
        final String password = etPasswordReg.getText().toString();

        LocalStore = new userStorage(this);


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalStore.storeUserData(new User(username,password));

                startActivity(new Intent(RegisterActivity.this, LogInActivity.class));

            }
        });

    }




    /*public void onClick(View v) {

        if(v.getId() == R.id.tvRegister)
        {
            String username = etNameReg.getText().toString();
            String password = etPasswordReg.getText().toString();

            User user = new User(username,password);
            startActivity(new Intent(this, RegisterActivity.class));
        }

    }*/
}
