package com.example.gammastoreduplicate;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gammastoreduplicate.PageTransfromers.DepthPageTransformer;

import java.util.ArrayList;

public class IntroSequenceActivity extends AppCompatActivity {
    ViewPager intro_view;
    ArrayList<Fragment> fragment_list;
    LinearLayout skip_text;
    LinearLayout start_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_sequence);
        intro_view = findViewById(R.id.view_pager123abv);

        start_text = findViewById(R.id.start_layout);
        skip_text = findViewById(R.id.skip_layout);
        fragment_list = new ArrayList<>();
        fragment_list.add(new FragmentIntroSeq1());
        fragment_list.add(new FragmentIntroSeq2());
        fragment_list.add(new FragmentIntroSeq3());

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragment_list);
        intro_view.setAdapter(adapter);
        intro_view.setPageTransformer(true, new DepthPageTransformer());
        skip_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intro_view.setCurrentItem(2,true);
            }
        });

        start_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroSequenceActivity.this,LoginIntroActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
                finish();
            }
        });

        intro_view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {
                int position = intro_view.getCurrentItem();
                if(position == 2){
//                    skip_text.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out));

                    skip_text.animate().alpha(0.0f).setDuration(300);
                    skip_text.setVisibility(View.INVISIBLE);

//                    start_text.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
                    start_text.animate().alpha(1.0f).setDuration(300);
                    start_text.setVisibility(View.VISIBLE);
                }
                if(position == 1){
                    if(start_text.getVisibility() == View.VISIBLE) {
//                        start_text.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
                        start_text.animate().alpha(0.0f).setDuration(500);
                        start_text.setVisibility(View.GONE);
                    }
                    if(skip_text.getVisibility() == View.INVISIBLE) {
//                        skip_text.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                        skip_text.animate().alpha(1.0f).setDuration(500);
                        skip_text.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}