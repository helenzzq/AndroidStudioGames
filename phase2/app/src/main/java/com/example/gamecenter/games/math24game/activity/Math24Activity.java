package com.example.gamecenter.games.math24game.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.gamecenter.games.math24game.Math24Presenter;
import com.example.gamecenter.games.math24game.model.Math24Manager;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.strategy.GameTimer;


public class Math24Activity extends BaseActivity implements GameView, View.OnClickListener {
    private Button minus, multiply, divide;
    //    private Operators plus, minus, multiply, divide;
    private Button equal;
    private Button clear;
    private Button leftBracket;
    private Button rightBracket;
    private TextView mathExpression, result, message, textLive, scoreText;
    private Button[] nums, operatorBtns;
    private int numLives = 3;
    private int score = 0;
    private Math24Presenter presenter;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24);

        setUpNumBtnView();
        setUpOperators();
        setUpBrackets();
        setUpMenuBtn();
        setTextSpace();
        setUpMenuBtn();

        Button pauseBtn = findViewById(R.id.mathPause);
        GameTimer gameTimer = new GameTimer(findViewById(R.id.mathTimer));
        gameTimer.restart();
        pauseBtn.setTag(0);
        setPauseButton(pauseBtn, gameTimer);


        scoreText = findViewById(R.id.score);
        scoreText.setText(String.format("Your score %d", score));
        //set the first question, including set the text of number buttons
        presenter = new Math24Presenter(new Math24Manager(), this);
//        SharedPreferences level = getSharedPreferences("mathLevel", Context.MODE_PRIVATE);
        presenter.onStart();
    }

    private void setUpNumBtnView() {
        //create number buttons whose texts are questions

        Button num1 = findViewById(R.id.mathnum1);
        Button num2 = findViewById(R.id.mathnum2);
        Button num3 = findViewById(R.id.mathnum3);
        Button num4 = findViewById(R.id.mathnum4);
        nums = new Button[]{num1, num2, num3, num4};
        setOnClickNums();
    }

    private void setUpOperators() {
        Button plus = findViewById(R.id.btn_plus);
        minus = findViewById(R.id.btn_minus);
        multiply = findViewById(R.id.btn_multiply);
        divide = findViewById(R.id.btn_divide);
        operatorBtns = new Button[]{plus, minus, multiply, divide};
        equal = findViewById(R.id.btn_equal);
        equal.setEnabled(false);
        equal.setOnClickListener(this);
        setOnClickOperators();

    }

    private void setUpMenuBtn() {
        Button nextGame = findViewById(R.id.btn_next);
        Button backGame = findViewById(R.id.btn_back);
        Button help = findViewById(R.id.btn_help);
        clear = findViewById(R.id.btn_clear);
        nextGame.setOnClickListener(this);
        backGame.setOnClickListener(this);
        help.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    private void setUpBrackets() {
        //create variables left bracket, right bracket and assign the the buttons to them respectively
        leftBracket = findViewById(R.id.btn_left);
        rightBracket = findViewById(R.id.btn_right);
        setOnClickBrackets();

    }

    private void setTextSpace() {
        //put the output in result
        result = findViewById(R.id.math24result);
        //the text saying whether or not the answer is accurate
        message = findViewById(R.id.message);
        //the text saying the number of lives left
        textLive = findViewById(R.id.text_live);
        //create a text space called mathExpression to put the equation entered by the player
        mathExpression = findViewById(R.id.tv_calculation);
    }


    void disableBtns(Button[] btn) {
        for (Button b : btn) {
            b.setEnabled(false);
        }
    }

    void enableBtns(Button[] btn) {
        for (Button b : btn) {
            b.setEnabled(true);
        }
    }

    void enableBracket(boolean left, boolean right) {
        leftBracket.setEnabled(left);
        rightBracket.setEnabled(right);

    }

    private void setOnClickNums() {
        for (Button num : nums) {
            num.setOnClickListener(v -> {
                mathExpression.append(num.getText());
                enableBtns(operatorBtns);
                enableBracket(false, true);
                num.setEnabled(false);
                checkNumDisabled();
            });
        }


    }

    private void setOnClickBrackets(
    ) {
        for (Button bracket : new Button[]{leftBracket, rightBracket}) {
            bracket.setOnClickListener(v ->
            {
                if (bracket == leftBracket) {
                    rightBracket.setEnabled(false);
                    disableBtns(operatorBtns);
                }
                if (checkNumDisabled()){
                    disableBtns(operatorBtns);
                }
                mathExpression.append(bracket.getText());
            });
        }
    }
    private void setOnClickOperators() {
        for (Button btn : operatorBtns) {
            btn.setOnClickListener(v -> {
                disableBtns(operatorBtns);
                enableBracket(true, false);
                mathExpression.append(btn.getText());
                checkNumDisabled();
            });
        }

    }

    public Button[] getNums() {
        return nums;
    }

    @SuppressLint("DefaultLocale")
    @Override
    //set on click event on buttons
    public void onClick(View view) {
        switch (view.getId()) {
            //button event reset the mathExpression text
            case R.id.btn_clear:
                mathExpression.setText("");
                enableBtns(nums);
                enableBtns(operatorBtns);
                enableBracket(true, true);
                break;
            case R.id.btn_next:
                goToResult();
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_help:
                switchToPage(Math24IntroActivity.class);
                break;
            case R.id.btn_equal:
                presenter.calculateResult(mathExpression.getText().toString());
            default:
                break;
        }
    }

    public void resetAll() {
        mathExpression.setText("");
        clear.setEnabled(false);
        disableBtns(nums);
        equal.setEnabled(false);
    }

    private boolean checkNumDisabled() {
        for (Button num : nums) {
            if (num.isEnabled()) {
                return false;
            }
        }
        equal.setEnabled(true);
        return true;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void setMessage(String display) {
        message.setText(message.getText() + display);

    }

    @SuppressLint("SetTextI18n")
    public void showResult(int value) {
        result.setText("Result:"+ value);
    }




    @SuppressLint("DefaultLocale")
    public void setNumText(Button num, int question){
        num.setText(String.format("%d", question));

    }
    @Override
    public void goToResult() {
        super.goToResult(Math24ResultActivity.class, "MATH24_SCORE", score);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void updateScore(int score) {
        scoreText.setText(String.format("Your score: %d", this.score += score));

    }


    public void updateLives(){
        numLives -= 1;
        textLive.setText(String.format("Lives : %d", numLives));
    }

    public void showFailure(){
        textLive.setTextColor(Color.RED);
        message.setText("You lose the game!!!");
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public int getNumLives() {
        return numLives;
    }
}
