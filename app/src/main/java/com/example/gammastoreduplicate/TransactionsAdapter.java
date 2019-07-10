package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>{

    Context context;
    ArrayList<TransactionsClass> transaction_items;
    TransactionsAdapter.onItemClickListener mListener;

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_transactions_list,viewGroup,false);
        TransactionsViewHolder tvh = new TransactionsViewHolder(v,mListener);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsViewHolder transactionsViewHolder, int i) {

        TransactionsClass tc = transaction_items.get(i);
        transactionsViewHolder.date_of_purchase.setText(tc.date_purchased);
        transactionsViewHolder.purchase_item_name.setText(tc.product_purchased_name);
        transactionsViewHolder.purchase_item_cost.setText(tc.cost_in_$_purchased);

    }

    @Override
    public int getItemCount() {
        return transaction_items.size();
    }

    public interface onItemClickListener{
        public void onViewClick(int position);
    }

    public void setOnClickListener(TransactionsAdapter.onItemClickListener listener){mListener = listener;}

    public class TransactionsViewHolder extends RecyclerView.ViewHolder{

        TextView date_of_purchase;
        TextView purchase_item_name;
        TextView purchase_item_cost;

        public TransactionsViewHolder(View itemView,onItemClickListener listener){
            super(itemView);

            date_of_purchase = itemView.findViewById(R.id.date_purchase);
            purchase_item_name = itemView.findViewById(R.id.purchase_name);
            purchase_item_cost = itemView.findViewById(R.id.purchase_cost);

        }
    }

    public TransactionsAdapter(Context context, ArrayList<TransactionsClass> transaction_items) {
        this.context = context;
        this.transaction_items = transaction_items;
    }
}
