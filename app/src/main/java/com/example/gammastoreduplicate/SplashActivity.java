package com.example.gammastoreduplicate;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       new Timer().schedule(new TimerTask() {
           @Override
           public void run() {
               Intent intent = new Intent(SplashActivity.this,IntroSequenceActivity.class);
               Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                       android.R.anim.fade_out).toBundle();
               startActivity(intent, bundle);
               finish();
           }
       },3000);
    }
}
