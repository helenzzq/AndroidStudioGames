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

    public TestCell getmBoxPreviousPos(){
        return mBoxPreviousPos;
    }


    public void setmBoxPreviousPos(){
        this.mBoxPreviousPos = mBoxPreviousPos;
    }

    public TestCell getmBoxCurrentPos(){
        return mBoxCurrentPos;
    }

    public void setBoxCurrentPos(){
        this.mBoxCurrentPos = mBoxCurrentPos;
    }



}
