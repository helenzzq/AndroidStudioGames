package com.example.myapplication.sliding;

import com.example.myapplication.gamemanager.GameController;
import com.example.myapplication.gamemanager.GameManager;
import com.example.myapplication.gamemanager.GameView;
import com.example.myapplication.scoreboard.Scoreboard;

class SlidingPresenter implements GameController {
    private SlidingManager slidingManager;
    private GameView weaponView;
    private SlidingGrid SlidingGrid;


    SlidingPresenter(SlidingManager slidingManager, SlidingGrid gridView) {
        this.slidingManager = slidingManager;
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
     * @param manager
     */
    @Override
    public void setGameManager(GameManager manager) {

    }

    /**
     * @param scoreboard
     * @param user
     * @return
     */
    @Override
    public boolean checkToAddScore(Scoreboard scoreboard, String user) {
        return false;
    }
}
