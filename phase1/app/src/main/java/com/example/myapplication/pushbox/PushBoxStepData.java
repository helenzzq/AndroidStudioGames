package com.example.myapplication.pushbox;

public class PushBoxStepData {
    public TestCell mManPreviousPos;
    public TestCell mManCurrentPos;
    public TestCell mBoxPreviousPos;
    public TestCell mBoxCurrentPos;
    public PushBoxStepData(){
//        mManPreviousPos = new TestCell();
//        mManCurrentPos = new TestCell();
//        mBoxPreviousPos = new TestCell();
//        mBoxCurrentPos = new TestCell();

    }
    public TestCell getManPreviousPos(){
        return mManPreviousPos;
    }

    public void setManPreviousPos(TestCell mManPreviousPos){
        this.mManPreviousPos = mManPreviousPos;
    }

    public TestCell getManCurrentPos(){
        return mManCurrentPos;
    }

    public void setManCurrentPos(TestCell mManCurrentPos){
        this.mManCurrentPos = mManCurrentPos;
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
