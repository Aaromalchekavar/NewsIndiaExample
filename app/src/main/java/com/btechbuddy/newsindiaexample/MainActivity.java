package com.btechbuddy.newsindiaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView newsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecycler = findViewById(R.id.recyclerNews);
// shows the loading progress dialogue box
        final ProgressDialog loader = new ProgressDialog(this);
        loader.setMessage("Loading data...");
        loader.setCancelable(false);
        loader.show();

        String API_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=2a869a0b5dfb4fdba1d33380af3b584a";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //to stop progress dialogue box automatically...just dismis it
                loader.dismiss();
                handleTheRequestedResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.dismiss();
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
                MySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void handleTheRequestedResponse(JSONObject response) {
        ArrayList<ModelOfNews> newsArray = new ArrayList<>();
        try {
            JSONArray articlesArray = response.getJSONArray("articles");
            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject newsObject = (JSONObject) articlesArray.get(i);
                String newsTitle = newsObject.getString("title");
                String newsImageUrl = newsObject.getString("urlToImage");
                String newsUrl = newsObject.getString("url");
                String newsDesc = newsObject.getString("description");

                ModelOfNews newsItem = new ModelOfNews(newsTitle,newsImageUrl,newsUrl,newsDesc);
                newsArray.add(newsItem);


            }
            newsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
            newsRecycler.setAdapter(new AdapterNews(getApplicationContext(),newsArray));
//            articlesArray.length().fori Shorcut for forLoop with something as particular length
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}