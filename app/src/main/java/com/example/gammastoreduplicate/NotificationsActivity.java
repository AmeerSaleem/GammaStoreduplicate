package com.example.gammastoreduplicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView rcv_notification;
    ArrayList<NotificationClass> notification_list;
    ImageView notifications_back;
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notifications_back = findViewById(R.id.notifications_back_arrow);
        notifications_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rcv_notification = findViewById(R.id.recycler_notifications);

        notification_list = new ArrayList<>();

        notification_list.add(new NotificationClass("Gamma Store",getString(R.string.thanks_for_downloading),
                "http://icons.iconarchive.com/icons/petalart/free-shopping/256/shopping-bag-icon.png"));

        notification_list.add(new NotificationClass("Gamma Store",getString(R.string.your_item_delivery),
                "http://icons.iconarchive.com/icons/petalart/free-shopping/256/shopping-bag-icon.png"));

        notification_list.add(new NotificationClass("Gamma Store",getString(R.string.pending_items),
                "http://icons.iconarchive.com/icons/petalart/free-shopping/256/shopping-bag-icon.png"));

        notification_list.add(new NotificationClass("Gamma Store",getString(R.string.get_discount),
                "http://icons.iconarchive.com/icons/petalart/free-shopping/256/shopping-bag-icon.png"));

        NotificationsAdapter adapter = new NotificationsAdapter(this,notification_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv_notification.setLayoutManager(manager);
        rcv_notification.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallbackNotification(adapter));
        itemTouchHelper.attachToRecyclerView(rcv_notification);
    }
}