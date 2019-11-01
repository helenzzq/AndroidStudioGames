package com.example.myapplication.Math24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainPageActivity;
import com.example.myapplication.Math24.Math24Activity;
import com.example.myapplication.R;

public class Math24ResultActivity extends AppCompatActivity {
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_result);


        TextView scoreMath24 = (TextView) findViewById(R.id.scoremath24);
        TextView highestScoreMath24 = (TextView) findViewById(R.id.highestScoreMath24);

        int score = getIntent().getIntExtra("SCORE2048",0);
        scoreMath24.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA2048", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE2048",0);

        if(score>highScore){
            highestScoreMath24.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE2048",score);
            editor.commit();
        }else{
            highestScoreMath24.setText("High Score: " + highScore);
        }

        finish= findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void backToMain(){
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

}




