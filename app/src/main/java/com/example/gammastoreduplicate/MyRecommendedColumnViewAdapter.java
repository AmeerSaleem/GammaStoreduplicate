package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecommendedColumnViewAdapter extends RecyclerView.Adapter<MyRecommendedColumnViewAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    MyRecommendedColumnViewAdapter.onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }


    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

        TextView product_name;
        TextView prev_cost;
        TextView cost;
        TextView items_sold;
        ImageView product_image;
        RatingBar ratingBar;
        TextView review_count;
        TextView location;
        TextView no_available;
        View available_bar;
        LinearLayout total_card1;

        public RecommendedViewHolder(@NonNull View itemView, final MyRecommendedColumnViewAdapter.onItemClickListener listener) {

            super(itemView);

            total_card1 = itemView.findViewById(R.id.recommended_column_first_card);
            product_name = itemView.findViewById(R.id.recommended_column_product_name);
            product_image = itemView.findViewById(R.id.recommended_column_product_image);
            cost = itemView.findViewById(R.id.recommended_column_cost);
//            prev_cost = itemView.findViewById(R.id.recommended_column_prev_cost);
//            ratingBar = itemView.findViewById(R.id.recommended_card_view_rating);
            review_count = itemView.findViewById(R.id.recommended_column_review_count);
            items_sold = itemView.findViewById(R.id.recommended_column_items_sold);

            total_card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public MyRecommendedColumnViewAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_recommended_column_view_list, viewGroup, false);
        RecommendedViewHolder rvh = new RecommendedViewHolder(v, mListener);
        return rvh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyRecommendedColumnViewAdapter.RecommendedViewHolder recommendedViewHolder, int i) {


        ProductClass pc = sale_items.get(i);

        recommendedViewHolder.product_name.setText(pc.product_name);

        Glide
                .with(context)
                .load(pc.product_bitmaps.get(0))
                .into(recommendedViewHolder.product_image);

//        recommendedViewHolder.prev_cost.setPaintFlags(recommendedViewHolder.prev_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        recommendedViewHolder.prev_cost.setText("$" + pc.prev_cost_in_$);


        recommendedViewHolder.cost.setText("$" + pc.cost_in_$);
//        recommendedViewHolder.ratingBar.setRating(pc.rating);
        recommendedViewHolder.review_count.setText(String.valueOf(pc.rating));
        recommendedViewHolder.items_sold.setText(String.valueOf(pc.items_sold) + " items sold");


    }

    @Override
    public int getItemCount() {
        return sale_items.size();
    }
}
