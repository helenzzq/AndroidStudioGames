package com.example.myapplication.pushbox.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.pushbox.*;

import java.io.IOException;

public class PushBoxChooseLevelActivity extends AppCompatActivity implements View.OnClickListener {
    private Button level1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_box_choose_level);

        level1 = findViewById(R.id.btn_level1);
        level1.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_level1:
                Intent intent1 = new Intent(PushBoxChooseLevelActivity.this, PushBoxGameActivity.class);
                startActivity(intent1);
                break;
        }

    }
}


