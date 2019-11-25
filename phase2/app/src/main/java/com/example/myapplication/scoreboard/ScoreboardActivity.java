package com.example.myapplication.scoreboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.loginregister.LoginActivity;
import com.example.myapplication.useraccount.User;

public class ScoreboardActivity extends AppCompatActivity {

    private User currentPlayer = LoginActivity.currentPlayer;

    public static Scoreboard scoreboard;

    public static final String fileName = "slidingtilesscores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        addReturnButtonListener();


    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.button);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }
    private void switchToStarting(){

    }

    /**
     * Active the button to return to the main game screen.
     */

}
