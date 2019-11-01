package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.catchball.CatchBallActivity;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    //crete mediaPlayer variable with type MediaPlayer
    private MediaPlayer mediaPlayer;
    //create buttons: back, start, play, pause, day, and night
    private Button back, start, play, pause, day, night;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

// create a mediaplayer variable
        mediaPlayer = MediaPlayer.create(this, R.raw.alone);
//recognize the bottoms and assign them to the corresponding variables
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
            //If we press start, we will go to CatchBall Game
            case R.id.btn_start2:
                Intent intent1 = new Intent(SettingsActivity.this, CatchBallActivity.class);
                startActivity(intent1);
                break;
                //If we press back button, we will finish
            case R.id.btn_back1:
                finish();
                break;
                //if we press pause, the music will stop playing
            case R.id.btn_pause:
                mediaPlayer.pause();
                break;
                //if we press start, the music will start playing
            case R.id.btn_play:
                mediaPlayer.start();
                break;
                //if we press bottom day, it would be day theme
            case R.id.btn_day:
                break;
                //if we press buttom night, it would be night theme
            case R.id.btn_night:
                break;
            default:
                break;
        }
    }
}
