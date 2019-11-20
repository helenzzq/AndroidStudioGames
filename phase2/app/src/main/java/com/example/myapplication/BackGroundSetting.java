package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class BackGroundSetting {

    public void setWallPaper(TextView[] texts, Activity context, ViewGroup layout, String on){
        if (on.equals("true")) {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.deernight));

            for (TextView k : texts) {
                k.setTextColor(Color.parseColor("#FFFFFF"));}
        } else {
            layout.setBackground(ContextCompat.getDrawable(context, R.drawable.whitebackground));

            for (TextView t : texts) {
                t.setTextColor(Color.parseColor("#FF777070"));
            }
        }

    }
}
