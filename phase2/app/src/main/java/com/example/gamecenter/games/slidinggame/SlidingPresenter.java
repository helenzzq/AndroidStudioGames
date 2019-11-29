package com.example.gamecenter.games.slidinggame;

import com.example.gamecenter.gameinterface.GameController;
import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.gameinterface.MyObserver;
import com.example.gamecenter.gameinterface.MySubject;
import com.example.gamecenter.games.slidinggame.activity.SlidingGrid;
import com.example.gamecenter.games.slidinggame.model.SlidingManager;
import com.example.gamecenter.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class SlidingPresenter implements GameController, MySubject {
    private SlidingManager slidingManager;
    private GameView weaponView;
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

    public void setWeaponView(GameView weaponView) {

        this.weaponView = weaponView;
    }

    public SlidingManager getSlidingManager() {
        return slidingManager;
    }


    public void restart() {
        weaponView.updateScore(0);
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
        weaponView.updateScore(slidingManager.getScore());
        if(slidingManager.isGameOver()){
            weaponView.goToResult();
        }

//        slidingManager.swipe(vertical, leftUp);
//        weaponView.addScore(slidingManager.getScore());
//        if(slidingManager.isGameOver()){
//            weaponView.showResult();
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
        weaponView = null;
    }
}
