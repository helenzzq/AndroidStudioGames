package com.example.gamecenter.games.slidinggame.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.strategy.BackGroundSetter;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.gameinterface.GameMenu;
import com.example.gamecenter.login.MainMenuActivity;


public class SlidingMenu extends BaseActivity implements GameMenu {
    private Handler handler;
    private Activity current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        findViewById(R.id.help_slide).setOnClickListener(v -> switchToPage(SlidingIntroActivity.class));

    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
