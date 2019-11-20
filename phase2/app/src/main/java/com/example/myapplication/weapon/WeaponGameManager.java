package com.example.myapplication.weapon;

import android.view.WindowManager;

import com.example.myapplication.pictype2048.WeaponActivity;

public class WeaponGameManager {

    private CardMap cm;
    private WeaponCard[][] cardsMap;
    //private boolean gameOver;

    WeaponGameManager(WindowManager window, int x, int y){
        cm = new CardMap();
      //  gameOver = false;
        cardsMap = cm.getCardsMap();

    }


    void swipeLeft(){
        boolean merge = false;
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                for (int x1 = x + 1; x1 < 4; x1++){
                    if(cardsMap[x1][y].getNum() > 0){

                        if (cardsMap[x][y].getNum() <=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            merge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);

                            WeaponActivity.getWeaponActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge){
            cm.addRandomNum();
            checkComplete();
        }

    }

    void swipeRight(){
        boolean merge = false;
        for (int y = 0; y < 4; y++){
            for (int x = 3; x >= 0; x--){

                for (int x1 = x - 1; x1 >= 0; x1--){
                    if(cardsMap[x1][y].getNum() > 0){

                        if (cardsMap[x][y].getNum() <=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            merge = true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            WeaponActivity.getWeaponActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }

                        break;
                    }
                }
            }
        }
        if (merge){
            cm.addRandomNum();
            checkComplete();
        }

    }

    void swipeUp(){
        boolean merge = false;
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){

                for (int y1 = y + 1; y1 < 4; y1++){
                    if(cardsMap[x][y1].getNum() > 0){

                        if (cardsMap[x][y].getNum() <=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            merge = true;

                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            WeaponActivity.getWeaponActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge){
            cm.addRandomNum();
            checkComplete();
        }

    }

    void swipeDown(){
        boolean merge = false;
        for (int x = 0; x < 4; x++){
            for (int y = 3; y >= 0; y--){

                for (int y1 = y - 1; y1 >= 0; y1--){
                    if(cardsMap[x][y1].getNum() > 0){

                        if (cardsMap[x][y].getNum() <=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            merge = true;

                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);

                            WeaponActivity.getWeaponActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;

                        }

                        break;
                    }
                }
            }
        }
        if (merge){
            cm.addRandomNum();
            checkComplete();
        }

    }

    void checkComplete(){

        boolean complete = true;

        ALL:
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                if (cardsMap[x][y].getNum() == 0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))){

                    complete = false;
                    break ALL;

                }
            }
        }

        if(complete){
            WeaponActivity.getWeaponActivity().goToResult();
        }

    }





}
