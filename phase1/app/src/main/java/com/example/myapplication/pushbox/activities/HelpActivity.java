package com.example.myapplication.pushbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start3:
                Intent intent1 = new Intent(HelpActivity.this, PushBoxGameActivity.class);
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