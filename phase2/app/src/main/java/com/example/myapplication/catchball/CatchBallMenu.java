package com.example.myapplication.catchball;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.HelpActivity;
import com.example.myapplication.R;
import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.gamemanager.BaseActivity;
import com.example.myapplication.scoreboard.ScoreboardActivity;


public class CatchBallMenu extends BaseActivity {
    //there are three buttons in Main page: Start, setting and help
    private Handler handler;
    private Activity current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catchballmenu);

        current = this;
        setButtons();
         this.handler = new Handler();
         this.runnable.run();


    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.catchBallmenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.catchBall_text)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };

    private void setButtons(){
        setBtn(findViewById(R.id.newballGame), CatchBallActivity.class);
        setBtn(findViewById(R.id.ballScoreBoardbtn), ScoreboardActivity.class);
        setBtn(findViewById(R.id.help_ball), HelpActivity.class);
        onClickSetting(findViewById(R.id.setting_btn_ball));

    }

}
