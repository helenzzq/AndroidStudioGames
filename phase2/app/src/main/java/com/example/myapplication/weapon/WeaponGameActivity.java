package com.example.myapplication.weapon;

import android.app.AppComponentFactory;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class WeaponGameActivity extends AppComponentFactory implements Observer, Serializable {




    @Override
    public void update(Observable o, Object arg) {
        //Need to implement something
    }
}
