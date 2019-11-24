package com.example.myapplication.sliding;

import com.example.myapplication.gamemanager.GameController;
import com.example.myapplication.gamemanager.GameManager;
import com.example.myapplication.gamemanager.GameView;
import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;
import com.example.myapplication.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

class SlidingPresenter implements GameController, MySubject {
    private SlidingManager slidingManager;
    private GameView weaponView;
    private SlidingGrid SlidingGrid;

    /**
     * The list of observers of this class
     */
    private static List<MyObserver> observers;



    SlidingPresenter(SlidingManager slidingManager, SlidingGrid gridView) {
        this.slidingManager = slidingManager;
        observers = new ArrayList<>();
        SlidingGrid = gridView;

    }

    void setWeaponView(GameView weaponView) {

        this.weaponView = weaponView;
    }

    SlidingManager getSlidingManager() {
        return slidingManager;
    }


    void restart() {
        weaponView.updateScore(0);
        notifyObservers();
        slidingManager.getCardCollection().setCardCollection();
        slidingManager.getCardCollection().addRandomNum();
        slidingManager.getCardCollection().addRandomNum();
    }

    void swipe(boolean vertical, boolean leftUp) {
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

    void onDestroy() {
        weaponView = null;
    }
}
