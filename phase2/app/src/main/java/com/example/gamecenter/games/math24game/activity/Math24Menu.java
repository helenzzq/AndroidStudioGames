package com.example.gamecenter.games.math24game.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamecenter.games.catchballgame.activity.CatchBallScoreboardActivity;
import com.example.gamecenter.games.math24game.Math24Presenter;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;


public class Math24Menu extends BaseActivity implements GameMenu {
    private Handler handler;
    private Activity current;

    /**
     * A ScoreBoard.
     */
    public static Scoreboard scoreboard;

    private static final String fileName = "Math24Scores.ser";

    private User currentPlayer = UserManager.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScore(scoreboardFileSaver.getGlobalScores());

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
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.savePrincess)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };

    private void setButtons(){
        setQuitBtn();
        setHelpBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_math));
    }


    /**
     * Activate the quit button.
     */
    @Override
    public void setScoreboardBtn(){
        findViewById(R.id.mathScoreBoardbtn).setOnClickListener(v->switchToPage(Math24ScoreboardActivity.class));
    }
    
    @Override
    public void setQuitBtn() {
        findViewById(R.id.QuitmathButton).setOnClickListener(v-> switchToPage(MainMenuActivity.class));
    }

    @Override
    public void setNewGameBtn() {
        findViewById(R.id.newmathGame).setOnClickListener(v -> switchToPage(Math24Activity.class));
    }
    @Override
    public void setHelpBtn() {
        findViewById(R.id.help_mathBtn).setOnClickListener(v-> switchToPage(Math24IntroActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
