package com.example.gamecenter.games.catchballgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamecenter.R;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;

public class CatchBallScoreboardActivity extends AppCompatActivity {

    private User currentPlayer = UserManager.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        addReturnButtonListener();

        //Change to appropriate game title and score description
        TextView gameTitle = findViewById(R.id.GameTitle);
        TextView scoreDescription = findViewById(R.id.ScoreDescription);
        gameTitle.setText("CatchBall");
        scoreDescription.setText("Total points of ball");

        TextView globalScoresText = findViewById(R.id.GlobalScores);

        String globalScoreValues = CatchBallMenu.scoreboard.getScoreValues(false, currentPlayer);
        globalScoresText.setText(globalScoreValues);

        TextView userScoresText = findViewById(R.id.UserScores);
        String userScoreValues = CatchBallMenu.scoreboard.getScoreValues(true, currentPlayer);
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
        Intent tmp = new Intent(this, CatchBallMenu.class);
        startActivity(tmp);
    }
}
