package com.example.gammastoreduplicate;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class BRANDDetailWorkingActivity extends AppCompatActivity {

    CardView card_dest;
    ImageView image_dest;
    boolean exiting;
    ImageView nike_duist;
    ImageView brand_detail_back;
    static int bottomsheet_type_selected;
    ArrayList<BrandProduct> brand_products;
    LinearLayout sort_layout, refine_layout;
    LinearLayout brand_detail_layout;
    Button message_button, notification_button;
    RecyclerView rcv_brand_details;
    CollapsingToolbarLayout collapsing_toolbar;
    ImageView collapsing_imageview;
    Toolbar toolbar_for_collapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branddetail_working);

        card_dest = findViewById(R.id.card_destination);
        image_dest = findViewById(R.id.image_destination);
        nike_duist = findViewById(R.id.collapsing_imageb);
        brand_detail_layout = findViewById(R.id.brand_details_layout);
        exiting = false;
//        collapsing_imageview = findViewById(R.id.collapsing_image);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionValues startValues = new TransitionValues();
            ObjectAnimator animator1 = ObjectAnimator
                    .ofFloat(card_dest, "radius", 0);
            animator1.setDuration(150);
            animator1.start();

            getWindow().setSharedElementEnterTransition(new ChangeBounds()
                    .setDuration(375).setInterpolator(new DecelerateInterpolator()));
            getWindow().getSharedElementEnterTransition()
                    .addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                            if (!exiting) {
                                nike_duist.setVisibility(View.VISIBLE);
                                ObjectAnimator animator = ObjectAnimator
                                        .ofFloat(card_dest, "radius", 0);
                                animator.setDuration(150);
                                animator.start();
                            } else {
                                toggleOut();
                                ObjectAnimator animator = ObjectAnimator
                                        .ofFloat(card_dest, "radius", 40);
                                animator.setDuration(150);
                                animator.start();
                            }
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            toggleIn();
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });
        }


        message_button = findViewById(R.id.brand_detail_message);
        message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BRANDDetailWorkingActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        });
        notification_button = findViewById(R.id.brand_detail_notifications);
        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BRANDDetailWorkingActivity.this, NotificationsActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        });

        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        int brand_selected = getIntent().getIntExtra("brand_type", 0);
//        Toast.makeText(this, "selected: " + brand_selected, Toast.LENGTH_SHORT).show();
        BrandProduct bp1 = null;

        rcv_brand_details = findViewById(R.id.recycler_brand_detail);
        rcv_brand_details.setNestedScrollingEnabled(false);
        switch (brand_selected) {

            case 0:
                bp1 = new BrandProduct("Nike Basketball shoes", R.drawable.nike_basketball_shoes,
                        100, 4.6f, 367);
                image_dest.setBackgroundResource(R.drawable.nike_brand);
                break;
            case 1:
                bp1 = new BrandProduct("Nike Basketball shoes", R.drawable.nike_basketball_shoes,
                        100, 4.6f, 367);
//                    collapsing_imageview.setBackgroundResource(R.drawable.nike_brand);
                image_dest.setBackgroundResource(R.drawable.nike_brand);
                collapsing_toolbar.setTitle(getString(R.string.nike_sport_small));
                break;
            case 2:
                bp1 = new BrandProduct("Calvin Klein Blue Shirt", R.drawable.brand_product_calvin_klein,
                        45, 4.9f, 963);
//                collapsing_imageview.setBackgroundResource(R.drawable.ck_brand);
                image_dest.setBackgroundResource(R.drawable.ck_brand);
                collapsing_toolbar.setTitle(getString(R.string.calvin_klein_small));
                break;
            case 3:
                bp1 = new BrandProduct("Tommy Hilfiger polo shirt", R.drawable.brand_product_tommy_hilfiger,
                        29, 4.7f, 763);
                image_dest.setBackgroundResource(R.drawable.tommyhilfiger_brand);
//                collapsing_imageview.setBackgroundResource(R.drawable.tommyhilfiger_brand);
                collapsing_toolbar.setTitle(getString(R.string.tommy_hilfiger_small));
                break;
            case 4:
                bp1 = new BrandProduct("Apple iPhone X", R.drawable.iphone_x,
                        1145, 4.3f, 3263);
                image_dest.setBackgroundResource(R.drawable.apple_brand);
//                collapsing_imageview.setBackgroundResource(R.drawable.apple_building);
                collapsing_toolbar.setTitle(getString(R.string.apple_tech_small));
                break;
            case 5:
                bp1 = new BrandProduct("Samsung A9s", R.drawable.brnd_product_samsung_a9s,
                        1025, 4.9f, 4963);
                image_dest.setBackgroundResource(R.drawable.samsung_brand);
//                collapsing_imageview.setBackgroundResource(R.drawable.samsung_brand);
                collapsing_toolbar.setTitle(getString(R.string.samsung_tech_small));
                break;
        }

        brand_products = new ArrayList<>();
        brand_products.add(bp1);
        brand_products.add(bp1);
        brand_products.add(bp1);
        brand_products.add(bp1);

        BrandProductAdapter adapter = new BrandProductAdapter(this, brand_products);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv_brand_details.setLayoutManager(manager);
        rcv_brand_details.setAdapter(adapter);

        bottomsheet_type_selected = 0;
        AppBarLayout abl = findViewById(R.id.app_bar_layout);
        AppBarLayout.MarginLayoutParams params = (AppBarLayout.MarginLayoutParams) abl.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        abl.setLayoutParams(params);

        sort_layout = findViewById(R.id.sort_view);
        refine_layout = findViewById(R.id.refine_view);

        sort_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bottomsheet_type_selected = 1;
                    BrandDETAILBottomSheetDialog bottomSheet = new BrandDETAILBottomSheetDialog();
                    bottomSheet.show(getSupportFragmentManager(), "BottomSheet");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        refine_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    bottomsheet_type_selected = 2;
                    BrandDETAILBottomSheetDialog bottomSheet = new BrandDETAILBottomSheetDialog();
                    bottomSheet.show(getSupportFragmentManager(), "BottomSheet");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        brand_detail_back = findViewById(R.id.brand_details_back_arrow1);
        brand_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Transition transition = new CircleToRectTransition();
//            transition.setDuration(500);
//            getWindow().setSharedElementEnterTransition(transition);
//            getWindow().setSharedElementExitTransition(new CircleToRectTransition().setDuration(1500));
//        }


//        image_destination.setBackgroundResource(R.color.colorPrimaryDark);

//        getWindow().setEnterTransition(new Explode());
//
//
//        setEnterSharedElementCallback(new SharedElementCallback() {
//            @Override
//            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//
//            }
//
//            @Override
//            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
//            }
//        });


    @Override
    public void onBackPressed() {
        exiting = true;
        nike_duist.setVisibility(View.INVISIBLE);
        supportFinishAfterTransition();
    }

    private void toggleIn() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition1 = new Slide(Gravity.BOTTOM);
            transition1.setDuration(600);
            transition1.setInterpolator(new DecelerateInterpolator());
            transition1.addTarget(R.id.brand_details_layout);
            NestedScrollView nest = findViewById(R.id.brand_slide_root);
            TransitionManager.beginDelayedTransition(nest, transition1);
            brand_detail_layout.setVisibility(View.VISIBLE);
        }

    }

    private void toggleOut() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Transition transition1 = new Fade();
//            transition1.setDuration(300);
//            transition1.setInterpolator(new DecelerateInterpolator());
//            transition1.addTarget(R.id.brand_details_layout);
//            NestedScrollView nest = findViewById(R.id.brand_slide_root);
//            TransitionManager.beginDelayedTransition(nest, transition1);
//            brand_detail_layout.setVisibility(View.GONE);

        }
    }
