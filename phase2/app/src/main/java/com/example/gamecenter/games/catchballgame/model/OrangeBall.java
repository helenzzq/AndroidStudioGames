package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

/**
 * An OrangeBall
 */
public class OrangeBall extends Ball {
    /** point of the OrangeBall*/
    private int point;

    /**
     * Create an OrangeBall
     * @param x
     * @param y
     * @param view
     * @param speed
     */
    OrangeBall(int x, int y, ImageView view, int speed){
        super(x,y, view, speed);
        point = 10;


    }

    
    @Override
    public int getPoint() {
        return point;
    }


    //12
    @Override
    public void move(int screenWidth, int frameHeight, int width) {
        super.move(screenWidth,frameHeight, 20);
    }


}
