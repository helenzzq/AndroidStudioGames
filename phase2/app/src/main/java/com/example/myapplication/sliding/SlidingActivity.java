package com.example.myapplication.sliding;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.gamemanager.GameView;


@SuppressLint("Registered")
public class SlidingActivity extends AppCompatActivity implements GameView {

    private int score = 0;
    private TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);
        tvScore = findViewById(R.id.tvScore);
        SlidingGrid.getPresenter().setWeaponView(this);
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void updateScore(int s) {
        score = s;
        tvScore.setText(score + "");
    }

    @Override
    public void showResult() {
        Intent intent = new Intent(SlidingActivity.this, SlidingResultActivity.class);
        intent.putExtra("SCORE2048", score);
        startActivity(intent);
    }

//    public void setPause2048Btn(){
//        findViewById(R.id.PauseGameBtn2048).setOnClickListener(v -> {
//
//            Intent i = new Intent(this, BaseActivity.class);
//            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(i);
//
//        });
//    }


}
