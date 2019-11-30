package com.example.gamecenter.gamedata;

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
     * @param score numerical representation of score
     * @param time numerical representation of time used
     * @param numDeath represent the number for the user's death in game
     */

    public void constructGameData(int score, int time, int numDeath){
        this.gameDataBuilder.setScore(score);
        this.gameDataBuilder.setDeath(numDeath);
        this.gameDataBuilder.setTime(time);
    }


    public GameDataBuilder getGameDataBuilder() {
        return gameDataBuilder;
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

    public String getUserName(){
        return gameDataBuilder.getUserName();
    }
    /**
     *
     * @return the highest score in gameDataBuilder
     */
    public int getHighestScore(){
        return gameDataBuilder.getHighestScore();
    }
}
