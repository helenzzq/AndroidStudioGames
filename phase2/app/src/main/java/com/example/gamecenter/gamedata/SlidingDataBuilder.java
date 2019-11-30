package com.example.gamecenter.gamedata;

import android.content.SharedPreferences;

public class SlidingDataBuilder extends GameDataBuilder {


    /**
     * @param userName the name of game
     * @param gameData
     */
    public SlidingDataBuilder(String userName, SharedPreferences gameData) {
        super(userName, gameData);
    }

    public SlidingDataBuilder(SharedPreferences gameData) {
        super(gameData);
    }
}
