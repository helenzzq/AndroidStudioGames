package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.LoginRegister.MainActivity;
import com.example.myapplication.R;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    private MediaPlayer mediaPlayer;
    private Button back, start, play, pause, day, night;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        MediaPlayer Alone = MediaPlayer.create(SettingsActivity.this,R.raw.Alone);
//        Alone.start();


        mediaPlayer = MediaPlayer.create(this, R.raw.alone);

        back = findViewById(R.id.btn_back1);
        back.setOnClickListener(this);
        start = findViewById(R.id.btn_start2);
        start.setOnClickListener(this);
        pause = findViewById(R.id.btn_pause);
        pause.setOnClickListener(this);
        play = findViewById(R.id.btn_play);
        play.setOnClickListener(this);
        day = findViewById(R.id.btn_day);
        day.setOnClickListener(this);
        night = findViewById(R.id.btn_night);
        night.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start2:
                Intent intent1 = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_back1:
                finish();
                break;
            case R.id.btn_pause:
                mediaPlayer.pause();
                break;
            case R.id.btn_play:
                mediaPlayer.start();
                break;
            case R.id.btn_day:
                break;
            case R.id.btn_night:
                break;
            default:
                break;
        }
    }
}
