package com.example.myapplication.catchball;

import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.myapplication.gamecenter.GameController;
import com.example.myapplication.gamecenter.GameManager;
import com.example.myapplication.MyObserver;
import com.example.myapplication.MySubject;
import com.example.myapplication.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;


class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;


    private CatchBallManager manager;
    private CatchBallActivity catchBallView;

    CatchBallPresenter(CatchBallActivity boardView,String level, ImageView[] imgs, WindowManager windowManager) {
        this.catchBallView = boardView;
        observers = new ArrayList<>();
        setUpBoard(level, imgs, windowManager);
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
            catchBallView.goToResult();
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
