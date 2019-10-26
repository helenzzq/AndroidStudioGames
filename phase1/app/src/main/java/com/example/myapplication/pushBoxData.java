package com.example.myapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.content.res.Resources;

public class pushBoxData {
    //to record man's steps and box's steps
    private List<pushBoxStepData> manGameSteps = new ArrayList<>();
    //create man's position
    private TestCell manPosition = new TestCell();
    //remember the location of the flags
    private List<TestCell> flagCells = new ArrayList<>();
    //create variable represents the row number
    private int mRowNum;
    //create variable represents the column number
    private int mColumnNum;

//    public pushBoxData(Resources res, int level  ) throws IOException{
//        if(pushBoxInitialData.size() == 0){
//            pushBoxInitialData.readInitialData(res, pushBoxInitialData.CONFIG_FILE_NAME);
//        }
//        selectLevel = level;

//
//    }
}
