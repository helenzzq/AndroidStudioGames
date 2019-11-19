package com.example.myapplication.catchball;

import android.view.MotionEvent;

class CatchBallPresenter {

    private CatchBallManager manager;
    private CatchBallView catchBallView;

    CatchBallPresenter(CatchBallView boardView, CatchBallManager manager) {
        this.manager = manager;
        this.catchBallView = boardView;

    }


    void onStart(MotionEvent action, boolean startFlag, int frameHeight) {
        if (startFlag) {
            catchBallView.makeAction(action);

        } else {
            catchBallView.setStartFlag(true);
            manager.updatePlayerSize();
            manager.getBoard().setFrameHeight(frameHeight);

            catchBallView.updateTimer();
        }

    }


    void hitCheck(boolean actionFlag) {
        int score = manager.hitCheck();
        catchBallView.updateScore(score);
        if (manager.isGameOver()) {
            catchBallView.hideStartLabel();
            catchBallView.stopTimer();
            catchBallView.showResult();
        } else {
            manager.changePos(actionFlag);
        }
    }

    void onDestroy() {
        catchBallView = null;
    }


}
