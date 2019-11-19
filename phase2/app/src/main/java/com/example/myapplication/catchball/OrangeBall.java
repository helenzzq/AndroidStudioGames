package com.example.myapplication.catchball;

import android.widget.ImageView;

public class OrangeBall extends Ball {


    private int x;
    private int y;
    private ImageView view;
    private int point;
    private int CenterX;
    private int CenterY;

    OrangeBall(int x, int y, ImageView view) {
        super(x, y, view);
        CenterX = x + view.getWidth()/2;
        CenterY = y + view.getHeight()/2;
        point = 10;
    }


    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth,frameHeight, 12, 20);
    }


}
