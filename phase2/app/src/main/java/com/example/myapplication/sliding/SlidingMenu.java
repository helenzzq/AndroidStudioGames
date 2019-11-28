package com.example.myapplication.sliding;

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

import com.example.myapplication.HelpActivity;
import com.example.myapplication.R;
import com.example.myapplication.strategyclass.BackGroundSetter;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.gamecenter.GameMenu;
import com.example.myapplication.loginregister.MainMenuActivity;


public class SlidingMenu extends BaseActivity implements GameMenu, PopupMenu.OnMenuItemClickListener {
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
        setLoadBtn();
        setNewGameBtn();
        onClickSettingBtn(findViewById(R.id.setting_btn_slide));
    }
    /**
     * Activate the items in the dropDown menu.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Class targetGame = SlidingActivity.class;
        SharedPreferences level = getSharedPreferences("slidingLevel", Context.MODE_PRIVATE);
        switch (item.getItemId()) {
            case R.id.map3x3:
                //Select difficulty
                switchToPage(targetGame);
                level.edit().putString("level", "easy").apply();
                return true;

            case R.id.map4x4:
                //controller.setUpBoard("Hard");
                level.edit().putString("level", "hard").apply();
                switchToPage(SlidingActivity.class);
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
        findViewById(R.id.QuitslideButton).setOnClickListener(v-> switchToPage(MainMenuActivity.class));


    }

    @Override
    public void setNewGameBtn() {
        findViewById(R.id.newslidingGame).setOnClickListener(this::showPopup);

    }
    @Override
    public void setHelpBtn() {
        findViewById(R.id.help_slide).setOnClickListener(v-> switchToPage(HelpActivity.class));

    }

    @Override
    public void setLoadBtn() {
//        findViewById(R.id.Loadballbtn).setOnClickListener();

    }

    @Override
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_sliding_level);
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
