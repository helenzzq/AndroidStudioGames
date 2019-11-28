package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


@SuppressLint("Registered")
public class SlidingActivity extends BaseActivity implements GameView {

    private int score = 0;
    private TextView tvScore;
    //The number of columns and rows.
    public static int num;

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
        tvScore = findViewById(R.id.tvScore);
        SlidingGrid.getPresenter().setWeaponView(this);
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
        tvScore.setText(score + "");
    }

    @Override
    public void goToResult() {
        super.goToResult(SlidingResultActivity.class,score);
    }


//    public void setPauseButton(){
//        findViewById(R.id.Pause2048).setOnClickListener(v -> {
//
//        });
//    }

    public void setBackButton(){
        findViewById(R.id.BacktoMain).setOnClickListener(v -> {
            Intent i = new Intent(this, SlidingMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            SlidingGrid.getPresenter();
            finish();

        });
    }


}
