package com.example.myapplication.pictype2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.gamemanager.GameMenu;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


public class WeaponActivity extends AppCompatActivity {

    private int score = 0;
    private TextView tvScore;
    private static WeaponActivity weaponActivity = null;

    public WeaponActivity(){

        weaponActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);


        tvScore = (TextView) findViewById(R.id.tvScore);
    }

    public void clearScore(){
        score = 0;
        showScore();
    }

    public void showScore(){
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }



    public static WeaponActivity getWeaponActivity() {
        return weaponActivity;
    }

    public void goToResult() {
        Intent intent = new Intent(WeaponActivity.this, WeaponResultActivity.class);
        intent.putExtra("SCORE2048", score);
        startActivity(intent);
    }

    public void setPause2048Btn(){
        findViewById(R.id.PauseGameBtn2048).setOnClickListener(v -> {

            Intent i = new Intent(this, GameMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });
    }


}
