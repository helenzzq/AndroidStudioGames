package com.example.myapplication.catchball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.BackGroundSetting;
import com.example.myapplication.R;
import com.example.myapplication.pictype2048.WeaponActivity;

public class CatchBallResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball_result);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel =  findViewById(R.id.highScoreLabel);


        //get the SharedPreference object
        SharedPreferences sharedPref = getSharedPreferences("switch", Context.MODE_PRIVATE);
        String on = sharedPref.getString("on", "");
        LinearLayout layout = findViewById(R.id.catchBallResult);

        //Set the BackGround
        BackGroundSetting backGroundSetting = new BackGroundSetting();
        backGroundSetting.setWallPaper(new TextView[]{findViewById(R.id.savePrincess)},
                this, layout, on);


        int score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score>highScore){
            highScoreLabel.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.apply();

        }else{
            highScoreLabel.setText("High Score: " + highScore);
        }
    }

    public void next(View view){
        startActivity(new Intent(getApplicationContext(), WeaponActivity.class));
    }
}
