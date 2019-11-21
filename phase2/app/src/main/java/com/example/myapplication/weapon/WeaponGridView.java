package com.example.myapplication.weapon;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;


public class WeaponGridView extends GridLayout{

    private static WeaponPresenter presenter;
    private WeaponCard[][] weaponCard;
    private float startX;
    private float startY;

    public WeaponGridView(Context context) {
        super(context);
        initGameView();

    }

    public WeaponGridView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initGameView();
    }

    public WeaponGridView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initGameView();

    }

    public static WeaponPresenter getPresenter() {
        return presenter;
    }

    private void initGameView() {
        presenter = new WeaponPresenter(new WeaponManager(),this);
        setColumnCount(4);
        setBackgroundColor(0xFFFFFFFF);
        addCards(getCardwidth(), getCardwidth(), presenter.getWeaponManager().getWeaponCards());
        setOnTouchListener(this::setOnTouch);


    }

    private boolean setOnTouch(View v, MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //Determine direction
                WeaponManager weaponManager = presenter.getWeaponManager();
                float offsetX = event.getX() - startX;
                float offsetY = event.getY() - startY;
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    if (offsetX < -5) {
//                            weaponManager.swipeLeft();
                        //Left
                        presenter.swipe(false, true);
                    } else if (offsetX > 5) {
                        //Right
//                            weaponManager.swipeRight();
                        presenter.swipe(false, false);

                    }
                } else {
                    if (offsetY < -5) {
                        weaponManager.swipeUp();
                        presenter.swipe(true, true);
                    } else if (offsetY > 5) {
                        weaponManager.swipeDown();
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

    }

    private int getCardwidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return (cardWidth - 10) / 4;
    }

    private void addCards(int cardWidth, int cardHeight, WeaponCard[][] weaponCard) {

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                WeaponCard c = new WeaponCard(getContext());
                c.setNum(2);
                addView(c, cardWidth, cardHeight);
                weaponCard[x][y] = c;
            }
        }
        this.weaponCard = weaponCard;

    }
}
