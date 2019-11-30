package com.example.gamecenter.games.catchballgame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.HelpActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.games.catchballgame.presenter.CatchBallPresenter;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.scoreboard.Scoreboard;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;

public class CatchBallMenu extends BaseActivity implements GameMenu {
    //there are three buttons in Main page: Start, setting and help

    /**
     * The Scoreboard
     */
    public static Scoreboard scoreboard;

    /**
     * The CatchballPresenter
     */
    public static CatchBallPresenter controller;

    /**
     * The file of CatchBallScores.
     */
    private static final String fileName = "CatchBallScores.ser";

    private Handler handler;
    private Activity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //GameFileSaver gameFileSaver = new GameFileSaver(this, LoginActivity.currentPlayer.getCatchBallGameFile());
        /*if(gameFileSaver.getGameManager() != null){
            controller.setGameManager(gameFileSaver.getGameManager());
        }
        controller.register(gameFileSaver);
*/
        //Scoreboard MVC setup
        /*
        scoreboard = new Scoreboard();
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboard.register(scoreboardFileSaver);
        scoreboard.setGlobalScore(scoreboardFileSaver.getGlobalScores());
        gameFileSaver.saveToFile();*/

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
        setQuitBtn();
        setHelpBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_ball));
    }

    /**
     * Activate the quit button.
     */
    @Override
    public void setQuitBtn() {
        findViewById(R.id.quitBallBtn).setOnClickListener(v-> switchToPage(MainMenuActivity.class));
    }

    public void setScoreboardBtn(){
        findViewById(R.id.ballScoreBoardbtn).setOnClickListener(v->switchToPage(CatchBallScoreboardActivity.class));
    }

    @Override
    public void setNewGameBtn() {
        findViewById(R.id.newballGame).setOnClickListener(v -> switchToPage(CatchBallActivity.class));

    }

    @Override
    public void setHelpBtn() {
        findViewById(R.id.help_ball).setOnClickListener(v-> switchToPage(HelpActivity.class));

    }
}
