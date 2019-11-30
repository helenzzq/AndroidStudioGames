package com.example.gamecenter.games.math24game.activity;

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

import com.example.gamecenter.games.math24game.Math24Presenter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.login.MainMenuActivity;
import com.example.gamecenter.scoreboard.Scoreboard;


public class Math24Menu extends BaseActivity implements GameMenu {
    private Handler handler;
    private Activity current;

    /**
     * A Math24Presenter.
     */
    public static Math24Presenter math24Presenter;

    /**
     * A ScoreBoard.
     */
    public static Scoreboard scoreboard;

    /**
     * A file with Math24.
     */
    private static final String fileName = "math24scores.ser";

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
            BackGroundSetter.setWallPaper(new TextView[]{findViewById(R.id.savePrincess)},current,layout);
            handler.postDelayed(runnable, 50);
        }
    };

    private void setButtons(){
        setQuitBtn();
        setHelpBtn();
        setLoadBtn();
        setNewGameBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_math));
    }




    /**
     * Activate the quit button.
     */
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
    public void setLoadBtn() {
//        findViewById(R.id.Loadballbtn).setOnClickListener();

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
