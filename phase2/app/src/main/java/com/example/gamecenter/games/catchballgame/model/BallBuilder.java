package com.example.gamecenter.games.catchballgame.model;

import android.widget.ImageView;

public class BallBuilder {

    private Ball ball;

    public BallBuilder(){
        ball = new Ball();
    }

    public BallBuilder setX(int x) {
        ball.setX(x);
        return this;
    }

    public BallBuilder setY(int y) {
        ball.setY(y);
        return this;
    }


    public BallBuilder setView(ImageView view) {
        ball.setView(view);
        return this;
    }

    public BallBuilder setPoint(int point){
        ball.setPoint(point);
        return this;
    }
    public BallBuilder setSpeed(int speed) {
        ball.setSpeed(speed);
        return this;
    }

    public boolean checkStatus() {
        return ball.getStatus();
    }

    public BallBuilder move(int screenWidth, int frameHeight, int width) {
        return this;
    }

    public Ball build(){
        return ball;
    }

}
