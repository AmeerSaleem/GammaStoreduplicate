package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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

public class MyRecommendedListViewAdapter extends RecyclerView.Adapter<MyRecommendedListViewAdapter.RecommendedViewHolder> {

    Context context;
    ArrayList<ProductClass> sale_items;
    MyRecommendedListViewAdapter.onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position,ImageView sharedImage);

    }

    public void setOnClickListener(MyRecommendedListViewAdapter.onItemClickListener listener) {
        mListener = listener;
    }


    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

//        ShimmerFrameLayout shimmer;
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
        CardView total_card1;

        public RecommendedViewHolder(@NonNull View itemView, final MyRecommendedListViewAdapter.onItemClickListener listener) {

            super(itemView);

//            shimmer = itemView.findViewById(R.id.recommended_list_view_shimmer);
            total_card1 = itemView.findViewById(R.id.recommended_list_view_card);
            product_name = itemView.findViewById(R.id.recommended_list_view_title);
            product_image = itemView.findViewById(R.id.recommended_list_view_image);
            cost = itemView.findViewById(R.id.recommended_list_view_cost);
            prev_cost = itemView.findViewById(R.id.recommended_list_view_prev_cost);
//            ratingBar = itemView.findViewById(R.id.recommended_card_view_rating);


            total_card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position,product_image);
                        }
                    }
                }
            });


        }
    }

    public MyRecommendedListViewAdapter(Context context, ArrayList<ProductClass> sale_items) {
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_recommended_listview_list, viewGroup, false);
        RecommendedViewHolder rvh = new RecommendedViewHolder(v, mListener);
        return rvh;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecommendedListViewAdapter.RecommendedViewHolder recommendedViewHolder, int i) {


        final ProductClass pc = sale_items.get(i);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(recommendedViewHolder.product_image, "small_list_img"+i);
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

                        recommendedViewHolder.product_name.setText(pc.product_name);
                        recommendedViewHolder.product_name.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.prev_cost.setPaintFlags(recommendedViewHolder.prev_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        recommendedViewHolder.prev_cost.setText("$" + pc.prev_cost_in_$);
                        recommendedViewHolder.prev_cost.setBackgroundColor(Color.WHITE);
                        recommendedViewHolder.cost.setText("$" + pc.cost_in_$);
                        recommendedViewHolder.cost.setBackgroundColor(Color.WHITE);
                        return false;
                    }
                })
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate().dontTransform())
                .into(recommendedViewHolder.product_image);

//        recommendedViewHolder.ratingBar.setRating(pc.rating);
//        recommendedViewHolder.review_count.setText(String.valueOf(pc.rating));
//        recommendedViewHolder.items_sold.setText(String.valueOf(pc.items_sold) + " items sold");
    }

    @Override
    public int getItemCount() {
        return sale_items.size();
    }
}
