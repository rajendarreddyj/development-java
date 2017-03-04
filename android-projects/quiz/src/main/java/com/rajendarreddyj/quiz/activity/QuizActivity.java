package com.rajendarreddyj.quiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rajendarreddyj.quiz.R;
import com.rajendarreddyj.quiz.dao.dao.QuizDBHelper;
import com.rajendarreddyj.quiz.pojo.pojo.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> quesList;
    private int score = 0;
    private int qid = 0;
    private Question currentQ;
    private TextView txtQuestion;
    private RadioButton rda, rdb, rdc, rdd;
    private Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        QuizDBHelper db = new QuizDBHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        rdd = (RadioButton) findViewById(R.id.radio3);
        butNext = (Button) findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getAnswer() + " " + answer.getText());
                if (currentQ.getAnswer().equals(answer.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (qid < 5) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getOptionA());
        rdb.setText(currentQ.getOptionB());
        rdc.setText(currentQ.getOptionC());
        rdd.setText(currentQ.getOptionD());
        rda.setChecked(false);
        rdb.setChecked(false);
        rdc.setChecked(false);
        rdd.setChecked(false);
        qid++;
    }
}
