package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class CatchBallIntroActivity extends AppCompatActivity {
    private Button resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_intro);
        resume = findViewById(R.id.btn_resume1);
        resume.setOnClickListener(v -> {
            Intent catchBallresume1 = new Intent(CatchBallIntroActivity.this, CatchBallActivity.class);
            startActivity(catchBallresume1);
        });


    }
}