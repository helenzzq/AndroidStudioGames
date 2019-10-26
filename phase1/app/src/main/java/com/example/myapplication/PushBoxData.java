package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class PushBoxData {
    //to record man's steps and box's steps
    private List<PushBoxStepData> manGameSteps = new ArrayList<>();
    //create man's position
    private TestCell manPosition = new TestCell();
    //remember the location of the flags
    private List<TestCell> flagCells = new ArrayList<>();
    //create variable represents the row number
    private int mRowNum;
    //create variable represents the column number
    private int mColumnNum;

//    public PushBoxData(Resources res, int level  ) throws IOException{
//        if(PushBoxInitialData.size() == 0){
//            PushBoxInitialData.readInitialData(res, PushBoxInitialData.CONFIG_FILE_NAME);
//        }
//        selectLevel = level;

//
//    }
}
