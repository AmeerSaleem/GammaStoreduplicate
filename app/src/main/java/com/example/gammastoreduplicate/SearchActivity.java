package com.example.gammastoreduplicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class SearchActivity extends AppCompatActivity {

    ImageView search_back_button;
    TextView initial_price;
    DiscreteSeekBar seekbar;
    String source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        source = getIntent().getStringExtra("entry");
        initial_price = findViewById(R.id.price_change);
        seekbar = findViewById(R.id.filter_search_seekbar);

        seekbar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                initial_price.setText(String.valueOf("$ " + value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });


        search_back_button = findViewById(R.id.search_filter_back_arrow);

        search_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
//        if(source.equals("main")) {
//            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
//            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
//            startActivity(intent, bundle);
//            finish();
//        }
//
//        else if(source.equals("category")){
//            Intent intent = new Intent(SearchActivity.this, CategoryActivity.class);
//            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
//            startActivity(intent, bundle);
//            finish();
//        }
//
//        else{
//            super.onBackPressed();
//            finish();
//        }

        finish();
        overridePendingTransition(0,R.anim.fade_out);
//        else if(source.equals("pd")){
//            Intent intent = new Intent(SearchActivity.this, ProductDETAILsActivity.class);
//            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
//            startActivity(intent, bundle);
//        }
    }
}
