package com.example.myapplication.scoreboard;

import java.io.Serializable;

public class Score implements Serializable ,Comparable<Score>{

    private int score;

    private String user;

    public Score(int score, String user) {
        this.score = score;
        this.user = user;
    }

    /*public Score(int score){
        this.score = score;
        this.user = "Anonymously"
    }
    *
     */

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername(){return user;}


    /**
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Score o) {
        return this.getScore() - o.getScore();
    }
}