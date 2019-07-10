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

public class MyPromoAdapter extends RecyclerView.Adapter<MyPromoAdapter.PromoItemHolder> {

    Context context;
    ArrayList<ClassPromotion> promo_list;
    onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }


    public void setOnClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public class PromoItemHolder extends RecyclerView.ViewHolder {
//        ShimmerFrameLayout shimmer;
        ImageView promo_image;
        TextView discount_text;
        TextView discount_amount;
        LinearLayout promo_item;


        public PromoItemHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);

//            shimmer = itemView.findViewById(R.id.promo_shimmer);
            promo_image = itemView.findViewById(R.id.promotion_image);
            discount_text = itemView.findViewById(R.id.discount_text);
            discount_amount = itemView.findViewById(R.id.discount_amount);
            promo_item = itemView.findViewById(R.id.promo_item);


            promo_item.setOnClickListener(new View.OnClickListener() {
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

    public MyPromoAdapter(Context context, ArrayList<ClassPromotion> promo_list) {
        this.context = context;
        this.promo_list = promo_list;
    }


    @NonNull
    @Override
    public PromoItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_promotion_list, viewGroup, false);
        PromoItemHolder pih = new PromoItemHolder(v, mListener);
        return pih;
    }

    @Override
    public void onBindViewHolder(@NonNull final PromoItemHolder promoItemHolder, final int i) {

        final ClassPromotion cp = promo_list.get(i);

        Glide.with(context)
                .load(cp.image_source)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        promoItemHolder.shimmer.stopShimmer();
//                        promoItemHolder.shimmer.setShimmer(null);
                        promoItemHolder.discount_text.setText(cp.text);
                        promoItemHolder.discount_text.setBackgroundColor(Color.TRANSPARENT);
                        if (!cp.discount.equals("0")) {
                            promoItemHolder.discount_amount.setText(cp.discount);
                            promoItemHolder.discount_amount.setBackgroundColor(Color.TRANSPARENT);
                        } else {
                            promoItemHolder.discount_amount.setVisibility(View.GONE);
                        }

                        int remainder_layout = (i + 6) % 6;
                        switch (remainder_layout) {
                            case 0:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade_greeeen));
                                break;
                            case 1:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade3));
                                break;
                            case 2:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade1));
                                break;
                            case 3:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade4));
                                break;
                            case 4:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade2));
                                break;
                            case 5:
                                promoItemHolder.promo_item.setBackground(context.getResources().getDrawable(R.drawable.themeshade5));
                                break;
                        }
                        return false;
                    }
                })
                .into(promoItemHolder.promo_image);
    }

    @Override
    public int getItemCount() {
        return promo_list.size();
    }
}