package com.example.myapplication.scoreboard;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable {

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



    @Override
    public int compareTo(Score other) {

        return Integer.compare(this.getScore(),other.getScore());
    }
}
