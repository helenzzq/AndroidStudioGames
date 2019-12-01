package com.example.gamecenter.games.slidinggame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.activity.CatchBallScoreboardActivity;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.login.MainMenuActivity;


public class SlidingMenu extends BaseActivity implements GameMenu {
    private Handler handler;
    private Activity current;

    /**
     * A ScoreBoard.
     */
    public static Scoreboard scoreboard;

    private static final String fileName = "SlidingScores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScore(scoreboardFileSaver.getGlobalScores());

        setContentView(R.layout.activity_slidingmenu);

        current = this;
        setButtons();
        this.handler = new Handler();
        this.runnable.run();


    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout layout = findViewById(R.id.slidingMenu);
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.sliding_text)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };
    private void setButtons(){
        setQuitBtn();
        setHelpBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_slide));
    }

    /**
     * Activate the quit button.
     */
    @Override
    public void setQuitBtn() {
        findViewById(R.id.QuitslideButton).setOnClickListener(v-> switchToPage(MainMenuActivity.class));


    }

    @Override
    public void setNewGameBtn() {
        findViewById(R.id.newslidingGame).setOnClickListener(v -> switchToPage(SlidingActivity.class));

    }
    @Override
    public void setHelpBtn() {
        findViewById(R.id.help_slide).setOnClickListener(v -> switchToPage(SlidingMenuIntroActivity.class));

    }


    @Override
    public void setScoreboardBtn(){
        findViewById(R.id.slideScoreBoardbtn).setOnClickListener(v->switchToPage(SlidingScoreboardActivity.class));
    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
