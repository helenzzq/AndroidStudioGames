package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.gamedata.GameDataBuilder;
import com.example.gamecenter.login.MainMenuActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SlidingResultActivity extends AppCompatActivity {

    private Button mainPage;
    private int score;
    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_result);

        TextView slidingScoreLabel = findViewById(R.id.tvScore);
        TextView slidingHighScoreLabel = findViewById(R.id.highScoreLabel2048);

        score = getScore("SLIDING_SCORE");
        slidingScoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("SLIDING_GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("SLIDING_HIGH_SCORE",0);

        if(score>highScore){
            slidingHighScoreLabel.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("SLIDING_HIGH_SCORE",score);
            editor.apply();
        }else{
            slidingHighScoreLabel.setText("High Score: " + highScore);

        }

        mainPage= findViewById(R.id.btn_slidingbackToMain);
        mainPage.setOnClickListener(v -> openMath24());
    }

    public void openMath24(){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public int getScore(String dataName){
        score = getIntent().getIntExtra(dataName,0);
        return score;
    }

    public int getTime(){
        return 0;//not yet pass time data
    }

}
