package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MyRecommended3ColumnAdapter extends RecyclerView.Adapter<MyRecommended3ColumnAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position,CardView shared_image);

        public void onItemClick2(int position,CardView shared_image);

        public void onItemClick3(int position,CardView shared_image);

    }

    public void setOnClickListener(MyRecommended3ColumnAdapter.onItemClickListener listener) {
        mListener = listener;
    }


    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

//        ShimmerFrameLayout shimmer1;
//        ShimmerFrameLayout shimmer2;
//        ShimmerFrameLayout shimmer3;

        TextView product_name1, product_name2, product_name3;
        TextView prev_cost1, prev_cost2, prev_cost3;
        TextView cost1, cost2, cost3;
        TextView items_sold1, items_sold2, items_sold3;
        ImageView product_image1, product_image2, product_image3;
        RatingBar ratingBar1, ratingBar2, ratingBar3;
        TextView review_count1, review_count2, review_count3;
        LinearLayout total_card1, total_card2, total_card3;
        CardView under_card1, under_card2, under_card3;

        public RecommendedViewHolder(@NonNull View itemView, final onItemClickListener listener) {

            super(itemView);
//            shimmer1 = itemView.findViewById(R.id.recommended3_shimmer1);
            total_card1 = itemView.findViewById(R.id.recommended_3_column_first_card1);
            product_name1 = itemView.findViewById(R.id.recommended_3_column_product_name1);
            product_image1 = itemView.findViewById(R.id.recommended_3_column_product_image1);
            cost1 = itemView.findViewById(R.id.recommended_3_column_cost1);
            ratingBar1 = itemView.findViewById(R.id.recommended_3_column_rating_bar1);
            review_count1 = itemView.findViewById(R.id.recommended_3_column_review_count1);
//            items_sold1 = itemView.findViewById(R.id.recommended_3_column_items_sold1);
            under_card1 = itemView.findViewById(R.id.recommended_card_view_under_image1);

//            shimmer2 = itemView.findViewById(R.id.recommended3_shimmer2);
            total_card2 = itemView.findViewById(R.id.recommended_3_column_second_card2);
            product_name2 = itemView.findViewById(R.id.recommended_3_column_product_name2);
            product_image2 = itemView.findViewById(R.id.recommended_3_column_product_image2);
            cost2 = itemView.findViewById(R.id.recommended_3_column_cost2);
            ratingBar2 = itemView.findViewById(R.id.recommended_3_column_rating_bar2);
            review_count2 = itemView.findViewById(R.id.recommended_3_column_review_count2);
//            items_sold2 = itemView.findViewById(R.id.recommended_3_column_items_sold2);
            under_card2 = itemView.findViewById(R.id.recommended_card_view_under_image2);

//            shimmer3 = itemView.findViewById(R.id.recommended3_shimmer3);
            total_card3 = itemView.findViewById(R.id.recommended_3_column_third_card3);
            product_name3 = itemView.findViewById(R.id.recommended_3_column_product_name3);
            product_image3 = itemView.findViewById(R.id.recommended_3_column_product_image3);
            cost3 = itemView.findViewById(R.id.recommended_3_column_cost3);
            ratingBar3 = itemView.findViewById(R.id.recommended_3_column_rating_bar3);
            review_count3 = itemView.findViewById(R.id.recommended_3_column_review_count3);
//            items_sold3 = itemView.findViewById(R.id.recommended_3_column_items_sold3);
            under_card3 = itemView.findViewById(R.id.recommended_card_view_under_image3);


            total_card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position,under_card1);
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
                            listener.onItemClick2(position,under_card2);
                        }
                    }
                }
            });

            total_card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick3(position,under_card3);
                        }
                    }
                }
            });


        }
    }

    public MyRecommended3ColumnAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_recommended_3_column_view_list, viewGroup, false);
        RecommendedViewHolder rvh = new RecommendedViewHolder(v, mListener);
        return rvh;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecommendedViewHolder recommendedViewHolder, int i) {


        int r = (i * 3);
        final ProductClass pc = sale_items.get(r);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(recommendedViewHolder.under_card1, "small_3col_img"+r);
        }

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
//                        recommendedViewHolder.shimmer1.stopShimmer();
//                        recommendedViewHolder.shimmer1.setShimmer(null);

                        recommendedViewHolder.product_name1.setText(pc.product_name);
                        recommendedViewHolder.product_name1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.ratingBar1.setVisibility(View.VISIBLE);
//        recommendedViewHolder.prev_cost1.setText("$" + pc.prev_cost_in_$);
                        recommendedViewHolder.cost1.setText("$" + pc.cost_in_$);
                        recommendedViewHolder.cost1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.review_count1.setText(String.valueOf(pc.rating));
                        recommendedViewHolder.review_count1.setBackgroundColor(Color.WHITE);

                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(recommendedViewHolder.product_image1);

//        recommendedViewHolder.items_sold1.setText(String.valueOf(pc.items_sold) + " items sold");

        if (r + 1 < sale_items.size()) {
            final ProductClass pc2 = sale_items.get(r + 1);

            int r2 = r + 1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewCompat.setTransitionName(recommendedViewHolder.under_card2, "small_3col_img"+r2);
            }

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
//                            recommendedViewHolder.shimmer2.stopShimmer();
//                            recommendedViewHolder.shimmer2.setShimmer(null);
                            recommendedViewHolder.product_name2.setText(pc2.product_name);
                            recommendedViewHolder.ratingBar2.setVisibility(View.VISIBLE);
                            recommendedViewHolder.product_name2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.cost2.setText("$" + pc2.cost_in_$);
                            recommendedViewHolder.cost2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.review_count2.setText(String.valueOf(pc2.rating));
                            recommendedViewHolder.review_count2.setBackgroundColor(Color.WHITE);

                            return false;
                        }
                    })
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(recommendedViewHolder.product_image2);

//            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//            recommendedViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");

        } else {
            recommendedViewHolder.total_card2.setVisibility(View.INVISIBLE);
        }


        if (r + 2 < sale_items.size()) {
            final ProductClass pc3 = sale_items.get(r + 2);

            int r3 = r + 2;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewCompat.setTransitionName(recommendedViewHolder.under_card3, "small_3col_img"+r3);
            }
            Glide
                    .with(context)
                    .load(pc3.product_bitmaps.get(0))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

//                            recommendedViewHolder.shimmer3.stopShimmer();
//                            recommendedViewHolder.shimmer3.setShimmer(null);
                            recommendedViewHolder.product_name3.setText(pc3.product_name);
                            recommendedViewHolder.ratingBar3.setVisibility(View.VISIBLE);
                            recommendedViewHolder.product_name3.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.cost3.setText("$" + pc3.cost_in_$);
                            recommendedViewHolder.cost3.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.review_count3.setText(String.valueOf(pc3.rating));
                            recommendedViewHolder.review_count3.setBackgroundColor(Color.WHITE);

                            return false;
                        }
                    })
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(recommendedViewHolder.product_image3);

//            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//            recommendedViewHolder.items_sold3.setText(String.valueOf(pc2.items_sold) + " items sold");

        } else {
            recommendedViewHolder.total_card3.setVisibility(View.INVISIBLE);
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
        if ((sale_items.size() % 3) == 0) {

            return sale_items.size() / 3;

        } else {

            return ((sale_items.size() / 3) + 1);

        }


    }
}