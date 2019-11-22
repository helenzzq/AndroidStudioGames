package com.example.myapplication.catchball;

import android.widget.ImageView;

public class OrangeBall extends Ball {



    private int point;

    OrangeBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 10;


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
