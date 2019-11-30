package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class CatchBallIntroActivity extends AppCompatActivity implements View.OnClickListener {
    private Button resume;
    private TextView introTitle, introBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_intro);
        resume = findViewById(R.id.btn_resume1);
        resume.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_resume1:
                finish();
                break;
        }
    }
}