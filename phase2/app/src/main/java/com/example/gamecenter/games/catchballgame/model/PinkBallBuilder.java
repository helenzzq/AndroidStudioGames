package com.example.gamecenter.games.catchballgame.model;

public class PinkBallBuilder extends BallBuilder {
    private Ball ball;

    public PinkBallBuilder(){
        ball = new Ball();
    }

    public PinkBallBuilder move(int screenWidth, int frameHeight, int width) {
        ball.move(screenWidth,frameHeight, 20);
        return this;
    }
}
