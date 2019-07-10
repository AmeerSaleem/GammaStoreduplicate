package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MyPromoActivityAdapter extends RecyclerView.Adapter<MyPromoActivityAdapter.PromoViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    onItemClickListener mListener;
    boolean showShimmer = true;
    int SHIMMER_ITEM_NUMBER = 5;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }


    public class PromoViewHolder extends RecyclerView.ViewHolder {

        ShimmerFrameLayout shimmer_layout;
        TextView discount1, discount2;
        TextView product_name1, product_name2;
        TextView prev_cost1, prev_cost2;
        TextView cost1, cost2;
        TextView items_sold1, items_sold2;
        ImageView product_image1, product_image2;
        ImageView ratingBar1, ratingBar2;
        TextView review_count1, review_count2;
        TextView location;
        TextView no_available;
        View available_bar;
        LinearLayout total_card1, total_card2;
        LinearLayout template, actual_data, promotional_placeholder;
        LinearLayout discount_bar1, discount_bar2;

        public PromoViewHolder(@NonNull View itemView, final onItemClickListener listener) {

            super(itemView);

            discount_bar1 = itemView.findViewById(R.id.promotional_activity_discount_bar_1);
            discount_bar2 = itemView.findViewById(R.id.promotional_activity_discount_bar_2);
            discount1 = itemView.findViewById(R.id.promotional_activity_discount1);
            discount2 = itemView.findViewById(R.id.promotional_activity_discount2);
            shimmer_layout = itemView.findViewById(R.id.shimmer_layout);
            template = itemView.findViewById(R.id.promo_template);
            actual_data = itemView.findViewById(R.id.promo_actual_data1);
            promotional_placeholder = itemView.findViewById(R.id.promotional_placeholder1);

            total_card1 = itemView.findViewById(R.id.promotion_activity_first_card);
            product_name1 = itemView.findViewById(R.id.promotion_activity_product_name);
            product_image1 = itemView.findViewById(R.id.promotion_activity_product_image);
            cost1 = itemView.findViewById(R.id.promotion_activity_cost);
            ratingBar1 = itemView.findViewById(R.id.promotion_activity_rating_bar);
            review_count1 = itemView.findViewById(R.id.promotion_activity_review_count);
            items_sold1 = itemView.findViewById(R.id.promotion_activity_items_sold);

            items_sold2 = itemView.findViewById(R.id.promotion_activity_items_sold2);
            total_card2 = itemView.findViewById(R.id.promotion_activity_second_card);
            product_name2 = itemView.findViewById(R.id.promotion_activity_product_name2);
            product_image2 = itemView.findViewById(R.id.promotion_activity_product_image2);
            cost2 = itemView.findViewById(R.id.promotion_activity_cost2);
            ratingBar2 = itemView.findViewById(R.id.promotion_activity_rating_bar2);
            review_count2 = itemView.findViewById(R.id.promotion_activity_review_count2);

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

    public MyPromoActivityAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public PromoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_promotion_activity_list, viewGroup, false);
        PromoViewHolder pvh = new PromoViewHolder(v, mListener);
        return pvh;

    }

    @Override
    public void onBindViewHolder(@NonNull final PromoViewHolder promoViewHolder, int i) {

//        if (showShimmer) {
//
//            promoViewHolder.shimmer_layout.startShimmer();
//
//        } else {


        final int r = (i * 2);
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
                        promoViewHolder.shimmer_layout.stopShimmer();
                        promoViewHolder.shimmer_layout.setShimmer(null);
                        showShimmer = false;

                        promoViewHolder.product_name1.setText(pc.product_name);
                        promoViewHolder.product_name1.setBackgroundColor(Color.WHITE);
                        promoViewHolder.cost1.setText("$" + pc.cost_in_$);
                        promoViewHolder.cost1.setBackgroundColor(Color.WHITE);
                        promoViewHolder.review_count1.setText(String.valueOf(pc.rating));
                        promoViewHolder.review_count1.setBackgroundColor(Color.WHITE);
                        promoViewHolder.ratingBar1.setBackgroundResource(R.drawable.ic_star_gold_24dp);
                        promoViewHolder.items_sold1.setText(String.valueOf(pc.items_sold) + " items sold");
                        promoViewHolder.items_sold1.setBackgroundColor(Color.WHITE);
                        promoViewHolder.discount_bar1.setBackgroundResource(R.drawable.discount_box);
                        promoViewHolder.discount1.setText(String.valueOf(pc.discount) + "%");

                        if (sale_items.size() % 2 != 1) {
                            ProductClass pc2 = sale_items.get(r + 1);

                            promoViewHolder.product_name2.setText(pc2.product_name);
                            promoViewHolder.product_name2.setBackgroundColor(Color.WHITE);

                            Glide
                                    .with(context)
                                    .load(pc2.product_bitmaps.get(0))
                                    .into(promoViewHolder.product_image2);

//            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
                            promoViewHolder.cost2.setText("$" + pc2.cost_in_$);
                            promoViewHolder.cost2.setBackgroundColor(Color.WHITE);
                            promoViewHolder.review_count2.setText(String.valueOf(pc2.rating));
                            promoViewHolder.review_count2.setBackgroundColor(Color.WHITE);
                            promoViewHolder.ratingBar2.setBackgroundResource(R.drawable.ic_star_gold_24dp);
                            promoViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");
                            promoViewHolder.items_sold2.setBackgroundColor(Color.WHITE);
                            promoViewHolder.discount_bar2.setBackgroundResource(R.drawable.discount_box);
                            promoViewHolder.discount2.setText(String.valueOf(pc2.discount) + "%");
                        } else {
                            promoViewHolder.total_card2.setVisibility(View.INVISIBLE);
                        }
                        return false;
                    }
                })
                .into(promoViewHolder.product_image1);

//            PromotionActivity.skeletonScreen.hide();
//        recommendedViewHolder.prev_cost1.setText("$" + pc.prev_cost_in_$);
//            promoViewHolder.cost1.setText("$" + pc.cost_in_$);
//            promoViewHolder.cost1.setBackgroundColor(Color.WHITE);
//            promoViewHolder.review_count1.setText(String.valueOf(pc.rating));
//            promoViewHolder.review_count1.setBackgroundColor(Color.WHITE);
//            promoViewHolder.ratingBar1.setBackgroundResource(R.drawable.ic_star_gold_24dp);
//            promoViewHolder.items_sold1.setText(String.valueOf(pc.items_sold) + " items sold");
//            promoViewHolder.items_sold1.setBackgroundColor(Color.WHITE);
//            promoViewHolder.discount_bar1.setBackgroundResource(R.drawable.discount_box);
//            promoViewHolder.discount1.setText(String.valueOf(pc.discount) + "%");
//
//            if (sale_items.size() % 2 != 1) {
//                ProductClass pc2 = sale_items.get(r + 1);
//
//                promoViewHolder.product_name2.setText(pc2.product_name);
//                promoViewHolder.product_name2.setBackgroundColor(Color.WHITE);
//
//                Glide
//                        .with(context)
//                        .load(pc2.product_bitmaps.get(0))
//                        .into(promoViewHolder.product_image2);
//
////            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//                promoViewHolder.cost2.setText("$" + pc2.cost_in_$);
//                promoViewHolder.cost2.setBackgroundColor(Color.WHITE);
//                promoViewHolder.review_count2.setText(String.valueOf(pc2.rating));
//                promoViewHolder.review_count2.setBackgroundColor(Color.WHITE);
//                promoViewHolder.ratingBar2.setBackgroundResource(R.drawable.ic_star_gold_24dp);
//                promoViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");
//                promoViewHolder.items_sold2.setBackgroundColor(Color.WHITE);
//                promoViewHolder.discount_bar2.setBackgroundResource(R.drawable.discount_box);
//                promoViewHolder.discount2.setText(String.valueOf(pc2.discount) + "%");
//            } else {
//                promoViewHolder.total_card2.setVisibility(View.INVISIBLE);
//            }
    }
//    }

    @Override
    public int getItemCount() {
        if (showShimmer == true) {
            return SHIMMER_ITEM_NUMBER / 2;
        } else {
            if ((sale_items.size() % 2) == 0) {

                return sale_items.size() / 2;

            } else {

                return ((sale_items.size() / 2) + 1);

            }

        }
    }
}