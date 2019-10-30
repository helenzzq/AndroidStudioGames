package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CatchBallActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    //Size
    private int frameHeight;
    private int boxSize;
    private int screenHeight;
    private int screenWidth;



    //Position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;

    //Score for the game
    private int score=0;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //Status check
    private boolean action_flag = false;
    private boolean start_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box=(ImageView) findViewById(R.id.box);
        orange=(ImageView) findViewById(R.id.orange);
        pink=(ImageView) findViewById(R.id.pink);
        black=(ImageView) findViewById(R.id.black);

        //get the screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        //Move to out of screen
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        scoreLabel.setText("Score: 0" );

    }

    public void changePos(){

        hitCheck();

        //Orange
        orangeX -= 12;
        if(orangeX < 0){
            orangeX = screenWidth + 20;
            orangeY = (int)Math.floor(Math.random()*(frameHeight-orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);


        //black
        blackX -= 16;
        if(blackX < 0){
            blackX = screenWidth + 10;
            blackY = (int)Math.floor(Math.random()*(frameHeight-black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        //pink
        pinkX -= 20;
        if(pinkX < 0){
            pinkX = screenWidth + 3000;
            pinkY = (int)Math.floor(Math.random()*(frameHeight-pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        //Move Box
        if(action_flag){
            //Touching
            boxY -= 20;
        } else{
            //Releasing
            boxY += 20;
        }

        //check box position
        if(boxY<0)
            boxY=0;

        if(boxY>frameHeight-boxSize)
            boxY=frameHeight-boxSize;

        box.setY(boxY);

        scoreLabel.setText("Score: " + score );
    }

    public void hitCheck(){
        //if the center of the ball is in the box,it counts as a hit

        //Orange
        int orangeCenterX = orangeX + orange.getWidth()/2;
        int orangeCenterY = orangeY + orange.getHeight()/2;

        //hit must satisfies following condition
        //0<= orangeCenterX <= boxWidth
        //boxY <= orangeCenterY <= boxY+boxSize

        if( 0<=orangeCenterX && orangeCenterX<=boxSize &&
                boxY <= orangeCenterY && orangeCenterY <= boxY +boxSize){

                score += 10;
                orangeX = -10;

        }

        //Pink
        int pinkCenterX = pinkX + pink.getWidth()/2;
        int pinkCenterY = pinkY + pink.getHeight()/2;

        //hit must satisfies following condition
        //0<= pinkCenterX <= boxWidth
        //boxY <= pinkCenterY <= boxY+boxSize

        if( 0<=pinkCenterX && pinkCenterX<=boxSize &&
                boxY <= pinkCenterY && pinkCenterY <= boxY +boxSize){

            score += 30;
            pinkX = -10;

        }

        //black
        int blackCenterX = blackX + black.getWidth()/2;
        int blackCenterY = blackY + black.getHeight()/2;


        if( 0<=blackCenterX && blackCenterX<=boxSize &&
                boxY <= blackCenterY && blackCenterY <= boxY +boxSize){

            //stop the timer!!
            timer.cancel();
            timer=null;

            //Show Result
            Intent intent = new Intent(getApplicationContext(), resultActivity.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        if(start_flag == false){

            start_flag = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY=(int)box.getY();

            //The box is the square.(height and width are the same.)
            boxSize=box.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },0,20);

        }else{

            if(me.getAction()==MotionEvent.ACTION_DOWN){
                action_flag = true;
            } else if(me.getAction() == MotionEvent.ACTION_UP){
                action_flag = false;
            }
        }




        return true;
    }
}
