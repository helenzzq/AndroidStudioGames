package com.example.myapplication.math24;

import android.widget.Button;

import com.example.myapplication.gamemanager.GameController;
import com.example.myapplication.gamemanager.GameManager;

public class Math24Presenter implements GameController {

    private Math24Manager mathManager;
    private Math24Activity mathView;


    Math24Presenter(Math24Manager mathManager, Math24Activity mathView) {
        this.mathManager = mathManager;
        this.mathView = mathView;
    }
    public void onStart(String level){
        int[] questions = mathManager.getQuestion(level);
        Button[] nums = mathView.getNums();
        for (int i = 0; i <4;i++){
            mathView.setNumText(nums[i],questions[i]);
        }


    }

    void calculateResult(String mathExpression){
        int result = mathManager.calculate(mathExpression);
        mathView.showResult(result);

        checkToAddScore();

    }
    public void checkToAddScore(){
        if(mathManager.isGameOver()){
            mathView.setMessage("Congratulations! \n");
            mathView.resetAll();
            mathView.updateScore(mathManager.getScore());
            mathView.goToResult();
        }
        else{
            mathView.setMessage("It's Wrong!!!");
            mathView.lostLife();
        }

    }

    public void onDestroy() {
        mathView = null;
    }


    @Override
    public Math24Manager getGameManager() {
        return mathManager;
    }

    @Override
    public void setGameManager(GameManager manager) {

    }
}
