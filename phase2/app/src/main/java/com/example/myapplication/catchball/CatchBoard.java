package com.example.myapplication.catchball;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

 class CatchBoard extends Observable implements Serializable {
    private int screenWidth;
    private OrangeBall orange;
    private BlackBall black;
    private PinkBall pink;
    private Ball[] balls;
    private PlayerPrince playerPrince;
    private int frameHeight;


    /**
     * Create a Catchboard
     * @param window a windowmanager
     */
    CatchBoard(WindowManager window, int x, int y, ImageView[] views){

        Display display = window.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        playerPrince = new PlayerPrince(views[3]);
        orange = new OrangeBall(x,y, views[0]);
        black = new BlackBall(x,y, views[1]);
        pink = new PinkBall(x,y, views[2]);
        balls = new Ball[]{orange, black, pink};

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