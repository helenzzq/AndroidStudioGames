package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;


/**
 *  A class of the black ball.
 */
class BlackBall extends Ball {

  /**
   * The point of the ball.
   * */
  private int point;

  /**
   * Constructor of the blackball.
   *
   * @param x
   * @param y
   * @param view
   * @param speed
   */
  BlackBall(int x, int y, ImageView view, int speed) {
        super(x,y, view, speed);
        point = 0;

    }

  /**
   * A getter of the point of the black ball.
   * @return */
  @Override
  public int getPoint() {
        return point;
    }

  /**
   * Let the black ball move.
   *
   * @param screenWidth the screenwidth
   * @param frameHeight the frameHeight of the screen
   * @param width the width of the item
   */
  @Override
  public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 10);
    }


}

