package com.example.myapplication.gamemanager;

public interface Observer {
    /**
     * Update accordingly after subject calls notifyObservers()
     */
    void update();

    /**
     * Set the subject to be obsevred
     * @param subject to be observed
     */
    void setSubject(Subject subject);
}
