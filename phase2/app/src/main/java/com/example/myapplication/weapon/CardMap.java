package com.example.myapplication.weapon;


import android.graphics.Point;


import java.util.ArrayList;
import java.util.List;

public class CardMap {
    private int screeHeight;
    private int screenWidth;
    private WeaponCard[][] cardsMap;
    private List<Point> emptyPoints;


    /**
     * Set the initial CardMap
     */
    CardMap(){
        cardsMap = new WeaponCard[4][4];
        emptyPoints = new ArrayList<Point>();
        addRandomNum();
        addRandomNum();

    }

    WeaponCard[][] getCardsMap(){
        return cardsMap;
    }

    int getScreenWidth(){
        return screenWidth;
    }

    void addRandomNum(){

        emptyPoints.clear();

        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                if (cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);

    }





}
