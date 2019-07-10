package com.example.gammastoreduplicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rcv_chat;
    private MessageListAdapter adapter;
    ArrayList<ChatMessageClass> messageList;
    EditText chat_message;
    ImageView send_message_button;
    ImageView chat_back_arrow;

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chat_back_arrow = findViewById(R.id.chat_back_arrow);

        chat_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        chat_message = findViewById(R.id.chat_message_edit_text);
        send_message_button = findViewById(R.id.send_message_button);

        send_message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chat_message.getText().toString().equals("")) {

                } else {
                    String user_message = chat_message.getText().toString();
//                    String time = "11:43pm";

                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();

                    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    String formattedDate = dateFormat.format(date);

                    messageList.add(new ChatMessageClass(user_message, formattedDate));
                    adapter.notifyItemInserted(messageList.size());
                    chat_message.setText("");
                    rcv_chat.smoothScrollToPosition(messageList.size() - 1);
                }
            }
        });

        messageList = new ArrayList<>();
        messageList.add(new ChatMessageClass(getString(R.string.hello_how_can_i_help), "11:40pm"));
//        messageList.add(new ChatMessageClass("Hello, hello","11:41pm"));
//        messageList.add(new ChatMessageClass("are you there?","11:42pm"));

        rcv_chat = findViewById(R.id.recycler_chat);
        adapter = new MessageListAdapter(this, messageList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv_chat.setLayoutManager(manager);
        rcv_chat.setAdapter(adapter);
        adapter.notifyItemInserted(messageList.size());
    }
}
