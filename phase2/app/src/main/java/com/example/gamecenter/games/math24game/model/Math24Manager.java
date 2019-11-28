package com.example.gamecenter.games.math24game.model;

import com.example.gamecenter.GameManager;
import com.example.gamecenter.games.math24game.strategy.Calculator;

public class Math24Manager implements GameManager {

    private int score;
    private boolean gameOver;
    private QuestionBank questionBank;

    public Math24Manager(){
        this.score = 10;
        gameOver =false;
        questionBank = new QuestionBank();
    }


    private int setAddScore(int coefficient){
        return score * coefficient;
    }


    public int[] getQuestion(String level){
        if (level.equals("easy")){
            score = setAddScore(5);
        }
        else{
            score = setAddScore(10);
        }

        return questionBank.getRandomQ(level);
    }

    //test if the result equals 24
    public boolean isGameOver(){
        return gameOver;
    }


    //determine if the player's answer can make it to 24
    public int calculate(String equation){
        int result = (int)Math.round(Calculator.getResult(equation));
        gameOver = (result == 24);
        return result;
    }

    public int getScore() {
        return score;
    }
}
