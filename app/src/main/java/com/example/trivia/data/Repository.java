package com.example.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    ArrayList<Question> questionArrayList = new ArrayList<>();

    public List<Question> getQuestions(final QuestionListAsyncResponse callback){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {

                        try {
                            Question newQuestion = new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1));


                            questionArrayList.add(newQuestion);
                            //Log.d("Repo", "questionArrayList: " + questionArrayList);
                            //Log.d("Repo", "onCreate:" + response.getJSONArray(i).get(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    if (null!=callback) callback.processFinished(questionArrayList);
                }, error -> {
            // TODO: Handle error

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }
}
