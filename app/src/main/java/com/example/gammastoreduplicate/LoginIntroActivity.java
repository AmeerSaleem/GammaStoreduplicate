package com.example.gammastoreduplicate;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoginIntroActivity extends AppCompatActivity {

    ArrayList<String> view_pager_login_images;
    ArrayList<Drawable> backImageDrawables;
    ViewPager login_intro_viewpager;
    int currentPage1;
    TextView skip_text;
    LinearLayout sign_up_layout;
    LinearLayout login_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intro_activity);

        backImageDrawables = new ArrayList<>();
        backImageDrawables.add(getResources().getDrawable(R.drawable.pexels_photo_972804));
        backImageDrawables.add(getResources().getDrawable(R.drawable.pexels_photo_935760));
        backImageDrawables.add(getResources().getDrawable(R.drawable.pexels_photo_264507_small));
        backImageDrawables.add(getResources().getDrawable(R.drawable.pexels_photo_264554_small));
        backImageDrawables.add(getResources().getDrawable(R.drawable.pexels_photo_1078958_small));

        login_layout = findViewById(R.id.login_button);

        login_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginIntroActivity.this,LoginInputActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
                finish();
            }
        });
        sign_up_layout = findViewById(R.id.sign_up_button);

        sign_up_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginIntroActivity.this,SignUpActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
                finish();
            }
        });
        skip_text = findViewById(R.id.skip_text);

        skip_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginIntroActivity.this,MainActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
                finish();
            }
        });
        login_intro_viewpager = findViewById(R.id.view_pager_login);

//        view_pager_login_images = new ArrayList<>();
//        view_pager_login_images.add("https://images.pexels.com/photos/972804/pexels-photo-972804.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        view_pager_login_images.add("https://images.pexels.com/photos/935760/pexels-photo-935760.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        view_pager_login_images.add("https://images.pexels.com/photos/264507/pexels-photo-264507.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        view_pager_login_images.add("https://images.pexels.com/photos/1078958/pexels-photo-1078958.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        view_pager_login_images.add("https://images.pexels.com/photos/264554/pexels-photo-264554.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");

//        view_pager_login_images.add("https://images.pexels.com/photos/787929/pexels-photo-787929.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        view_pager_login_images.add("https://images.pexels.com/photos/1368690/pexels-photo-1368690.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");

//        ViewPagerStringAdapter adapter = new ViewPagerStringAdapter(this,view_pager_login_images);
        ViewPagerStringAdapter adapter = new ViewPagerStringAdapter(this,backImageDrawables);
        login_intro_viewpager.setAdapter(adapter);

        currentPage1 = login_intro_viewpager.getCurrentItem();
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            @Override
            public void run() {
//                if (currentPage1 == view_pager_login_images.size()) {
                if (currentPage1 == backImageDrawables.size()) {
                    currentPage1 = 0;
                }
                login_intro_viewpager.setCurrentItem(currentPage1++, true);
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 0, 1500);

        login_intro_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                currentPage1 = login_intro_viewpager.getCurrentItem();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}