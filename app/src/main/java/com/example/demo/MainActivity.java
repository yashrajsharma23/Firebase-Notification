package com.example.demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt= findViewById(R.id.txt);

        SharedPreferences prefs = getSharedPreferences("Pref_demo", MODE_PRIVATE);
        String token = prefs.getString("token", "No Token found");//"No name defined" is the default value.
        txt.setText("Hello, \nYour token number is : "+ token);
    }
}
