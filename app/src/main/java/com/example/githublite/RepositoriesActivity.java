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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RepositoriesActivity extends AppCompatActivity {

    String url;
    TextView accName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        accName = findViewById(R.id.name);
        ArrayList<Repo> repoList = new ArrayList<>();

        Intent intent = getIntent();
        String username = intent.getStringExtra("userName").trim();

        url = "https://api.github.com/users/"+username+"/repos";

        accName.setText(username);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView tv = findViewById(R.id.error);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                      int size = response.length();
                      for (int i=0;i<size;i++){
                          try {
                              Repo repo = new Repo();
                              repo.setName(response.getJSONObject(i).getString("name"));
                              repo.setDescription(response.getJSONObject(i).getString("description"));
                              repo.setNumStars(response.getJSONObject(i).getString("stargazers_count"));
                              repo.setLanguage(response.getJSONObject(i).getString("language"));
                              repoList.add(repo);
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }
                        RepoAdapter repoAdapter = new RepoAdapter(repoList) ;
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(repoAdapter);
                        repoAdapter.setOnItemClickListener(new RepoAdapter.OnItemClickListener() {
                            @Override
                            public void OnItemClick(int position) {
                                try {
                                  Intent intent = new Intent(getApplicationContext(),IssuesActivity.class);
                                  intent.putExtra("repoName",response.getJSONObject(position).getString("name"));
                                  intent.putExtra("userName",username);
                                  startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setVisibility(View.VISIBLE);
                Toast.makeText(RepositoriesActivity.this, "Network Error", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);

    }
}