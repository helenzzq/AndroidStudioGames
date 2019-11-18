package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountsException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.useraccount.UserManager;
import com.example.myapplication.useraccount.NoPassWordException;
import com.example.myapplication.useraccount.DuplicateException;

public class CreateAccountActivity extends AppCompatActivity {

    /**
     * A UserManager
     */
    private UserManager users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        users = FirstActivity.userManager;
        setupSignUpListener();
    }

    /**
     * Activate the signUp button.
     */
    @SuppressLint("SetTextI18n")
    public void setupSignUpListener() {
        Button signUp = findViewById(R.id.btnNewRegister);
        signUp.setOnClickListener((v) -> {
            String userName = ((EditText) findViewById(R.id.etNewName)).getText().toString();
            String passWord = ((EditText) findViewById(R.id.etNewPassword)).getText().toString();
            TextView textBox = findViewById(R.id.etTextBox);
            try {
                users.signUp(userName, passWord);
            } catch (DuplicateException e) {
                textBox.setText("This username has been registered.");
                return;
            } catch (AccountsException e) {
                textBox.setText("Please fill in your username.");
                return;
            } catch (NoPassWordException e) {
                textBox.setText("Please fill in your password.");
                return;
            }
            textBox.setText("Sign up Successfully!");
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);
        });

    }
}
