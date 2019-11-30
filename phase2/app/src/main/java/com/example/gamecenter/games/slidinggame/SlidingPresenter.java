package com.example.gamecenter.games.slidinggame;

import android.content.Intent;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.slidinggame.activity.SlidingActivity;
import com.example.gamecenter.games.slidinggame.activity.SlidingGrid;
import com.example.gamecenter.games.slidinggame.model.SlidingManager;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class SlidingPresenter implements GameController, MySubject {
    private SlidingManager slidingManager;
    private SlidingActivity slidingView;
    private com.example.gamecenter.games.slidinggame.activity.SlidingGrid SlidingGrid;

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;



    public SlidingPresenter(SlidingManager slidingManager, SlidingGrid gridView) {
        this.slidingManager = slidingManager;
        observers = new ArrayList<>();
        SlidingGrid = gridView;

    }

    public void setSlidingView(SlidingActivity slidingView) {

        this.slidingView = slidingView;
    }

    public SlidingManager getSlidingManager() {
        return slidingManager;
    }


    public void restart() {
        slidingView.updateScore(0);
        notifyObservers();
        slidingManager.setCardCollection();
    }

    public void swipe(boolean vertical, boolean leftUp) {
        if(vertical){
            if(leftUp){
                slidingManager.swipeUp();
            }
            else{
                slidingManager.swipeDown();
            }
        }
        else {
            if(leftUp){
                slidingManager.swipeLeft();
            }
            else{
                slidingManager.swipeRight();
            }
        }
        slidingView.updateScore(slidingManager.getScore());
        if((slidingManager.isNextLevel())&&(slidingView.getIsLevel1())){
            SlidingActivity.notLevel1();
//            SlidingActivity.setNum();
//            slidingManager.setNum(4);
            int level1Score = slidingManager.getScore();
            slidingView.startLevel2();
            slidingView.updateScore(level1Score);
            slidingManager.setScore(level1Score);


        }
        if(slidingManager.isGameOver()){
            slidingView.goToResult();
        }

//        slidingManager.swipe(vertical, leftUp);
//        slidingView.addScore(slidingManager.getScore());
//        if(slidingManager.isGameOver()){
//            slidingView.showResult();
//        }
    }


    /**
     * A getter for the Game Manager.
     */
    @Override
    public GameManager getGameManager() {
        return null;
    }

    /**
     * A setter for the Game Manager
     *
     * @param manager The game manager.
     */
    @Override
    public void setGameManager(GameManager manager) {

    }

    /**
     * @param scoreboard The game's scoreboard.
     * @param user The user's username.
     * @return return true if the game is over. Otherwise return false.
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
       if(slidingManager.isGameOver()){
           scoreboard.addScore(user,slidingManager.getScore());
           slidingManager = null;
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

    public void onDestroy() {
        slidingView = null;
    }
}
