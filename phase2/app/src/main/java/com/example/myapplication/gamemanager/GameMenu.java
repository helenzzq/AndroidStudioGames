package com.example.myapplication.gamemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.SavePrincessActivity;
import com.example.myapplication.R;
import com.example.myapplication.pictype2048.WeaponActivity;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        setResumeBtn();
        setBackBtn();
    }

    public void setResumeBtn(){
        findViewById(R.id.btn_gameResume).setOnClickListener(v -> {

            Intent i = new Intent(this, WeaponActivity.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }

    public void setBackBtn(){
        findViewById(R.id.btn_backtoMainPage).setOnClickListener(v -> {
        Intent i = new Intent(this, SavePrincessActivity.class);
        i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);

    });
   }

}