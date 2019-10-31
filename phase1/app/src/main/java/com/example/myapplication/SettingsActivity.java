package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.pushbox.activities.PushBoxGameActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button back, start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        back = findViewById(R.id.btn_back1);
        back.setOnClickListener(this);
        start = findViewById(R.id.btn_start2);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start2:
                Intent intent1 = new Intent(SettingsActivity.this, PushBoxGameActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_back1:
                finish();
                break;
            default:
                break;
        }
    }
}
