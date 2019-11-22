package com.example.myapplication.gamemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.SettingsActivity;

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public abstract class BaseActivity extends AppCompatActivity{


    public void setBtn(Button Btn, Class targetPage){
        Btn.setOnClickListener(v -> {
            //wait to implement loading
            Intent i = new Intent(this, targetPage);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }

    public void onClickSetting(ImageView settingBtn){
        settingBtn.setOnClickListener(v -> {
            //wait to implement loading
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }

/*    public void autoRefresh(Handler handler, ViewGroup layout, TextView[] textViews, Activity activity){
        mHandler = handler;
        mHandler.postDelayed(() -> {
            //Refresh the Page every 0.5 sec
            BackGroundSetter.setWallPaper(textViews, activity
                    , layout);
        }, 50);


    }*/


}
