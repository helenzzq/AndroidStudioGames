package com.example.myapplication.catchball;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myapplication.BackGroundSetter;
import com.example.myapplication.R;
import com.example.myapplication.SettingsActivity;
import com.example.myapplication.weapon.WeaponActivity;


public class CatchBallResultActivity extends AppCompatActivity {

    private Handler mHandler;
    private Activity current;
    private ImageView setting;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_result);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);


        setting = findViewById(R.id.setting_btn_ball);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(CatchBallResultActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });
        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

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
        startActivity(new Intent(getApplicationContext(), WeaponActivity.class));
    }
}
