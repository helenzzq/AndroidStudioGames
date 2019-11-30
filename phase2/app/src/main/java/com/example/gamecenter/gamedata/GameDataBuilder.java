package com.example.gamecenter.gamedata;

import android.content.Context;
import android.content.SharedPreferences;

/**
* A game data builder.
*/

public class GameDataBuilder {
    /**
     * The gameData
     */
    private SharedPreferences gameData;

    /**
     *
     * @param gameName the name of game
     * @param context the context
     */
     GameDataBuilder(String gameName, Context context){
        gameData = context.getSharedPreferences(gameName, Context.MODE_PRIVATE);

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
        return gameData.getInt("highestScore", 0);
    }

    /**
     * Return the numerical time value according to key
     * @return time
     */

    int getTime() {
        return gameData.getInt("Time", 0);
    }


    void setScore(String dataName, int score) {
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt(dataName, score);
        editor.apply();
    }


    void setTime(int time) {
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt("Time", time);
        editor.apply();
    }

    void setDeath(int numDeath) {
        SharedPreferences.Editor editor = gameData.edit();
        editor.putInt("death", numDeath);
        editor.apply();
    }

    int getNumDeath(){
         return gameData.getInt("death",0);
    }
}
