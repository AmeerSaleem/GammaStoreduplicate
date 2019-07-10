package com.example.gammastoreduplicate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DeliveryActivity extends AppCompatActivity {

    TextView go2payment;
    ImageView shipping_back;

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(DeliveryActivity.this, MainActivity.class);
//        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
//                android.R.anim.fade_out).toBundle();
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent, bundle);
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        shipping_back = findViewById(R.id.shipping_address_back_arrow1);
        shipping_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        go2payment = findViewById(R.id.go_2_final_payment);

        go2payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryActivity.this, PaymentActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                intent.putExtra("entry", "main");
                startActivity(intent, bundle);
                finish();
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( ev );    }
}
