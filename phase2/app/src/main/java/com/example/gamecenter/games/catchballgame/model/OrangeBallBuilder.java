package com.example.gamecenter.games.catchballgame.model;

public class OrangeBallBuilder extends BallBuilder {

    private Ball ball;

    public OrangeBallBuilder(){
        ball = new Ball();
    }

    public OrangeBallBuilder move(int screenWidth, int frameHeight, int width) {
        ball.move(screenWidth,frameHeight, 20);
        return this;
    }
}
