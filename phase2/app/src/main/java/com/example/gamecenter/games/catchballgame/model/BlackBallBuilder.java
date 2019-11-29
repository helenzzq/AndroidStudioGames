package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

public class BlackBallBuilder extends BallBuilder {

    private Ball ball;

    public BlackBallBuilder(){
        ball = new Ball();
    }

    public BlackBallBuilder move(int screenWidth, int frameHeight, int width) {
        ball.move(screenWidth,frameHeight, 10);
        return this;
    }

}
