package com.example.myapplication.pushbox.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.pushbox.*;

public class PushBoxGameActivity extends Activity {

    private int mGameLevel;
    private PushBoxView mGameView;
    private Button mBtnPrvLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//        mGameLevel = intent.getIntExtra(PushBoxChooseLevelActivity.LEVEL, 1);
        PushBoxBitmaps.LoadBitmaps(getResources());  // Load pictures before PushBoxView created.
        PushBoxSound.loadSound(getAssets());
        setContentView(R.layout.activity_push_box_game);
        mGameView = findViewById(R.id.game_board);

        mBtnPrvLevel = findViewById(R.id.btn_prv_level);
        mBtnPrvLevel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mGameView.gotoPrvLevel();
            }
        });

        Button btnNextLevel = findViewById(R.id.btn_next_level);
        btnNextLevel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mGameView.gotoNextLevel();
            }
        });

        Button btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameView.resetGame();
            }
        });

        Button btnExit = (Button) findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitApp();
            }
        });
    }

    public int getGameLevel() {
        return mGameLevel;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PushBoxBitmaps.releaseBitmaps();               //release pics storage
        PushBoxSound.releaseSound();                   //release sound storage
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game_act, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_prv_level:
                mGameView.gotoPrvLevel();
                break;
            case R.id.itm_next_level:
                mGameView.gotoNextLevel();
                break;
            case R.id.itm_reset_game:
                mGameView.resetGame();
                break;
            case R.id.itm_undo_label:
                mGameView.undoMove();
                break;
            case R.id.itm_change_level:
                finish();
                break;
            case R.id.itm_game_exit:
                exitApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exitApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        System.exit(0);
    }
}
