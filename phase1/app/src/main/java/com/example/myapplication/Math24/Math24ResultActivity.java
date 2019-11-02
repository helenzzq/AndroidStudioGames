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
import com.example.myapplication.pictype2048.WeaponActivity;

public class Math24ResultActivity extends AppCompatActivity {
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_result);


        TextView scoreMath24 = (TextView) findViewById(R.id.scoremath24);
        TextView highestScoreMath24 = (TextView) findViewById(R.id.highestScoreMath24);

        int score = getIntent().getIntExtra("SCOREMath24",0);
        scoreMath24.setText(score + "");
        highestScoreMath24.setText(String.format("%d", 100));

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
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }

}




