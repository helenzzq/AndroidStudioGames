package com.example.myapplication.pushbox;

public class PushBoxLevelInitialData {
    int mRowNum;
    int mColumnNum;

    String [] mInitialState;

    public PushBoxLevelInitialData(int rowNum, int columnNum){
        mRowNum= rowNum;
        mColumnNum = columnNum;
        mInitialState = new String[rowNum];
    }

    public PushBoxLevelInitialData(int rowNum, int columnNum, String[] initialState){
        mRowNum = rowNum;
        mColumnNum = columnNum;
        mInitialState = initialState;
    }
}
