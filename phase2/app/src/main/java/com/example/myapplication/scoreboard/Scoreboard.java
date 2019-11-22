package com.example.myapplication.scoreboard;

import android.icu.text.Edits;

import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;
import com.example.myapplication.useraccount.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.NoSuchElementException;

public class Scoreboard implements Serializable, MySubject, Iterator {

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

    public String getScoreValues(User currentPlayer){

        ArrayList<Score> scoreList;
        int numScores;

        scoreList = GlobalScore;

        if (scoreList.size() < 5){
            numScores = scoreList.size();
        }

        else
            {
                numScores = 5;
            }

        // ListIterator to traverse the list
       // ListIterator iterator = GlobalScore.listIterator();

        StringBuilder scoreValues = new StringBuilder();
        for (int i = 0; i < numScores; i++) {
            Score currentItem = scoreList.get(i);
            scoreValues.append(String.format(Locale.US, "%s: %d",
                    currentItem.getUsername(), currentItem.getScore())).append("\n");
        }
        return scoreValues.toString();
    }

    public void addScore(int score,String currentPlayer){
        Score s = new Score(score,currentPlayer);
        GlobalScore.add(s);
        sortScores(GlobalScore);
        notifyObservers();
    }

    public ArrayList<Score> getGlobalScoreboard(){
        return GlobalScore;
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

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Object next() {
        return null;
    }
}