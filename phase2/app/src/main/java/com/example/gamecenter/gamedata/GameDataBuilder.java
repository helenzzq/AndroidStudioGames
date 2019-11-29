package com.example.gamecenter.gamedata;

import android.content.Context;
import android.content.SharedPreferences;

public class GameDataBuilder {
    private SharedPreferences gameData;

     GameDataBuilder(String gameName, Context context){
        gameData = context.getSharedPreferences(gameName, Context.MODE_PRIVATE);

    }

    int getScore() {
        return gameData.getInt("score", 0);
    }

    int getHighestScore(){
        return gameData.getInt("highestScore", 0);


    }

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

}
