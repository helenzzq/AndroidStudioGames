package com.example.myapplication.loginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.BackGroundSetting;
import com.example.myapplication.SavePrincessActivity;
import com.example.myapplication.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //get the SharedPreference object
        SharedPreferences sharedPref = getSharedPreferences("switch", Context.MODE_PRIVATE);
        String on = sharedPref.getString("on", "");
        ConstraintLayout layout = findViewById(R.id.mainmenu);

        //Set the BackGround
        BackGroundSetting backGroundSetting = new BackGroundSetting();
        backGroundSetting.setWallPaper(new TextView[0],
                this, layout, on);


        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String display = preferences.getString("MainMenuActivity","");

        final Button btPlay =findViewById(R.id.btPlay);



        btPlay.setOnClickListener(v -> {
            Intent displayScreen = new Intent(MainMenuActivity.this, SavePrincessActivity.class);
            startActivity(displayScreen);
        });


        if(display.equals("User or password is incorrect"))
        {
            btPlay.setEnabled(false);
        }

        
    }



}
