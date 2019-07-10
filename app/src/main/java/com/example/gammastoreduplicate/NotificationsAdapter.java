package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ReviewPdViewHolder>{

    Context context;
    ArrayList<NotificationClass> notification_items;
    NotificationsAdapter.onItemClickListener mListener;

    public interface onItemClickListener {

        public void onItemClick(int position);

    }

    public void setOnClickListener(NotificationsAdapter.onItemClickListener listener){mListener = listener;}

    public NotificationsAdapter(Context context, ArrayList<NotificationClass> notification_items){

        this.context = context;
        this.notification_items= notification_items;

    }

    public class ReviewPdViewHolder extends RecyclerView.ViewHolder{

        SelectableRoundedImageView notification_image;
        TextView notification_source;
        TextView notification_message;

        public ReviewPdViewHolder(@NonNull View itemView, final onItemClickListener listener){

            super(itemView);
            notification_image = itemView.findViewById(R.id.notification_image);
            notification_source = itemView.findViewById(R.id.notification_source);
            notification_message = itemView.findViewById(R.id.notification_message);

        }

    }


    @NonNull
    @Override
    public ReviewPdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_notification_list,viewGroup,false);
        ReviewPdViewHolder rph = new ReviewPdViewHolder(v,mListener);
        return rph;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewPdViewHolder reviewPdViewHolder, int i) {

        NotificationClass rc = notification_items.get(i);

        reviewPdViewHolder.notification_source.setText(rc.notification_source);
        reviewPdViewHolder.notification_message.setText(rc.notification_message);

        Glide
                .with(context)
                .load(rc.notification_image)
                .into(reviewPdViewHolder.notification_image);

    }



    @Override
    public int getItemCount() {
        return notification_items.size();
    }

    public void deleteItem(int position){
        NotificationClass recentlyDeleted = notification_items.get(position);
        int recentlyDeletedPosition = position;
        notification_items.remove(position);
        notifyItemRemoved(position);
    }


}
