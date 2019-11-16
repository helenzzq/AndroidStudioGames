package com.example.myapplication.useraccount;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.R;

public class userStorage {

    //allow us to store data in file
    public static final String USERFILE = "userDetails";
    SharedPreferences userDatabase;

    public userStorage(Context context){

        userDatabase = context.getSharedPreferences(USERFILE,context.MODE_PRIVATE);

    }

    public void storeUserData(User user){

        //update information in the shared preferences
        SharedPreferences.Editor fileEditor = userDatabase.edit();
        fileEditor.putString(user.getUsername()+"password",user.getPassword());
        fileEditor.commit();
    }

    public boolean checkPassowrd(User user){

        if(user.getPassword() == null){
            return false;
        }

        if(userDatabase.getString(user.getUsername() + "password","") == user.getPassword())
        {
            return true;
        }


        return false;
    }

    //get details for user loggin
    public User getLoginUser(){
        String name = userDatabase.getString("username","");
        String password = userDatabase.getString("password","");

        User StoredUser = new User(name,password);
        return StoredUser;
    }

    //if a user is login 1,logout 0
    public void setLogin(boolean status){
        SharedPreferences.Editor fileEd = userDatabase.edit();
        fileEd.putBoolean("status",status);
        fileEd.commit();
    }

    //clear users data when they log out

    public void clearUserData(){
        SharedPreferences.Editor fileEd = userDatabase.edit();
        fileEd.clear();
        fileEd.commit();
    }




}
