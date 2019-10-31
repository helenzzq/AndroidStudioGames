package com.example.myapplication.pushbox.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.SecondActivity;

public class PushBoxMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start, intro, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_box_main);

        start = findViewById(R.id.btn_start);
        start.setOnClickListener(this);
        intro = findViewById(R.id.btn_intro);
        intro.setOnClickListener(this);
        exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                Intent xgq = new Intent(PushBoxMainActivity.this, PushBoxChooseLevelActivity.class);
                startActivity(xgq);
                break;
            case R.id.btn_intro:
                Intent i = new Intent(PushBoxMainActivity.this, PushBoxIntroActivity.class);
                startActivity(i);
                break;
            case R.id.btn_exit:
                Intent intent3 = new Intent(PushBoxMainActivity.this, SecondActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
