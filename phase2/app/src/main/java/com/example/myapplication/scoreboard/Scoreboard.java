package com.example.myapplication.scoreboard;

import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.Subject;

public class Scoreboard implements Serializable, MySubject {

    private ArrayList<Score> GlobalScore;

    private static List<MyObserver> observers;

    public Scoreboard(){
        GlobalScore = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public Scoreboard(ArrayList<Score> scores){
        GlobalScore = scores;
        observers = new ArrayList<>();
    }

    public ArrayList<Score> getGlobalScore(){
        return GlobalScore;
    }

    public void setGlobalScore(ArrayList<Score> globalScore) {
        GlobalScore = globalScore;
    }

    public void sortScores(ArrayList<Score> scores){
        Collections.sort(scores);
    }


    /**
     * Register the MyObserver object to observe
     *
     * @param obj to register
     */
    @Override
    public void register(MyObserver obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!observers.contains(obj)) {
            observers.add(obj);
            obj.setSubject(this);
        }

    }
        @Override
        public void notifyObservers(){
            for (MyObserver obj : observers) {
                obj.update();
            }
    }
}