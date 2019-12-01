package com.example.gamecenter.games.slidinggame.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.gamecenter.games.slidinggame.SlidingPresenter;
import com.example.gamecenter.games.slidinggame.model.SlidingCard;
import com.example.gamecenter.games.slidinggame.model.SlidingManager;


public class SlidingGrid extends GridLayout{

    private static SlidingPresenter presenter;
    private float startX;
    private float startY;
    public static int num;

    public SlidingGrid(Context context) {
        super(context);
        setNum(SlidingActivity.getNum());
        initGameView();

    }

    public SlidingGrid(Context context, AttributeSet attrs) {

        super(context, attrs);
        setNum(SlidingActivity.getNum());
        initGameView();
    }

    public SlidingGrid(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        setNum(SlidingActivity.getNum());
        initGameView();

    }


    public static SlidingPresenter getPresenter() {
        return presenter;
    }

    private void initGameView() {
        presenter = new SlidingPresenter(new SlidingManager(),this);
        setColumnCount(num);
        setBackgroundColor(0xFFFFFFFF);
        addCards(getCardwidth(), getCardwidth(), presenter.getSlidingManager().getSlidingCards());
        setOnTouchListener(this::setOnTouch);
    }

    public static void setNum(int num){
        SlidingGrid.num = num;
    }

    public static int getNum(){
        return num;
    }

    private boolean setOnTouch(View v, MotionEvent event){

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //Determine direction
                float offsetX = event.getX() - startX;
                float offsetY = event.getY() - startY;
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
                        //Left
                        presenter.swipe(false, true);
                    } else if (offsetX > 5) {
                        presenter.swipe(false, false);

                    }
                } else {
                    if (offsetY < -5) {
                        presenter.swipe(true, true);
                    } else if (offsetY > 5) {
                        presenter.swipe(true, false);
                    }
                }
                break;
        }
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onPause(){
        this.setOnTouchListener((view, motionEvent) -> true);
    }
    public void onResume(){
        setOnTouchListener(this::setOnTouch);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        presenter.restart();

    }

    private int getCardwidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return (cardWidth - 10) / num;
    }

    private void addCards(int cardWidth, int cardHeight, SlidingCard[][] slidingCard) {

        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                SlidingCard c = new SlidingCard(getContext());
                c.setNum(1);
                addView(c, cardWidth, cardHeight);
                slidingCard[x][y] = c;
            }
        }
    }

    public void onDestory(){
        presenter = null;
    }

}
