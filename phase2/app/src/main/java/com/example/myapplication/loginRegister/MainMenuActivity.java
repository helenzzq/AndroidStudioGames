package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.catchball.CatchBallMenu;
import com.example.myapplication.R;
import com.example.myapplication.SettingsActivity;

public class MainMenuActivity extends AppCompatActivity {

    private ImageView setting;
    private Handler mHandler;
    private Activity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);


        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();


        SharedPreferences preferences = getSharedPreferences("USER", MODE_PRIVATE);
        String display = preferences.getString("MainMenuActivity", "");

        final Button btPlay = findViewById(R.id.btPlay);

        setting = findViewById(R.id.setting_btn_mainMenu);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(MainMenuActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });


        btPlay.setOnClickListener(v -> {
            Intent displayScreen = new Intent(MainMenuActivity.this, CatchBallMenu.class);
            startActivity(displayScreen);
        });


        if (display.equals("User or password is incorrect")) {
            btPlay.setEnabled(false);
        }


    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {
            ConstraintLayout layout = findViewById(R.id.mainmenu);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            MainMenuActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable


}
