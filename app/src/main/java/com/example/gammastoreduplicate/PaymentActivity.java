package com.example.gammastoreduplicate;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PaymentActivity extends AppCompatActivity {

    RadioButton rd1, rd2, rd3, rd4;
    TextView final_payment_button;
    AlertDialog payment_success_dialog;
    ImageView payment_back;

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment_back = findViewById(R.id.payment_back_arrow);
        payment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rd1 = findViewById(R.id.radio_button_credit_card);
        rd2 = findViewById(R.id.radio_button_cod);
        rd3 = findViewById(R.id.radio_button_paypal);
        rd4 = findViewById(R.id.radio_button_google_wallet);

        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(true);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
            }
        });

        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd2.setChecked(true);
                rd1.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
            }
        });

        rd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd3.setChecked(true);
                rd2.setChecked(false);
                rd1.setChecked(false);
                rd4.setChecked(false);
            }
        });

        rd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd4.setChecked(true);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd1.setChecked(false);
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater1 = getLayoutInflater();
        View dialogView = inflater1.inflate(R.layout.dialog_payment_completed, null);
        dialogBuilder.setView(dialogView);
        payment_success_dialog = dialogBuilder.create();
        payment_success_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final_payment_button = findViewById(R.id.final_payment_text_button);
        final_payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_success_dialog.show();

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        payment_success_dialog.dismiss();
                        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), android.R.anim.fade_in,
                                android.R.anim.fade_out).toBundle();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent, bundle);
                        finish();
                    }
                }, 2500);


            }
        });
    }
}