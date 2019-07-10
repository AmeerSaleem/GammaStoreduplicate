package com.example.gammastoreduplicate;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DummyFragment extends Fragment {

    private TextView countDownText;
    private CountDownTimer  countDownTimer;
    private long timeInMilliSeconds = 86400000;

    ArrayList<ProductClass> sale_items;
    RecyclerView rcv_flash;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_dummy,container,false);

        countDownText = v.findViewById(R.id.countdown_time);
        startTimer();

        sale_items = new ArrayList<>();
        populateSaleItems();

        rcv_flash = v.findViewById(R.id.recycler_flash_sale);

        MyFlashSaleAdapter adapter = new MyFlashSaleAdapter(getContext(),sale_items);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcv_flash.setLayoutManager(manager);
        rcv_flash.setAdapter(adapter);

        return v;
    }

    private void populateSaleItems() {
//        ArrayList<Bitmap> produxt_image1 = new ArrayList<>();
//        produxt_image1.add(BitmapFactory.decodeResource(getResources(),R.drawable.product_image_acer_helios_x350));
//
//        ArrayList<Bitmap> produxt_image2 = new ArrayList<>();
//        produxt_image2.add(BitmapFactory.decodeResource(getResources(),R.drawable.product_image_dress));
//
//        ArrayList<Bitmap> produxt_image3 = new ArrayList<>();
//        produxt_image3.add(BitmapFactory.decodeResource(getResources(),R.drawable.product_image_flare_s7));
//
//        ArrayList<Bitmap> produxt_image4 = new ArrayList<>();
//        produxt_image4.add(BitmapFactory.decodeResource(getResources(),R.drawable.product_image_book2));
//
//        ArrayList<Bitmap> produxt_image5 = new ArrayList<>();
//        produxt_image5.add(BitmapFactory.decodeResource(getResources(),R.drawable.product_image_game_2));


        ArrayList<String> produxt_image1 = new ArrayList<>();
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_acer_helios_x350));
        produxt_image1.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));


        ArrayList<String> produxt_image2 = new ArrayList<>();
//            produxt_image2.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_dress));
        produxt_image2.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));

        ArrayList<String> produxt_image3 = new ArrayList<>();
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_flare_s7));
        produxt_image3.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));

        ArrayList<String> produxt_image4 = new ArrayList<>();
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_book2));
        produxt_image4.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));

        ArrayList<String> produxt_image5 = new ArrayList<>();
//            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.product_image_game_2));
        produxt_image5.add(new String("https://static.techspot.com/images/products/2017/laptops/org/2018-05-10-product-14.jpg"));



        sale_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                produxt_image1,1500,2100,4,11,25,"United Kingdom"));

        sale_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                produxt_image2,1200,1450,5,16,9,"France"));

        sale_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                produxt_image3,400,550,4,19,14,"United Kingdom"));

        sale_items.add(new ProductClass("The Godfather by Mario Puzo",
                produxt_image4,40,60,5,1031,45,"United Kingdom"));

        sale_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                produxt_image5,70,90,4,232,74,"United Kingdom"));

    }

    private void startTimer() {

        countDownTimer = new CountDownTimer(timeInMilliSeconds,1000) {
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

    public void updateTimer(){
        int hours = (int) timeInMilliSeconds/3600000;
        int minutes = (int) timeInMilliSeconds % 3600000 / 60000;
        int seconds = (int) timeInMilliSeconds % 3600000 % 60000 /1000;

        String timeLeftText;

        if(hours < 10){
            timeLeftText = "0" + hours+" : ";
        }
        else{
            timeLeftText = hours + " : ";
        }

        if(minutes < 10){
            timeLeftText += "0" + minutes+" : ";
        }
        else{
            timeLeftText += minutes + " : ";
        }

        if(seconds < 10){
            timeLeftText += "0" + seconds + " ";
        }
        else{
            timeLeftText += seconds + " ";
        }

        countDownText.setText(timeLeftText);

    }
}
