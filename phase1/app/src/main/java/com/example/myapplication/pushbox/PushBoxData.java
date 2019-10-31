package com.example.myapplication.pushbox;

import java.io.IOException;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PushBoxData {
    //to record Prince's steps and box's steps
    private List<PushBoxStepData> princeGameSteps = new ArrayList<>();
    //create prince's position
    private TestCell princePosition = new TestCell();
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
    //score
    private int score;

    public PushBoxData(Resources res, int level) throws IOException{
        if(PushBoxInitialData.GameLevels.size() == 0){
            PushBoxInitialData.addInitGameData();
            PushBoxInitialData.readInitialData(res, PushBoxInitialData.CONFIG_FILE_NAME);
        }
        selectedLevel = level;
        selectedInitialData = PushBoxInitialData.GameLevels.get(level-1);
        initializePushBoxState();


    }
    private void initializePushBoxState(){
        mRowNum = selectedInitialData.mRowNum;
        mColumnNum = selectedInitialData.mColumnNum;
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
                if (mPushBoxState[r].charAt(c) == PushBoxInitialData.PRINCE || mPushBoxState[r].charAt(c) == PushBoxInitialData.PRINCE_FLAG) {
                    princePosition.set(r, c);
                }
                if (mPushBoxState[r].charAt(c) == PushBoxInitialData.FLAG || mPushBoxState[r].charAt(c) == PushBoxInitialData.PRINCE_FLAG
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

    public TestCell getmPrincePostion(){
        return  princePosition;
    }

    public int getBoardColumnNum(){
        return mColumnNum;
    }

    public int getBoardRowNum(){
        return mRowNum;
    }

    private void go(int srcRow, int srcColumn, int destRow, int destColumn){
        if (destRow < 0 || destRow >= mRowNum || destColumn < 0 || destColumn >= mColumnNum)
            return;   //越界
//        mCurrentStep = null;
        boolean isBoxMoved = false;
        int rowOffset = destRow - srcRow;
        int columnOffset = destColumn - srcColumn;
        char cell = mPushBoxState[destRow].charAt(destColumn);
        if (cell == PushBoxInitialData.BOX) {
            isBoxMoved = moveBox(destRow, destColumn, destRow + rowOffset, destColumn + columnOffset);
            cell = mPushBoxState[destRow].charAt(destColumn);
        }

        if (cell == PushBoxInitialData.NOTHING || cell == PushBoxInitialData.FLAG){
            princeGoAway();
            princePosition.row = destRow;
            princePosition.column = destColumn;
            mPushBoxState[princePosition.row].setCharAt(princePosition.column, PushBoxInitialData.PRINCE);

            recordMoveInfo(srcRow, srcColumn, destRow, destColumn, isBoxMoved);
        }
    }

    public void goUp() {
        go(princePosition.row, princePosition.column, princePosition.row - 1, princePosition.column);
    }

    public void goRight() {
        go(princePosition.row, princePosition.column, princePosition.row, princePosition.column + 1);
    }

    public void goLeft() {
        go(princePosition.row, princePosition.column, princePosition.row, princePosition.column - 1);
    }

    public void goDown(){
        go(princePosition.row, princePosition.column, princePosition.row + 1, princePosition.column);
    }




    private void recordMoveInfo(int srcRow, int srcColumn, int destRow, int destColumn, boolean isBoxMoved) {
        PushBoxStepData stepData = new PushBoxStepData();
        stepData.setPrincePreviousPos(new TestCell(srcRow, srcColumn));
        stepData.setPrinceCurrentPos(new TestCell(destRow, destColumn));
        if (isBoxMoved){
            int rowOffset = destRow - srcRow;
            int columnOffset = destColumn - srcColumn;
            stepData.setBoxPreviousPos(new TestCell(destRow, destColumn));
            stepData.setBoxCurrentPos(new TestCell(destRow + rowOffset, destColumn + columnOffset));
        }
        princeGameSteps.add(stepData);
//        logOneStep(stepData);
    }

    private void restoreInitialState(int row, int column) {
        if (hasFlag(row, column))
            mPushBoxState[row].setCharAt(column, PushBoxInitialData.FLAG);
        else
            mPushBoxState[row].setCharAt(column, PushBoxInitialData.NOTHING);
    }

    private void princeGoAway() {
        restoreInitialState(princePosition.row, princePosition.column);
    }

    //把箱子从(srcRow, srcColumn)移动到(destRow, destColumn)
    private boolean moveBox(int srcRow, int srcColumn, int destRow, int destColumn){
        if (destRow < 0 || destRow >= mRowNum || destColumn < 0 || destColumn >= mColumnNum)
            return false;
        char cell = mPushBoxState[destRow].charAt(destColumn);
        if (cell  == PushBoxInitialData.NOTHING || cell == PushBoxInitialData.FLAG){
            restoreInitialState(srcRow, srcColumn);
            mPushBoxState[destRow].setCharAt(destColumn, PushBoxInitialData.BOX);
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
        if (princeGameSteps.isEmpty())
            return false;
        PushBoxStepData step = princeGameSteps.remove(princeGameSteps.size() - 1);
//        logUndoOneStep(step);
        assert(princePosition.isEqualTo(step.getPrinceCurrentPos()));
        restoreInitialState(step.getPrinceCurrentPos().row, step.getPrinceCurrentPos().column);
        int PrinceRow = step.getPrincePreviousPos().row;
        int PrinceColumn = step.getPrincePreviousPos().column;
        princePosition.set(PrinceRow, PrinceColumn);
        mPushBoxState[PrinceRow].setCharAt(PrinceColumn, 'M');
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
        logOneStep(step.getPrinceCurrentPos(), step.getPrincePreviousPos(), step.getBoxCurrentPos(), step.getBoxPreviousPos());
    }

    private void logOneStep(PushBoxStepData step) {
        TestCell princePrvPos = step.getPrincePreviousPos();
        TestCell princeCurPos = step.getPrinceCurrentPos();
        TestCell boxPrvPos = step.getBoxPreviousPos();
        TestCell boxCurPos = step.getBoxCurrentPos();
        logOneStep(princePrvPos, princeCurPos, boxPrvPos, boxCurPos);
    }

    private void logOneStep(TestCell princePrvPos, TestCell princeCurPos, TestCell boxPrvPos, TestCell boxCurPos) {
        Log.d("GameData", "一步：(" + princePrvPos.row + ", " + princePrvPos.column + ") -> (" + princeCurPos.row + ", " + princeCurPos.column + ")" );
        if (boxPrvPos != null && boxCurPos != null) {
            Log.d("GameData", "箱子：(" + boxPrvPos.row + ", " + boxPrvPos.column + ") -> (" + boxCurPos.row + ", " + boxCurPos.column + ")" );
        }
    }




}
