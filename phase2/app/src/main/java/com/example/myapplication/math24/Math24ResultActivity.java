package com.example.myapplication.math24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.catchball.CatchBallMenu;
import com.example.myapplication.R;

public class Math24ResultActivity extends AppCompatActivity {
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_result);


        TextView scoreMath24 = (TextView) findViewById(R.id.scoremath24);
        TextView highestScoreMath24 = (TextView) findViewById(R.id.highestScoreMath24);

        int score = getIntent().getIntExtra("SCOREMATH24",0);
        scoreMath24.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATAMATH24", Context.MODE_PRIVATE);
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

        finish= findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });
    }

    public void backToMain(){
        Intent intent = new Intent(this, CatchBallMenu.class);
        startActivity(intent);
    }

}




