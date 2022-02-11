package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.Repository;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    //String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AppController.getInstance(this.getApplicationContext()).addToRequestQueue();
        new Repository().getQuestions();
    }
}