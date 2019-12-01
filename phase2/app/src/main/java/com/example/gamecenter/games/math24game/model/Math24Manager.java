package com.example.gamecenter.games.math24game.model;

import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.games.math24game.strategy.Calculator;

public class Math24Manager implements GameManager {

    private int score;
    private boolean gameOver;
    private QuestionBank questionBank;
    private boolean checkAnswer;
    private int lives;

    public Math24Manager(){
        this.score = 0;
        gameOver =false;
        lives = 3;
        questionBank = new QuestionBank();
    }


    public int[] getQuestion(){
        return questionBank.getRandomQ(checkNextLevel());

    }

    //test if the result equals 24
    public boolean isGameOver(){
        return (checkNextLevel()&& score >= 600 )|| lives <=0 ;
    }

    public boolean isCheckAnswer(){
        return checkAnswer;
    }

    @Override
    public boolean checkNextLevel() {
        return score >= 150;
    }

    public int getLives() {
        return lives;
    }

    //determine if the player's answer can make it to 24
    public int calculate(String equation){
        int result = (int)Math.round(Calculator.getResult(equation));
        if (result == 24) {
            checkAnswer = true;
            addScore();

        }
        else{
            lives -= 1;
            checkAnswer = false;

        }
        return result;
    }


    private void addScore(){
        if (score< 150) {
            score += 50;
        } else {
            score += 100;
        }

    }

    public int getScore() {
        return score;
    }
}

