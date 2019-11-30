package com.example.gamecenter.gamedata;

import android.content.Context;
import android.content.SharedPreferences;

public class CatchBallDataBuilder extends GameDataBuilder {

    /**
     * Build a game data file for CatchBall according to current context
     * @param context The context
     */
    public CatchBallDataBuilder(Context context) {
        super("catchBall", context);
    }

}
