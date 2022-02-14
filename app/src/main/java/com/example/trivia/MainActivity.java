package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.QuestionListAsyncResponse;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    List<Question> questionList;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questionList = new Repository().getQuestions(questionArrayList -> {
                    binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex).getQuestion());
                    updateCounter();
                }
                );

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1 ) % questionList.size();
            updateQuestion();
        });
        binding.buttonTrue.setOnClickListener(view -> {
            checkAnswer(true);
        });
        binding.buttonFalse.setOnClickListener(view -> {
            checkAnswer(false);
        });


    }

    private void updateCounter() {
        binding.textViewOutOf.setText("Question: " + currentQuestionIndex + "/" + questionList.size());
    }


    private void checkAnswer(boolean userChoseCorrect) {
        //Log.d("Main", "Debug: Reached check answer.");
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId;
        if (userChoseCorrect == answer){
            snackMessageId = R.string.correctAnswer;
            Log.d("Quiz", "Answer: correct");
        } else {
            snackMessageId = R.string.incorrect;
            Log.d("Quiz", "Answer: incorrect");
        }
        //Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT);
        Toast.makeText(getApplicationContext(), snackMessageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getQuestion();
        binding.questionTextView.setText(question);
        updateCounter();
    }

}