package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;


public class PinkBall extends Ball {



    private int point;

    PinkBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 30;


    }

    @Override
    public int getPoint() {
        return point;
    }


    //12
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 20);
    }


}