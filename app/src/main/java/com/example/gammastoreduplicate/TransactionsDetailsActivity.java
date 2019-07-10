package com.example.gammastoreduplicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TransactionsDetailsActivity extends AppCompatActivity {

    RecyclerView rcv_transactions;
    ArrayList<TransactionsClass> transaction_list;
    ImageView transactions_back;

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_details);

        transactions_back = findViewById(R.id.transactions_back_arrow);
        transactions_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        transaction_list = new ArrayList<>();
        transaction_list.add(new TransactionsClass("Jan 01","iPhone X","$ 1200"));
        transaction_list.add(new TransactionsClass("Feb 12","MacBook Pro","$ 1950"));
        transaction_list.add(new TransactionsClass("Mar 21","Apple phone case","$ 40"));
        transaction_list.add(new TransactionsClass("Oct 16","Blue silk dress","$ 130"));
        transaction_list.add(new TransactionsClass("Dec 01","Nike Air shoes","$ 65"));
        rcv_transactions = findViewById(R.id.recycler_transactions);

        TransactionsAdapter adapter = new TransactionsAdapter(this,transaction_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        rcv_transactions.setLayoutManager(manager);
        rcv_transactions.setAdapter(adapter);
    }
}