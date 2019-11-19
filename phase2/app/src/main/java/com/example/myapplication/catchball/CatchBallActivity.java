package com.example.myapplication.catchball;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

/*BASED ON: https://youtu.be/ojD6ZDi2ep8
ALL CREDIT FOR THE ORIGINAL IMPLEMENTATION OF A SIMILAR SINGLETON GOES TO THE ORIGINAL AUTHOR OF
    THE CODE.*/

public class CatchBallActivity extends AppCompatActivity implements CatchBallView {

    private TextView scoreLabel;
    private TextView startLabel;

    //Score for the game
    private int score=0;
    //Presenter
    private CatchBallPresenter presenter;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //Status check
    private boolean actionFlag = false;
    private boolean startFlag = false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);

        scoreLabel.setText("Score: 0" );

        ImageView[] imgs = new ImageView[]{findViewById(R.id.orange),findViewById(R.id.black),
                findViewById(R.id.pink), findViewById(R.id.box)};

//        frameHeight = frame.getHeight();
        presenter = new CatchBallPresenter(this,
                new CatchBallManager(getWindowManager(), -80,-80, imgs));

    }

//    public void changePos(){
//
//        hitCheck();
//
//        //Orange
//        orangeX -= 12;
//        if(orangeX < 0){
//            orangeX = screenWidth + 20;
//            orangeY = (int)Math.floor(Math.random()*(frameHeight-orange.getHeight()));
//        }
//        orange.setX(orangeX);
//        orange.setY(orangeY);
//
//
//        //black
//        blackX -= 16;
//        if(blackX < 0){
//            blackX = screenWidth + 10;
//            blackY = (int)Math.floor(Math.random()*(frameHeight-black.getHeight()));
//        }
//        black.setX(blackX);
//        black.setY(blackY);
//
//        //pink
//        pinkX -= 20;
//        if(pinkX < 0){
//            pinkX = screenWidth + 3000;
//            pinkY = (int)Math.floor(Math.random()*(frameHeight-pink.getHeight()));
//        }
//        pink.setX(pinkX);
//        pink.setY(pinkY);
//
//        //Move Box
//        if(actionFlag){
//            //Touching
//            boxY -= 20;
//        } else{
//            //Releasing
//            boxY += 20;
//        }
//
//        //check box position
//        if(boxY<0)
//            boxY=0;
//
//        if(boxY>frameHeight-boxSize)
//            boxY=frameHeight-boxSize;
//
//        box.setY(boxY);
//
//        scoreLabel.setText("Score: " + score );
//    }
//
//
//    public void hitCheck(){
//        //if the center of the ball is in the box,it counts as a hit
//
//        //Orange
//        int orangeCenterX = orangeX + orange.getWidth()/2;
//        int orangeCenterY = orangeY + orange.getHeight()/2;
//
//        //hit must satisfies following condition
//        //0<= orangeCenterX <= boxWidth
//        //boxY <= orangeCenterY <= boxY+boxSize
//
//        if(validate(orangeCenterX,orangeCenterY,boxY,boxSize)){
//
//                score += 10;
//                orangeX = -10;
//
//        }
//
//        //Pink
//        int pinkCenterX = pinkX + pink.getWidth()/2;
//        int pinkCenterY = pinkY + pink.getHeight()/2;
//
//        //hit must satisfies following condition
//        //0<= pinkCenterX <= boxWidth
//        //boxY <= pinkCenterY <= boxY+boxSize
//
//        if(validate(pinkCenterX,pinkCenterY,boxY,boxSize)){
//
//            score += 30;
//            pinkX = -10;
//
//        }
//
//        //black
//        int blackCenterX = blackX + black.getWidth()/2;
//        int blackCenterY = blackY + black.getHeight()/2;
//
//
//        if(validate(blackCenterX,blackCenterY,boxY,boxSize)){
//
//            //stop the timer
//            timer.cancel();
//            timer=null;
//
//            //Show Result
//            Intent intent = new Intent(getApplicationContext(), CatchBallResultActivity.class);
//            intent.putExtra("SCORE",score);
//            startActivity(intent);
//
//        }
//    }

    @Override
    public void stopTimer(){
        //stop the timer
        timer.cancel();
        timer=null;
    }
    @Override
    public void showResult(){
        //Show Result
        Intent intent = new Intent(getApplicationContext(), CatchBallResultActivity.class);
        intent.putExtra("SCORE",score);
        startActivity(intent);
    }



    @SuppressLint("SetTextI18n")
    public void updateTimer(){
        startLabel.setVisibility(View.GONE);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> presenter.hitCheck(actionFlag));
                scoreLabel.setText("Score: " + score );
            }
        },0,20);

    }

    @Override
    public boolean onTouchEvent(MotionEvent action) {

        FrameLayout frame = findViewById(R.id.frame);
        presenter.onStart(action,startFlag, frame.getHeight());
        return true;
    }


    @Override
    public void makeAction(MotionEvent action){
        if(action.getAction()==MotionEvent.ACTION_DOWN){
            actionFlag = true;
        } else if(action.getAction() == MotionEvent.ACTION_UP){
            actionFlag = false;
        }

    }
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void hideStartLabel(){
        startLabel.setVisibility(View.GONE);
    }

    @Override
    public void updateScore(int score) {
        this.score += score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setActionFlag(boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    @Override
    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    @Override
    public boolean isStartFlag() {
        return startFlag;
    }

    @Override
    public boolean isActionFlag() {
        return actionFlag;
    }


}
