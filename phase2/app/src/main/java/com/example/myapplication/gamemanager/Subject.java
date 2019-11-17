package com.example.myapplication.gamemanager;

public interface Subject {
    /**
     * Register the Observer object to observe
     * @param obj to register
     */

    void register(Observer obj);

    /**
     * A method to notifyObservers to change
     */
    void notifyObservers();

}
