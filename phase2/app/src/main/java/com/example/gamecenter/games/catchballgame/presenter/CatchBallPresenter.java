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


public class CatchBallPresenter implements GameController, MySubject {

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;
    private static GameData gameData;


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



        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.setBoardHeight(frameHeight);
            catchBallView.hideStartLabel();
            catchBallView.getGameTimer().restart();
            catchBallView.updateTimer();
        }

    }

    public void hitCheck(boolean actionFlag) {
        manager.hitCheck();
        catchBallView.updateScore(manager.getScore());
        if (manager.isGameOver()) {
            catchBallView.getGameTimer().stop();
            setGameData();
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
    private void setGameData(){
        User currentPlayer = UserManager.getCurrentUser();
        String username = currentPlayer.getUsername();
        gameData = new GameData(new CatchBallDataBuilder(currentPlayer.getUsername(),
                catchBallView.getSharedPreferences(username+"catchBall", Context.MODE_PRIVATE))) ;
        gameData.constructGameData(manager.getScore(), catchBallView.getGameTimer().getTime(),1);

    }
}
