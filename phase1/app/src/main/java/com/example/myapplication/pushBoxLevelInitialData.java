package com.example.myapplication;

public class pushBoxLevelInitialData {
    int mRowNum;
    int mColumnNum;

    String [] mInitialState;

    public pushBoxLevelInitialData(int rowNum, int columnNum){
        mRowNum= rowNum;
        mColumnNum = columnNum;
        mInitialState = new String[rowNum];
    }

    public pushBoxLevelInitialData(int rowNum, int columnNum, String[] initialState){
        mRowNum = rowNum;
        mColumnNum = columnNum;
        mInitialState = initialState;
    }
}
