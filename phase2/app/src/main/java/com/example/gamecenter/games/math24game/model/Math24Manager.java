package com.example.gamecenter.games.math24game.model;

import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.games.math24game.strategy.Calculator;

public class Math24Manager implements GameManager {

    private int score;
    private boolean gameOver;
    private QuestionBank questionBank;
    private boolean checkAnswer;

    public Math24Manager(){
        this.score = 0;
        gameOver =false;
        questionBank = new QuestionBank();
    }





    public int[] getQuestion(){
        return questionBank.getRandomQ(checkNextLevel());

    }

    //test if the result equals 24
    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isCheckAnswer(){
        return checkAnswer;
    }

    @Override
    public boolean checkNextLevel() {
        return score >= 100;
    }


    //determine if the player's answer can make it to 24
    public int calculate(String equation){
        int result = (int)Math.round(Calculator.getResult(equation));
        checkAnswer = (result == 24);
        return result;
    }


    public int getScore() {
        if(! checkNextLevel()){
            score += 50;
        }
        else{
            score += 100;
        }
        return score;
    }
}

