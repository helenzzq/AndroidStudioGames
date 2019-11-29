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
import com.example.gamecenter.gamedata.GameDataSaver;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.setting.SettingsActivity;
import com.example.gamecenter.games.slidinggame.activity.SlidingMenu;
import com.example.gamecenter.login.LoginActivity;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.user.User;

public class CatchBallResultActivity extends AppCompatActivity implements GameDataSaver {

    private Handler mHandler;
    private Activity current;
    private ImageView setting;
    private Button mainPage;
    private int score;

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
        mainPage = findViewById(R.id.btn_catchballbackToMain);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(CatchBallResultActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });
        //Set the runnable and handler

        mainPage.setOnClickListener(v -> {
            Intent mainPage1 = new Intent(CatchBallResultActivity.this, MainMenuActivity.class);
            startActivity(mainPage1);
        });

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

        score = getScore("CATCH_BALL_SCORE");
        scoreLabel.setText(score + "");
        usernameLabel.setText("Username: " + currentPlayer.getUsername());
        currentPlayer.setScore(score);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("CATCH_BALL_HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("CATCH_BALL_HIGH_SCORE", score);
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

    public int getScore(String dataName){
        int score = getIntent().getIntExtra(dataName,0);
        return score;
    }

    public int getTime(){
        return 0;//not yet pass the time data
    }

    public void next(View view) {
        startActivity(new Intent(getApplicationContext(), SlidingMenu.class));
    }
}
