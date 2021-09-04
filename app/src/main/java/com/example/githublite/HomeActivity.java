package com.example.githublite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    EditText userNameET;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userNameET = findViewById(R.id.userNameET);
        button = findViewById(R.id.viewRepositoriesBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameET.getText().toString().trim();
                if(userName.isEmpty()){
                    userNameET.getText().clear();
                    userNameET.setHint("Cannot be empty");
                    userNameET.setHintTextColor(Color.RED);
                }else {
                    Intent intent = new Intent(getApplicationContext(), RepositoriesActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                }
            }
        });

    }
}