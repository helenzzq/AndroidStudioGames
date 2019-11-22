package com.example.myapplication.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.SavePrincessActivity;
import com.example.myapplication.gamemanager.GameFileSaver;
import com.example.myapplication.gamemanager.GameView;
import com.example.myapplication.loginRegister.LoginActivity;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

/*BASED ON: https://youtu.be/ojD6ZDi2ep8
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

public class CatchBallActivity extends AppCompatActivity implements GameView, Observer, Serializable {

    private TextView scoreLabel;
    private TextView startLabel;
    private Button pauseButton;


    //Score for the game
    private int score=0;
    //Presenter
    private CatchBallPresenter presenter;

    //Initialize Class
    private Handler handler = new Handler();
    public Timer timer = new Timer();

    //Status check
    private boolean actionFlag = false;
    private boolean startFlag = false;
    private boolean pauseFlag = false;


    private static final String fileName = "catchball.ser";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);
        setBackButton();
        setSaveButton();
        ImageView[] imgs = new ImageView[]{findViewById(R.id.orange),findViewById(R.id.black),
                findViewById(R.id.pink), findViewById(R.id.box)};
        presenter = new CatchBallPresenter(this,
                new CatchBallManager(getWindowManager(), -80,-80, imgs));
//        GameFileSaver gameFileSaver = new GameFileSaver(this, LoginActivity.currentPlayer.
//                getCatchBallGameFile());
//
//        if(gameFileSaver.getGameManager() != null){
//            presenter.setGameManager(gameFileSaver.getGameManager());
//        }
//        presenter.register(gameFileSaver);
//        gameFileSaver.saveToFile();



        pauseButton =findViewById(R.id.catchBallPause);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);

        scoreLabel.setText("Score: 0" );



    }

    /**
     * Stop the Timer
     */
    public void stopTimer(){
        //stop the timer
        timer.cancel();
        timer=null;
    }
    /**
     * Go to the result of the Game
     */
    @Override
    public void showResult(){
        //Show Result
        Intent intent = new Intent(getApplicationContext(), CatchBallResultActivity.class);
        intent.putExtra("SCORE",score);
        startActivity(intent);
    }

    /**
     * Update the Timer
     */

    @SuppressLint("SetTextI18n")
    public void updateTimer(){
        startLabel.setVisibility(View.GONE);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    presenter.hitCheck(actionFlag);
                    scoreLabel.setText("Score: " + score );
                });
            }
        },0,20);

    }

    @Override
    public boolean onTouchEvent(MotionEvent action) {

        FrameLayout frame = findViewById(R.id.frame);
        presenter.onStart(action,startFlag, frame.getHeight()-130);
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


    public int getScore() {
        return score;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    @SuppressLint("SetTextI18n")

    public void setPauseButton() {
        findViewById(R.id.catchBallPause).setOnClickListener(v -> {
            if (!pauseFlag && startFlag){
                pauseFlag = true;

                stopTimer();

                //Change Button Text;
                pauseButton.setText("RESUME");
            }
            else{

                pauseFlag = false;

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(() -> {
                            presenter.hitCheck(actionFlag);
                            scoreLabel.setText("Score: " + score );
                        });
                    }
                },0,20);

                //Change Button Text;
                pauseButton.setText("PAUSE");

            }


        });
    }

    public void setBackButton(){
        findViewById(R.id.catchBallBack).setOnClickListener(v -> {
            Intent i = new Intent(this, SavePrincessActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        presenter.notifyObservers();
     //Implement code here.
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
