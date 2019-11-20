package com.example.myapplication.weapon;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.gamemanager.GameMenu;
import com.example.myapplication.pictype2048.WeaponActivity;
import com.example.myapplication.pictype2048.WeaponResultActivity;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class WeaponGameActivity extends AppCompatActivity implements WeaponGameView{

    private TextView tvScore;

    //Score for the game
    private int score=0;

    //Presenter for the game
    private WeaponPresenter presenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);

        tvScore = (TextView) findViewById(R.id.tvScore);
    }

    @Override
    public void goToResult() {
        Intent intent = new Intent(getApplicationContext(), WeaponResultActivity.class);
        intent.putExtra("SCORE2048", score);
        startActivity(intent);
    }

    @Override
    public int getCardWidth() {
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();

        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        return ( cardWidth - 10 ) / 4;
    }



//    @Override
//    public void addCards(int cardWidth, int cardHight){
//        WeaponCard c;
//        for(int y = 0; y < 4; y++){
//            for(int x = 0; x < 4; x++){
//                c = new WeaponCard(getContext());
//                c.setNum(2);
//                addView(c, cardWidth, cardHight);
//
//                cardsMap[x][y] = c;
//            }
//        }
//
//    }

    @Override
    public void clearScore() {
        score = 0;
        showScore();
    }

    @Override
    public void showScore() {
        tvScore.setText(score +"");
    }

    @Override
    public void addScore(int s) {
        score += s;

    }

    @Override
    public void setPause2048Btn() {
        findViewById(R.id.PauseGameBtn2048).setOnClickListener(v -> {
            Intent i = new Intent(this, GameMenu.class);
            i.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);

        });

    }
}
