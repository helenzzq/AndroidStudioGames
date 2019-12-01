package com.example.gamecenter.games.slidinggame.model;

import com.example.gamecenter.gameinterface.GameManager;
import com.example.gamecenter.games.slidinggame.activity.SlidingActivity;
import com.example.gamecenter.games.slidinggame.activity.SlidingGrid;

public class SlidingManager implements GameManager {


    private SlidingCard[][] slidingCards;
    private CardCollection cardCollection;

    private int score;
    private boolean gameOver;
    private boolean nextLevel;

    private static int num;

    public SlidingManager() {
        setNum(SlidingGrid.getNum());
        gameOver = false;
        cardCollection = new CardCollection();
        slidingCards = cardCollection.getCards();
        if(SlidingActivity.getIsLevel1()){
        score = 0;
        }


    }

    public static void setNum(int num) {
        SlidingManager.num = num;
    }

    static int getNum(){
        return num;
    }


    public SlidingCard[][] getSlidingCards() {
        return slidingCards;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    private void checkAllPair() {
        gameOver = true;
        ALL:
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (slidingCards[x][y].getNum() == 0 ||
                        (x > 0 && slidingCards[x][y].equals(slidingCards[x - 1][y])) ||
                        (x < (num - 1) && slidingCards[x][y].equals(slidingCards[x + 1][y])) ||
                        (y > 0 && slidingCards[x][y].equals(slidingCards[x][y - 1])) ||
                        (y < (num - 1) && slidingCards[x][y].equals(slidingCards[x][y + 1]))) {
                    gameOver = false;
                    break ALL;

                }

            }
        }

    }

     public void swipeLeft() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                for (int x1 = x + 1; x1 < num; x1++) {
                    if (slidingCards[x1][y].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x1][y].getNum());
                            slidingCards[x1][y].setNum(0);

                            x--;
                            merge = true;
                        } else if (slidingCards[x][y].equals(slidingCards[x1][y])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x1][y].setNum(0);

                            score += (Math.pow(2,slidingCards[x][y].getNum()));
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();
            nextLevel = checkNextLevel();
        }

    }

     public void swipeRight() {
        boolean merge = false;
        for (int y = 0; y < num; y++) {
            for (int x = (num - 1); x >= 0; x--) {

                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (slidingCards[x1][y].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x1][y].getNum());
                            slidingCards[x1][y].setNum(0);

                            x++;
                            merge = true;
                        } else if (slidingCards[x][y].equals(slidingCards[x1][y])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x1][y].setNum(0);
                            score += (Math.pow(2,slidingCards[x][y].getNum()));
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();
            if(SlidingActivity.getIsLevel1()){
            nextLevel = checkNextLevel();}
        }

    }

     public void swipeUp() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {

                for (int y1 = y + 1; y1 < num; y1++) {
                    if (slidingCards[x][y1].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x][y1].getNum());
                            slidingCards[x][y1].setNum(0);

                            y--;
                            merge = true;

                        } else if (slidingCards[x][y].equals(slidingCards[x][y1])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x][y1].setNum(0);
                            score += (Math.pow(2,slidingCards[x][y].getNum()));
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();
            if(SlidingActivity.getIsLevel1()){
                nextLevel = checkNextLevel();}
        }

    }

     public void swipeDown() {
        boolean merge = false;
        for (int x = 0; x < num; x++) {
            for (int y = (num - 1); y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (slidingCards[x][y1].getNum() > 0) {

                        if (slidingCards[x][y].getNum() <= 0) {
                            slidingCards[x][y].setNum(slidingCards[x][y1].getNum());
                            slidingCards[x][y1].setNum(0);

                            y++;
                            merge = true;

                        } else if (slidingCards[x][y].equals(slidingCards[x][y1])) {
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() + 1);
                            slidingCards[x][y1].setNum(0);

                            score += (Math.pow(2,slidingCards[x][y].getNum()));
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge) {
            cardCollection.addRandomNum();
            checkAllPair();
            if(SlidingActivity.getIsLevel1()){
                nextLevel = checkNextLevel();}
        }

    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean checkNextLevel() {
        int currentScore = getScore();
        nextLevel = (currentScore >= 50);
        return nextLevel;
    }


    public void setCardCollection(){
        cardCollection.setCardCollection();
        cardCollection.addRandomNum();
        cardCollection.addRandomNum();
    }

    public void startNextLevel(){

    }


}
