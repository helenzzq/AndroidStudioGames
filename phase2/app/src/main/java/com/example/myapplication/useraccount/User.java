package com.example.myapplication.useraccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User o = (User) obj;
            return(o.getUsername().equals(this.username));
        }
        return false;
    }

}
