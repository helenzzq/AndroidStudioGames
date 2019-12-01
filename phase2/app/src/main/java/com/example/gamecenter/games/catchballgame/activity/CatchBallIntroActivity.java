package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CatchBallIntroActivity extends AppCompatActivity {

    //Initialize Class
    private Handler handler = new Handler();
    private Activity current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_intro);

        this.handler = new Handler();
        this.runnable.run();

        current = this;
    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.catchBallmenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.catchBall_text)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };
}