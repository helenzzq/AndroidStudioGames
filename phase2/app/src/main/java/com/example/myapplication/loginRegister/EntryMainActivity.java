package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.BackGroundSetting;
import com.example.myapplication.R;
import com.example.myapplication.useraccount.UserManager;
import com.example.myapplication.useraccount.UserFileSaver;

public class EntryMainActivity extends AppCompatActivity{
    public static UserManager userManager;


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

        //get the SharedPreference object
        SharedPreferences sharedPref = getSharedPreferences("switch", Context.MODE_PRIVATE);
        String on = sharedPref.getString("on", "");
        ConstraintLayout layout = findViewById(R.id.entryMainPage);

        //Set the BackGround
        BackGroundSetting backGroundSetting = new BackGroundSetting();
        backGroundSetting.setWallPaper(new TextView[0],
                this, layout, on);

    }

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
            Intent tmp = new Intent(this, CreateAccountActivity.class);
            startActivity(tmp);
        });

    }


}
