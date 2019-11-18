package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.useraccount.User;
import com.example.myapplication.useraccount.UserManager;

public class LoginActivity extends AppCompatActivity{


    /**
     * A UserManager.
     */
    private UserManager users;

    /**
     * The current player of the game.
     */
    public static User currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users = FirstActivity.userManager;
        setupSignInListener();
    }

    /**
     *  Activate the signIn button.
     */
    @SuppressLint("SetTextI18n")
    public void setupSignInListener() {
        Button signIn = findViewById(R.id.btnLogin);
        signIn.setOnClickListener((v) -> {
            String userName = ((EditText)findViewById(R.id.etName)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.etPassword)).getText().toString();
            TextView textBox = findViewById(R.id.etTextBox);
            try {
                currentPlayer = users.signIn(userName, passWord);
            }
            catch (AuthenticatorException e){
                textBox.setText("Incorrect Password.");
                return;
            }
            catch (AccountsException e) {
                textBox.setText("Username is not found.");
                return;
            }
            textBox.setText("Sign In Successfully!");
            Intent tmp = new Intent(this, MainMenuActivity.class);
            startActivity(tmp);
        });
    }


}
