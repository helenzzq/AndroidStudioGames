package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
    private TextView level;
    //The number of columns and rows.
    public static int num;
    private static GameTimer gameTimer;
    private static boolean isLevel1 = true;

    //private TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNum();
        setContentView(R.layout.activity_sliding);

        textScore = findViewById(R.id.tvScore);
        level = findViewById(R.id.slidingLevel);
        setLevelText();
        setBackButton();
        setHelpButton();

        SlidingGrid.getPresenter().setSlidingView(this);

        if (isLevel1) {
            gameTimer = new GameTimer(findViewById(R.id.slidingTimer));
        }else{
            gameTimer = getGameTimer();
        }

        gameTimer.restart();

        Button pauseBtn = findViewById(R.id.pause2048);
        pauseBtn.setTag(0);
        setPauseButton(pauseBtn, gameTimer);


    }

    public void setLevelText(){
        if (isLevel1){
            level.setText("LEVEL1");
        }
        else{
            level.setText("LEVEL2");
        }
    }

    public static void setNum() {
        if (isLevel1){
            num = 3;
        }
        else{
            num = 4;
        }
    }

    public static int getNum() {
        return num;
    }

    public static boolean getIsLevel1(){
        return isLevel1;
    }

    public static void notLevel1(){
        isLevel1 = !isLevel1;
    }

    public static GameTimer getGameTimer() {
        return gameTimer;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void updateScore(int s) {
        score = s;
        textScore.setText(score + "");
    }

    @Override
    public void goToResult() {
        super.goToResult(SlidingResultActivity.class, "SLIDING_SCORE", score);
    }


    public void setBackButton() {
        findViewById(R.id.backtoMain).setOnClickListener(v -> {
            Intent i = new Intent(this, SlidingMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            SlidingGrid.getPresenter();
            finish();

        });
    }

    public void setHelpButton() {
        findViewById(R.id.Help2048).setOnClickListener(v -> {
            Intent i = new Intent(this, SlidingIntroActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            gameTimer.stop();

        });
    }

    public void startLevel2(){
        Intent intent = new Intent(SlidingActivity.this, SlidingActivity.class);
        startActivity(intent);
    }

    public SlidingActivity getSlidingActivity(){
        return this;
    }
}


