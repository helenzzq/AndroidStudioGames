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
    private PinkBall pink;
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
        orange = new OrangeBall(x,y, views[0],baseSpeed);
        black = new BlackBall(x,y, views[1], baseSpeed + 4);
        pink = new PinkBall(x,y, views[2], baseSpeed + 8);
        balls = new Ball[] {orange,black,pink};
//        balls = new ArrayList<>();
//        for (int i = 0; i< 2; i++){
//            balls.add( new OrangeBall(x+10*i,y+10*i, views[0],baseSpeed));
//            balls.add( new BlackBall(x+10*i,y+10*i, views[1], baseSpeed + 4));
//            balls.add(new PinkBall(x+10*i,y+10*i, views[2], baseSpeed + 8));
//        }

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