package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.catchball.CatchBallActivity;
import com.example.myapplication.loginRegister.RegisterActivity;
import com.example.myapplication.scoreboard.Scoreboard;
import com.example.myapplication.scoreboard.ScoreboardActivity;

public class SavePrincessActivity extends AppCompatActivity implements View.OnClickListener{
    //there are three buttons in Main page: Start, setting and help
    private Handler mHandler;
    private Activity current;
    private ImageView setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catchballmenu);

        //Set the runnable and handler

        current = this;
        this.mHandler = new Handler();
        this.mRunnable.run();

        //recognize three buttons and assign them to the corresponding Button variables
        Button start = findViewById(R.id.newballGame);
        start.setOnClickListener(this);
        Button scoreBoard = findViewById(R.id.ballScoreBoardbtn);
        scoreBoard.setOnClickListener(this);
        Button help = findViewById(R.id.help_ball);
        help.setOnClickListener(this);
        setting = findViewById(R.id.setting_btn_ball);
        setting.setOnClickListener(this);
    }
    private final Runnable mRunnable = new Runnable()
    {
        public void run()

        {   RelativeLayout layout = findViewById(R.id.catchBallmenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.SavePrince)}, current
                    , layout);
            SavePrincessActivity.this.mHandler.postDelayed(mRunnable, 50);
        }

    };//runnable
    @Override
    public void onClick(View view){
        switch (view.getId()){
            // press button start, we will Go to CatchBall Game
            case R.id.newballGame:
                Intent intent1 = new Intent(SavePrincessActivity.this, CatchBallActivity.class);
                startActivity(intent1);
                break;
            // press button setting, we will go to a setting page
            case R.id.ballScoreBoardbtn:
                Intent intent2 = new Intent(SavePrincessActivity.this, ScoreboardActivity.class);
                startActivity(intent2);
                break;
            //press help button, will go the help page
            case R.id.btn_help:
                Intent intent3 = new Intent(SavePrincessActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
            case R.id.setting_btn_ball:
                Intent intent4 = new Intent(SavePrincessActivity.this, SettingsActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}
