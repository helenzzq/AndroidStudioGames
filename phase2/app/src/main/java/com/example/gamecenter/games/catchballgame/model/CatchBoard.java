package com.example.gamecenter.games.catchballgame.model;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Observable;

public class CatchBoard extends Observable implements Serializable {
    private int screenWidth;
    private OrangeBall orange;
    private BlackBall black;
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
        orange = new OrangeBall(x,y,views[0],baseSpeed);
        pink = new PinkBall(x,y,views[2],baseSpeed+8);
        black = new BlackBall(x,y,views[1],baseSpeed+4);

        //orange = new OrangeBall().setX(x).setY(y).setView(views[0]).setPoint(10).setSpeed(baseSpeed).build();
        //black = new BlackBall().setX(x).setY(y).setView(views[1]).setPoint(0).setSpeed(baseSpeed+4).build();
        //pink = new PinkBallBuilder().setX(x).setY(y).setView(views[2]).setPoint(30).setSpeed(baseSpeed+8).build();
        balls = new Ball[] {orange,black,pink};

    }

     void setBaseSpeed(int baseSpeed){
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