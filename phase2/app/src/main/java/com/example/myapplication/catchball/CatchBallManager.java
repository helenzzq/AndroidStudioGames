package com.example.myapplication.catchball;

import android.view.WindowManager;
import android.widget.ImageView;


import com.example.myapplication.gamemanager.GameManager;

import java.io.Serializable;

class CatchBallManager implements GameManager, Serializable {

    private CatchBoard board;
    private PlayerPrince player;
    private boolean gameOver;
    private int score;


    CatchBallManager(CatchBoard catchBoard) {
        board = catchBoard;
        player = board.getPlayerPrince();
        gameOver = false;

    }


    void changePos(boolean action_flag) {
        //Call hitcheck() before changePos
        int frameHeight = board.getFrameHeight();
        for (Ball ball : board.getBalls()) {
            ball.move(board.getScreenWidth(), frameHeight, 0);
        }
        player.move(action_flag, frameHeight);

    }


     void hitCheck() {
        //if the center of the Ball is in the box,it counts as a hit
         for (Ball ball : board.getBalls()) {
            if (validate(ball.getCenterX(), ball.getCenterY(), player.getY(), player.getSize())) {
                if(ball instanceof BlackBall){
                    gameOver = true;
                break;}
                else{
                    score += ball.getPoint();
                ball.setX(-10);
                }
            }
        }

    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isGameOver(){
        return gameOver;
    }

    CatchBoard getBoard() {
        return board;
    }

    void updatePlayerSize(){
        player.setY((int)player.getView().getY());
        player.setSize(player.getView().getHeight());
    }


    private boolean validate(int X, int Y, int itemY, int itemSize) {

        return 0 <= X && X <= itemSize && itemY <= Y && Y <= itemY + itemSize;

    }






}
