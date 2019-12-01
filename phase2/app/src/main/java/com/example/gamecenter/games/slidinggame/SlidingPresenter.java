package com.example.gamecenter.games.slidinggame;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
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
    private com.example.gamecenter.games.slidinggame.activity.SlidingGrid slidingGrid;

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;



    public SlidingPresenter(SlidingManager slidingManager, SlidingGrid gridView) {
        this.slidingManager = slidingManager;
        observers = new ArrayList<>();
        slidingGrid = gridView;

    }

    public void setSlidingView(SlidingActivity slidingView) {
        this.slidingView = slidingView;
    }

    public SlidingManager getSlidingManager() {
        return slidingManager;
    }


    public void restart() {
        slidingView.updateScore(getGameManager().getScore());
        notifyObservers();
        slidingManager.setCardCollection();
    }

    public void swipe(boolean vertical, boolean leftUp) {
        if (vertical) {
            if (leftUp) {
                slidingManager.swipeUp();
            } else {
                slidingManager.swipeDown();
            }
        } else {
            if (leftUp) {
                slidingManager.swipeLeft();
            } else {
                slidingManager.swipeRight();
            }
        }
        checkGameOver();
        slidingView.updateScore(slidingManager.getScore());
        if ((slidingManager.checkNextLevel()) && (SlidingActivity.getIsLevel1())) {
            SlidingActivity.changeLevel();
            slidingView.startLevel2();
            slidingManager.setScore(slidingManager.getScore());


        }
    }


//        slidingManager.swipe(vertical, leftUp);
//        slidingView.addScore(slidingManager.getScore());
//        if(slidingManager.isGameOver()){
//            slidingView.showResult();
//        }


     void checkGameOver(){
        if(slidingManager.isGameOver()){
            slidingView.goToResult();
            slidingGrid.onDestory();
        }
    }

    public void onPause(){
        slidingGrid.onPause();
    }

    public void onResume(){
        slidingGrid.onResume();
    }

    /**
     * A getter for the Game Manager.
     */
    @Override
    public GameManager getGameManager() {
        return slidingManager;
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

    private void onDestroy() {
        slidingView = null;
        slidingGrid = null;
    }
}
