package com.scarlat.marius.braintrainer.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scarlat.marius.braintrainer.GameDesign.Game;
import com.scarlat.marius.braintrainer.OperationTypes.Operation;
import com.scarlat.marius.braintrainer.R;
import com.scarlat.marius.braintrainer.Util.Constants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int remainingTime;
    private int tempScore;
    private Game game;

    private Button startButton;
    private Button playAgainButton;
    private TextView sumTextView;
    private TextView resultTextView;
    private TextView pointsTextView;
    private TextView timerTextView;
    private RelativeLayout gameRelativeLayout;
    private GridLayout answersGridLayout;
    private CountDownTimer timer;

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        generateQuestions();
        startTimer();
    }

    public void chooseAnswer (View view) {

        if (!game.isActive()) {
            return;
        }

        String message = Constants.INCORRECT_ANSWER;
        int buttonTag = Integer.valueOf((String) view.getTag());

        game.increaseAttempts();
        if (buttonTag == game.getCorrectAnsIndex()) {
            ++ tempScore;
            game.increaseScore();
            message = Constants.CORRECT_ANSWER;
        }

        if (tempScore >= 5) {
            Log.i(TAG, "chooseAnswer: Adding bonus time !");
            updateTimer(5);
            Toast.makeText(this, " +5 seconds", Toast.LENGTH_SHORT).show();
            tempScore = 0;
        }

        updateScore(message);
        generateQuestions();

    }

    public void playAgain (View view) {

        playAgainButton.setVisibility(View.INVISIBLE);
        timerTextView.setText(Constants.AVAILABLE_TIME);
        pointsTextView.setText(Constants.INITIAL_SCORE);
        resultTextView.setText("");

        game.initializeGameInfo();
        generateQuestions();
        startTimer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        tempScore = 0;
        remainingTime = 30000;

        game = new Game();
        game.initializeGameInfo();
    }


    private void startTimer() {
        initTimer(30000, 1000);
        timer.start();
    }

    private void updateTimer(int bonusSeconds) {
        timer.cancel();
        initTimer((remainingTime + bonusSeconds) * 1000, 1000);
        timer.start();
    }

    private void initTimer(int totalTime, int tickTime) {

        int deltaTime = 100;

        timer = new CountDownTimer(totalTime + deltaTime, tickTime) {

            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int) millisUntilFinished / 1000;
                timerTextView.setText(String.valueOf((int) (millisUntilFinished / 1000)) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText(Constants.TIME_IS_UP);
                resultTextView.setText("Your score is " + Integer.toString(game.getScore()) +
                        "/" + Integer.toString(game.getAttempts()));

                playAgainButton.setVisibility(View.VISIBLE);
                game.setActive(false);
            }
        };
    }

    private void initializeViews() {
        startButton = (Button) findViewById(R.id.startButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        answersGridLayout = (GridLayout) findViewById(R.id.answersGridLayout);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
    }

    private void generateQuestions() {

        game.generateOperation();
        Operation currentOp = game.getOperation();


        sumTextView.setText(Integer.toString(currentOp.getA()) + currentOp.getSymbol() +
                                            Integer.toString(currentOp.getB()));
        updateButtons();
    }

    private void updateScore(String message) {
        resultTextView.setText(message);
        pointsTextView.setText( Integer.toString(game.getScore()) + "/" +
                                Integer.toString(game.getAttempts()));
    }


    private void updateButtons() {

        int size = answersGridLayout.getChildCount();
        int counter = 0;

        for (int i = 0; i < size; ++i) {
            View child = answersGridLayout.getChildAt(i);

            if (child instanceof Button) {
                ((Button) child).setText(Integer.toString(game.getAnswers().get(counter)));

                if (game.getAvailableAnswers() > counter) {
                    ++ counter;
                }
            }
        }
    }
}
