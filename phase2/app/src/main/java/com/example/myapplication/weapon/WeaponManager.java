package com.example.myapplication.weapon;

import com.example.myapplication.gamemanager.GameManager;

class WeaponManager implements GameManager {


    private WeaponCard[][] weaponCards;
    private CardCollection cardCollection;

    private int score;
    private boolean vertical;
    private boolean leftUp;
    private boolean gameOver;

    WeaponManager() {
        gameOver = false;
        cardCollection = new CardCollection();
        weaponCards = cardCollection.getCards();
        score = 0;

    }

    WeaponCard[][] getWeaponCards() {
        return weaponCards;
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

    private WeaponCard setCardByDirection(int changed, int fixed) {
        WeaponCard card = weaponCards[fixed][changed];
        if (!vertical) {
            card = weaponCards[changed][fixed];
        }
        return card;
    }

    private int checkPos(int changed, int dynamic, int fixed, int add) {
        WeaponCard card = weaponCards[fixed][changed];
        WeaponCard cardNext = weaponCards[fixed][dynamic];

        if (vertical) {
            card = weaponCards[changed][fixed];
            cardNext = weaponCards[dynamic][fixed];
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
                WeaponCard cardNext = setCardByDirection(dynamic, fixed);
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
                if (weaponCards[x][y].getNum() == 0 ||
                        (x > 0 && weaponCards[x][y].equals(weaponCards[x - 1][y])) ||
                        (x < 3 && weaponCards[x][y].equals(weaponCards[x + 1][y])) ||
                        (y > 0 && weaponCards[x][y].equals(weaponCards[x][y - 1])) ||
                        (y < 3 && weaponCards[x][y].equals(weaponCards[x][y + 1]))) {
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
                    if (weaponCards[x1][y].getNum() > 0) {

                        if (weaponCards[x][y].getNum() <= 0) {
                            weaponCards[x][y].setNum(weaponCards[x1][y].getNum());
                            weaponCards[x1][y].setNum(0);

                            x--;
                            merge = true;
                        } else if (weaponCards[x][y].equals(weaponCards[x1][y])) {
                            weaponCards[x][y].setNum(weaponCards[x][y].getNum() * 2);
                            weaponCards[x1][y].setNum(0);

                            score += (weaponCards[x][y].getNum());
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
                    if (weaponCards[x1][y].getNum() > 0) {

                        if (weaponCards[x][y].getNum() <= 0) {
                            weaponCards[x][y].setNum(weaponCards[x1][y].getNum());
                            weaponCards[x1][y].setNum(0);

                            x++;
                            merge = true;
                        } else if (weaponCards[x][y].equals(weaponCards[x1][y])) {
                            weaponCards[x][y].setNum(weaponCards[x][y].getNum() * 2);
                            weaponCards[x1][y].setNum(0);
                            score += (weaponCards[x][y].getNum());
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
                    if (weaponCards[x][y1].getNum() > 0) {

                        if (weaponCards[x][y].getNum() <= 0) {
                            weaponCards[x][y].setNum(weaponCards[x][y1].getNum());
                            weaponCards[x][y1].setNum(0);

                            y--;
                            merge = true;

                        } else if (weaponCards[x][y].equals(weaponCards[x][y1])) {
                            weaponCards[x][y].setNum(weaponCards[x][y].getNum() * 2);
                            weaponCards[x][y1].setNum(0);
                            score += (weaponCards[x][y].getNum());
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
        score = 0;
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (weaponCards[x][y1].getNum() > 0) {

                        if (weaponCards[x][y].getNum() <= 0) {
                            weaponCards[x][y].setNum(weaponCards[x][y1].getNum());
                            weaponCards[x][y1].setNum(0);

                            y++;
                            merge = true;

                        } else if (weaponCards[x][y].equals(weaponCards[x][y1])) {
                            weaponCards[x][y].setNum(weaponCards[x][y].getNum() * 2);
                            weaponCards[x][y1].setNum(0);

                            score += (weaponCards[x][y].getNum());
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
