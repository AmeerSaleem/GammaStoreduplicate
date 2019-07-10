package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private List<ChatMessageClass> mMessageList;

    public MessageListAdapter(Context context, List<ChatMessageClass> messageList) {

        mContext = context;
        mMessageList = messageList;

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        } else if (position >= 1) {
            return VIEW_TYPE_MESSAGE_SENT;
        }

        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;

        switch(viewType){
            case VIEW_TYPE_MESSAGE_SENT:
                view = LayoutInflater.from(mContext).inflate(R.layout.row_chat_user,viewGroup,false);
                RecyclerView.ViewHolder viewHolder = new SentMessageHolder(view);
                return viewHolder;

            case VIEW_TYPE_MESSAGE_RECEIVED:
                view = LayoutInflater.from(mContext).inflate(R.layout.row_chat_service,viewGroup,false);
                RecyclerView.ViewHolder viewHolder1 = new ReceivedMessageHolder(view);
                return viewHolder1;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ChatMessageClass message = mMessageList.get(i);
        int viewType = viewHolder.getItemViewType();
        switch(viewType){

            case VIEW_TYPE_MESSAGE_SENT:
            ((SentMessageHolder) viewHolder).bind(message);
            break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
            ((ReceivedMessageHolder) viewHolder).bind(message);
            break;
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_body1);
            timeText = itemView.findViewById(R.id.text_message_time1);
        }

        void bind(ChatMessageClass message) {
            messageText.setText(message.message);
            timeText.setText(message.created_at);
        }

    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText, timeText, nameText;

        ReceivedMessageHolder(View itemView) {

            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);

        }

        void bind(ChatMessageClass message) {

            messageText.setText(message.message);

            timeText.setText(message.created_at);


        }

    }

}
