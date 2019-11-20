package com.example.myapplication.weapon;

public class WeaponPresenter {
    private WeaponGameManager weaponManager;
    private WeaponGameView  weaponView;

    WeaponPresenter(WeaponGameManager weaponManager, WeaponGameView weaponView){
        this.weaponManager = weaponManager;
        this.weaponView = weaponView;
    }



}
