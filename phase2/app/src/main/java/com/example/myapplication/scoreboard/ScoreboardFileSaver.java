package com.example.myapplication.scoreboard;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.gamemanager.MyObserver;
import com.example.myapplication.gamemanager.MySubject;

import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class ScoreboardFileSaver implements Serializable, MyObserver {

    private String fileName;

    private Context context;

    /*
    A List of globalScores
     */
    private ArrayList<Score> GlobalScores  = new ArrayList<>();

    private Scoreboard subject;

    /**
     * Update accordingly after subject calls notifyObservers()
     */

    public ScoreboardFileSaver(Context context,String fileName){
        this.context = context;
        this.fileName = fileName;
        loadFromFile();

    }


    public void loadFromFile(){
        try{
            InputStream inputStream = context.openFileInput(fileName);

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        }


    }

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName,Context.MODE_PRIVATE));
            outputStream.writeObject(GlobalScores);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    @Override
    public void update() {
        GlobalScores = subject.getGlobalScore();
        saveToFile(fileName);
    }

    /**
     * Set the subject to be obsevred
     *
     * @param subject to be observed
     */
    @Override
    public void setSubject(MySubject subject) {
        this.subject = (Scoreboard)subject;

    }
}
