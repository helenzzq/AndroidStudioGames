package com.example.myapplication.math24;

import android.widget.Button;

import com.example.myapplication.GameController;
import com.example.myapplication.GameManager;
import com.example.myapplication.MyObserver;
import com.example.myapplication.MySubject;
import com.example.myapplication.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Math24Presenter implements GameController , MySubject {

    private Math24Manager mathManager;
    private Math24Activity mathView;
    private static List<MyObserver> observers;


    Math24Presenter(){
        observers = new ArrayList<>();
    }

    Math24Presenter(Math24Manager mathManager, Math24Activity mathView) {
        this.mathManager = mathManager;
        this.mathView = mathView;
        observers = new ArrayList<>();
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
//
//    @Override
//    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
//        if(slidingManager.isGameOver()){
//            scoreboard.addScore(user,slidingManager.getScore());
//            slidingManager = null;
//            notifyObservers();
//            return true;
//        }
//        return false;
//    }
    public void checkToAddScore(){
        if(mathManager.isGameOver()){
            mathView.setMessage("Congratulations! \n");
            mathView.resetAll();
            mathView.updateScore(mathManager.getScore());
            mathView.goToResult();
        }
        else{
            mathView.setMessage("It's Wrong!!!");
            mathView.updateLives();
            if(mathView.getNumLives() == 0) {
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

    @Override
    public void setGameManager(GameManager manager) {
        this.mathManager =(Math24Manager) manager;
    }

    /**
     * @param scoreboard
     * @param user
     * @return
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if(mathManager.isGameOver()){
            scoreboard.addScore(user,mathManager.getScore());
            mathManager = null;
            notifyObservers();
            return true;
        }
        return false;
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
