package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MyFlashSaleAdapter extends RecyclerView.Adapter<MyFlashSaleAdapter.FlashSaleViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    onItemClickListener mListener;
    boolean showShimmer = true;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }

    public void setOnClickListener(MyFlashSaleAdapter.onItemClickListener listener) {
        mListener = listener;
    }

    public class FlashSaleViewHolder extends RecyclerView.ViewHolder {

//        ShimmerFrameLayout shimmer;
        TextView product_name;
        TextView prev_cost;
        TextView cost;
        ImageView product_image;
        RatingBar ratingBar;
        TextView review_count;
        TextView location;
        TextView no_available;
        View available_bar;
        LinearLayout total_card;

        public FlashSaleViewHolder(@NonNull View itemView, final onItemClickListener listener) {

            super(itemView);

//            shimmer = itemView.findViewById(R.id.flash_sale_shimmer);
            total_card = itemView.findViewById(R.id.flash_sale_card);
            product_name = itemView.findViewById(R.id.flash_sale_product_name);
            product_image = itemView.findViewById(R.id.flash_sale_product_image);
            prev_cost = itemView.findViewById(R.id.flash_sale_prev_cost);
            cost = itemView.findViewById(R.id.flash_sale_cost);
            ratingBar = itemView.findViewById(R.id.flash_sale_rating_bar);
            review_count = itemView.findViewById(R.id.flash_sale_review_count);
            location = itemView.findViewById(R.id.flash_sale_location);
            no_available = itemView.findViewById(R.id.flash_sale_available);
            available_bar = itemView.findViewById(R.id.flash_sale_available_bar);

            total_card.setOnClickListener(new View.OnClickListener() {
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

    public MyFlashSaleAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public FlashSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_flash_sale_list, viewGroup, false);
        FlashSaleViewHolder fsh = new FlashSaleViewHolder(v, mListener);
        return fsh;

    }

    @Override
    public void onBindViewHolder(@NonNull final FlashSaleViewHolder flashSaleViewHolder, int i) {

        final ProductClass pc = sale_items.get(i);

        Glide
                .with(context)
                .load(pc.product_bitmaps.get(0))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

//                        flashSaleViewHolder.shimmer.stopShimmer();
//                        flashSaleViewHolder.shimmer.setShimmer(null);
                        showShimmer = false;
                        flashSaleViewHolder.product_name.setText(pc.product_name);
                        flashSaleViewHolder.product_name.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.prev_cost.setPaintFlags(flashSaleViewHolder.prev_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        flashSaleViewHolder.prev_cost.setText("$" + pc.prev_cost_in_$);
                        flashSaleViewHolder.prev_cost.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.cost.setText("$" + pc.cost_in_$);
                        flashSaleViewHolder.cost.setBackgroundColor(Color.WHITE);;
                        flashSaleViewHolder.ratingBar.setRating(pc.rating);
                        flashSaleViewHolder.ratingBar.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.review_count.setText("(" + pc.review_count + ")");
                        flashSaleViewHolder.review_count.setBackgroundColor(Color.WHITE);;
                        flashSaleViewHolder.location.setText(pc.location);
                        flashSaleViewHolder.location.setBackgroundColor(Color.WHITE);;
                        flashSaleViewHolder.no_available.setText(pc.no_available + " available");
                        flashSaleViewHolder.no_available.setBackgroundColor(Color.WHITE);;

                        if (pc.no_available > 9) {
                            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_theme);
                        } else {
                            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_orange);
                        }

                        return false;
                    }
                })
                .into(flashSaleViewHolder.product_image);

//        flashSaleViewHolder.prev_cost.setPaintFlags(flashSaleViewHolder.prev_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        flashSaleViewHolder.prev_cost.setText("$" + pc.prev_cost_in_$);
//        flashSaleViewHolder.cost.setText("$" + pc.cost_in_$);
//        flashSaleViewHolder.ratingBar.setRating(pc.rating);
//        flashSaleViewHolder.review_count.setText("(" + pc.review_count + ")");
//        flashSaleViewHolder.location.setText(pc.location);
//        flashSaleViewHolder.no_available.setText(pc.no_available + " available");
//
//        if (pc.no_available > 9) {
//            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_theme);
//        } else {
//            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_orange);
//        }




//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(0,5);
//
//        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) flashSaleViewHolder.available_bar.getLayoutParams();
//        lp.setMargins(0,8,5,14);
//        flashSaleViewHolder.available_bar.setLayoutParams(params);

//        LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) flashSaleViewHolder.available_bar.getLayoutParams();
//        lay.weight = pc.no_available;

//        flashSaleViewHolder.available_bar.getLayoutParams().

    }

    @Override
    public int getItemCount() {
        return sale_items.size();
    }
}