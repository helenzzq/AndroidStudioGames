package com.example.gamecenter.gamedata;

import android.provider.ContactsContract;

public class GameData {


    private GameDataBuilder gameDataBuilder;

    public GameData(GameDataBuilder gameDataBuilder){
        this.gameDataBuilder = gameDataBuilder;

    }

    public void constructGameData(String key, int score, int time, int numDeath){
        this.gameDataBuilder.setScore(key, score);
        this.gameDataBuilder.setDeath(numDeath);
        this.gameDataBuilder.setTime(time);
    }

    public int getTime(){
        return gameDataBuilder.getTime();
    }
    public int getScore(){
        return gameDataBuilder.getScore();
    }

    public int getNumDeath(){
        return gameDataBuilder.getNumDeath();
    }
    public int getHighestScore(){
        return gameDataBuilder.getHighestScore();
    }
}
