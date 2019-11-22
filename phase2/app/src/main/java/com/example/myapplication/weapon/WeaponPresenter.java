package com.example.myapplication.weapon;

import com.example.myapplication.gamemanager.GameView;

class WeaponPresenter {
    private WeaponManager weaponManager;
    private GameView weaponView;
    private WeaponGridView WeaponGridView;


    WeaponPresenter(WeaponManager weaponManager, WeaponGridView gridView) {
        this.weaponManager = weaponManager;
        WeaponGridView = gridView;

    }

    void setWeaponView(GameView weaponView) {

        this.weaponView = weaponView;
    }

    WeaponManager getWeaponManager() {
        return weaponManager;
    }


    void restart() {
        weaponView.updateScore(0);
        weaponManager.getCardCollection().setCardCollection();
        weaponManager.getCardCollection().addRandomNum();
        weaponManager.getCardCollection().addRandomNum();
    }

    void swipe(boolean vertical, boolean leftUp) {
        if(vertical){
            if(leftUp){
                weaponManager.swipeUp();
            }
            else{
                weaponManager.swipeDown();
            }
        }
        else {
            if(leftUp){
                weaponManager.swipeLeft();
            }
            else{
                weaponManager.swipeRight();
            }
        }
        weaponView.updateScore(weaponManager.getScore());
        if(weaponManager.isGameOver()){
            weaponView.showResult();
        }

//        weaponManager.swipe(vertical, leftUp);
//        weaponView.addScore(weaponManager.getScore());
//        if(weaponManager.isGameOver()){
//            weaponView.showResult();
//        }
    }




}
