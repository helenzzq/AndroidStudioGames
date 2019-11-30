package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;


/**
 *
 */
class BlackBall extends Ball {



    private int point;


    BlackBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 0;

    }

    @Override
    public int getPoint() {
        return point;
    }

    //16
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 10);
    }


}

