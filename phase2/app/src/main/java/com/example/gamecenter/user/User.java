package com.example.gamecenter.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String username;
    private String password;
    private List<String> gameFile;
    private int score;

    public User(String username,String password){

        this.username=username;

        this.password=password;

        this.score = 0;
    }

    private List<String> generateGameFiles(){
        List<String> gameFiles = new ArrayList<>();
        gameFiles.add(this.username + "CatchBall.ser");
        gameFiles.add(this.username + "Weapon.ser");
        gameFiles.add(this.username + "Math24.ser");
        return gameFiles;
    }


    /**
     * Returns the file containing this user's data.
     */
    public String getCatchBallGameFile() {
        return gameFile.get(0);
    }


    public String getWeaponGameFile(){return gameFile.get(1);}

    public String getMath24GameFile(){return gameFile.get(2);}

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
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
