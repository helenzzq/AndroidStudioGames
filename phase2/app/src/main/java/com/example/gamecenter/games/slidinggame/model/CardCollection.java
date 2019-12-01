package com.example.gamecenter.games.slidinggame.model;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

class CardCollection {
    private List<Point> emptyPoints;
    private  SlidingCard[][] cardCollection;
    private static int num;

    CardCollection(int num){
        this.num = num;
        cardCollection = new SlidingCard[num][num];
        emptyPoints = new ArrayList<>();
    }

     SlidingCard[][] getCards() {
        return cardCollection;
    }

    private static void setNum(int num){
        CardCollection.num = num;
    }

    public static int getNum()
    {
        return num;
    }
    
    void setCardCollection() {
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                cardCollection[x][y].setNum(0);
            }
        }
    }

    void addRandomNum() {

        emptyPoints.clear();

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                if (cardCollection[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardCollection[p.x][p.y].setNum(Math.random() > 0.1 ? 1 : 2);
    }
}
