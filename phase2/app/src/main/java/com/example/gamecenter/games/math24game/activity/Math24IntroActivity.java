package com.example.gamecenter.games.math24game.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gamecenter.R;

import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class Math24IntroActivity extends AppCompatActivity implements View.OnClickListener {

    /** onCreate method for Math24 Introduction page
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_intro);

        Button back = findViewById(R.id.btn_back2);
        back.setOnClickListener(this);
    }


    /** Set the back button
     * @param view
     *
     */
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back2) {
            finish();
        }
    }
}