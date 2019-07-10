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

public class MyFlashSale2ColumnAdapter extends RecyclerView.Adapter<MyFlashSale2ColumnAdapter.FlashSaleViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }


    public class FlashSaleViewHolder extends RecyclerView.ViewHolder {

        ShimmerFrameLayout shimmer1;
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

        ShimmerFrameLayout shimmer2;
        TextView product_name2;
        TextView prev_cost2;
        TextView cost2;
        ImageView product_image2;
        RatingBar ratingBar2;
        TextView review_count2;
        TextView location2;
        TextView no_available2;
        View available_bar2;
        LinearLayout total_card2;

        public FlashSaleViewHolder(@NonNull View itemView, final onItemClickListener listener) {

            super(itemView);

            shimmer1 = itemView.findViewById(R.id.flash_act_shimmer1);
            total_card = itemView.findViewById(R.id.flash_sale_card1);
            product_name = itemView.findViewById(R.id.flash_sale_product_name1);
            product_image = itemView.findViewById(R.id.flash_sale_product_image1);
            prev_cost = itemView.findViewById(R.id.flash_sale_prev_cost1);
            cost = itemView.findViewById(R.id.flash_sale_cost1);
            ratingBar = itemView.findViewById(R.id.flash_sale_rating_bar1);
            review_count = itemView.findViewById(R.id.flash_sale_review_count1);
            location = itemView.findViewById(R.id.flash_sale_location1);
            no_available = itemView.findViewById(R.id.flash_sale_available1);
            available_bar = itemView.findViewById(R.id.flash_sale_available_bar1);

            shimmer2 = itemView.findViewById(R.id.flash_act_shimmer2);
            total_card2 = itemView.findViewById(R.id.flash_sale_card2);
            product_name2 = itemView.findViewById(R.id.flash_sale_product_name2);
            product_image2 = itemView.findViewById(R.id.flash_sale_product_image2);
            prev_cost2 = itemView.findViewById(R.id.flash_sale_prev_cost2);
            cost2 = itemView.findViewById(R.id.flash_sale_cost2);
            ratingBar2 = itemView.findViewById(R.id.flash_sale_rating_bar2);
            review_count2 = itemView.findViewById(R.id.flash_sale_review_count2);
            location2 = itemView.findViewById(R.id.flash_sale_location2);
            no_available2 = itemView.findViewById(R.id.flash_sale_available2);
            available_bar2 = itemView.findViewById(R.id.flash_sale_available_bar2);

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

            total_card2.setOnClickListener(new View.OnClickListener() {
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

    public MyFlashSale2ColumnAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public FlashSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_flash_sale_list_2_column, viewGroup, false);
        FlashSaleViewHolder fsh = new FlashSaleViewHolder(v, mListener);
        return fsh;

    }

    @Override
    public void onBindViewHolder(@NonNull final FlashSaleViewHolder flashSaleViewHolder, int i) {

        int r = i * 2;
        final ProductClass pc = sale_items.get(r);

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

                        flashSaleViewHolder.shimmer1.stopShimmer();
                        flashSaleViewHolder.shimmer1.setShimmer(null);
                        flashSaleViewHolder.product_name.setText(pc.product_name);
                        flashSaleViewHolder.product_name.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.prev_cost.setPaintFlags(flashSaleViewHolder.prev_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        flashSaleViewHolder.prev_cost.setText("$" + pc.prev_cost_in_$);
                        flashSaleViewHolder.prev_cost.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.cost.setText("$" + pc.cost_in_$);
                        flashSaleViewHolder.cost.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.ratingBar.setRating(pc.rating);
                        flashSaleViewHolder.ratingBar.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.review_count.setText("(" + pc.review_count + ")");
                        flashSaleViewHolder.review_count.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.location.setText(pc.location);
                        flashSaleViewHolder.location.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.no_available.setText(pc.no_available + " available");
                        flashSaleViewHolder.no_available.setBackgroundColor(Color.WHITE);

                        if (pc.no_available > 9) {
                            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_theme);
                        } else {
                            flashSaleViewHolder.available_bar.setBackgroundResource(R.drawable.curved_view_orange);
                        }


                        return false;
                    }
                })
                .into(flashSaleViewHolder.product_image);


        if (r + 1 < sale_items.size()) {
            final ProductClass pc2 = sale_items.get(r + 1);

            Glide
                    .with(context)
                    .load(pc2.product_bitmaps.get(0))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            flashSaleViewHolder.shimmer2.stopShimmer();
                            flashSaleViewHolder.shimmer2.setShimmer(null);
                            flashSaleViewHolder.product_name2.setText(pc2.product_name);
                            flashSaleViewHolder.product_name2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.prev_cost2.setPaintFlags(flashSaleViewHolder.prev_cost2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            flashSaleViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
                            flashSaleViewHolder.prev_cost2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.cost2.setText("$" + pc2.cost_in_$);
                            flashSaleViewHolder.cost2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.ratingBar2.setRating(pc2.rating);
                            flashSaleViewHolder.ratingBar2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.review_count2.setText("(" + pc2.review_count + ")");
                            flashSaleViewHolder.review_count2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.location2.setText(pc2.location);
                            flashSaleViewHolder.location2.setBackgroundColor(Color.WHITE);
                            flashSaleViewHolder.no_available2.setText(pc2.no_available + " available");
                            flashSaleViewHolder.no_available2.setBackgroundColor(Color.WHITE);

                            if (pc2.no_available > 9) {
                                flashSaleViewHolder.available_bar2.setBackgroundResource(R.drawable.curved_view_theme);
                            } else {
                                flashSaleViewHolder.available_bar2.setBackgroundResource(R.drawable.curved_view_orange);
                            }
                            return false;
                        }
                    })
                    .into(flashSaleViewHolder.product_image2);

//            flashSaleViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//            flashSaleViewHolder.prev_cost2.setPaintFlags(flashSaleViewHolder.prev_cost2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            flashSaleViewHolder.cost2.setText("$" + pc2.cost_in_$);
//            flashSaleViewHolder.ratingBar2.setRating(pc2.rating);
//            flashSaleViewHolder.review_count2.setText("(" + pc2.review_count + ")");
//            flashSaleViewHolder.location2.setText(pc2.location);
//            flashSaleViewHolder.no_available2.setText(pc2.no_available + " available");
//
//            if (pc2.no_available > 9) {
//                flashSaleViewHolder.available_bar2.setBackgroundResource(R.drawable.curved_view_theme);
//            } else {
//                flashSaleViewHolder.available_bar2.setBackgroundResource(R.drawable.curved_view_orange);
//            }

        } else {
            flashSaleViewHolder.total_card2.setVisibility(View.INVISIBLE);
        }
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
        if (sale_items.size() % 2 == 0) {
            return sale_items.size() / 2;
        } else {
            return (sale_items.size() / 2) + 1;
        }
    }
}