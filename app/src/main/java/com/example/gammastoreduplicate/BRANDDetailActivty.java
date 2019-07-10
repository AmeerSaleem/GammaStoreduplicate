package com.example.gammastoreduplicate;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yalantis.guillotine.util.ActionBarInterpolator;

import net.cachapa.expandablelayout.util.FastOutSlowInInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BRANDDetailActivty extends AppCompatActivity {

    CollapsingToolbarLayout collapsing_toolbar;
    ImageView collapsing_imageview;
    Toolbar toolbar_for_collapse;
    AppBarLayout abl;
    static int bottomsheet_type_selected;
    ArrayList<BrandProduct> brand_products;
    RecyclerView rcv_brand_details;
    ImageView brand_detail_back;
    LinearLayout sort_layout, refine_layout;
    Button message_button, notification_button;
    ImageView nike_dust;
    CardView card_detail;
    boolean exiting;
    LinearLayout brand_detail_layout;

//    @Override
//    public void onBackPressed() {
//        finish();
//        overridePendingTransition(0,R.anim.fade_out);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        card_detail = findViewById(R.id.card_view_target1);
        setContentView(R.layout.activity_branddetail_activty);
        nike_dust = findViewById(R.id.collapsing_imageb);

        brand_detail_layout = findViewById(R.id.brand_details_layout);
        brand_detail_layout.animate()
                .translationY(brand_detail_layout.getHeight())
                .setDuration(300);
        exiting = false;
        collapsing_imageview = findViewById(R.id.collapsing_image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(675).setInterpolator(new LinearOutSlowInInterpolator()));
            getWindow().getSharedElementEnterTransition()
                    .addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {
                            if (!exiting) {
//                                Toast.makeText(BRANDDetailActivty.this, "From Transition Start", Toast.LENGTH_SHORT).show();
                                ObjectAnimator animator = ObjectAnimator
                                        .ofFloat(card_detail, "radius", 0);
                                animator.setDuration(675);
                                animator.start();
                                nike_dust.setVisibility(View.VISIBLE);
                            } else {
                                ObjectAnimator animator = ObjectAnimator
                                        .ofFloat(card_detail, "radius", 40);
                                animator.setDuration(675);
                                animator.start();
                            }
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            brand_detail_layout.setVisibility(View.VISIBLE);
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

//        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(475).setInterpolator(new LinearOutSlowInInterpolator()));
//        setExitSharedElementCallback(new android.app.SharedElementCallback() {
//            @Override
//            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//
//                ImageView sharedImageView = null;
//                for (View view : sharedElements) {
//                    if (view instanceof ImageView) {
//                        sharedImageView = (ImageView) view;
//                        break;
//                    }
//                }
//
//                if (sharedImageView != null) {
//                    sharedImageView.setClipToOutline(true);
//
//                    ObjectAnimator.ofInt(sharedImageView, new Property<ImageView, Integer>(Integer.class, "outlineRadius") {
//                        @Override
//                        public Integer get(ImageView object) {
//                            return 0;
//                        }
//
//                        @Override
//                        public void set(ImageView object, final Integer value) {
//                            object.setOutlineProvider(new ViewOutlineProvider() {
//                                @Override
//                                public void getOutline(View view, Outline outline) {
////                                    outline.setRect(0,0,0,0);
//                                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(),value);
//                                }
//                            });
//                        }
//                    },1500, 0).setDuration(645).start();
//                }
//            }
//
//            @Override
//            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//
//            }
//
//            @Override
//            public void onRejectSharedElements(List<View> rejectedSharedElements) {
//            }
//
//            @Override
//            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//            }
//        });
//
//        setEnterSharedElementCallback(new SharedElementCallback() {
//            @Override
//            public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//                Animation in = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
//                nike_dust.setVisibility(View.VISIBLE);
//                nike_dust.startAnimation(in);
//                ImageView sharedImageView = null;
//
//                for (View view : sharedElements) {
//                    if (view instanceof ImageView) {
//                        sharedImageView = (ImageView) view;
//                        break;
//                    }
//                }
//
//                if (sharedImageView != null) {
//                    sharedImageView.setClipToOutline(true);
//
//                    ObjectAnimator.ofInt(sharedImageView, new Property<ImageView, Integer>(Integer.class, "outlineRadius") {
//                        @Override
//                        public Integer get(ImageView object) {
//                            return 0;
//                        }
//
//                        @Override
//                        public void set(ImageView object, final Integer value) {
//                            object.setOutlineProvider(new ViewOutlineProvider() {
//                                @Override
//                                public void getOutline(View view, Outline outline) {
//                                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
//                                }
//                            });
//                        }
//                    },  0).setDuration(645).start();
//                }
//            }
//
//            @Override
//            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//                Animation in = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
//                nike_dust.setVisibility(View.VISIBLE);
//                nike_dust.startAnimation(in);
////                ImageView sharedImageView = null;
////
////                for (View view : sharedElements) {
////                    if (view instanceof ImageView) {
////                        sharedImageView = (ImageView) view;
////                        break;
////                    }
////                }
////
////                if (sharedImageView != null) {
////                    sharedImageView.setClipToOutline(true);
////
////                    ObjectAnimator.ofInt(sharedImageView, new Property<ImageView, Integer>(Integer.class, "outlineRadius") {
////                        @Override
////                        public Integer get(ImageView object) {
////                            return 0;
////                        }
////
////                        @Override
////                        public void set(ImageView object, final Integer value) {
////                            object.setOutlineProvider(new ViewOutlineProvider() {
////                                @Override
////                                public void getOutline(View view, Outline outline) {
////                                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
////                                }
////                            });
////                        }
////                    },  0).setDuration(645).start();
////                }
////
//
//
//
//
////                ImageView nike_dust = findViewById(R.id.collapsing_imageb);
//
////                if(Build.VERSION.SDK_INT >= 19){
////                    TransitionManager.beginDelayedTransition((ViewGroup) nike_dust.getParent());
////                }
////                nike_dust.animate()
////                        .translationY(nike_dust.getHeight())
////                        .alpha(1.0f)
////                        .setDuration(800)
////                .start();
////                nike_dust.animate().alpha(1.0f).start();
//
////                nike_dust.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onRejectSharedElements(List<View> rejectedSharedElements) {
//            }
//
//            @Override
//            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//            }
//
//
//        });

            message_button = findViewById(R.id.brand_detail_message);
            message_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BRANDDetailActivty.this, ChatActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                    startActivity(intent, bundle);
                }
            });
            notification_button = findViewById(R.id.brand_detail_notifications);
            notification_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BRANDDetailActivty.this, NotificationsActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                    startActivity(intent, bundle);
                }
            });
            brand_detail_back = findViewById(R.id.brand_details_back_arrow);
            brand_detail_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
            int brand_selected = getIntent().getIntExtra("brand_type", 0);
            BrandProduct bp1 = null;

            rcv_brand_details = findViewById(R.id.recycler_brand_detail);
            rcv_brand_details.setNestedScrollingEnabled(false);
            switch (brand_selected) {

                case 0:
                    bp1 = new BrandProduct("Nike Basketball shoes", R.drawable.nike_basketball_shoes,
                            100, 4.6f, 367);
                    break;
                case 1:
                    bp1 = new BrandProduct("Nike Basketball shoes", R.drawable.nike_basketball_shoes,
                            100, 4.6f, 367);
//                    collapsing_imageview.setBackgroundResource(R.drawable.nike_brand);
                    collapsing_toolbar.setTitle(getString(R.string.nike_sport_small));
                    break;
                case 2:
                    bp1 = new BrandProduct("Calvin Klein Blue Shirt", R.drawable.brand_product_calvin_klein,
                            45, 4.9f, 963);
                    collapsing_imageview.setBackgroundResource(R.drawable.ck_brand);
                    collapsing_toolbar.setTitle(getString(R.string.calvin_klein_small));
                    break;
                case 3:
                    bp1 = new BrandProduct("Tommy Hilfiger polo shirt", R.drawable.brand_product_tommy_hilfiger,
                            29, 4.7f, 763);
                    collapsing_imageview.setBackgroundResource(R.drawable.tommyhilfiger_brand);
                    collapsing_toolbar.setTitle(getString(R.string.tommy_hilfiger_small));
                    break;
                case 4:
                    bp1 = new BrandProduct("Apple iPhone X", R.drawable.iphone_x,
                            1145, 4.3f, 3263);
                    collapsing_imageview.setBackgroundResource(R.drawable.apple_building);
                    collapsing_toolbar.setTitle(getString(R.string.apple_tech_small));
                    break;
                case 5:
                    bp1 = new BrandProduct("Samsung A9s", R.drawable.brnd_product_samsung_a9s,
                            1025, 4.9f, 4963);
                    collapsing_imageview.setBackgroundResource(R.drawable.samsung_brand);
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
            abl = findViewById(R.id.app_bar_layout);
//            AppBarLayout.MarginLayoutParams params = (AppBarLayout.MarginLayoutParams) abl.getLayoutParams();
//            params.setMargins(0, 0, 0, 0);
//            abl.setLayoutParams(params);
            toolbar_for_collapse = findViewById(R.id.toolbar_for_collapse);
//        toolbar_for_collapse.setContentInsetsAbsolute(0,0);

//        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsingToolbarTitleCollapsed);
//        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.CollapsingToolbarTitleExpanded);

            Typeface typeFace = ResourcesCompat.getFont(this, R.font.ageopersonaluse);
            collapsing_toolbar.setCollapsedTitleTypeface(typeFace);
            collapsing_toolbar.setExpandedTitleTypeface(typeFace);

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

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Slide slide = new Slide(Gravity.BOTTOM);
//            slide.addTarget(R.id.collapsing_image);
//            slide.setInterpolator(AnimationUtils.loadInterpolator(this,android.R.interpolator.bounce));
//            slide.setDuration(500);
//            getWindow().setExitTransition(slide);
//        }

        }
}

        @Override
        public void onBackPressed () {
            exiting = true;
            nike_dust.setVisibility(View.INVISIBLE);
            supportFinishAfterTransition();
        }
    }
