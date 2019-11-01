package com.example.myapplication.pictype2048;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;


public class WeaponView extends GridLayout {

    private WeaponCard[][] cardsMap = new WeaponCard[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();

    public WeaponView(Context context) {
        super(context);
        initGameView();
    }

    public WeaponView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initGameView();
    }

    public WeaponView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initGameView();
    }


    private void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xFF999999);
        addCards(getCardwidth(), getCardwidth());

        setOnTouchListener(new View.OnTouchListener(){

            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX()-startX;
                        offsetY = event.getY()-startY;

                        if (Math.abs(offsetX)>Math.abs(offsetY)){
                            if (offsetX < -5){
                                swipeLeft();
                            }
                            else if(offsetX > 5){
                                swipeRight();
                            }
                        }else{
                            if(offsetY < -5){
                                swipeUp();
                            }else if(offsetY > 5){
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h)-10)/4;



        startGame();
    }

    private int getCardwidth(){
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return ( cardWidth - 10 ) / 4;
    }

    private void addCards(int cardWidth, int cardHight){
        WeaponCard c;
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                c = new WeaponCard(getContext());
                c.setNum(2);
                addView(c, cardWidth, cardHight);

                cardsMap[x][y] = c;
            }
        }

    }

    private void startGame(){

        WeaponActivity.getWeaponActivity().clearScore();

        for (int y=0; y<4; y++){
            for (int x=0; x<4; x++){
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();

    }

    private void addRandomNum(){

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

    private void swipeLeft(){
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
            addRandomNum();
            checkComplete();
        }

    }

    private void swipeRight(){
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
            addRandomNum();
            checkComplete();
        }

    }

    private void swipeUp(){
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
            addRandomNum();
            checkComplete();
        }

    }

    private void swipeDown(){
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
            addRandomNum();
            checkComplete();
        }

    }

    private void checkComplete(){

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
