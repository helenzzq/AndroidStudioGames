package com.example.gamecenter.gameinterface;


/**
 * An Interface of GameManager
 */
public interface GameManager {

    /**
     * Return the Score of the game.
     */
    int getScore();

    /**
     * Check whether the game is over;
     */
    boolean isGameOver();


    /**
     * Check whether we can go to next level
     */
    boolean checkNextLevel();


}
