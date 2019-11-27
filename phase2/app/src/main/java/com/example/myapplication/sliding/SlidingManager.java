package com.example.myapplication.sliding;

import com.example.myapplication.GameManager;

class SlidingManager implements GameManager {


    private SlidingCard[][] slidingCards;
    private CardCollection cardCollection;

    private int score;
    private boolean gameOver;

    private static int num;

    SlidingManager() {
        setNum(SlidingGrid.getNum());
        gameOver = false;
        cardCollection = new CardCollection();
        slidingCards = cardCollection.getCards();
        score = 0;

    }

    private static void setNum(int num) {
        SlidingManager.num = num;
    }

    static int getNum(){
        return num;
    }

    SlidingCard[][] getSlidingCards() {
        return slidingCards;
    }

    CardCollection getCardCollection() {
        return cardCollection;
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

     void swipeLeft() {
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
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() * 2);
                            slidingCards[x1][y].setNum(0);

                            score += (slidingCards[x][y].getNum());
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
        }

    }

     void swipeRight() {
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
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() * 2);
                            slidingCards[x1][y].setNum(0);
                            score += (slidingCards[x][y].getNum());
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
        }

    }

     void swipeUp() {
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
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() * 2);
                            slidingCards[x][y1].setNum(0);
                            score += (slidingCards[x][y].getNum());
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
        }

    }

     void swipeDown() {
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
                            slidingCards[x][y].setNum(slidingCards[x][y].getNum() * 2);
                            slidingCards[x][y1].setNum(0);

                            score += (slidingCards[x][y].getNum());
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
        }

    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }


}
