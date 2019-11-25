package com.example.myapplication.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.R;
import com.example.myapplication.SettingsActivity;
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

    private ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        users = EntryMainActivity.userManager;
        setupSignInListener();

        current = this;

        this.mHandler = new Handler();
        this.mRunnable.run();


        setting = findViewById(R.id.setting_btn_log);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(LoginActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });

    }
    private final Runnable mRunnable = new Runnable()
    {
        public void run()
        {   ConstraintLayout layout = findViewById(R.id.loginPage);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            LoginActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable

    private Handler mHandler;
    private Activity current;


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
