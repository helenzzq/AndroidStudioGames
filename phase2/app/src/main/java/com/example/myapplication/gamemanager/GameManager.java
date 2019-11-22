package com.example.myapplication.gamemanager;

/**
 * An Interface of GameManager
 */
public interface GameManager {

    /**
     * Get the Score of the game.
     */
    int getScore();

    /**
     * Return true iff finish the game.
     */
    boolean isGameOver();


}
