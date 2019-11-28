package com.example.gamecenter.login;

import android.accounts.AccountsException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.user.DuplicateException;
import com.example.gamecenter.user.NoPassWordException;
import com.example.gamecenter.user.UserManager;


@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {

    private ImageView setting;
    /**
     * A UserManager
     */
    private UserManager users;
    private Handler mHandler;
    private Activity current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        users = MainEntryActivity.userManager;
        setupSignUpListener();

        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

         setting = findViewById(R.id.setting_btn_register);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(RegisterActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });
    }
    private final Runnable mRunnable = new Runnable()
    {
        public void run()

        {   ConstraintLayout layout = findViewById(R.id.register);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            RegisterActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable
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
