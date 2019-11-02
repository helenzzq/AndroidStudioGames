package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.Math24.Math24Activity;
import com.example.myapplication.catchball.CatchBallActivity;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
    //there are three buttons in Main page: Start, setting and help
    private Button start,settings, help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //recognize three buttons and assign them to the corresponding Button variables
        start = findViewById(R.id.btn_start);
        start.setOnClickListener(this);
        settings = findViewById(R.id.btn_settings);
        settings.setOnClickListener(this);
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);
    }
    @Override

    public void onClick(View view){
        switch (view.getId()){
            // press button start, we will Go to CatchBall Game
            case R.id.btn_start:
                Intent intent1 = new Intent(MainPageActivity.this, CatchBallActivity.class);
                startActivity(intent1);
                break;
            // press button setting, we will go to a setting page
            case R.id.btn_settings:
                Intent intent2 = new Intent(MainPageActivity.this, SettingsActivity.class);
                startActivity(intent2);
                break;
            //press help button, will go the help page
            case R.id.btn_help:
                Intent intent3 = new Intent(MainPageActivity.this, HelpActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
