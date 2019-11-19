package com.example.myapplication.catchball;

import android.widget.ImageView;

class PlayerPrince {

    private ImageView view;
    private int size;
    private int y;
    
    PlayerPrince(ImageView view) {
        this.view = view;
        this.size = view.getHeight();
    }

    /**
     * A setter for the y coordinate of the ball
     */
    void setY(int y) {
        this. y = y;
    }


    /**
     * A getter for the y coordinate of the ball
     */

    int getY() {
        return y;
    }

    /**
     * A setter for the ball's view
     */
    void setView(ImageView appearance) {
        this.view = appearance;
    }
    /**
     * A getter for the ball's view
     */
    //cannot change appearance
    ImageView getView() {
        return view;
    }

    void setSize(int size) {
        this.size = size;
    }

    int getSize() {
        return size;
    }

    void move(boolean action, int frameHeight){
        if(action){
            //Touching
            y -= 20;
        } else{
            //Releasing
            y += 20;
        }

        //check box position
        if(y<0)
            y=0;

        if(y > frameHeight - size)
            y = frameHeight - size;

        view.setY(y);
        
    }
    
    
}
