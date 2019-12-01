package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * A class of the ball
 * */
class Ball implements MoveItem {

    //view of the ball
    private ImageView view;
    //x coordinate of the ball
    private int x;
    //y coordinate of the ball
    private int y;
    //
    private int point;
    //speed of the ball
    private int speed;

    private boolean status;


    //ball information including its x and y coordinates, view and speed
    Ball(int x, int y, ImageView view, int speed){
        this.view = view;
        view.setX(x);
        view.setY(y);
        point = 0;
        this.speed = speed;

     }

    /**
     * A getter for the point of the ball
     */
    @Override
    public int getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public boolean getStatus(){return this.status;}

    /**
     * A setter for the x coordinate of the ball
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    public boolean CheckStatus() {
        return this.status;
    }

    /**
     * A setter for the y coordinate of the ball
     * @param y
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }
    /**
     * A getter for the x coordinate of the ball
     */
    @Override
    /**
     * A getter for the x coordinate of the ball
     */
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
   * A setter for th speed of the ball.
   * @param speed */
  public void setSpeed(int speed) {
        this.speed = speed;
    }

  /**
   * A getter for the speed of the balls.
   * @return */
  public int getSpeed() {
        return speed;
    }

    /**
     * A getter for the ball's view
     */
    //cannot change appearance
    public ImageView getView() {
        return view;
    }

    /*
    return y coordinate of the centre of the ball
    */

    int getCenterY() {

        return y + view.getHeight()/2;
    }

    /**
    *return X coordinate of the centre of the ball
    */
    int getCenterX() {

        return x + view.getWidth()/2;
    }

  /**
   * Let the ball move.
   *
   * @param screenWidth the screenwidth
   * @param frameHeight the frameHeight of the screen
   * @param width the width of the item
   */
  @Override
  public void move(int screenWidth, int frameHeight, int width) {

        x -= speed;
        if(x < 0){
            x = screenWidth + width;
            y = (int)Math.floor(Math.random()*(frameHeight-view.getHeight()));
        }
        view.setX(x);
        view.setY(y);
    }



}
