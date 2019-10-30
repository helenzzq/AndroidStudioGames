package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;


public class PushBoxChooseLevelAdapter extends ArrayAdapter<Boolean>{
    private Context mContext;
    private String[] mGuanQiaList;
    private Boolean[] mGameLevels_PassedOrNot;  //true means pass, false means not pass

    public PushBoxChooseLevelAdapter(Context context, int resource, Boolean[] levelList) {
        super(context, resource, levelList);
        mContext = context;
        mGameLevels_PassedOrNot = levelList;
    }

    @Override
    public Boolean getItem(int position) {
        return mGameLevels_PassedOrNot[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvLevel = null;
        if (convertView == null)
            tvLevel = new TextView(mContext);
        else
            tvLevel = (TextView) convertView;
        int level = position + 1;
        tvLevel.setText(level + " level");
        tvLevel.setGravity(Gravity.CENTER);
        if (mGameLevels_PassedOrNot[position])
            tvLevel.setBackgroundResource(R.drawable.gv_passed_guanqia_item_tv_border);
        else
            tvLevel.setBackgroundResource(R.drawable.gv_guanqia_item_tv_border);
        tvLevel.setPadding(10, 10, 10, 10);
        tvLevel.setTextSize(18f);
        return  tvLevel;
    }
}
