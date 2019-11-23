package com.example.myapplication.gamemanager;

import com.example.myapplication.scoreboard.Scoreboard;

public interface GameController {


    /**
     * A getter for the Game Manager.
     */

    GameManager getGameManager();

    /**
     * A setter for the Game Manager
     */
    void setGameManager(GameManager manager);

    /**
     *
     * @param scoreboard
     * @param user
     * @return
     */
    boolean checkToAddScore(Scoreboard scoreboard, String user);

    /**
     * Set up the board according to level of difficulty
     * @param level a String about stating level.
     */
  //  void setUpBoard(String level);
}
