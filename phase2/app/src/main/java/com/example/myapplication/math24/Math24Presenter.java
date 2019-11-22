package com.example.myapplication.math24;

public class Math24Presenter {

    private Math24Manager math24Manager;
    private Math24View math24View;


    Math24Presenter(Math24Manager math24Manager, Math24View math24View) {
        this.math24Manager = math24Manager;
        this.math24View = math24View;

    }

    void setMath24View(Math24View math24View) {
        this.math24View = math24View;
    }

    Math24Manager getWeaponManager() {
        return math24Manager;
    }

//
//    void restart() {
//        weaponView.clearScore();
//        weaponManager.getCardCollection().setCardCollection();
//        weaponManager.getCardCollection().addRandomNum();
//        weaponManager.getCardCollection().addRandomNum();
//    }



}
