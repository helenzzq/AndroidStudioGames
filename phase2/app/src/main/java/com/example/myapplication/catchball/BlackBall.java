package com.example.myapplication.catchball;

import android.widget.ImageView;

/**
 *
 */
class BlackBall extends Ball {



    private int point;


    BlackBall(int x, int y, ImageView view){
        super(x,y, view);
        point = 0;

    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth,frameHeight, 16, 10);
    }


}
