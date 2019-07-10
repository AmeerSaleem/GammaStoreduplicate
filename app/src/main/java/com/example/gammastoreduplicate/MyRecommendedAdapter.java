package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

public class MyRecommendedAdapter extends RecyclerView.Adapter<MyRecommendedAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    MyRecommendedAdapter.onItemClickListener mListener;
    boolean showShimmer = true;
    boolean showShimmer2 = true;


    public interface onItemClickListener {

        public void onViewClick(int position, ImageView shared_2col_image);

        public void onViewClick2(int position, ImageView shared_2col_image);

    }

    public void setOnClickListener(MyRecommendedAdapter.onItemClickListener listener) {
        mListener = listener;
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

        TextView grey_star1,grey_star2;
//        ShimmerFrameLayout shimmer;
//        ShimmerFrameLayout shimmer2;
        TextView product_name1, product_name2;
        TextView prev_cost1, prev_cost2;
        TextView cost1, cost2;
        TextView items_sold1, items_sold2;
        ImageView product_image1, product_image2;
        RatingBar ratingBar1, ratingBar2;
        TextView review_count1, review_count2;
        TextView location;
        TextView no_available;
        View available_bar;
        CardView total_card1, total_card2;

        public RecommendedViewHolder(@NonNull View itemView, final MyRecommendedAdapter.onItemClickListener listener) {

            super(itemView);
            grey_star1 = itemView.findViewById(R.id.recommended2_grey_star);
            grey_star2 = itemView.findViewById(R.id.recommended2_grey_star2);
//            shimmer = itemView.findViewById(R.id.recommended2_shimmer);
            total_card1 = itemView.findViewById(R.id.recommended_first_card_view);
            product_name1 = itemView.findViewById(R.id.recommended_product_name);
            product_image1 = itemView.findViewById(R.id.recommended_product_image);
            cost1 = itemView.findViewById(R.id.recommended_cost);
            ratingBar1 = itemView.findViewById(R.id.recommended_rating_bar);
            review_count1 = itemView.findViewById(R.id.recommended_review_count);
            items_sold1 = itemView.findViewById(R.id.recommended_items_sold);
            items_sold2 = itemView.findViewById(R.id.recommended_items_sold2);

//            shimmer2 = itemView.findViewById(R.id.recommended2_shimmer2);
            total_card2 = itemView.findViewById(R.id.recommended_second_card_view);
            product_name2 = itemView.findViewById(R.id.recommended_product_name2);
            product_image2 = itemView.findViewById(R.id.recommended_product_image2);
            cost2 = itemView.findViewById(R.id.recommended_cost2);
            ratingBar2 = itemView.findViewById(R.id.recommended_rating_bar2);
            review_count2 = itemView.findViewById(R.id.recommended_review_count2);

            total_card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onViewClick(position,product_image1);
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
                            listener.onViewClick2(position,product_image2);
                        }
                    }
                }
            });
        }
    }

    public MyRecommendedAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_recommended_list, viewGroup, false);
        RecommendedViewHolder rvh = new RecommendedViewHolder(v, mListener);
        return rvh;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecommendedViewHolder recommendedViewHolder, int i) {


        final int r = (i * 2);
        final ProductClass pc = sale_items.get(r);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(recommendedViewHolder.product_image1, "small_3col_img"+r);
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
//                        recommendedViewHolder.shimmer.stopShimmer();
//                        recommendedViewHolder.shimmer.setShimmer(null);
                        showShimmer = false;
                        recommendedViewHolder.grey_star1.setVisibility(View.GONE);
                        recommendedViewHolder.ratingBar1.setVisibility(View.VISIBLE);
                        recommendedViewHolder.ratingBar1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.product_name1.setText(pc.product_name);
                        recommendedViewHolder.product_name1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.cost1.setText("$" + pc.cost_in_$);
                        recommendedViewHolder.cost1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.review_count1.setText(String.valueOf(pc.rating));
                        recommendedViewHolder.review_count1.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.items_sold1.setText(String.valueOf(pc.items_sold) + " items sold");
                        recommendedViewHolder.items_sold1.setBackgroundColor(Color.WHITE);

//                        if (!((r + 1) == sale_items.size())) {
//                            ProductClass pc2 = sale_items.get(r + 1);
//
//                            recommendedViewHolder.product_name2.setText(pc2.product_name);
//                            recommendedViewHolder.product_name2.setBackgroundColor(Color.WHITE);
//
//                            Glide
//                                    .with(context)
//                                    .load(pc2.product_bitmaps.get(0))
//                                    .into(recommendedViewHolder.product_image2);
//
////            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//                            recommendedViewHolder.cost2.setText("$" + pc2.cost_in_$);
//                            recommendedViewHolder.cost2.setBackgroundColor(Color.WHITE);
//                            recommendedViewHolder.review_count2.setText(String.valueOf(pc2.rating));
//                            recommendedViewHolder.review_count2.setBackgroundColor(Color.WHITE);
//                            recommendedViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");
//                            recommendedViewHolder.items_sold2.setBackgroundColor(Color.WHITE);
//
//                        } else {
//                            recommendedViewHolder.total_card2.setVisibility(View.INVISIBLE);
//                        }

                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(recommendedViewHolder.product_image1);

        if (!((r + 1) == sale_items.size())) {
            final ProductClass pc2 = sale_items.get(r + 1);

            int r2 = r + 1;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewCompat.setTransitionName(recommendedViewHolder.product_image2, "small_3col_img"+r2);
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
                            showShimmer2 = false;

                            recommendedViewHolder.grey_star2.setVisibility(View.GONE);
                            recommendedViewHolder.product_name2.setText(pc2.product_name);
                            recommendedViewHolder.product_name2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.ratingBar2.setVisibility(View.VISIBLE);
                            recommendedViewHolder.ratingBar2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.cost2.setText("$" + pc2.cost_in_$);
                            recommendedViewHolder.cost2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.review_count2.setText(String.valueOf(pc2.rating));
                            recommendedViewHolder.review_count2.setBackgroundColor(Color.WHITE);
                            recommendedViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");
                            recommendedViewHolder.items_sold2.setBackgroundColor(Color.WHITE);


                            return false;
                        }
                    })
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(recommendedViewHolder.product_image2);
        } else {
            recommendedViewHolder.total_card2.setVisibility(View.INVISIBLE);
        }

//            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//        recommendedViewHolder.prev_cost1.setText("$" + pc.prev_cost_in_$);
//        recommendedViewHolder.cost1.setText("$" + pc.cost_in_$);
//        recommendedViewHolder.review_count1.setText(String.valueOf(pc.rating));
//        recommendedViewHolder.items_sold1.setText(String.valueOf(pc.items_sold) + " items sold");
//
//        if (sale_items.size() % 2 != 1) {
//            ProductClass pc2 = sale_items.get(r + 1);
//
//            recommendedViewHolder.product_name2.setText(pc2.product_name);
//
//            Glide
//                    .with(context)
//                    .load(pc2.product_bitmaps.get(0))
//                    .into(recommendedViewHolder.product_image2);
//
////            recommendedViewHolder.prev_cost2.setText("$" + pc2.prev_cost_in_$);
//            recommendedViewHolder.cost2.setText("$" + pc2.cost_in_$);
//            recommendedViewHolder.review_count2.setText(String.valueOf(pc2.rating));
//            recommendedViewHolder.items_sold2.setText(String.valueOf(pc2.items_sold) + " items sold");
//
//        } else {
//            recommendedViewHolder.total_card2.setVisibility(View.INVISIBLE);
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
        if ((sale_items.size() % 2) == 0) {

            return sale_items.size() / 2;

        } else {

            return ((sale_items.size() / 2) + 1);

        }


    }
}