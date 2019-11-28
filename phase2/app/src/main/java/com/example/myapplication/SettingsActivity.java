package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.strategyclass.BackGroundSetter;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    //crete mediaPlayer variable with type MediaPlayer
    private MediaPlayer mediaPlayer;
    //create buttons: back, start, play, pause, day, and night
    private Switch setBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // create a mediaplayer variable
        mediaPlayer = MediaPlayer.create(this, R.raw.alone);

        //recognize the bottoms and assign them to the corresponding variables
        Button[] buttons = new Button[]{findViewById(R.id.btn_back1),
                findViewById(R.id.btn_pause),
                findViewById(R.id.btn_play)};

        //create a background switch
        setBackground = findViewById(R.id.Setbackground);
        setBackground.setOnClickListener(this);

        updateSwitch();

        for (Button bb : buttons) {
            bb.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.Setbackground:
                //Enable set background button
                setBackGround();
            default:
                break;
        }
    }

    private void updateSwitch(){
        // update the switch
        if (BackGroundSetter.isSwitchStatus()){
            setBackground.setChecked(true);

        }
        else{
            setBackground.setChecked(false);
        }
        setBackGround();


    }

    private void setBackGround(){
        //Enable set background button
        boolean on = (setBackground).isChecked();
        TextView[] texts = new TextView[]{findViewById(R.id.character_text),
                findViewById(R.id.background_text), findViewById(R.id.music), findViewById(R.id.settings)};

        ConstraintLayout layout = findViewById(R.id.setting);

        if (on) {
            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.deernight));
            BackGroundSetter.setSwitchStatus(true );

            for (TextView k : texts) {
            k.setTextColor(Color.parseColor("#FFFFFF"));}
        } else {
            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.whitebackground));
            BackGroundSetter.setSwitchStatus(false );
            for (TextView t : texts) {
                t.setTextColor(Color.parseColor("#FF777070"));
            }


        }


    }
}
