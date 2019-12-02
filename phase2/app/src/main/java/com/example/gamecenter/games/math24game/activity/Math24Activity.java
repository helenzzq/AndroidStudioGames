package com.example.gamecenter.games.math24game.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gamecenter.games.math24game.Math24Presenter;
import com.example.gamecenter.games.math24game.model.Math24Manager;
import com.example.gamecenter.scoreboard.ScoreboardFileSaver;
import com.example.gamecenter.strategy.BaseActivity;
import com.example.gamecenter.R;
import com.example.gamecenter.gameinterface.GameView;
import com.example.gamecenter.strategy.GameTimer;
import com.example.gamecenter.strategy.prompts.GamePrompts;
import com.example.gamecenter.strategy.prompts.Prompts;
import com.example.gamecenter.user.User;
import com.example.gamecenter.user.UserManager;


public class Math24Activity extends BaseActivity implements GameView, View.OnClickListener {
    private GameTimer gameTimer;
    //    private Operators plus, minus, multiply, divide;
    private Button equal;
    private Button clear;
    private Button leftBracket, nextBtn;
    private Button rightBracket;
    private TextView mathExpression, result, message, textLive, scoreText,level;
    private Button[] nums, operatorBtns;
    private int score = 0;

    private Math24Presenter presenter;

    private static final String fileName = "Math24Scores.ser";

    private User currentPlayer = UserManager.getCurrentUser();


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
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
        gameTimer = new GameTimer(findViewById(R.id.mathTimer));
        gameTimer.restart();
        pauseBtn.setTag(0);

        nextBtn.setEnabled(false);

        level = findViewById(R.id.level);
        level.setText("LEVEL1");
        setPauseButton(pauseBtn, gameTimer);
        scoreText = findViewById(R.id.score);
        scoreText.setText(String.format("Your score %d", score));
        //set the first question, including set the text of number buttons
        presenter = new Math24Presenter(new Math24Manager(), this);
        presenter.onStart();
    }

    @Override
    public void setPauseButton(Button pauseBtn, GameTimer gameTimer) {
        pauseBtn.setOnClickListener(v -> {
            super.setPauseButton(pauseBtn, gameTimer);
            if (pauseBtn.getText().equals("RESUME")){
                disableAll();
            }
            else{
                enableAll();
            }
        });
    }

    public GameTimer getGameTimer() {
        return gameTimer;
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

    public Button getNextBtn() {
        return nextBtn;
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
                clearText();
                enableBtns(nums);
                enableBtns(operatorBtns);
                enableBracket(true, true);
                break;
            case R.id.btn_next:
                presenter.onStart();
                enableAll();
                nextBtn.setEnabled(false);
                clearText();
                break;
            case R.id.btn_back:
                backToMain();
                break;
            case R.id.btn_intro:
                switchToPage(Math24IntroActivity.class);
                break;
            case R.id.btn_equal:
                presenter.calculateResult(mathExpression.getText().toString());
            default:
                break;
        }
    }

    private void backToMain(){
        finish();
        switchToPage(Math24Menu.class);
    }

    public void disableAll() {
        clear.setEnabled(false);
        disableBtns(nums);
        disableBtns(operatorBtns);
        equal.setEnabled(false);
        disableBtns(new Button[]{leftBracket, rightBracket, clear});


    }
    public void clearText(){
        result.setText("");
        mathExpression.setText("");
        message.setText("");

    }

    public void enableAll(){
        enableBracket(true,true);
        enableBtns(operatorBtns);
        equal.setEnabled(false);
        enableBtns(nums);
        clear.setEnabled(true);

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

    public void goToResult(String displayName) {
        presenter.getGameManager().checkToAddScore(Math24Menu.scoreboard,displayName);
        ScoreboardFileSaver scoreboardFileSaver = new ScoreboardFileSaver(this, fileName);
        scoreboardFileSaver.saveToFile(fileName);

        finish();
        super.goToResult(Math24ScoreboardActivity.class, displayName);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void updateScore(int score) {
        scoreText.setText(String.format("Your score: %d", this.score = score));
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }

    @SuppressLint("DefaultLocale")
    public void setLives(int lives) {
        textLive.setText(String.format("Lives: %d", lives));
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

    public Button getClear() {
        return clear;
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
        Button minus = findViewById(R.id.btn_minus);
        Button multiply = findViewById(R.id.btn_multiply);
        Button divide = findViewById(R.id.btn_divide);
        operatorBtns = new Button[]{plus, minus, multiply, divide};
        equal = findViewById(R.id.btn_equal);
        equal.setEnabled(false);
        equal.setOnClickListener(this);
        setOnClickOperators();

    }

    private void setUpMenuBtn() {
        nextBtn = findViewById(R.id.btn_next);
        Button backGame = findViewById(R.id.btn_back);
        Button help = findViewById(R.id.btn_intro);
        clear = findViewById(R.id.btn_clear);
        nextBtn.setOnClickListener(this);
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

    /**
     * Show prompts on the screen.
     * */
    public void showPrompt() {
        ViewGroup layout = findViewById(R.id.catchBall);
        Prompts prompts = new GamePrompts();
        AlertDialog dialog = prompts.createPrompt(getLayoutInflater(), layout, this);
        prompts.getBackToMainBtn().setOnClickListener(v -> {backToMain();
        });
        prompts.getDisplayBothBtn().setOnClickListener(v -> {
            goToResult(currentPlayer.getUsername());
        });
        prompts.getOnlyScoreBtn().setOnClickListener(v -> goToResult(""));
        dialog.show();

    }


}
