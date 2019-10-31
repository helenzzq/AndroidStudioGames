package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.pushbox.activities.PushBoxMainActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private Button start,settings, help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        start = findViewById(R.id.btn_start);
        start.setOnClickListener(this);
        settings = findViewById(R.id.btn_settings);
        settings.setOnClickListener(this);
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_start:
                Intent intent1 = new Intent(SecondActivity.this, PushBoxMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_settings:
                Intent intent2 = new Intent(SecondActivity.this, SettingsActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_help:
                Intent intent3 = new Intent(SecondActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
