package com.example.myapplication;

public class PushBoxStepData {
    public TestCell mManPreviousPos;
    public TestCell mManCurrentPos;
    public TestCell mBoxPreviousPos;
    public TestCell mBoxCurrentPos;
    public PushBoxStepData(){

    }
    public TestCell getManPreviousPos(){
        return mManPreviousPos;
    }

    public void setManPreviousPos(){
        this.mManPreviousPos = mManPreviousPos;
    }

    public TestCell getManCurrentPos(){
        return mManCurrentPos;
    }

    public void setManCurrentPos(){
        this.mManCurrentPos = mManCurrentPos;
    }

    public TestCell getBoxPreviousPos(){
        return mBoxPreviousPos;
    }


    public void setBoxPreviousPos(){
        this.mBoxPreviousPos = mBoxPreviousPos;
    }

    public TestCell getBoxCurrentPos(){
        return mBoxCurrentPos;
    }

    public void setBoxCurrentPos(){
        this.mBoxCurrentPos = mBoxCurrentPos;
    }



}
