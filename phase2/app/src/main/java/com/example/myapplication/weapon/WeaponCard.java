package com.example.myapplication.weapon;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;

public class WeaponCard extends FrameLayout {
    private ImageView pic;
    private int[] picArray = new int[33000];
    private int num = 0;



    public WeaponCard(Context context) {
        super(context);
        matchPic();
        pic = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(pic, lp);

        setNum(0);
    }

    public void matchPic() {
        picArray[0] = 0;
        picArray[2] = R.drawable.rare0;
        picArray[4] = R.drawable.rare1;
        picArray[8] = R.drawable.rare2;
        picArray[16] = R.drawable.rare3;
        picArray[32] = R.drawable.rare4;
        picArray[64] = R.drawable.rare5;
        picArray[128] = R.drawable.rare6;
        picArray[256] = R.drawable.rare7;
        picArray[512] = R.drawable.rare8;
        picArray[1024] = R.drawable.rare9;
        picArray[2048] = R.drawable.rare10;
        picArray[4096] = R.drawable.rare11;
        picArray[8192] = R.drawable.rare12;
        picArray[16384] = R.drawable.rare13;
        picArray[32768] = R.drawable.rare14;

    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        pic.setBackgroundResource(picArray[num]);


    }

    public boolean equals(WeaponCard o) {
        return getNum() == o.getNum();
    }

}