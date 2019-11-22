package com.example.myapplication.catchball;

import android.widget.ImageView;

public class OrangeBall extends Ball {



    private int point;

    OrangeBall(int x, int y, ImageView view) {
        super(x, y, view);
        point = 10;
    }

    @Override
    public int getPoint() {
        return point;
    }


    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth,frameHeight, 12, 20);
    }


}
