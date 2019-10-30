package com.example.myapplication;

import java.io.IOException;
import android.content.res.Resources;
import android.util.Log;

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
    //the initial data of current level
    private PushBoxLevelInitialData selectedInitialData;
    //selected level
    private int selectedLevel;
    //
    private StringBuffer[] mPushBoxState;

    public PushBoxData(Resources res, int level) throws IOException{
        if(PushBoxInitialData.size() == 0){
            PushBoxInitialData.readInitialData(res, PushBoxInitialData.CONFIG_FILE_NAME);
        }
        selectedLevel = level;
        selectedInitialData = PushBoxInitialData.GameLevels.get(level-1);
        initializePushBoxState();


    }
    private void initializePushBoxState(){
        mRowNum = selectedInitialData.mRowNum;
        mColumnNum = selectedInitialData.DEFAULT_ROW_NUM;
        if (mRowNum < PushBoxInitialData.DEFAULT_COLUMN_NUM){
            mPushBoxState = new StringBuffer[PushBoxInitialData.DEFAULT_ROW_NUM];
        }
        else{
            mPushBoxState = new StringBuffer[mRowNum];
        }
        StringBuffer leftBlanks = new StringBuffer("");
        StringBuffer rightBlanks = new StringBuffer("");

        if (mColumnNum < PushBoxInitialData.DEFAULT_COLUMN_NUM){
            int leftBlankCnt = (PushBoxInitialData.DEFAULT_COLUMN_NUM - mColumnNum) / 2;
            for (int i = 0; i < leftBlankCnt; i++ )
                leftBlanks.append(" ");
            for (int i = 0; i < PushBoxInitialData.DEFAULT_COLUMN_NUM - mColumnNum - leftBlankCnt; i++)
                rightBlanks.append(" ");
            mColumnNum = PushBoxInitialData.DEFAULT_COLUMN_NUM;
        }
        for (int r = 0; r < mRowNum; r++) {
            mPushBoxState[r] = new StringBuffer(leftBlanks);
            mPushBoxState[r].append(selectedInitialData.mInitialState[r]);
            mPushBoxState[r].append(rightBlanks);
            //Log.d("GameData", "initializeGameState(), mGameState[" + r + "].length=" + mGameState[r].length());
            for (int c = 0; c < mColumnNum; c++) {
                if (mPushBoxState[r].charAt(c) == PushBoxInitialData.MAN || mPushBoxState[r].charAt(c) == PushBoxInitialData.MAN_FLAG) {
                    manPosition.set(r, c);
                }
                if (mPushBoxState[r].charAt(c) == PushBoxInitialData.FLAG || mPushBoxState[r].charAt(c) == PushBoxInitialData.MAN_FLAG
                        || mPushBoxState[r].charAt(c) == PushBoxInitialData.BOX_FLAG){
                    TestCell cell = new TestCell(r, c);
                    flagCells.add(cell);
                }
            }
        }
    }

    public StringBuffer[] getGameState() {
        return mPushBoxState;
    }

    public TestCell getmManPostion(){
        return  manPosition;
    }

    public int getBoardColumnNum(){
        return mColumnNum;
    }

    public int getBoardRowNum(){
        return mRowNum;
    }

    public void goUp() {
        go(manPosition.row, manPosition.column, manPosition.row - 1, manPosition.column);
    }

    public void goRight() {
        go(manPosition.row, manPosition.column, manPosition.row, manPosition.column + 1);
    }

    public void goLeft() {
        go(manPosition.row, manPosition.column, manPosition.row, manPosition.column - 1);
    }

    private void recordMoveInfo(int srcRow, int srcColumn, int destRow, int destColumn, boolean isBoxMoved) {
        PushBoxStepData stepData = new PushBoxStepData();
        stepData.setManPreviousPos(new TestCell(srcRow, srcColumn));
        stepData.setManCurrentPos(new TestCell(destRow, destColumn));
        if (isBoxMoved){
            int rowOffset = destRow - srcRow;
            int columnOffset = destColumn - srcColumn;
            stepData.setBoxPreviousPos(new TestCell(destRow, destColumn));
            stepData.setBoxCurrentPos(new TestCell(destRow + rowOffset, destColumn + columnOffset));
        }
        manGameSteps.add(stepData);
//        logOneStep(stepData);
    }

    private void restoreInitialState(int row, int column) {
        if (hasFlag(row, column))
            mPushBoxState[row].setCharAt(column, PushBoxInitialData.FLAG);
        else
            mPushBoxState[row].setCharAt(column, PushBoxInitialData.NOTHING);
    }

    private void manGoAway() {
        restoreInitialState(manPosition.row, manPosition.column);
        if (GameSound.isSoundAllowed()) GameSound.playOneStepSound();
    }

    //把箱子从(srcRow, srcColumn)移动到(destRow, destColumn)
    private boolean moveBox(int srcRow, int srcColumn, int destRow, int destColumn){
        if (destRow < 0 || destRow >= mRowNum || destColumn < 0 || destColumn >= mColumnNum)
            return false;
        char cell = mPushBoxState[destRow].charAt(destColumn);
        if (cell  == PushBoxInitialData.NOTHING || cell == PushBoxInitialData.FLAG){
            restoreInitialState(srcRow, srcColumn);
            mPushBoxState[destRow].setCharAt(destColumn, PushBoxInitialData.BOX);
            if (GameSound.isSoundAllowed()) GameSound.playMoveBoxSound();
            return true;
        }
        return false;
    }
    //据所选关卡的初始数据处获取单元格(row, column)是否有红旗
    public boolean hasFlag(int row, int column) {
        for (int i = 0; i < flagCells.size(); i++) {
            TestCell cell = flagCells.get(i);
            if (cell.row == row && cell.column == column)
                return true;
        }
        return  false;
    }

    //所有箱子到达目的地了么？是的话，返回true, 否则返回false。
    public boolean isGameOver() {
        for (int i = 0; i < flagCells.size(); i++){
            TestCell cell = flagCells.get(i);
//            Log.d("GameData", "isGameOver(), Flag " + i + "=(" + cell.row + ", " + cell.column + ")");
            if (mPushBoxState[cell.row].charAt(cell.column) != 'B')
                return false;
        }
        return true;
    }
    public boolean undoMove(){
        if (manGameSteps.isEmpty())
            return false;
        PushBoxStepData step = manGameSteps.remove(manGameSteps.size() - 1);
//        logUndoOneStep(step);
        assert(manPosition.isEqualTo(step.getManCurrentPos()));
        restoreInitialState(step.getManCurrentPos().row, step.getManCurrentPos().column);
        int manRow = step.getManPreviousPos().row;
        int manColumn = step.getManPreviousPos().column;
        manPosition.set(manRow, manColumn);
        mPushBoxState[manRow].setCharAt(manColumn, 'M');
        TestCell boxPrvPos = step.getBoxPreviousPos();
        TestCell boxCurPos = step.getBoxCurrentPos();
        if (boxPrvPos != null && boxCurPos != null){
            //assert mGameState[boxCurPos.row].charAt(boxCurPos.column) == 'B';
            restoreInitialState(boxCurPos.row, boxCurPos.column);
            mPushBoxState[boxPrvPos.row].setCharAt(boxPrvPos.column, 'B');
        }
        return true;
    }

    private void logUndoOneStep(PushBoxStepData step) {
        logOneStep(step.getManCurrentPos(), step.getManPreviousPos(), step.getBoxCurrentPos(), step.getBoxPreviousPos());
    }

    private void logOneStep(PushBoxStepData step) {
        TestCell manPrvPos = step.getManPreviousPos();
        TestCell manCurPos = step.getManCurrentPos();
        TestCell boxPrvPos = step.getBoxPreviousPos();
        TestCell boxCurPos = step.getBoxCurrentPos();
        logOneStep(manPrvPos, manCurPos, boxPrvPos, boxCurPos);
    }

    private void logOneStep(TestCell manPrvPos, TestCell manCurPos, TestCell boxPrvPos, TestCell boxCurPos) {
        Log.d("GameData", "一步：(" + manPrvPos.row + ", " + manPrvPos.column + ") -> (" + manCurPos.row + ", " + manCurPos.column + ")" );
        if (boxPrvPos != null && boxCurPos != null) {
            Log.d("GameData", "箱子：(" + boxPrvPos.row + ", " + boxPrvPos.column + ") -> (" + boxCurPos.row + ", " + boxCurPos.column + ")" );
        }
    }




}
