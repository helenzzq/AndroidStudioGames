package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * A class for the pink ball.
 * */
public class PinkBall extends Ball {

  /**
   * The point of the pink ball.
   * */
  private int point;

  /** Constructor for the pink ball class.
   * @param x
   * @param y
   * @param view
   * @param speed
   */
  PinkBall(int x, int y, ImageView view, int speed) {
        super(x,y, view, speed);
        point = 30;
    }

  /**
   * A getter for the point of the pink ball.
   * @return */
  @Override
  public int getPoint() {
        return point;
    }

  /**
   * Let the pink ball move.
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