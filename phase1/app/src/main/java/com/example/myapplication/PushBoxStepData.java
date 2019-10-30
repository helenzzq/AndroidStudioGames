package com.example.myapplication;



public class PushBoxStepData {
    public TestCell mManPrvPosition;
    public TestCell mManCurrentPosition;
    public TestCell mBoxPrvPosition;
    public TestCell mBoxCurrentPosition;

    public PushBoxStepData(){
//        mManPrvPosition = new TCell();
//        mManCurrentPosition = new TCell();
//        mBoxPrvPosition = new TCell();
//        mBoxCurrentPosition = new TCell();
    }

    public TestCell getManPrvPosition() {
        return mManPrvPosition;
    }

    public void setManPrvPosition(TestCell mManPrvPosition) {
        this.mManPrvPosition = mManPrvPosition;
    }

    public TestCell getManCurrentPosition() {
        return mManCurrentPosition;
    }

    public void setManCurrentPosition(TestCell mManCurrentPosition) {
        this.mManCurrentPosition = mManCurrentPosition;
    }

    public TestCell getBoxPrvPosition() {
        return mBoxPrvPosition;
    }

    public void setBoxPrvPosition(TestCell mBoxPrvPosition) {
        this.mBoxPrvPosition = mBoxPrvPosition;
    }

    public TestCell getBoxCurrentPosition() {
        return mBoxCurrentPosition;
    }

    public void setBoxCurrentPosition(TestCell mBoxCurrentPosition) {
        this.mBoxCurrentPosition = mBoxCurrentPosition;
    }


}
