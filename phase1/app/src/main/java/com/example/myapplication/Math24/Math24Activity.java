package com.example.myapplication.Math24;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.graphics.Color;

import com.example.myapplication.MainPageActivity;
import com.example.myapplication.pictype2048.WeaponActivity;


public class Math24Activity extends AppCompatActivity implements View.OnClickListener {
    private Button plus, minor, multiply, divide, equal, nextgame, restart, back, help, left_bracket, right_bracket;
    private TextView calculation, result, message, textLive;
    private Button num1, num2, num3, num4;
    private int numLives = 3;

    static private int questions[][] = {
            {5,5,5,1},
            {2,7,9,10},
            {2,8,8,8},
            {3,3,3,3},
            {3,3,4,5},
            {4,4,5,7},
            {4,4,6,8},
            {5,7,8,9},
            {6,6,7,10},
            {6,7,8,9}};

    //create a question randomly choose one from the 10 elements in questions
    public int[] createQuestion() {
        return questions[(int) (Math.random() * (10 - 1) + 1)];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24);

        plus = findViewById(R.id.btn_plus);
        plus.setOnClickListener(this);
        minor = findViewById(R.id.btn_minor);
        minor.setOnClickListener(this);
        multiply = findViewById(R.id.btn_multiply);
        multiply.setOnClickListener(this);
        divide = findViewById(R.id.btn_divide);
        divide.setOnClickListener(this);
        left_bracket = findViewById(R.id.btn_plus);
        left_bracket.setOnClickListener(this);
        right_bracket = findViewById(R.id.btn_plus);
        right_bracket.setOnClickListener(this);
        equal = findViewById(R.id.btn_equal);
        equal.setOnClickListener(this);

        num1 = findViewById(R.id.ib_1);
        num1.setText(String.format("%d",createQuestion()[0]));
        num1.setOnClickListener(this);
        num2 = findViewById(R.id.ib_2);
        num2.setText(String.format("%d",createQuestion()[1]));
        num2.setOnClickListener(this);
        num3 = findViewById(R.id.ib_3);
        num3.setText(String.format("%d",createQuestion()[2]));
        num3.setOnClickListener(this);
        num4 = findViewById(R.id.ib_4);
        num4.setText(String.format("%d",createQuestion()[3]));
        num4.setOnClickListener(this);

//        num1 = findViewById(R.id.ib_1);
//        num1.setOnClickListener(this);
//        num2 = findViewById(R.id.ib_2);
//        num2.setOnClickListener(this);
//        num3 = findViewById(R.id.ib_3);
//        num3.setOnClickListener(this);
//        num4 = findViewById(R.id.ib_4);
//        num4.setOnClickListener(this);
        calculation = findViewById(R.id.tv_calculation);
        calculation.setText("");

        result = findViewById(R.id.textView6);
        result.setText("");
        message = findViewById(R.id.message);
        message.setText("");
        textLive = findViewById(R.id.text_live);

        nextgame = findViewById(R.id.btn_next);
        nextgame.setOnClickListener(this);
        restart = findViewById(R.id.btn_restart);
        restart.setOnClickListener(this);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(this);
        help = findViewById(R.id.btn_help);
        help.setOnClickListener(this);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_plus:
                calculation.append("+");
//                startActivity();
                break;
            case R.id.btn_minor:
                calculation.append("-");
//                startActivity();
                break;
            case R.id.btn_multiply:
                calculation.append("*");
//                startActivity();
                break;
            case R.id.btn_divide:
                calculation.append("/");
                break;
            case R.id.ib_1:
                calculation.append(num1.getText());
                break;
            case R.id.ib_2:
                calculation.append(num2.getText());
                break;
            case R.id.ib_3:
                calculation.append(num3.getText());
                break;
            case R.id.ib_4:
                calculation.append(num4.getText());
                break;
            case R.id.btn_equal:
                result.append("24");
                /*result.setText(calculate(calculation.getText().toString()));*/
                if(result.getText().toString().equals("24")){
                    message.setText("Congratulations!!!");
                }
                else {
                    message.setText("It's wrong!!!");
                    numLives-=1;
                    textLive.setText(String.format("lives remaining: %d", numLives));
                    if(numLives == 0){
                        textLive.setTextColor(Color.RED);
                        message.setText("You Are Out!!!");
                    }
                }
                break;
            case R.id.btn_next:
                Intent nextgame = new Intent(Math24Activity.this, MainPageActivity.class);
                startActivity(nextgame);
            case R.id.btn_back:
                Intent backgame = new Intent(Math24Activity.this, WeaponActivity.class);
                startActivity(backgame);
        }
    }
}
