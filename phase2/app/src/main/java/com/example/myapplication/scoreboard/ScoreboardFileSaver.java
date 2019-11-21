package com.example.myapplication.scoreboard;

import android.content.Context;

import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreboardFileSaver implements Serializable, MyObserver {

    private String fileName;

    private Context context;

    /*
    A List of globalScores
     */
    private ArrayList<Score> GlobalScores  = new ArrayList<>();

    private Scoreboard subject;

    /**
     * Update accordingly after subject calls notifyObservers()
     */

    public ScoreboardFileSaver(Context context,String fileName){
        this.context = context;
        this.fileName = fileName;

    }


    public void loadFromFile(){

    }

    @Override
    public void update() {

    }

    /**
     * Set the subject to be obsevred
     *
     * @param subject to be observed
     */
    @Override
    public void setSubject(MySubject subject) {

    }
}
