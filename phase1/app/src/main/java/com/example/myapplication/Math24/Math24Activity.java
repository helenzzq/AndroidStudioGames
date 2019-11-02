package com.example.myapplication.Math24;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.graphics.Color;
import java.util.concurrent.*;
import com.example.myapplication.Math24.Math24Calculation;
import com.example.myapplication.Math24.Math24ResultActivity;
import com.example.myapplication.pictype2048.WeaponActivity;


public class Math24Activity extends AppCompatActivity implements View.OnClickListener {
    private Button plus, minor, multiply, divide, equal, nextGame, clear, backGame, help, left_bracket, right_bracket;
    private TextView calculation, result, message, textLive, scoreText;
    private Button num1, num2, num3, num4;
    private int numLives = 3, score = 0;
    private Math24Calculation ques = new Math24Calculation();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24);

        //create variables plus, minor, multiply, divide, and assign the buttons to them respectively
        plus = findViewById(R.id.btn_plus);
        plus.setOnClickListener(this);
        minor = findViewById(R.id.btn_minor);
        minor.setOnClickListener(this);
        multiply = findViewById(R.id.btn_multiply);
        multiply.setOnClickListener(this);
        divide = findViewById(R.id.btn_divide);
        divide.setOnClickListener(this);
        //create variables left bracket, right bracket and assign the the buttons to them respectively
        left_bracket = findViewById(R.id.btn_left);
        left_bracket.setOnClickListener(this);
        right_bracket = findViewById(R.id.btn_right);
        right_bracket.setOnClickListener(this);
        //create variable equal, assign the button to it
        equal = findViewById(R.id.btn_equal);
        equal.setOnClickListener(this);
        equal.setEnabled(false);

        //select one random question in question[][], and assign each number in each number in the
        //four buttons
        int [] question = ques.createQuestion();
        num1 = findViewById(R.id.ib_1);
        num1.setText(String.format("%d", question[0]));
        num1.setOnClickListener(this);
        num2 = findViewById(R.id.ib_2);
        num2.setText(String.format("%d", question[1]));
        num2.setOnClickListener(this);
        num3 = findViewById(R.id.ib_3);
        num3.setText(String.format("%d", question[2]));
        num3.setOnClickListener(this);
        num4 = findViewById(R.id.ib_4);
        num4.setText(String.format("%d", question[3]));
        num4.setOnClickListener(this);

        //create a text space called calculation to put the equation entered by the player
        calculation = findViewById(R.id.tv_calculation);
        calculation.setText("");

        //put the output in result
        result = findViewById(R.id.textView6);
        result.setText("");

        //the text saying whether or not the answer is accurate
        message = findViewById(R.id.message);
        message.setText("");
        //the text saying the number of lives left
        textLive = findViewById(R.id.text_live);
        //
        scoreText = findViewById(R.id.score);
        scoreText.setText(String.format("Your score %d", score));

        nextGame = findViewById(R.id.btn_next);
        nextGame.setOnClickListener(this);
        clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener(this);
        backGame = findViewById(R.id.btn_back);
        backGame.setOnClickListener(this);
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);


    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_plus:
                calculation.append("+");
                break;
            case R.id.btn_minor:
                calculation.append("-");
                break;
            case R.id.btn_multiply:
                calculation.append("*");
                break;
            case R.id.btn_divide:
                calculation.append("/");
                break;
            case R.id.ib_1:
                calculation.append(num1.getText());
                num1.setEnabled(false);
                break;
            case R.id.ib_2:
                calculation.append(num2.getText());
                num2.setEnabled(false);
                break;
            case R.id.ib_3:
                calculation.append(num3.getText());
                num3.setEnabled(false);
                break;
            case R.id.ib_4:
                calculation.append(num4.getText());
                num4.setEnabled(false);
                break;
            case R.id.btn_left:
                calculation.append(left_bracket.getText());
                break;
            case R.id.btn_right:
                calculation.append(right_bracket.getText());
                break;
            case R.id.btn_next:
                Intent nextgame = new Intent(Math24Activity.this,
                        Math24ResultActivity.class);
                nextgame.putExtra("SCOREMath24", score);
                startActivity(nextgame);
            case R.id.btn_back:
                Intent backgame = new Intent(Math24Activity.this,
                        WeaponActivity.class);
                startActivity(backgame);

            case R.id.btn_clear:
                calculation.setText("");
                num1.setEnabled(true);
                num2.setEnabled(true);
                num3.setEnabled(true);
                num4.setEnabled(true);
                break;
            default:
                break;
        }
        if(!num1.isEnabled()&& !num2.isEnabled()&& !num3.isEnabled()&&!num4.isEnabled()){
            equal.setEnabled(true);
            if(view.getId()==R.id.btn_equal){
                num1.setEnabled(true);
                num2.setEnabled(true);
                num3.setEnabled(true);
                num4.setEnabled(true);
                int value = ques.calculate(calculation.getText().toString());
                result.setText(String.format("%d",value));
                calculation.setText("");
                if (ques.is24(value)){
                    message.setText("Congratulations! \nClick On Next to Proceed.");
                    scoreText.setText(String.format("Your score: %d", score += 100));
                    num1.setEnabled(false);
                    num2.setEnabled(false);
                    num3.setEnabled(false);
                    num4.setEnabled(false);
                    equal.setEnabled(false);
                }
                else{
                    message.setText("It's Wrong!!!");
                    numLives -= 1;
                    textLive.setText(String.format("lives remaining: %d", numLives));
                    equal.setEnabled(false);
                    if (numLives == 0) {
                        textLive.setTextColor(Color.RED);
                        message.setText("Click On Next to Proceed.");
                    }
                }
            }
        }
    }

}