package com.example.myapplication.catchball;

import android.widget.ImageView;

class Ball implements MoveItem {

    private int x;
    private int y;
    private ImageView view;
    private int point;
    private int CenterX = 0;
    private int CenterY = 0;


    Ball(int x, int y, ImageView view){
        this.x = x;
        this.y = y;
        this.view = view;
        view.setX(x);
        view.setY(y);

    }


    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    /**
     * A setter for the x coordinate of the ball
     */
    @Override
    public void setX(int x) {
        view.setX(x);
        this.x = x;
    }
    /**
     * A setter for the y coordinate of the ball
     */
    @Override
    public void setY(int y) {
        view.setY(y);
        this.y = y;
    }
    /**
     * A getter for the x coordinate of the ball
     */
    @Override

    public int getX() {
        return x;
    }
    /**
     * A getter for the y coordinate of the ball
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * A setter for the ball's view
     */
    public void setView(ImageView appearance) {
        this.view = appearance;
    }
    /**
     * A getter for the ball's view
     */
    //cannot change appearance
    public ImageView getView() {
        return view;
    }


    int getCenterY() {
        return CenterY;
    }

    int getCenterX() {
        return CenterX;
    }
    @Override
    public void move(int screenWidth, int frameHeight, int changeInX, int width){
        x -= changeInX;
        if(x < 0){
            x = screenWidth + width;
            y = (int)Math.floor(Math.random()*(frameHeight-view.getHeight()));
        }
        view.setX(x);
        view.setY(y);
    }

}
