package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.strategy.GameTimer;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


@SuppressLint("Registered")
public class SlidingActivity extends BaseActivity implements GameView {

    private int score = 0;
    private TextView textScore;
    //The number of columns and rows.
    public static int num;
    private GameTimer gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences levels = getSharedPreferences("slidingLevel", Context.MODE_PRIVATE);
        if("easy".equals(levels.getString("level", ""))){
            setNum(3);
        }else{
            setNum(4);
        }
        setContentView(R.layout.activity_sliding);
        setBackButton();
        textScore = findViewById(R.id.tvScore);
        SlidingGrid.getPresenter().setWeaponView(this);

        Button pauseBtn = findViewById(R.id.pause2048);

        gameTimer = new GameTimer(findViewById(R.id.slidingTimer));
        gameTimer.restart();
        pauseBtn.setTag(0);
        setPauseButton(pauseBtn, gameTimer);

    }

    public static void setNum(int column){
        num = column;
    }

    public static int getNum(){
        return num;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void updateScore(int s) {
        score = s;
        textScore.setText(score + "");
    }

    @Override
    public void goToResult() {
        super.goToResult(SlidingResultActivity.class,"SLIDING_SCORE", score);
    }


    public void setBackButton(){
        findViewById(R.id.backtoMain).setOnClickListener(v -> {
            Intent i = new Intent(this, SlidingMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            SlidingGrid.getPresenter();
            finish();

        });
    }


}
