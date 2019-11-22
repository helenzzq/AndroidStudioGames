package com.example.myapplication.catchball;

import android.widget.ImageView;

public class PinkBall extends Ball {

    private int point;


    PinkBall(int x, int y, ImageView view) {
        super(x, y, view);
        point = 30;


    }

    @Override
    public int getPoint() {
        return point;
    }

    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth, frameHeight, 20, 3000);
    }


}
