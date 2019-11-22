package com.example.myapplication.StrategyClass;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

public class BackGroundSetter {

    private static boolean switchStatus;

    public static boolean isSwitchStatus() {
        return switchStatus;
    }

    public static void setSwitchStatus(boolean switchStatus) {
        BackGroundSetter.switchStatus = switchStatus;
    }

    public static void setWallPaper(TextView[] texts, Activity context, ViewGroup layout){
        if (switchStatus) {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.deernight));

            for (TextView k : texts) {
                k.setTextColor(Color.parseColor("#FFFFFF"));}
        } else {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.whitebackground));

            for (TextView t : texts) {
                t.setTextColor(Color.parseColor("#0081BB"));
            }
        }

    }
}
