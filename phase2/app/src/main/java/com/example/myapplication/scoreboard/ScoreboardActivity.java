package com.example.myapplication.scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.catchball.CatchBallMenu;
import com.example.myapplication.loginRegister.LoginActivity;
import com.example.myapplication.useraccount.User;

public class ScoreboardActivity extends AppCompatActivity {

    private User currentPlayer = LoginActivity.currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
    }

    /**
     * Active the button to return to the main game screen.
     */

}
