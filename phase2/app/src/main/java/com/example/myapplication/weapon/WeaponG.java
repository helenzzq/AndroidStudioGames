package com.example.myapplication.weapon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;


@SuppressLint("ViewConstructor")
public class WeaponG extends GridLayout implements View.OnTouchListener {

    static WeaponPresenter presenter;

    public WeaponG(Context context) {
        super(context);
        initGameView();
    }

     public WeaponG(Context context, AttributeSet attrs) {

        super(context, attrs);
        initGameView();
    }

    public WeaponG(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initGameView();

    }

    void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xFFFFFFFF);
        addCards(getCardwidth(), getCardwidth(), presenter.getWeaponManager().getCardCollection().getCards());

    }


    private float startX;
    private float startY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //Determine direction
                float offsetX = event.getX() - startX;
                float offsetY = event.getY() - startY;
                v.performClick();

                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
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



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        presenter.restart();
        //call presenter

    }

    private int getCardwidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return (cardWidth - 10) / 4;
    }

    private void addCards(int cardWidth, int cardHight, WeaponCard[][] weaponCard) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                WeaponCard c = new WeaponCard(getContext());
                c.setNum(2);
                addView(c, cardWidth, cardHight);
                weaponCard[x][y] = c;
            }
        }

    }



}
