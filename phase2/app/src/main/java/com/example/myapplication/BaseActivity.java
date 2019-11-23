package com.example.myapplication;

import android.content.Intent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public abstract class BaseActivity extends AppCompatActivity{


    public void switchToPage(Class targetPage){
            Intent i = new Intent(this, targetPage);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

    }

    public void onClickSettingBtn(ImageView settingBtn){
        settingBtn.setOnClickListener(v -> {
            //wait to implement loading
            Intent i = new Intent(this, SettingsActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }

    public void goToResult(Class targetPage, int score){
        Intent intent = new Intent(this, targetPage);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }


}
