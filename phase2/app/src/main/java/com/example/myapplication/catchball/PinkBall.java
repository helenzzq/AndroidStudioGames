package com.example.myapplication.catchball;

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


    //20
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth, frameHeight, 3000);
    }


}
