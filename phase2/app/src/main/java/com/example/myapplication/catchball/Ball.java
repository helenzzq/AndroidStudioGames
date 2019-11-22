package com.example.myapplication.catchball;

import android.widget.ImageView;

abstract class Ball implements MoveItem {


    private ImageView view;

    private int x;
    private int y;
    private int point;


    Ball(int x, int y, ImageView view){
        this.view = view;
        view.setX(x);
        view.setY(y);
        point = 0;
    }

    /**
     * A getter for the point of the ball
     */
    @Override
    public int getPoint(){
        return point;
    }

    /**
     * A setter for the x coordinate of the ball
     */
    @Override
    public void setX(int x) {
        this.x = x;

    }
    /**
     * A setter for the y coordinate of the ball
     */
    @Override
    public void setY(int y) {

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
     * A getter for the ball's view
     */
    //cannot change appearance
    public ImageView getView() {
        return view;
    }


    int getCenterY() {

        return y + view.getHeight()/2;
    }

    int getCenterX() {

        return x + view.getWidth()/2;
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
