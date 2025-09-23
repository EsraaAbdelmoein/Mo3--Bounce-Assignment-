package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private BouncingBallView bbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bbView = findViewById(R.id.custView);
    }

    public void EsraaButtonPressed(View v) {
        bbView.russButtonPressed();
    }
}
