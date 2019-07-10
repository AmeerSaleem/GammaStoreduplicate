package com.example.gammastoreduplicate;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    ImageView language_back;
    boolean isEnglishSelected;
    LinearLayout arabic_layout, english_layout;
    TextView arabic_text, english_text;
    TextView language_switch_btn;
    int i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        i1 = 0;
        language_switch_btn = findViewById(R.id.lang_change_button);
        language_switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnglishSelected) {
                    setLocale("en");
                } else {
                    setLocale("ar");
                }
            }
        });
        isEnglishSelected = true;
        arabic_layout = findViewById(R.id.arabic_layout);
        arabic_text = findViewById(R.id.arabic_label);
        english_text = findViewById(R.id.english_label);
        english_layout = findViewById(R.id.english_layout);
        arabic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnglishSelected) {
                    arabic_layout.setBackgroundResource(R.drawable.curved_view_theme);
                    arabic_text.setTextColor(Color.WHITE);

                    english_layout.setBackgroundResource(R.drawable.curved_view);
                    english_text.setTextColor(getResources().getColor(R.color.themeColor));

                    isEnglishSelected = false;

                }
            }
        });

        english_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!isEnglishSelected) || (i1 == 0)) {
                    english_layout.setBackgroundResource(R.drawable.curved_view_theme);
                    english_text.setTextColor(Color.WHITE);

                    arabic_layout.setBackgroundResource(R.drawable.curved_view);
                    arabic_text.setTextColor(getResources().getColor(R.color.themeColor));
                    i1++;
                    isEnglishSelected = true;

                }
            }
        });
        language_back = findViewById(R.id.language_back_arrow);
        language_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.fade_out);
    }

    public void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        this.getBaseContext().getResources()
                .updateConfiguration(config, this.getBaseContext()
                        .getResources()
                        .getDisplayMetrics());
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        finish();
        startActivity(refresh);

    }

}
