package com.example.gammastoreduplicate;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CategoryActivity extends AppCompatActivity {

    ViewPager catView;
    int currentPage;
    ArrayList category_discount_items;
    ArrayList category_popular_items;
    ArrayList category_new_items;
    RecyclerView rcv_category_discount;
    RecyclerView rcv_category_popular;
    RecyclerView rcv_category_new;
    ImageView category_search;
    ImageView category_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rcv_category_discount = findViewById(R.id.recycler_category_discount);
        rcv_category_popular = findViewById(R.id.recycler_category_popular);
        rcv_category_new = findViewById(R.id.recycler_category_new);
        category_discount_items = new ArrayList();
        category_popular_items = new ArrayList();
        category_new_items = new ArrayList();
        populateCategoryDiscount();
        populateCategoryPopular();
        populateCategoryNew();

        CategoryDiscountListAdapter adapter = new CategoryDiscountListAdapter(this,category_discount_items);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcv_category_discount.setLayoutManager(manager);
        rcv_category_discount.setAdapter(adapter);

        CategoryPopularListAdapter adapter1 = new CategoryPopularListAdapter(this,category_popular_items);
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcv_category_popular.setLayoutManager(manager1);
        rcv_category_popular.setAdapter(adapter1);

        CategoryPopularListAdapter adapter2 = new CategoryPopularListAdapter(this,category_new_items);
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcv_category_new.setLayoutManager(manager2);
        rcv_category_new.setAdapter(adapter2);

        category_back_button = findViewById(R.id.category_back_arrow);

        category_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        category_search = findViewById(R.id.category_search);

        category_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this,SearchActivity.class);
                intent.putExtra("entry","category");
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in,android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        });

        catView = findViewById(R.id.cat_viewpager);
        final ArrayList<Integer> slide_images_id = new ArrayList<>();
        slide_images_id.add(R.drawable.viewpager_pic1);
        slide_images_id.add(R.drawable.viewpager_pic2);
        slide_images_id.add(R.drawable.viewpager_pic3);
        slide_images_id.add(R.drawable.viewpager_pic4);

        ViewPagerImageAdapter vpAdapter = new ViewPagerImageAdapter(getApplicationContext(), slide_images_id);
        catView.setAdapter(vpAdapter);

        currentPage = catView.getCurrentItem();
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == slide_images_id.size()) {
                    currentPage = 0;
                }
                catView.setCurrentItem(currentPage++, true);
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 0, 1500);

        catView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                currentPage = catView.getCurrentItem();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void populateCategoryNew() {



        try {
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

            category_new_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));

            category_new_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            category_new_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            category_new_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            category_new_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));



//            category_discount_items.add(new ProductClass("Navy Blue sleeveless silk dress",
//                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void populateCategoryDiscount() {

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

            category_discount_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));

            category_discount_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            category_discount_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            category_discount_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            category_discount_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));
            category_discount_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void populateCategoryPopular() {

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


            category_popular_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            category_popular_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            category_popular_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));

            category_popular_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            category_popular_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));

            category_popular_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));


        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
//        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
//                android.R.anim.fade_in,android.R.anim.fade_out).toBundle();
//        startActivity(intent, bundle);

        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }
}
