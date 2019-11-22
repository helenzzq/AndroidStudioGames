package com.example.myapplication.math24;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.graphics.Color;


public class Math24Activity extends AppCompatActivity implements View.OnClickListener {
    private Button plus, minus, multiply, divide, equal, nextGame, clear, backGame, help, left_bracket, right_bracket;
    private TextView calculation, result, message, textLive, scoreText;
    private Button num1, num2, num3, num4;
    private int numLives = 3, score = 0;
    private boolean lackOperator = false;
    private Math24Manager ques = new Math24Manager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24);

        //create variables plus, minor, multiply, divide, and assign the buttons to them respectively
        plus = findViewById(R.id.btn_plus);
        plus.setOnClickListener(this);
        minus = findViewById(R.id.btn_minus);
        minus.setOnClickListener(this);
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

        //the text saying the current score
        scoreText = findViewById(R.id.score);
        scoreText.setText(String.format("Your score %d", score));

        //button proceed to result activity
        nextGame = findViewById(R.id.btn_next);
        nextGame.setOnClickListener(this);

        //button clear the text in calculation
        clear = findViewById(R.id.btn_clear);
        clear.setOnClickListener(this);

        //button back to the last activity
        backGame = findViewById(R.id.btn_back);
        backGame.setOnClickListener(this);

        //button to see the instruction of the game
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);


    }

    //set on click event on buttons
    public void onClick(View view) {
        switch (view.getId()) {
            //fill the operators and brackets in the calculation
            case R.id.btn_plus:
                disableOperators();
                right_bracket.setEnabled(false);
                calculation.append("+");
                break;
            case R.id.btn_minus:
                disableOperators();
                right_bracket.setEnabled(false);
                calculation.append("-");
                break;
            case R.id.btn_multiply:
                disableOperators();
                right_bracket.setEnabled(false);
                calculation.append("*");
                break;
            case R.id.btn_divide:
                disableOperators();
                right_bracket.setEnabled(false);
                calculation.append("/");
                break;
            case R.id.btn_left:
                right_bracket.setEnabled(false);
                disableOperators();
                calculation.append(left_bracket.getText());
                break;
            case R.id.btn_right:
                enableOperators();
                calculation.append(right_bracket.getText());
                break;

            //fill the numbers in the calculation
            case R.id.ib_1:
                calculation.append(num1.getText());
                enableOperators();
                left_bracket.setEnabled(false);
                right_bracket.setEnabled(true);
                num1.setEnabled(false);
                break;
            case R.id.ib_2:
                calculation.append(num2.getText());
                enableOperators();
                left_bracket.setEnabled(false);
                right_bracket.setEnabled(true);
                num2.setEnabled(false);
                break;
            case R.id.ib_3:
                calculation.append(num3.getText());
                enableOperators();
                left_bracket.setEnabled(false);
                right_bracket.setEnabled(true);
                num3.setEnabled(false);
                break;
            case R.id.ib_4:
                calculation.append(num4.getText());
                enableOperators();
                left_bracket.setEnabled(false);
                right_bracket.setEnabled(true);
                num4.setEnabled(false);
                break;

            //button event reset the calculation text
            case R.id.btn_clear:
                calculation.setText("");
                enableNums();
                enableOperators();
                right_bracket.setEnabled(true);
                left_bracket.setEnabled(true);
                break;

            //button event proceed to the result activity
            case R.id.btn_next:
                goToResult();
                /*Intent nextgame = new Intent(Math24Activity.this, Math24ResultActivity.class);
                nextgame.putExtra("SCOREMath24", score);
                startActivity(nextgame);*/
                break;

            //button event back to the last activity
            case R.id.btn_back:
                finish();
                break;
            //button event see help activity
            case R.id.btn_help:
                Intent help1 = new Intent(Math24Activity.this, Math24IntroActivity.class);
                startActivity(help1);
                break;

            default:
                break;
        }

        //equal button onclick event
        if(!num1.isEnabled()&& !num2.isEnabled()&& !num3.isEnabled()&&!num4.isEnabled()){
            //equal button is enabled only when four numbers are used
            equal.setEnabled(true);
            if(view.getId()==R.id.btn_equal){
                //four number buttons are enabled again after one try
                enableNums();
                int value = ques.calculate(calculation.getText().toString());
                result.setText(String.format("%d",value));
                calculation.setText("");

                //check if is the correct answer
                if (ques.is24(value)){
                    message.setText("Congratulations! \nClick On Next to Proceed.");
                    // clear button disabled after win the game
                    clear.setEnabled(false);
                    scoreText.setText(String.format("Your score: %d", score += 100));
                    disableNums();
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

    public void disableNums(){
        num1.setEnabled(false);
        num2.setEnabled(false);
        num3.setEnabled(false);
        num4.setEnabled(false);
    }

    public void enableNums(){
        num1.setEnabled(true);
        num2.setEnabled(true);
        num3.setEnabled(true);
        num4.setEnabled(true);
    }

    public void disableOperators(){
        plus.setEnabled(false);
        minus.setEnabled(false);
        multiply.setEnabled(false);
        divide.setEnabled(false);
    }

    public void enableOperators(){
        plus.setEnabled(true);
        minus.setEnabled(true);
        multiply.setEnabled(true);
        divide.setEnabled(true);
    }

    public void goToResult() {
        Intent intent = new Intent(Math24Activity.this, Math24ResultActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
    }
}