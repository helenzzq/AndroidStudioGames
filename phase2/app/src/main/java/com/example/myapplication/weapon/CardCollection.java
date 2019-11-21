package com.example.myapplication.weapon;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

class CardCollection {
    private List<Point> emptyPoints;
    private  WeaponCard[][] cardCollection;

    CardCollection(){
        cardCollection = new WeaponCard[4][4];
        emptyPoints = new ArrayList<>();
    }

     WeaponCard[][] getCards() {
        return cardCollection;
    }


    void setCardCollection() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardCollection[x][y].setNum(0);
            }
        }
    }

    void addRandomNum() {

        emptyPoints.clear();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardCollection[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardCollection[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }
}
