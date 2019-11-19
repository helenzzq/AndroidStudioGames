package com.example.myapplication.catchball;

import android.widget.ImageView;

public class PinkBall extends Ball {

    private ImageView view;
    private int point;
    private int CenterX;
    private int CenterY;
    private int x;
    private int y;

    PinkBall(int x, int y, ImageView view) {
        super(x, y, view);
        point = 30;
        CenterX = x + view.getWidth() / 2;
        CenterY = y + view.getHeight() / 2;


    }

    @Override
    int getPoint() {
        return point;
    }

    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth, frameHeight, 20, 3000);
    }


}
