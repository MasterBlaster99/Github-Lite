package com.example.githublite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ArrayList<Comment> list = new ArrayList<>();
        TextView tv = findViewById(R.id.error);

        Intent intent = getIntent();
        String username = intent.getStringExtra("userName");
        String repoName = intent.getStringExtra("repoName");
        String number = intent.getStringExtra("number");

        url = "https://api.github.com/repos/"+username+"/"+repoName+"/issues/"+number+"/comments";

        TextView repoNameHeading = findViewById(R.id.IssueName);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        repoNameHeading.setText(repoName);

        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for(int i=0;i<size;i++){
                            try {
                                Comment comment = new Comment();
                                comment.setUser(response.getJSONObject(i).getJSONObject("user").getString("login"));
                                comment.setComment(response.getJSONObject(i).getString("body"));
                                list.add(comment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CommentAdapter adapter = new CommentAdapter(list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                        if(list.size()==0){
                            tv.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Network " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}