package com.example.myapplication.pictype2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Math24Activity;
import com.example.myapplication.R;

public class WeaponResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_result);

        TextView scoreLabel2048 = (TextView) findViewById(R.id.tvScore);
        TextView highScoreLabel2048 = (TextView) findViewById(R.id.highScoreLabel2048);

        int score = WeaponActivity.getWeaponActivity().getScore();
        scoreLabel2048.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA2048", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE",0);

        if(score>highScore){
            highScoreLabel2048.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();
        }else{
            highScoreLabel2048.setText("High Score: " + highScore);
        }
    }

    public void next(View view){
        startActivity(new Intent(getApplicationContext(), Math24Activity.class));
    }

}
