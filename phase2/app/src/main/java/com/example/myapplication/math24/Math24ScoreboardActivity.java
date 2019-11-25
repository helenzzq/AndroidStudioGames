package com.example.myapplication.math24;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.useraccount.User;
import com.example.myapplication.loginregister.LoginActivity;
import com.example.myapplication.R;



public class Math24ScoreboardActivity extends AppCompatActivity {

    /**
     * The quick reference for the currently logged in player.
     */
    private User currentPlayer = LoginActivity.currentPlayer;

    /**
     *The controller of this view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        addReturnButtonListener();

        // Change to appropriate game title and score description
        TextView gameTitle = findViewById(R.id.GameTitle);
        TextView scoreDescription = findViewById(R.id.ScoreDescription);
        gameTitle.setText("Memory");
        scoreDescription.setText("Least moves taken");

        TextView globalScoresText = findViewById(R.id.GlobalScores);
        String globalScoreValues = Math24Menu.scoreboard.getScoreValues(false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = Math24Menu.scoreboard.getScoreValues(true, currentPlayer);
        userScoresText.setText(userScoreValues);
    }

    /**
     * Active the button to return to the main game screen.
     */
    private void addReturnButtonListener() {
        Button ReturnButton = findViewById(R.id.ReturnButton);
        ReturnButton.setOnClickListener(v -> switchToStarting());
    }
    private void switchToStarting(){
        Intent tmp = new Intent(this, Math24Menu.class);
        startActivity(tmp);
    }
}
