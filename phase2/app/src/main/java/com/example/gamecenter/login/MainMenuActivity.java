package com.example.gamecenter.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.activity.CatchBallMenu;
import com.example.gamecenter.games.math24game.activity.Math24Menu;
import com.example.gamecenter.games.slidinggame.activity.SlidingMenu;
import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;

public class MainMenuActivity extends BaseActivity {

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

        ImageView setting = findViewById(R.id.setting_btn_mainMenu);
        Button catchballGame = findViewById(R.id.btn_catchBall);
        Button slidingGame = findViewById(R.id.btn_slidingGame);
        Button math24Game = findViewById(R.id.btn_math24);

        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(MainMenuActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });

        catchballGame.setOnClickListener(v -> {
            Intent catchBallgame1 = new Intent(MainMenuActivity.this, CatchBallMenu.class);
            startActivity(catchBallgame1);
        });

        slidingGame.setOnClickListener(v -> {
            Intent slidingGame1 = new Intent(MainMenuActivity.this, SlidingMenu.class);
            startActivity(slidingGame1);
        });

        math24Game.setOnClickListener(v -> {
            Intent math24Game1 = new Intent(MainMenuActivity.this, Math24Menu.class);
            startActivity(math24Game1);
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
