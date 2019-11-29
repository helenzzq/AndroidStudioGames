package com.example.gamecenter.games.catchballgame.model;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Observable;

import static android.view.View.X;

public class CatchBoard extends Observable implements Serializable {
    private int screenWidth;
    private Ball orange;
    private Ball black;
    private Ball pink;
    private Ball[] balls;
    private PlayerPrince playerPrince;
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
        orange = new OrangeBallBuilder().setX(x).setY(y).setView(views[0]).setPoint(10).setSpeed(baseSpeed).build();
        black = new BlackBallBuilder().setX(x).setY(y).setView(views[1]).setPoint(0).setSpeed(baseSpeed+4).build();
        pink = new PinkBallBuilder().setX(x).setY(y).setView(views[2]).setPoint(30).setSpeed(baseSpeed+8).build();
        balls = new Ball[] {orange,black,pink};

    }

     void setBasespeed(int baseSpeed){
         for (int i = 0; i < 3; i ++){
             balls[i].setSpeed(baseSpeed + i * 4);
         }

     }


     Ball[] getBalls(){
        return balls;

    }

    int getScreenWidth() {
        return screenWidth;
    }

    void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    int getFrameHeight() {
        return frameHeight;
    }

    PlayerPrince getPlayerPrince(){
        return playerPrince;
    }

}