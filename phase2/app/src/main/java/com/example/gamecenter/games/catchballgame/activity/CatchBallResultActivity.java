package com.example.gamecenter.games.catchballgame.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.R;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.games.slidinggame.activity.SlidingMenu;
import com.example.gamecenter.login.LoginActivity;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.user.User;

public class CatchBallResultActivity extends AppCompatActivity {

    private Handler mHandler;
    private Activity current;
    private ImageView setting;
    private Button mainpage;

    private User currentPlayer = LoginActivity.currentPlayer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_result);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);
        TextView usernameLabel  = findViewById(R.id.username);

        setting = findViewById(R.id.setting_btn_ball);
        mainpage = findViewById(R.id.btn_catchballbackToMain);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(CatchBallResultActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });
        //Set the runnable and handler

        mainpage.setOnClickListener(v -> {
            Intent mainPage1 = new Intent(CatchBallResultActivity.this, MainMenuActivity.class);
            startActivity(mainPage1);
        });

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");
        usernameLabel.setText("Username: " + currentPlayer.getUsername());
        currentPlayer.setScore(score);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.apply();


        } else {
            highScoreLabel.setText("High Score: " + highScore);
        }
    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {
            LinearLayout layout = findViewById(R.id.catchBallResult);
            BackGroundSetter.setWallPaper(new TextView[0], current, layout);
            CatchBallResultActivity.this.mHandler.postDelayed(mRunnable, 50);
        }
    };

    public void next(View view) {
        startActivity(new Intent(getApplicationContext(), SlidingMenu.class));
    }
}
