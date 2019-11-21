package com.example.myapplication.weapon;

class WeaponPresenter {
    private WeaponManager weaponManager;
    private WeaponGameView weaponView;
    private WeaponGridView WeaponGridView;


    WeaponPresenter(WeaponManager weaponManager, WeaponGridView gridView) {
        this.weaponManager = weaponManager;
        WeaponGridView = gridView;

    }

    void setWeaponView(WeaponGameView weaponView) {
        this.weaponView = weaponView;
    }

    WeaponManager getWeaponManager() {
        return weaponManager;
    }


    void restart() {
        weaponView.clearScore();
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
        weaponView.addScore(weaponManager.getScore());
        if(weaponManager.isGameOver()){
            weaponView.goToResult();
        }

//        weaponManager.swipe(vertical, leftUp);
//        weaponView.addScore(weaponManager.getScore());
//        if(weaponManager.isGameOver()){
//            weaponView.goToResult();
//        }
    }




}
