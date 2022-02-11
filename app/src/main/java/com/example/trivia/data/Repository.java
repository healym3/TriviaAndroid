package com.example.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    ArrayList<Question> questionArrayList = new ArrayList<>();

    public List<Question> getQuestions(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Log.d("TAG", "onCreate: " + response.toString());
                    //textView.setText("Response: " + response.toString());
                }, error -> {
            // TODO: Handle error

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return null;
    }
}
