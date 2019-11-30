package com.example.gamecenter.gamedata;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
* A game data builder.
*/

class GameDataBuilder {
    /**
     * The gameData
     */
    private  SharedPreferences gameData;
    private SharedPreferences.Editor editor;

    /**
     *
     * @param dataName the name of game
     */
    @SuppressLint("CommitPrefEdits")
    GameDataBuilder(String dataName, SharedPreferences gameData){
        this.gameData = gameData;
        editor = gameData.edit();
        setUserName(dataName);
    }
    @SuppressLint("CommitPrefEdits")
    GameDataBuilder(SharedPreferences gameData){
        this.gameData = gameData;
        editor = gameData.edit();
    }

    public SharedPreferences getGameData() {
        return gameData;
    }

    /**
     * Return the numerical score value according to key
     * @return score
     */

    int getScore() {
        return gameData.getInt("score", 0);
    }

    /**
     * Return the numerical highest score value according to key
     * @return highest score
     */
    int getHighestScore(){
        return gameData.getInt("highestGameScore", 0);
    }

    /**
     * Return the used time for the game of the user
     * @return time
     */

    int getTime() {
        return gameData.getInt("Time", 0);
    }


    void setHighestScore(int score){
        editor.putInt("highestGameScore", score);
        editor.apply();
    }

    void setUserName(String userName){
        editor.putString("userName", userName);
        editor.apply();
    }

    String getUserName(){
        return gameData.getString("userName", "");
    }

    void setScore(int score) {
        editor.putInt("score", score);
        if (score > getHighestScore()){
            setHighestScore(score);
        }
        editor.apply();
    }


    void setTime(int time) {
        editor.putInt("Time", time);
        editor.apply();
    }

    void setDeath(int numDeath) {
        editor.putInt("death", numDeath);
        editor.apply();
    }

    int getNumDeath(){
         return gameData.getInt("death",0);
    }
}
