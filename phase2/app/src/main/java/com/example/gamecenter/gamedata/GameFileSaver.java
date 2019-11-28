package com.example.gamecenter.gamedata;

import android.content.Context;
import android.util.Log;

import com.example.gamecenter.GameController;
import com.example.gamecenter.GameManager;
import com.example.gamecenter.MyObserver;
import com.example.gamecenter.MySubject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

/*
A file saver for game states in all of the games.
 */

public class GameFileSaver implements Serializable, MyObserver {
    /**
     * The context
     */
    private Context context;

    /**
     * the GameController
     */
    private GameController subject;

    /**
     * The GameManager
     */
    private GameManager gameManager;

    /**
     * The fileName
     */
    private String fileName;

    public GameFileSaver(Context context, String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();
    }

    /**
     * Returns the associated GameManager.
     * @return the associated GameManager.
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Sets this game file saver's GameManager.
     * @param manager
     */
    public void setGameManager(GameManager manager) {
        this.gameManager = manager;
    }

    /**
     * Updates the game file saver, calls a save to file.
     */
    @Override
    public void update() {
        setGameManager(subject.getGameManager());
        saveToFile();
    }

    /**
     * Sets this game file saver's subject for observation.
     * @param subject
     */
    @Override
    public void setSubject(MySubject subject) {
        this.subject = (GameController) subject;
    }

    /**
     * Saves the current GameManager to a file.
     */
    public void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(gameManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
     * Loads a GameManager from a file, if the corresponding file exists.
     */
    public void loadFromFile() {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                gameManager = (GameManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }
        catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }



}
