package com.example.gammastoreduplicate;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CardDetailsActivity extends AppCompatActivity {

    AlertDialog editCardDialog;
    LinearLayout edit_card_layout;
    LinearLayout update_card_dialog_layout;
    ImageView card_details_back;

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        card_details_back = findViewById(R.id.card_details_back_arrow);
        card_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edit_card_layout = findViewById(R.id.edit_card_details_button);

        edit_card_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCardDialog.show();
//                editCardDialog.getWindow().setLayout(500, 670);
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater1 = getLayoutInflater();
        View dialogView = inflater1.inflate(R.layout.dialog_edit_card_details, null);
        dialogBuilder.setView(dialogView);

        editCardDialog = dialogBuilder.create();
        editCardDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        update_card_dialog_layout = dialogView.findViewById(R.id.update_card_dilog_button);
        update_card_dialog_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCardDialog.dismiss();
            }
        });
//        editCardDialog.getWindow().setLayout(500,590);

    }
}
