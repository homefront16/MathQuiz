package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button btnAnswer0, btnAnswer1, btnAnswer2, btnAnswer3, btnGo;
    TextView tvTimer, tvScore, tvQuestions, tvBottomMessage;
    ProgressBar pBarTimer;
    Game g = new Game();

    int secondsRemaining = 30;

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tvTimer.setText(Integer.toString(secondsRemaining) + " Seconds Remaining");
            pBarTimer.setProgress(30 - secondsRemaining);
        }

        @Override
        public void onFinish() {
            btnAnswer0.setEnabled(false);
            btnAnswer1.setEnabled(false);
            btnAnswer2.setEnabled(false);
            btnAnswer3.setEnabled(false);

            tvBottomMessage.setText("Time is up! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions() -1));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnGo.setVisibility(View.VISIBLE);
                }
            }, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnswer0 = findViewById(R.id.btnAnswer0);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnGo = findViewById(R.id.btnGo);

        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore);
        tvQuestions = findViewById(R.id.tvQuestions);
        tvBottomMessage = findViewById(R.id.tvBottomMessage);

        pBarTimer = findViewById(R.id.pBarTimer);


        tvTimer.setText("0Sec");
        tvQuestions.setText("");
        tvBottomMessage.setText("Press Go");
        tvScore.setText("0 pts");
        pBarTimer.setProgress(0);

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button startButton = (Button)v;

                startButton.setVisibility(View.INVISIBLE);
                secondsRemaining = 30;
                g = new Game();
                nextTurn();
                timer.start();

            }
        };
        btnGo.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Button buttonClicked = (Button)v;

            int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
            g.checkAnswer(answerSelected);
            tvScore.setText(Integer.toString(g.getScore()) + "pts");
            nextTurn();

            }
        };

        btnAnswer0.setOnClickListener(answerButtonClickListener);
        btnAnswer1.setOnClickListener(answerButtonClickListener);
        btnAnswer2.setOnClickListener(answerButtonClickListener);
        btnAnswer3.setOnClickListener(answerButtonClickListener);

    }

    private void nextTurn() {
        g.makeNewQuestion();
        int[] answers = g.getCurrentQuestion().getAnswerArray();

        btnAnswer0.setText(Integer.toString(answers[0]));
        btnAnswer1.setText(Integer.toString(answers[1]));
        btnAnswer2.setText(Integer.toString(answers[2]));
        btnAnswer3.setText(Integer.toString(answers[3]));

        btnAnswer0.setEnabled(true);
        btnAnswer1.setEnabled(true);
        btnAnswer2.setEnabled(true);
        btnAnswer3.setEnabled(true);

        tvQuestions.setText(g.getCurrentQuestion().getQuestionPhrase());
        tvBottomMessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));

    }
}