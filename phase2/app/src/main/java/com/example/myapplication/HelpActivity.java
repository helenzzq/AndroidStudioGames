package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.loginregister.EntryMainActivity;
import com.example.myapplication.math24.Math24Activity;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back, start, jumpToMath24;
    private Handler mHandler;
    private Activity current;
    private ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back = findViewById(R.id.btn_back2);
        back.setOnClickListener(this);
        start = findViewById(R.id.btn_start3);
        start.setOnClickListener(this);
        jumpToMath24 = findViewById(R.id.btn_math24);
        jumpToMath24.setOnClickListener(this);


        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

         setting = findViewById(R.id.setting_btn_help);
        setting.setOnClickListener(v -> {
            Intent intent2 = new Intent(HelpActivity.this, SettingsActivity.class);
            startActivity(intent2);
        });
    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {

            ConstraintLayout layout = findViewById(R.id.helpPage);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.text_help)}, current, layout);
            HelpActivity.this.mHandler.postDelayed(mRunnable, 50);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start3:
                Intent intent1 = new Intent(HelpActivity.this, EntryMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_back2:
                finish();
                break;
            case R.id.btn_math24:
                Intent intentQuickTest = new Intent(HelpActivity.this, Math24Activity.class);
                startActivity(intentQuickTest);
            default:
                break;
        }
    }
}