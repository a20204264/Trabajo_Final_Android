package com.example.trabajo_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    int demora=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask tarea= new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,
                        MainActivity.class);

                startActivity(i);

                finish();
            }


        };

        Timer tiempo = new Timer();

        tiempo.schedule(tarea,demora);
    }
}