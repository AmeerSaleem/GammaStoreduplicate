package com.example.gammastoreduplicate;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FlashSaleActivity extends AppCompatActivity {

    RecyclerView rcv_flash_activity;
    ArrayList flash_sale_items;
    private TextView countDownText;
    private CountDownTimer countDownTimer;
    private long timeInMilliSeconds = 86400000;
    ImageView back_button_flash_sale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_sale);

        back_button_flash_sale = findViewById(R.id.flash_sale_back_arrow);
        countDownText = findViewById(R.id.timer_display);
        startTimer();

        back_button_flash_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rcv_flash_activity = findViewById(R.id.recycler_flash_activity1);
        flash_sale_items = new ArrayList();
        populateFlashItems();

        MyFlashSale2ColumnAdapter adapter = new MyFlashSale2ColumnAdapter(this,flash_sale_items);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv_flash_activity.setLayoutManager(manager);
        rcv_flash_activity.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    private void populateFlashItems() {

        try {
//            ArrayList<Bitmap> produxt_image1 = new ArrayList<>();
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_acer_helios_x350));
//
//            ArrayList<Bitmap> produxt_image2 = new ArrayList<>();
//            produxt_image2.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_dress));
//
//            ArrayList<Bitmap> produxt_image3 = new ArrayList<>();
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_flare_s7));
//
//            ArrayList<Bitmap> produxt_image4 = new ArrayList<>();
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_book2));
//
//            ArrayList<Bitmap> produxt_image5 = new ArrayList<>();
//            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_game_2));

            ArrayList<String> produxt_image1 = new ArrayList<>();
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_acer_helios_x350));
            produxt_image1.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));

            ArrayList<String> produxt_image2 = new ArrayList<>();
//            produxt_image2.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_dress));
            produxt_image2.add(new String("https://stat.homeshop18.com/homeshop18/images/productImages/753/miss-chase-womens-silk-polyester-dress-navy-blue-714X1000-5X7-da0dcddba3f149ed83737105e1c06e50.jpg"));

            ArrayList<String> produxt_image3 = new ArrayList<>();
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_flare_s7));
            produxt_image3.add(new String("https://4.bp.blogspot.com/-fr-I9qMO5Xo/W-QtKQtjtmI/AAAAAAAACBg/wC0v3ObDVvMCjj3Q3m_i3FJsXew_QE-NgCLcBGAs/s1600/cherry-mobile-flare-s7-deluxe.jpg"));

            ArrayList<String> produxt_image4 = new ArrayList<>();
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_book2));
            produxt_image4.add(new String("https://pictures.abebooks.com/MASTODONBOOKS1521/22535913203.jpg"));

            ArrayList<String> produxt_image5 = new ArrayList<>();
            produxt_image5.add(new String("https://cf5.s3.souqcdn.com/item/2015/08/26/89/31/12/2/item_XL_8931122_9157583.jpg"));


            flash_sale_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));

            flash_sale_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            flash_sale_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            flash_sale_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            flash_sale_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));
            flash_sale_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void startTimer() {

        countDownTimer = new CountDownTimer(timeInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMilliSeconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    public void updateTimer() {
        int hours = (int) timeInMilliSeconds / 3600000;
        int minutes = (int) timeInMilliSeconds % 3600000 / 60000;
        int seconds = (int) timeInMilliSeconds % 3600000 % 60000 / 1000;

        String timeLeftText;

        if (hours < 10) {
            timeLeftText = "0" + hours + " : ";
        } else {
            timeLeftText = hours + " : ";
        }

        if (minutes < 10) {
            timeLeftText += "0" + minutes + " : ";
        } else {
            timeLeftText += minutes + " : ";
        }

        if (seconds < 10) {
            timeLeftText += "0" + seconds + " ";
        } else {
            timeLeftText += seconds + " ";
        }

        countDownText.setText(timeLeftText);

    }
}
