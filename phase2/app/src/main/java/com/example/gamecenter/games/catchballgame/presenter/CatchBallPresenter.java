package com.example.gamecenter.games.catchballgame.presenter;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.catchballgame.activity.CatchBallActivity;
import com.example.gamecenter.games.catchballgame.model.CatchBallManager;
import com.example.gamecenter.games.catchballgame.model.CatchBoard;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;


    private CatchBallManager manager;
    private CatchBallActivity catchBallView;

    public CatchBallPresenter(CatchBallActivity boardView, String level, ImageView[] imgs, WindowManager windowManager) {
        this.catchBallView = boardView;
        observers = new ArrayList<>();
        setUpBoard(level, imgs, windowManager);
    }


    public void onStart(MotionEvent action, boolean startFlag, int frameHeight) {
        if (startFlag) {
            catchBallView.makeAction(action);
            catchBallView.setPauseButton();


        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.setBoardHeight(frameHeight);
            catchBallView.hideStartLabel();
            catchBallView.getGameTimer().start();
            catchBallView.updateTimer();
        }

    }

    public void hitCheck(boolean actionFlag) {
        manager.hitCheck();
        catchBallView.updateScore(manager.getScore());
        if (manager.isGameOver()) {
            catchBallView.getGameTimer().stop();
            catchBallView.goToResult();
        } else {
            manager.changePos(actionFlag);
            if(manager.checkNextLevel()){
                catchBallView.setLevel("LEVEL2");
            }

        }
    }

    public void onDestroy() {
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
     * @param scoreboard
     * @param user
     * @return
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        if(manager.isGameOver())
        {
            scoreboard.addScore(user,manager.getScore());
            manager = null;
            notifyObservers();
            return true;
        }
        return false;
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


    /**
     * Set up Catch Ball game according to level
     * @param level given level
     */
    private void setUpBoard(String level, ImageView[] imgs, WindowManager windowManager) {
        if ("easy".equals(level)) {
            manager = new CatchBallManager(new CatchBoard(windowManager, -80,-80,8,imgs));
        } else {
            manager = new CatchBallManager(new CatchBoard(windowManager, -80,-80,14,imgs));
        }
        notifyObservers();
    }
}
