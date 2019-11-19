package com.example.myapplication.catchball;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

public class CatchBoard {
    private int screenHeight;
    private int screenWidth;
    private int frameHeight;
    private OrangeBall orange;
    private BlackBall black;
    private PinkBall pink;
    private Ball[] balls;
    private PlayerPrince playerPrince;



    /**
     * Create a Catchboard
     * @param window a windowmanager
     */
    CatchBoard(WindowManager window, FrameLayout frame, int x, int y, List<ImageView> views){

        Display disp = window.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);


        screenWidth = size.x;
        screenHeight = size.y;
        playerPrince = new PlayerPrince(views.get(3));
        orange = new OrangeBall(x,y, views.get(0));
        black = new BlackBall(x,y, views.get(1));
        pink = new PinkBall(x,y, views.get(2));
        balls = new Ball[]{orange, black, pink};

    }

    Ball[] getBalls(){

        return balls;

    }

    public BlackBall getBlack() {
        return black;
    }

    public OrangeBall getOrange() {
        return orange;
    }

    public PinkBall getPink() {
        return pink;
    }

    int getScreenHeight() {
        return screenHeight;
    }

    int getScreenWidth() {
        return screenWidth;
    }

     int getFrameHeight() {
        return frameHeight;
    }

    Object[] getGameElements(){
        return new Object[]{orange, black, pink, playerPrince};
    }
    PlayerPrince getPlayerPrince(){
        return playerPrince;
    }

}