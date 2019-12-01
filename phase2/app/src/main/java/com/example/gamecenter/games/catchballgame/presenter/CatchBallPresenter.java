package com.example.gamecenter.games.catchballgame.presenter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.gamecenter.gamedata.CatchBallDataBuilder;
import com.example.gamecenter.gamedata.GameData;
import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.catchballgame.activity.CatchBallActivity;
import com.example.gamecenter.games.catchballgame.model.CatchBallManager;
import com.example.gamecenter.games.catchballgame.model.CatchBoard;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for the presenter, in terms of MVP pattern, of the first game Catch Ball.
 * */
public class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;

  /**
   * Instance of game manager.
   * */
  private CatchBallManager manager;

  /**
   * Instance of the gameview.
   * */
  private CatchBallActivity catchBallView;

  /**
   * A constructor of the class.
   * @param boardView
   * @param manager
   */
  public CatchBallPresenter(CatchBallActivity boardView, CatchBallManager manager) {
        this.catchBallView = boardView;
        observers = new ArrayList<>();
        this.manager = manager;
    }

  /**
   * Initialize when starting the game.
   *
   * @param action
   * @param startFlag
   * @param frameHeight
   */
  public void onStart(MotionEvent action, boolean startFlag, int frameHeight) {
        if (startFlag) {
            catchBallView.makeAction(action);

        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.setBoardHeight(frameHeight);
            catchBallView.hideStartLabel();
            catchBallView.getGameTimer().restart();
            catchBallView.updateTimer();
        }

    }

  /**
   * Check whether hit the target ball.
   *
   * @param actionFlag */
  public void hitCheck(boolean actionFlag) {
        manager.hitCheck();
        catchBallView.updateScore(manager.getScore());
        if (manager.isGameOver()) {
            catchBallView.getGameTimer().stop();
            catchBallView.showPrompt();

        } else {
            manager.changePos(actionFlag);
            if(manager.checkNextLevel()){
                catchBallView.setLevel("LEVEL2");
            }

        }
    }

  /**
   * Destroy the game view.
   * */
  public void onDestroy() {
        catchBallView = null;
    }

  /**
   * Get the game manager.
   * @return */
  @Override
  public CatchBallManager getGameManager() {
        return manager;
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

  /**
   * Notify the observer.
   * */
  @Override
  public void notifyObservers() {
        for (MyObserver obs: observers) {
            obs.update();
        }
    }



}
