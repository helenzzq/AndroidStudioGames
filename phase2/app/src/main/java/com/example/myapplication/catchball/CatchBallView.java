package com.example.myapplication.catchball;

import android.view.MotionEvent;

interface CatchBallView {


    void showResult();

    void stopTimer();

    void updateTimer();

    void makeAction(MotionEvent action);

    int getScore();

    void setActionFlag(boolean actionFlag);

    void setStartFlag(boolean startFlag);


    boolean isStartFlag();

    void hideStartLabel();

    boolean isActionFlag();

    void updateScore(int score);
}
