package com.example.myapplication.math24;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.gamemanager.BaseActivity;
import com.example.myapplication.scoreboard.ScoreboardActivity;

public class Math24Menu extends BaseActivity {
    private Handler handler;
    private Activity current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24menu);

        current = this;
        setButtons();
        this.handler = new Handler();
        this.runnable.run();


    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.math24Menu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.math24_text)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };

    private void setButtons(){
        setBtn(findViewById(R.id.newmathGame), Math24Activity.class);
        setBtn(findViewById(R.id.mathScoreBoardbtn), ScoreboardActivity.class);
        setBtn(findViewById(R.id.help_mathBtn), Math24IntroActivity.class);
        onClickSetting(findViewById(R.id.setting_btn_math));

    }

}
