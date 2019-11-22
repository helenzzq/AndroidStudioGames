package com.example.myapplication.catchball;

import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.gamemanager.GameController;
import com.example.myapplication.gamemanager.GameManager;
import com.example.myapplication.gamemanager.GameView;
import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;

import java.util.ArrayList;
import java.util.List;

class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;


    private CatchBallManager manager;
    private CatchBallActivity catchBallView;

    CatchBallPresenter(CatchBallActivity boardView, CatchBallManager manager) {
        this.manager = manager;
        this.catchBallView = boardView;
        observers = new ArrayList<>();

    }


    void onStart(MotionEvent action, boolean startFlag, int frameHeight) {
        if (startFlag) {
            catchBallView.makeAction(action);
            catchBallView.setPauseButton();


        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.getBoard().setFrameHeight(frameHeight);

            catchBallView.updateTimer();

        }

    }



    void hitCheck(boolean actionFlag) {
        manager.hitCheck();
        catchBallView.updateScore(manager.getScore());
        if (manager.isGameOver()) {
            catchBallView.hideStartLabel();
            catchBallView.stopTimer();
            catchBallView.showResult();
        } else {
            manager.changePos(actionFlag);
        }
    }

    void onDestroy() {
        catchBallView = null;
    }


    @Override
    public CatchBallManager getGameManager() {
        return manager;
    }

    @Override
    public void setGameManager(GameManager manager) {
        this.manager = (CatchBallManager) manager;

    }

    /**
     * Add an observer, obs, to this class
     * @param obs The observer added
     */
    @Override
    public void register(MyObserver obs) {
        if(!observers.contains(obs))
        {observers.add(obs);
            obs.setSubject(this);}
    }

    @Override
    public void notifyObservers() {
        for (MyObserver obs: observers) {
            obs.update();
        }
    }
}
