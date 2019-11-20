package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.loginRegister.EntryMainActivity;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back, start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back = findViewById(R.id.btn_back2);
        back.setOnClickListener(this);
        start = findViewById(R.id.btn_start3);
        start.setOnClickListener(this);

        //get the SharedPreference object
        SharedPreferences sharedPref = getSharedPreferences("switch", Context.MODE_PRIVATE);
        String on = sharedPref.getString("on", "");
        ConstraintLayout layout = findViewById(R.id.helpPage);

        //Set the BackGround
        BackGroundSetting backGroundSetting = new BackGroundSetting();
        backGroundSetting.setWallPaper(new TextView[0],
                this, layout, on);
    }

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
            default:
                break;
        }
    }
}