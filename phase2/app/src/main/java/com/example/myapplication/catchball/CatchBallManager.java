package com.example.myapplication.catchball;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;



import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class CatchBallManager {

    private CatchBoard board;
    private PlayerPrince player;


    CatchBallManager(WindowManager window, FrameLayout frame, int x, int y, List<ImageView> views) {
        board = new CatchBoard(window, frame, x, y, views);
        player = board.getPlayerPrince();
    }

    public void changePos(boolean action_flag) {
        //Call isGaveOver before changePos

        int frameHeight = board.getFrameHeight();
        for (Ball ball : board.getBalls()) {
            ball.move(board.getScreenWidth(), frameHeight, 0, 0);
        }
        player.move(action_flag, frameHeight);

    }


    boolean isGameOver(int score) {
        //if the center of the Ball is in the box,it counts as a hit


        for (Ball ball : board.getBalls()) {
            if (validate(ball.getCenterX(), ball.getCenterY(), player.getY(), player.getSize())) {
                if(ball instanceof BlackBall){
                    return true;}
                else{
                score += ball.getPoint();
                ball.setX(ball.getX() - 10);
                }
            }
        }
        return false;




    }


    private boolean validate(int X, int Y, int itemY, int itemSize) {

        return 0 <= X && X <= itemSize && itemY <= Y && Y <= itemY + itemSize;

    }




}
