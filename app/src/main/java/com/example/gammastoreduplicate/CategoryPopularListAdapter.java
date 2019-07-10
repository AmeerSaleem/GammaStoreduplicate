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

public class CategoryPopularListAdapter extends RecyclerView.Adapter<CategoryPopularListAdapter.FlashSaleViewHolder>{

    Context context;
    ArrayList<ProductClass> sale_items;
    CategoryPopularListAdapter.onItemClickListener mListener;

    public interface onItemClickListener{

        public void onItemClick(int position);

    }

    public void setOnClickListener(CategoryPopularListAdapter.onItemClickListener listener){mListener = listener;}

    public class FlashSaleViewHolder extends RecyclerView.ViewHolder{

        ShimmerFrameLayout shimmer;
        TextView product_name;
        TextView prev_cost;
        TextView cost;
        ImageView product_image;
        ImageView ratingBar;
        TextView review_count;
        TextView location;
        TextView no_available;
        View available_bar;
        LinearLayout total_card;
        TextView items_sold12;

        public FlashSaleViewHolder(@NonNull View itemView, final CategoryPopularListAdapter.onItemClickListener listener) {

            super(itemView);

            shimmer = itemView.findViewById(R.id.category_popular_shimmer);
            total_card = itemView.findViewById(R.id.category_discount_card);
            product_name = itemView.findViewById(R.id.category_discount_name);
            product_image = itemView.findViewById(R.id.category_discount_image);
            cost = itemView.findViewById(R.id.category_discount_cost);
            ratingBar = itemView.findViewById(R.id.category_discount_rating_bar);
            review_count = itemView.findViewById(R.id.category_discount_review_count);
            items_sold12 = itemView.findViewById(R.id.category_discount_items_sold);

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
    public CategoryPopularListAdapter(Context context, ArrayList<ProductClass> sale_items){
        this.context = context;
        this.sale_items = sale_items;
    }

    @NonNull
    public CategoryPopularListAdapter.FlashSaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_category_popular_list,viewGroup,false);
        CategoryPopularListAdapter.FlashSaleViewHolder fsh = new CategoryPopularListAdapter.FlashSaleViewHolder(v,mListener);
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
                        flashSaleViewHolder.shimmer.stopShimmer();
                        flashSaleViewHolder.shimmer.setShimmer(null);

                        flashSaleViewHolder.product_name.setText(pc.product_name);
                        flashSaleViewHolder.product_name.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.cost.setText("$" + pc.cost_in_$);
                        flashSaleViewHolder.cost.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.review_count.setText("(" + pc.review_count + ")");
                        flashSaleViewHolder.review_count.setBackgroundColor(Color.WHITE);
                        flashSaleViewHolder.ratingBar.setVisibility(View.VISIBLE);
                        flashSaleViewHolder.ratingBar.setImageResource(R.drawable.ic_star_gold_24dp);
                        flashSaleViewHolder.items_sold12.setText(String.valueOf(pc.items_sold) + " items sold");

                        return false;
                    }
                })
                .into(flashSaleViewHolder.product_image);
    }

    public int getItemCount() {
        return sale_items.size();
    }
}