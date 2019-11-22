package com.example.myapplication;

import android.view.MenuItem;
import android.view.View;

public interface GameMenu {
    /**
     * Activate the quit button.
     */
    void setQuitBtn();

    /**
     * Activate the NewGame button.
     */
    void setNewGameBtn();

    /**
     * Activate the load button.
     */
    void setLoadBtn();
    /**
     * Pop up a menu of complexities.
     */
    void showPopup(View v);

    /**
     * Activate the NewGame button.
     */
    void setHelpBtn();

    /**
     * Display that a game was loaded successfully.
     */
    void makeToastLoadedText();

    /**
     * Display that there is no more saved game.
     */
    void makeToastNoLoadedText();


}

