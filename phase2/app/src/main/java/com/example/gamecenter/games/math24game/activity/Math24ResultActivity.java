package com.example.gamecenter.games.math24game.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamecenter.R;
import com.example.gamecenter.login.MainMenuActivity;

public class Math24ResultActivity extends AppCompatActivity {
    private Button mainpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_result);


        TextView scoreMath24 = findViewById(R.id.scoremath24);
        TextView highestScoreMath24 = findViewById(R.id.highestScoreMath24);

        int score = getIntent().getIntExtra("SCORE",0);
        scoreMath24.setText(score + "");

        SharedPreferences settings = getSharedPreferences("SCORE", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCOREMATH24",0);

        if(score>highScore){
            highestScoreMath24.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCOREMATH24",score);
            editor.commit();
        }else{
            highestScoreMath24.setText("High Score: " + highScore);
        }

//        SharedPreferences settings = getSharedPreferences("GAME_DATA2048", Context.MODE_PRIVATE);
//        int highScore = settings.getInt("HIGH_SCORE2048",0);

        mainpage= findViewById(R.id.btn_math24backToMain);
        mainpage.setOnClickListener(v -> {
            Intent mainpage1 = new Intent(Math24ResultActivity.this, MainMenuActivity.class);
            startActivity(mainpage1);
        });

    }


}




