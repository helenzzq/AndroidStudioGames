package com.example.myapplication.useraccount;

import android.content.Context;
import android.content.SharedPreferences;

public class userStorage {

    public static final String USERFILE = "userDetails";
    SharedPreferences userDatabase;

    public userStorage(Context context){

        userDatabase = context.getSharedPreferences(USERFILE,context.MODE_PRIVATE);

    }

    public void storeUserData(User user){

        //update information in the shared preferences
        SharedPreferences.Editor fileEditor = userDatabase.edit();
        fileEditor.putString("username",user.username);
        fileEditor.putString("password",user.password);
        fileEditor.commit();
    }

    public User getLoginUser(){
        String name = userDatabase.getString("username","");
        String password = userDatabase.getString("password","");

        User StoredUser = new User(name,password);

        return StoredUser;
    }
    
}
