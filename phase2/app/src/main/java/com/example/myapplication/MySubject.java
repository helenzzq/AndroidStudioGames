package com.example.myapplication;

public interface MySubject {
    /**
     * Register the MyObserver object to observe
     * @param obj to register
     */

    void register(MyObserver obj);

    /**
     * A method to notifyObservers to change
     */
    void notifyObservers();

}
