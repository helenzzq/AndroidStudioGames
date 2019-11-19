package com.example.myapplication.catchball;

import android.widget.ImageView;

public interface MoveItem {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    ImageView getView();

    void setView(ImageView views);


    void move(int screenWidth, int frameHeight, int changeInX, int width);

}