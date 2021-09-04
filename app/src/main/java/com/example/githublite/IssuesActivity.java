package com.example.githublite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
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
import org.json.JSONObject;

import java.util.ArrayList;

public class IssuesActivity extends AppCompatActivity {

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);

        ArrayList<Issue> list = new ArrayList<>();
        TextView tv = findViewById(R.id.error);

        Intent intent = getIntent();
        String username = intent.getStringExtra("userName");
        String repoName = intent.getStringExtra("repoName");

        url = "https://api.github.com/repos/"+username+"/"+repoName+"/issues";

        TextView repoNameHeading = findViewById(R.id.repoName);
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
                                Issue issue = new Issue();
                                issue.setName(response.getJSONObject(i).getString("title"));
                                issue.setIssueNumber(response.getJSONObject(i).getString("number"));
                                issue.setComments(response.getJSONObject(i).getString("comments"));
                                list.add(issue);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        IssueAdapter adapter = new IssueAdapter(list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                        if(list.size()==0){
                            tv.setVisibility(View.VISIBLE);
                        }
                        adapter.setOnItemClickListener(new IssueAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(int position) {
                                Intent intent = new Intent(getApplicationContext(),CommentsActivity.class);
                                try {
                                    intent.putExtra("repoName",repoName);
                                    intent.putExtra("number",response.getJSONObject(position).getString("number"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                intent.putExtra("userName",username);
                                startActivity(intent);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);

    }
}