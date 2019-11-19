package com.example.myapplication.catchball;

import android.widget.ImageView;

/**
 *
 */
class BlackBall extends Ball {

    private int x;
    private int y;
    private ImageView view;
    private int point;
    private int CenterX;
    private int CenterY;

    BlackBall(int x, int y, ImageView view){
        super(x,y, view);
        point = 0;
        CenterX = x + view.getWidth()/2;
        CenterY = y + view.getHeight()/2;

    }
    public void setCenterX(int centerX) {
        CenterX = centerX;
    }

    public void setCenterY(int centerY) {
        CenterY = centerY;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }


    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width) {
        super.move(screenWidth,frameHeight, 16, 10);
    }


}
