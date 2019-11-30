package com.example.gamecenter.gamedata;

import android.content.SharedPreferences;

public class CatchBallDataBuilder extends GameDataBuilder {


    /**
     * Build a game data file for CatchBall according to current context
     */
    public CatchBallDataBuilder(String userName, SharedPreferences gameData) {
        super(userName + "catchBall", gameData);

    }
    public CatchBallDataBuilder(SharedPreferences gameData){
        super(gameData);
    }


}
