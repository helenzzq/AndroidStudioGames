package com.example.myapplication.Math24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.Math24.Math24Activity;

import com.example.myapplication.MainPageActivity;
import com.example.myapplication.R;

public class Math24ResultActivity extends AppCompatActivity {

    private Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_result);

        TextView scoreLabelMath24 = findViewById(R.id.scoreLabelMath24);
        TextView highScoreLabelMath24 =  findViewById(R.id.highScoreLabelMath24);
        TextView livesMath24 = findViewById(R.id.math24Live);
        TextView timeMath24 = findViewById(R.id.math24Time);

        int score = getIntent().getIntExtra("SCOREMath24",0);
        scoreLabelMath24.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATAMath24", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCOREMath24",0);

        if(score>highScore){
            highScoreLabelMath24.setText("High Score: " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCOREMath24",score);
            editor.commit();
        }else{
            highScoreLabelMath24.setText("High Score: " + highScore);
        }

        finish = findViewById(R.id.btn_finish);
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
