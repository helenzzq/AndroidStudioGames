package com.example.myapplication.sliding;

import com.example.myapplication.gamemanager.GameView;

class SlidingPresenter {
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
            weaponView.showResult();
        }

//        slidingManager.swipe(vertical, leftUp);
//        weaponView.addScore(slidingManager.getScore());
//        if(slidingManager.isGameOver()){
//            weaponView.showResult();
//        }
    }




}
