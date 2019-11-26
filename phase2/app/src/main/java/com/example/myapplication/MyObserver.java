package com.example.myapplication;

public interface MyObserver {
    /**
     * Update accordingly after subject calls notifyObservers()
     */
    void update();

    /**
     * Set the subject to be obsevred
     * @param subject to be observed
     */
    void setSubject(MySubject subject);
}
