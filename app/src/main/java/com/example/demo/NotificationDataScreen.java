package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NotificationDataScreen extends Activity {
    TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt= findViewById(R.id.txt);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txt.setText("Received Message : "+ extras.getString("message", "empty message"));
        }
    }
}
