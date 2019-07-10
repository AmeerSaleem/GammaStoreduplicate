package com.example.gammastoreduplicate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ethanhua.skeleton.SkeletonScreen;

import java.util.ArrayList;

public class PromotionActivity extends AppCompatActivity {

    ArrayList<ProductClass> promotional_items;
    RecyclerView rcv_promotion;
    ImageView promotional_back_button;
    LinearLayout shimmer;
    static SkeletonScreen skeletonScreen;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

//        shimmer = findViewById(R.id.shimmer_include_layout);


        promotional_back_button = findViewById(R.id.week_promotion_back_arrow);
        promotional_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rcv_promotion = findViewById(R.id.recycler_promo_activity);
        promotional_items = new ArrayList<>();
        populatePromoItems();

        int noOfRows;
        if (promotional_items.size() % 2 == 1) {
            noOfRows = (promotional_items.size() / 2) + 1;
        } else {
            noOfRows = (promotional_items.size() / 2);
        }

        final MyPromoActivityAdapter adapter = new MyPromoActivityAdapter(this, promotional_items);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rcv_promotion.setLayoutManager(manager);
        rcv_promotion.hasFixedSize();
//        skeletonScreen = Skeleton.bind(rcv_promotion)
//                .adapter(adapter)
//                .load(R.layout.row_promotion_activity_placeholder_new_trial)
//                .count(noOfRows)
//                .color(R.color.white_color)
//                .duration(1200)
//                .show();

        rcv_promotion.setAdapter(adapter);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                populatePromoItems();
//                adapter.showShimmer = false;
//                adapter.notifyDataSetChanged();
//            }
//        },4000);
//                skeletonScreen.hide();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,R.anim.fade_out);
    }

    private void populatePromoItems() {

        try {
//            ArrayList<Bitmap> produxt_image1 = new ArrayList<>();
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_woman_skirt));
//
//            ArrayList<Bitmap> produxt_image2 = new ArrayList<>();
//            produxt_image2.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_mens_shirt));
//
//            ArrayList<Bitmap> produxt_image3 = new ArrayList<>();
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_gold_pendant));
//
//            ArrayList<Bitmap> produxt_image4 = new ArrayList<>();
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_hand_drill));
//
//            ArrayList<Bitmap> produxt_image5 = new ArrayList<>();
//            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_camera));
//
//            ArrayList<Bitmap> produxt_image6 = new ArrayList<>();
//            produxt_image6.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_apple_watch));
//
//            ArrayList<Bitmap> produxt_image7 = new ArrayList<>();
//            produxt_image7.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_unisex_converse));
//
//            ArrayList<Bitmap> produxt_image8 = new ArrayList<>();
//            produxt_image8.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_asics_backpack));


            ArrayList<String> produxt_image1 = new ArrayList<>();

            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_woman_skirt));
//            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_woman_skirt));

            ArrayList<String> produxt_image2 = new ArrayList<>();
            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176.jpg"));
            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176_3.jpg"));
            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176_1.jpg"));

            ArrayList<String> produxt_image3 = new ArrayList<>();
            produxt_image3.add(new String("http://cfs3.monicavinader.com/images/detail/9466209-gp-bm-sixt-gro.jpg"));
            produxt_image3.add(new String("http://cfs3.monicavinader.com/images/detail/9466209-gp-bm-sixt-gro.jpg"));
            produxt_image3.add(new String("http://cfs3.monicavinader.com/images/detail/9466209-gp-bm-sixt-gro.jpg"));
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_gold_pendant));
//            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_gold_pendant));

            ArrayList<String> produxt_image4 = new ArrayList<>();
            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_hand_drill));
//            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_hand_drill));

            ArrayList<String> produxt_image5 = new ArrayList<>();
            produxt_image5.add(new String("https://explorecams.com/cameras/canon-eos-100d.jpg"));
            produxt_image5.add(new String("https://explorecams.com/cameras/canon-eos-100d.jpg"));
            produxt_image5.add(new String("https://explorecams.com/cameras/canon-eos-100d.jpg"));
//            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_camera));
//            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_camera));

            ArrayList<String> produxt_image6 = new ArrayList<>();
            produxt_image6.add(new String("https://www.mobile57.com/products/38mm_Silver_Aluminum_Case_with_Orange_Sport_Band.jpg"));
            produxt_image6.add(new String("https://www.mobile57.com/products/38mm_Silver_Aluminum_Case_with_Orange_Sport_Band.jpg"));
            produxt_image6.add(new String("https://www.mobile57.com/products/38mm_Silver_Aluminum_Case_with_Orange_Sport_Band.jpg"));
//            produxt_image6.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_apple_watch));
//            produxt_image6.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_apple_watch));

            ArrayList<String> produxt_image7 = new ArrayList<>();
            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
//            produxt_image7.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_unisex_converse));
//            produxt_image7.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_unisex_converse));

            ArrayList<String> produxt_image8 = new ArrayList<>();
            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
//            produxt_image8.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_asics_backpack));
//            produxt_image8.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_asics_backpack));


            promotional_items.add(new ProductClass("Womens woolen skirt",
                    produxt_image1, 300, 450, 203, (float) 4.5, 11, 25, "United Kingdom"));

            promotional_items.add(new ProductClass("Mens regular-fit shirt",
                    produxt_image2, 1200, 1450, 116, (float) 4.7, 16, 9, "France"));

            promotional_items.add(new ProductClass("Gold necklace with emerald diamond",
                    produxt_image3, 400, 550, 58, (float) 5.0, 19, 14, "United Kingdom"));

            promotional_items.add(new ProductClass("Multi-purpose hand drill",
                    produxt_image4, 40, 60, 431, (float) 4.6, 1031, 45, "United Kingdom"));

            promotional_items.add(new ProductClass("Canon Camera EOS 750D",
                    produxt_image5, 70, 90, 136, (float) 4.6, 232, 74, "United Kingdom"));

            promotional_items.add(new ProductClass("Apple sports watch",
                    produxt_image6, 70, 90, 736, (float) 4.1, 232, 74, "United Kingdom"));


            promotional_items.add(new ProductClass("All-star Unisex Converse",
                    produxt_image7, 70, 90, 948, (float) 4.8, 232, 74, "United Kingdom"));


            promotional_items.add(new ProductClass("Asics camping backpack",
                    produxt_image8, 70, 90, 423, (float) 4.3, 232, 74, "United Kingdom"));


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}