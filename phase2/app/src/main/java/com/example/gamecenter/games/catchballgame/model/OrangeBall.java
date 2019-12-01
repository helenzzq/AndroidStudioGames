package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * A class of the orange ball.
 * */
public class OrangeBall extends Ball {

  /**
   * Point of the ball.
   * */
  private int point;

  /** Constructor for the orange ball.
   * @param x
   * @param y
   * @param view
   * @param speed
   */
  OrangeBall(int x, int y, ImageView view, int speed) {
        super(x,y, view, speed);
        point = 10;


    }

  /**
   * A getter for the point of tha orange ball.
   * @return */
  @Override
  public int getPoint() {
        return point;
    }

  /**
   * Let the orange ball move.
   *
   * @param screenWidth the screenwidth
   * @param frameHeight the frameHeight of the screen
   * @param width the width of the item
   */
  // 12
  @Override
  public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 20);
    }


}
