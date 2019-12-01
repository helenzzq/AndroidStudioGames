package com.example.gamecenter.games.catchballgame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.model.CatchBallManager;
import com.example.gamecenter.games.catchballgame.model.CatchBoard;
import com.example.gamecenter.games.catchballgame.presenter.CatchBallPresenter;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.strategy.GameTimer;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/*BASED ON: https://youtu.be/ojD6ZDi2ep8
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

public class CatchBallActivity extends BaseActivity implements GameView, Observer, Serializable {

    private TextView scoreLabel;
    private TextView startLabel;

    //Score for the game
    private int score=0;

    //Presenter
    private CatchBallPresenter presenter;

    //Initialize Class
    private Handler handler = new Handler();
    public GameTimer gameTimer;

    //Status check
    private boolean actionFlag = false;
    private boolean startFlag = false;
    private TextView level;


    private static final String fileName = "catchball.ser";

    private User currentPlayer = UserManager.getCurrentUser();


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);
        setBackButton();
        setSaveButton();

        ImageView[] imgs = new ImageView[]{findViewById(R.id.orange),findViewById(R.id.black),
                findViewById(R.id.pink), findViewById(R.id.box)};
        presenter = new CatchBallPresenter(this, new CatchBallManager(new CatchBoard(getWindowManager(), -80,-80,8,imgs)));


        level = findViewById(R.id.catchBallLevel);
        level.setText("LEVEL1"  );

//         Set GameTimer
        Chronometer chrono = findViewById(R.id.chronometerBall);
        gameTimer = new GameTimer(chrono);

        Button pauseButton = findViewById(R.id.catchBallPause);
        Button introButton = findViewById(R.id.catchBallIntro);
        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);

        scoreLabel.setText("Score: 0" );
        pauseButton.setTag(0);
        setPauseButton(pauseButton, gameTimer);
        introButton.setOnClickListener(v -> {
            Intent catchBallIntro1 = new Intent(CatchBallActivity.this, CatchBallIntroActivity.class);
            gameTimer.stop();
            startActivity(catchBallIntro1);
        });
    }

    /**
     * Go to the result of the Game
     */
    @Override
    public void goToResult() {
        presenter.getGameManager().checkToAddScore(CatchBallMenu.scoreboard,currentPlayer.getUsername());
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboardFileSaver.saveToFile(fileName);
        finish();
        super.goToResult(CatchBallResultActivity.class,"CATCH_BALL_SCORE", score);
    }


    /**
     * Update the GameTimer
     */

    @SuppressLint("SetTextI18n")
    public void updateTimer(){
        gameTimer.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    presenter.hitCheck(actionFlag);
                    scoreLabel.setText("Score: " + score );
                });
            }
        },0,20);

    }

    public void setLevel(String levels) {
       level.setText(levels);
    }

    @Override
    public boolean onTouchEvent(MotionEvent action) {

        FrameLayout frame = findViewById(R.id.frame);

        presenter.onStart(action, startFlag, frame.getHeight()-130);

        return true;
    }


    public void makeAction(MotionEvent action){
        if(action.getAction()==MotionEvent.ACTION_DOWN){
            actionFlag = true;
        } else if(action.getAction() == MotionEvent.ACTION_UP){
            actionFlag = false;
        }

    }

    public void hideStartLabel(){
        startLabel.setVisibility(View.GONE);
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public int getScore() {
        return score;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer) {
        pauseBtn.setOnClickListener(v -> {
            if ((int)pauseBtn.getTag() == 0 && startFlag){
                pauseBtn.setTag(1);

                gameTimer.stop();
                //Change Button Text;
                pauseBtn.setText("RESUME");

            }
            else{
                pauseBtn.setTag(0);
                gameTimer.restart();
                updateTimer();
                //Change Button Text;
                pauseBtn.setText("PAUSE");
            }

        });
    }

    public void setBackButton(){
        findViewById(R.id.catchBallBack).setOnClickListener(v -> {
            Intent i = new Intent(this, CatchBallMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            presenter.onDestroy();
            finish();
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        presenter.notifyObservers();
    }

    /**
     * Activate the save button.
     */
    private void setSaveButton() {
        Button saveButton = findViewById(R.id.catchBallSave);
        saveButton.setOnClickListener(v -> {
            presenter.notifyObservers();
            makeToastSavedText();
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }


}
