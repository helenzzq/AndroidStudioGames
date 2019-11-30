package com.example.gamecenter.gamedata;

import android.content.Context;

public class SlidingDataBuilder extends GameDataBuilder {

    /**
     * Build a game data file for sliding according to current context
     * @param context The context
     */
    SlidingDataBuilder( Context context) {
        super("sliding", context);
    }
}
