package com.example.gamecenter.strategy;

import android.os.SystemClock;
import android.widget.Chronometer;


import java.util.Timer;

public class GameTimer {

    private Timer timer;

    private Chronometer chrono;
    private long lastPause = 0;

    public GameTimer(Chronometer chrono){
        this.chrono = chrono;
        this.chrono.setFormat("USED TIME：%s");
        timer = new Timer();
    }
    public void restart(){
        timer = new Timer();
        if(lastPause == 0){
        chrono.setBase(SystemClock.elapsedRealtime());}
        else{
            chrono.setBase(chrono.getBase() + SystemClock.elapsedRealtime() - lastPause);
        }
        chrono.start();
    }

    public void stop(){
        timer.cancel();
        timer = null;
        chrono.stop();
        lastPause = SystemClock.elapsedRealtime();

    }

    public Timer getTimer() {
        return timer;
    }

    public int getTime(){
        return  (int)((SystemClock.elapsedRealtime() - chrono.getBase())/1000);
    }

}
