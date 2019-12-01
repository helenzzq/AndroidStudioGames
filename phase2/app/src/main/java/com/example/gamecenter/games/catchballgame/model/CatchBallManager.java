package com.example.gamecenter.games.catchballgame.model;


import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.io.Serializable;

/**
 * The class for the game manager of the first game: Catch Ball.
 * */
public class CatchBallManager implements GameManager, Serializable {

  /** Game board.*/
  private CatchBoard board;

  /** The current player.*/
  private PlayerPrince player;

  /** Whether game over or not.*/
  private boolean gameOver;

  /** Game score.*/
  private int score;

  /**
   * Constructor of catchBoard.
   * @param catchBoard */
  public CatchBallManager(CatchBoard catchBoard) {
        board = catchBoard;
        player = board.getPlayerPrince();
        gameOver = false;

    }

  /**
   * Change the position of the ball.
   *
   * @param actionFlag */
  public void changePos(boolean actionFlag) {
        //Call hitcheck() before changePos
        int frameHeight = board.getFrameHeight();
        for (Ball ball : board.getBalls()) {
            ball.move(board.getScreenWidth(), frameHeight, 0);
        }
        player.move(actionFlag, frameHeight);

    }

  /**
   * Set the height of the game board.
   * @param frameHeight */
  public void setBoardHeight(int frameHeight) {
        board.setFrameHeight(frameHeight);

    }

  /**
   * Check whether player hit the ball.
   * */
  public void hitCheck() {
        //if the center of the Ball is in the box,it counts as a hit
         for (Ball ball : board.getBalls()) {
            if (validate(ball.getCenterX(), ball.getCenterY(), player.getY(), player.getSize())) {
                if(ball instanceof BlackBall){
                    gameOver = true;
                    break;
                }
                else{
                    score += ball.getPoint();
                ball.setX(-10);
                }
            }
        }

    }

  /**
   * Get game score.
   * @return */
  @Override
  public int getScore() {
        return score;
    }

  /**
   * Determine whether game over.
   * @return */
  @Override
  public boolean isGameOver() {
        return gameOver;
    }

    public CatchBoard getBoard() {
        return board;
    }

  /**
   * Update the size of the player.
   * */
  public void updatePlayerSize() {
        player.setY((int)player.getView().getY());
        player.setSize(player.getView().getHeight());
    }

  /**
   * Restrict the balls on the screen, not beyond the screen.
   *
   * @param X
   * @param Y
   * @param itemY
   * @param itemSize
   * @return
   */
  private boolean validate(int X, int Y, int itemY, int itemSize) {

        return 0 <= X && X <= itemSize && itemY <= Y && Y <= itemY + itemSize;

    }

  /**
   * Check if going to the next level.
   * @return */
  public boolean checkNextLevel() {
        if (score >= 50) {
            board.setBaseSpeed(14);
            return true;

        }
        return false;

    }

    /**
     * @param scoreboard
     * @param user
     * @return
     */
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if(isGameOver())
        {
            scoreboard.addScore(user,this.getScore());
            return true;
        }
        return false;
    }



}
