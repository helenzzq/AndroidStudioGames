package com.example.gamecenter.gamedata;

import android.provider.ContactsContract;

public class GameData {

    /**
     * The gameDataBuilder
     */

    private GameDataBuilder gameDataBuilder;

    /**
     * Construct a GameDataBuilder
     * @param gameDataBuilder The gameDataBuilder
     */

    public GameData(GameDataBuilder gameDataBuilder){
        this.gameDataBuilder = gameDataBuilder;

    }

    /**
     *
     * @param scoreType represents whether the score or highest score to be displayed
     * @param score numerical representation of score
     * @param time numerical representation of time used
     * @param numDeath represent the number for the user's death in game
     */

    public void constructGameData(String scoreType, int score, int time, int numDeath){
        this.gameDataBuilder.setScore(scoreType, score);
        this.gameDataBuilder.setDeath(numDeath);
        this.gameDataBuilder.setTime(time);
    }

    /**
     *
     * @return the current time gameDataBuilder
     */

    public int getTime(){
        return gameDataBuilder.getTime();
    }

    /**
     *
     * @return the current score in gameDataBuilder
     */
    public int getScore(){
        return gameDataBuilder.getScore();
    }

    /**
     *
     * @return the current number of death in gameDataBuilder
     */
    public int getNumDeath(){
        return gameDataBuilder.getNumDeath();
    }

    /**
     *
     * @return the highest score in gameDataBuilder
     */
    public int getHighestScore(){
        return gameDataBuilder.getHighestScore();
    }
}
