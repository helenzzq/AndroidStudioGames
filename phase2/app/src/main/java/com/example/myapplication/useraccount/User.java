package com.example.myapplication.useraccount;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;

    public User(String username,String password){

        this.username=username;

        this.password=password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
}
