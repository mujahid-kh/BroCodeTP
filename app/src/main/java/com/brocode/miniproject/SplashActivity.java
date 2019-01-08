package com.brocode.miniproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (jsonReader.readFile(SplashActivity.this)) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            Toast.makeText(SplashActivity.this, "JSON file doesn't exist", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}