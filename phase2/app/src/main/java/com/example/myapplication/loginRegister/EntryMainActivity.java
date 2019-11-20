package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.BackGroundSetter;
import com.example.myapplication.R;
import com.example.myapplication.SavePrincessActivity;
import com.example.myapplication.SettingsActivity;
import com.example.myapplication.useraccount.UserManager;
import com.example.myapplication.useraccount.UserFileSaver;

public class EntryMainActivity extends AppCompatActivity{
    public static UserManager userManager;

    private Handler mHandler;
    private Activity current;
    /*BASED ON: hhttps://www.youtube.com/watch?v=fI9UTA-NaO4
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrymain);
        setupSignInListener();
        setupSignUpListener();
        userManager = new UserManager();
        UserFileSaver fileSaver = new UserFileSaver(this);
        userManager.register(fileSaver);
        userManager.setAllUsers(fileSaver.getAllUsers());

        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

        ImageView setting = findViewById(R.id.setting_btn_entry);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(EntryMainActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });

    }

    private final Runnable mRunnable = new Runnable()
    {
        public void run()

        {   ConstraintLayout layout = findViewById(R.id.entryMainPage);
            BackGroundSetter.setWallPaper(new TextView[0],current, layout);
            EntryMainActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable

    /**
     * Activate the signIn button.
     */
    public void setupSignInListener(){
        Button signIn = findViewById(R.id.SignIn);
        signIn.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);
        });

    }

    /**
     * Activate the signUp button.
     */
    public void setupSignUpListener(){
        Button signUp = findViewById(R.id.SignUp);
        signUp.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });

    }


}
