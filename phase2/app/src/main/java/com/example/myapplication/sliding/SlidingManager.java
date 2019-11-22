package com.example.myapplication.sliding;

import com.example.myapplication.gamemanager.GameManager;

class SlidingManager implements GameManager {


    private SlidingCard[][] slidingCards;
    private CardCollection cardCollection;

    private int score;
    private boolean vertical;
    private boolean leftUp;
    private boolean gameOver;

    SlidingManager() {
        gameOver = false;
        cardCollection = new CardCollection();
        slidingCards = cardCollection.getCards();
        score = 0;

    }

    SlidingCard[][] getSlidingCards() {
        return slidingCards;
    }

    void swipe(boolean veritical, boolean leftUp) {
        boolean merge = false;
        this.vertical = veritical;
        this.leftUp = leftUp;
        for (int fixed = 0; fixed < 4; fixed++) {
            if (leftUp) {
                merge = changeByDirection(merge, fixed, 0, 1);
            } else {
                merge = changeByDirection(merge, fixed, 3, -1);
            }
            if (merge) {
                cardCollection.addRandomNum();
                checkAllPair();
            }

        }
    }

    private SlidingCard setCardByDirection(int changed, int fixed) {
        SlidingCard card = slidingCards[fixed][changed];
        if (!vertical) {
            card = slidingCards[changed][fixed];
        }
        return card;
    }

    private int checkPos(int changed, int dynamic, int fixed, int add) {
        SlidingCard card = slidingCards[fixed][changed];
        SlidingCard cardNext = slidingCards[fixed][dynamic];

        if (vertical) {
            card = slidingCards[changed][fixed];
            cardNext = slidingCards[dynamic][fixed];
        }

        if (card.getNum() <= 0) {
            card.setNum(cardNext.getNum());
            cardNext.setNum(0);
            return -add;
        } else if (card.equals(cardNext)) {
            card.setNum(cardNext.getNum() * 2);
            cardNext.setNum(0);
            // add score to activity
            score += cardNext.getNum();
        }
        return 0;

    }

    private boolean setLoopCondition(int start) {
        if (leftUp) {
            return (start < 4);
        } else {
            return (start >= 0);
        }

    }

    private boolean changeByDirection(boolean m, int fixed, int start, int add) {
        while (setLoopCondition(start)) {
            int dynamic = start + add;
            while (setLoopCondition(dynamic)) {
                SlidingCard cardNext = setCardByDirection(dynamic, fixed);
                if (cardNext.getNum() > 0) {
                    start += checkPos(start, dynamic, fixed, add);
                    m = true;
                    break;
                }
                dynamic += add;
            }
            start += add;
        }

        return m;
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
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (slidingCards[x][y].getNum() == 0 ||
                        (x > 0 && slidingCards[x][y].equals(slidingCards[x - 1][y])) ||
                        (x < 3 && slidingCards[x][y].equals(slidingCards[x + 1][y])) ||
                        (y > 0 && slidingCards[x][y].equals(slidingCards[x][y - 1])) ||
                        (y < 3 && slidingCards[x][y].equals(slidingCards[x][y + 1]))) {
                    gameOver = false;
                    break ALL;

                }

            }
        }

    }

     void swipeLeft() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
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
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

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
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y + 1; y1 < 4; y1++) {
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
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

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
