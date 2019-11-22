package com.example.myapplication.sliding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.math24.Math24Activity;

import androidx.appcompat.app.AppCompatActivity;

public class SlidingResultActivity extends AppCompatActivity {

    private Button next;
    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_result);

        TextView scoreLabel2048 = findViewById(R.id.scoreLabel2048);
        TextView highScoreLabel2048 = findViewById(R.id.highScoreLabel2048);

        int score = getIntent().getIntExtra("SCORE2048",0);
        scoreLabel2048.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA2048", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE2048",0);

        if(score>highScore){
            highScoreLabel2048.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE2048",score);
            editor.apply();
        }else{
            highScoreLabel2048.setText("High Score: " + highScore);

        }

        next= findViewById(R.id.button2048);
        next.setOnClickListener(v -> openMath24());
    }

    public void openMath24(){
        Intent intent = new Intent(this, Math24Activity.class);
        startActivity(intent);
    }

}