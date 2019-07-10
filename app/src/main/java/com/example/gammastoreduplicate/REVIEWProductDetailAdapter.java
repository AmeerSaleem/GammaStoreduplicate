package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

public class REVIEWProductDetailAdapter extends RecyclerView.Adapter<REVIEWProductDetailAdapter.ReviewPdViewHolder>{

    Context context;
    ArrayList<ReviewClass> review_items;
    REVIEWProductDetailAdapter.onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }

    public void setOnClickListener(REVIEWProductDetailAdapter.onItemClickListener listener){mListener = listener;}

    public REVIEWProductDetailAdapter(Context context, ArrayList<ReviewClass> review_items){

        this.context = context;
        this.review_items = review_items;

    }

    public class ReviewPdViewHolder extends RecyclerView.ViewHolder{

        SelectableRoundedImageView review_image;
        RatingBar review_rating;
        TextView review_date;
        TextView review_comments;

        public ReviewPdViewHolder(@NonNull View itemView, final onItemClickListener listener){

            super(itemView);
            review_image = itemView.findViewById(R.id.review_image);
            review_rating = itemView.findViewById(R.id.review_rating);
            review_date = itemView.findViewById(R.id.review_date);
            review_comments = itemView.findViewById(R.id.review_comments_text);

        }

    }


    @NonNull
    @Override
    public ReviewPdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_review_pd_list,viewGroup,false);
        ReviewPdViewHolder rph = new ReviewPdViewHolder(v,mListener);
        return rph;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewPdViewHolder reviewPdViewHolder, int i) {

        ReviewClass rc = review_items.get(i);

        reviewPdViewHolder.review_date.setText(rc.date);
        reviewPdViewHolder.review_rating.setRating(rc.review_rating);
        reviewPdViewHolder.review_comments.setText(rc.review_comment);

        Glide
                .with(context)
                .load(rc.review_image)
                .into(reviewPdViewHolder.review_image);

    }

    @Override
    public int getItemCount() {
        return review_items.size();
    }

}
