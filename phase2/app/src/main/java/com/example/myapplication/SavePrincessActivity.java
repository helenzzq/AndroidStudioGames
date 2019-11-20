package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.catchball.CatchBallActivity;

public class SavePrincessActivity extends AppCompatActivity implements View.OnClickListener{
    //there are three buttons in Main page: Start, setting and help
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catchballmenu);

        ConstraintLayout layout = findViewById(R.id.catchBallMenu);

        //get the SharedPreference object
        SharedPreferences sharedPref = getSharedPreferences("switch", Context.MODE_PRIVATE);
        String on = sharedPref.getString("on", "");

        BackGroundSetting backGroundSetting = new BackGroundSetting();
        backGroundSetting.setWallPaper(new TextView[]{findViewById(R.id.savePrincess)},
                this, layout, on);

        //recognize three buttons and assign them to the corresponding Button variables
        Button start = findViewById(R.id.btn_start);
        start.setOnClickListener(this);
        Button settings = findViewById(R.id.scoreBoard);
        settings.setOnClickListener(this);
        Button help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);
        ImageView setting = findViewById(R.id.setting_btn1);
        setting.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            // press button start, we will Go to CatchBall Game
            case R.id.btn_start:
                Intent intent1 = new Intent(SavePrincessActivity.this, CatchBallActivity.class);
                startActivity(intent1);
                break;
            // press button setting, we will go to a setting page
            case R.id.scoreBoard:
//                Intent intent2 = new Intent(SavePrincessActivity.this, SettingsActivity.class);
//                startActivity(intent2);
                break;
            //press help button, will go the help page
            case R.id.btn_help:
                Intent intent3 = new Intent(SavePrincessActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
            case R.id.setting_btn1:
                Intent intent2 = new Intent(SavePrincessActivity.this, SettingsActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
