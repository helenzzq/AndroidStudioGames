package com.example.myapplication.pushbox.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class PushBoxMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_box_main);
        Button btnGameIntro = (Button) findViewById(R.id.btn_game_intro);
        btnGameIntro.setOnClickListener(new BtnGameIntro_ClickListener());

        Button btnGameStart = (Button) findViewById(R.id.btn_start_game);
        btnGameStart.setOnClickListener(new BtnStart_ClickListener());

        Button btnExit = (Button) findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new BtnExit_ClickListener());
    }

    private class BtnGameIntro_ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(PushBoxMainActivity.this, PushBoxIntroActivity.class);
            startActivity(i);
        }
    }

    private class BtnStart_ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent xgq = new Intent(PushBoxMainActivity.this, PushBoxChooseLevelActivity.class);
            startActivity(xgq);

        }
    }

    private class BtnExit_ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_start:
                Intent xgq = new Intent(PushBoxMainActivity.this, PushBoxChooseLevelActivity.class);
                startActivity(xgq);
                break;
            case R.id.itm_intro:
                Intent i = new Intent(PushBoxMainActivity.this, PushBoxIntroActivity.class);
                startActivity(i);
                break;
            case R.id.itm_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
