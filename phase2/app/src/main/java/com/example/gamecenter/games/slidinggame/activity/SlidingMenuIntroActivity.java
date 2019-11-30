package com.example.gamecenter.games.slidinggame.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class SlidingMenuIntroActivity extends AppCompatActivity implements View.OnClickListener {
    private Button resume;
    private TextView introTitle, introBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu_intro);
        resume = findViewById(R.id.btn_resume2);
        resume.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_resume2:
                finish();
                break;
        }
    }
}