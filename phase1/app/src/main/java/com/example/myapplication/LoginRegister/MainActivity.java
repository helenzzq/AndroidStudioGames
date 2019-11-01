package com.example.myapplication.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.SecondActivity;
import com.example.myapplication.useraccount.User;
import com.example.myapplication.useraccount.userStorage;

public class MainActivity extends AppCompatActivity{

    /*private EditText Name;
    private EditText Password;
    private Button Login;
    private int counter=3;
    userStorage LocalStore;
    private TextView tvRegiser;
    *
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText Password = findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String password = Password.getText().toString();

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);

                String userDetails = preferences.getString(user+ password + "data","User or password is incorrect");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display",userDetails);
                editor.commit();

                Intent displayScreen = new Intent(MainActivity.this,display.class);
                startActivity(displayScreen);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerScreen);
            }
        });
        //Login.setOnClickListener(this);
        //tvRegiser.setOnClickListener(this);

        //LocalStore = new userStorage(this);

    }

    /*@Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnLogin);
        {
            Name = (EditText) findViewById(R.id.etName);
            Password = findViewById(R.id.etPassword);
            Login = findViewById(R.id.btnLogin);
            tvRegiser = (TextView) findViewById(R.id.tvRegister);

            SharedPreferences preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
             //validate(Name.getText().toString(),Password.getText().toString());

            User user = new User(Name.getText().toString(),Password.getText().toString());

            LocalStore.storeUserData(user);
            LocalStore.setLogin(true);

            if(LocalStore.checkPassowrd(user)){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        }

        if(v.getId() == R.id.tvRegister)
        {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }


    //    private class ;

    /*private void validate(String userName, String userPassword) {
        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        else {

            counter--;

            if (counter==0)
            {
                //disable the button
                Login.setEnabled(false);
            }
        }
*/


}
