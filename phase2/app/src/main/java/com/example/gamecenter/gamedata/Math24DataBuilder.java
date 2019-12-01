package com.example.gamecenter.gamedata;

import android.content.SharedPreferences;

public class Math24DataBuilder extends GameDataBuilder {


    /**
     * @param dataName the name of game
     * @param gameData
     */
    public Math24DataBuilder(String dataName, SharedPreferences gameData) {
        super(dataName, gameData);
    }

    public Math24DataBuilder(SharedPreferences gameData) {
        super(gameData);
    }
}
