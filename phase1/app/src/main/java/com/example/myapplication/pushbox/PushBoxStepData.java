package com.example.myapplication.pushbox;

public class PushBoxStepData {
    public TestCell mPrincePreviousPos;
    public TestCell mPrinceCurrentPos;
    public TestCell mBoxPreviousPos;
    public TestCell mBoxCurrentPos;
    public PushBoxStepData(){
//        mPrincePreviousPos = new TestCell();
//        mPrinceCurrentPos = new TestCell();
//        mBoxPreviousPos = new TestCell();
//        mBoxCurrentPos = new TestCell();

    }
    public TestCell getPrincePreviousPos(){
        return mPrincePreviousPos;
    }

    public void setPrincePreviousPos(TestCell mPrincePreviousPos){
        this.mPrincePreviousPos = mPrincePreviousPos;
    }

    public TestCell getPrinceCurrentPos(){
        return mPrinceCurrentPos;
    }

    public void setPrinceCurrentPos(TestCell mPrinceCurrentPos){
        this.mPrinceCurrentPos = mPrinceCurrentPos;
    }

    public TestCell getBoxPreviousPos(){
        return mBoxPreviousPos;
    }


    public void setBoxPreviousPos(TestCell mBoxPreviousPos){
        this.mBoxPreviousPos = mBoxPreviousPos;
    }

    public TestCell getBoxCurrentPos(){
        return mBoxCurrentPos;
    }

    public void setBoxCurrentPos(TestCell mBoxCurrentPos){
        this.mBoxCurrentPos = mBoxCurrentPos;
    }



}
