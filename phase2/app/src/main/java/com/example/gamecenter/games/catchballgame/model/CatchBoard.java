package com.example.gamecenter.games.catchballgame.model;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Observable;

/**
 * The class for the game board of the first game:  Catch Ball.
 *
 * */
public class CatchBoard extends Observable implements Serializable {
  /** The width of the screen. */
  private int screenWidth;

    /** Balls.*/
    private OrangeBall orange;
    private BlackBall black;
    private Ball pink;
    private Ball[] balls;

    /** The player.*/
    private PlayerPrince playerPrince;

    /** The height of the frame.*/
    private int frameHeight;


    /**
     * Create a Catchboard
     * @param window a windowmanager
     */
    public CatchBoard(WindowManager window, int x, int y, int baseSpeed, ImageView[] views){

        Display display = window.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        playerPrince = new PlayerPrince(views[3]);
        orange = new OrangeBall(x,y,views[0],baseSpeed);
        pink = new PinkBall(x,y,views[2],baseSpeed+8);
        black = new BlackBall(x,y,views[1],baseSpeed+4);

        balls = new Ball[] {orange,black,pink};

    }

  /**
   * Set the base speed of the balls.
   * @param baseSpeed */
  void setBaseSpeed(int baseSpeed) {
         for (int i = 0; i < 3; i ++){
             balls[i].setSpeed(baseSpeed + i * 4);
         }
     }

  /**
   * A getter for the balls.
   *
   * @return */
  Ball[] getBalls() {
        return balls;

    }

  /**
   * A getter for the screen width.
   *
   * @return */
  int getScreenWidth() {
        return screenWidth;
    }

  /**
   * A setter for the frame height.
   *
   * @param frameHeight */
  void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

  /**
   * A getter for the frame hright.
   *
   * @return */
  int getFrameHeight() {
        return frameHeight;
    }

  /**
   * A getter for the player.
   *
   * @return */
  PlayerPrince getPlayerPrince() {
        return playerPrince;
    }

}