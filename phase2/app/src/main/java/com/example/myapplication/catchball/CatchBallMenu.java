package com.example.myapplication.catchball;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.loginregister.LoginActivity;
import com.example.myapplication.scoreboard.Scoreboard;

import com.example.myapplication.HelpActivity;
import com.example.myapplication.R;
import com.example.myapplication.StrategyClass.BackGroundSetter;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.GameMenu;
import com.example.myapplication.loginregister.MainMenuActivity;
import com.example.myapplication.scoreboard.ScoreboardFileSaver;
import com.example.myapplication.GameController;
import com.example.myapplication.GameFileSaver;

public class CatchBallMenu extends BaseActivity implements GameMenu, PopupMenu.OnMenuItemClickListener{
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
     * The file of slidingtilesscores.
     */
    private static final String fileName = "CatchBallscores.ser";

    private Handler handler;
    private Activity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*GameFileSaver gameFileSaver = new GameFileSaver(this, LoginActivity.currentPlayer.getCatchBallGameFile());
        if(gameFileSaver.getGameManager() != null){
            controller.setGameManager(gameFileSaver.getGameManager());
        }
        controller.register(gameFileSaver);

        //Scoreboard MVC setup
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
        setLoadBtn();
        setNewGameBtn();
        setScoreboardBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_ball));
    }

    /**
     * Activate the items in the dropDown menu.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Class targetGame = CatchBallActivity.class;
        SharedPreferences level = getSharedPreferences("ballLevel", Context.MODE_PRIVATE);
        switch (item.getItemId()) {
            case R.id.easy:
                //Select difficulty
                switchToPage(targetGame);
                level.edit().putString("level", "easy").apply();
                return true;

            case R.id.hard:
//                controller.setUpBoard("Hard");
                level.edit().putString("level", "hard").apply();
                switchToPage(CatchBallActivity.class);
                return true;

            default:
                return false;
        }
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
        findViewById(R.id.newballGame).setOnClickListener(this::showPopup);

    }
    @Override
    public void setHelpBtn() {
        findViewById(R.id.help_ball).setOnClickListener(v-> switchToPage(HelpActivity.class));

    }

    @Override
    public void setLoadBtn() {
//        findViewById(R.id.Loadballbtn).setOnClickListener();

    }

    @Override
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_choose_level);
        popup.show();

    }

    @Override
    public void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void makeToastNoLoadedText() {
        Toast.makeText(this, "No Saved Game", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
