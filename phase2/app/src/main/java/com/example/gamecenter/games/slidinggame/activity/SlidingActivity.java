package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamecenter.games.slidinggame.SlidingPresenter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.strategy.GameTimer;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


@SuppressLint("Registered")
public class SlidingActivity extends BaseActivity implements GameView {

    private static int score = 0;
    private TextView textScore;
    private TextView level;
    //The number of columns and rows.
    public static int num;
    @SuppressLint("StaticFieldLeak")
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
        Button pauseBtn = findViewById(R.id.pause2048);
        pauseBtn.setTag(0);
        setPauseButton(pauseBtn, gameTimer);
        textScore.setText(120);
        if (!isLevel1) {
            textScore.setText(score);
        } else {
            gameTimer = new GameTimer(findViewById(R.id.slidingTimer));

        }
        gameTimer.restart();

    }


    @Override
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer) {
        SlidingPresenter presenter = SlidingGrid.getPresenter();
        pauseBtn.setOnClickListener(v -> {
            if (( int ) pauseBtn.getTag() == 0) {
                pauseBtn.setTag(1);
                gameTimer.stop();
                presenter.onPause();

                //Change Button Text;
                pauseBtn.setText("RESUME");
            } else {
                pauseBtn.setTag(0);
                gameTimer.restart();
                //Change Button Text;
                presenter.onResume();
                pauseBtn.setText("PAUSE");
            }

        });

    }

    public void setLevelText() {
        if (isLevel1) {
            level.setText("LEVEL1");
        } else {
            level.setText("LEVEL2");
        }
    }

    private void setNum() {
        if (isLevel1) {
            num = 3;
        } else {
            num = 4;
        }
    }


    public static int getNum() {
        return num;
    }

    public static boolean getIsLevel1() {
        return isLevel1;
    }

    public static void changeLevel() {
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
        finish();
        super.goToResult(SlidingResultActivity.class, "SLIDING_SCORE", score);
    }


    public void setBackButton() {
        findViewById(R.id.backtoMain).setOnClickListener(v -> {
            isLevel1 = true;
            finish();
            switchToPage(SlidingMenu.class);

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

    public void startLevel2() {
        Intent intent = new Intent(SlidingActivity.this, SlidingActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

}


