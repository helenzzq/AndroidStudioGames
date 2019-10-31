package com.example.myapplication.useraccount;

import android.content.Context;
import android.content.SharedPreferences;

public class userStorage {

    public static final String USERFILE = "userDetails";
    SharedPreferences userLocal ;

    public userStorage(Context context){
        //userLocal = context.getSharedPreferences();
    }
}
