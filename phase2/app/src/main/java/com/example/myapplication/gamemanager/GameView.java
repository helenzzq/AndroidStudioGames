package com.example.myapplication.gamemanager;

public interface GameView {


    /**
     * Update the score in activity
     * @param score the score of the player
     */
    void updateScore(int score);

    /**
     * Go to the result page 
     */
    void goToResult();


}
