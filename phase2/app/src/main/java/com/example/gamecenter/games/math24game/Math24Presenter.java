package com.example.gamecenter.games.math24game;

import android.widget.Button;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.math24game.activity.Math24Activity;
import com.example.gamecenter.games.math24game.model.Math24Manager;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Math24Presenter implements GameController , MySubject {

    private Math24Manager mathManager;
    private Math24Activity mathView;
    private static List<MyObserver> observers;


    public Math24Presenter(Math24Manager mathManager, Math24Activity mathView) {
        this.mathManager = mathManager;
        this.mathView = mathView;
        observers = new ArrayList<>();
    }

    public void onStart() {
        mathView.setMessage("");
        mathView.getNextBtn().setEnabled(false);
        int[] questions = mathManager.getQuestion();
        Button[] nums = mathView.getNums();
        for (int i = 0; i < 4; i++) {
            mathView.setNumText(nums[i],questions[i]);
        }
        if (mathManager.checkNextLevel()){
            mathView.setLevel("Level2");
        }
    }



    public void calculateResult(String mathExpression){
        int result = mathManager.calculate(mathExpression);
        mathView.showResult(result);
        checkCurrentResult();

    }

    private void checkCurrentResult() {
        if(mathManager.isCheckAnswer()){
            mathView.setMessage("Congratulations!\n Click Next to proceed");
            mathView.getNextBtn().setEnabled(true);
            mathView.disableAll();
            mathView.updateScore(mathManager.getScore());
        }
        else{
            mathView.setMessage("It's Wrong!!!");
            mathView.updateLives();
            if(mathView.getGameTimer().getTime()/60 >= 3) {
                mathView.showFailure();
                mathView.goToResult();
            }
        }

    }

    public void onDestroy() {
        mathView = null;
    }


    @Override
    public Math24Manager getGameManager() {
        return this.mathManager;
    }



    /**
     * Register the MyObserver object to observe
     *
     * @param obj to register
     */
    @Override
    public void register(MyObserver obj) {
        if(!observers.contains(obj))
        {observers.add(obj);
            obj.setSubject(this);}
    }

    /**
     * A method to notifyObservers to change
     */
    @Override
    public void notifyObservers() {
        for (MyObserver obj:observers){
            obj.update();
        }
    }
}

