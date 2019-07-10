package com.example.gammastoreduplicate;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDETAILsActivity extends AppCompatActivity {

    CardView card_viewpager;
    ViewPager pdmView;
    ViewPager dialog_imagesView;
    ImageView black_selector, white_selector, blue_selector;
    LinearLayout size_back_1, size_back_2, size_back_3, size_back_4;
    TextView size_text_1, size_text_2, size_text_3, size_text_4;
    LinearLayout color_border_1, color_border_2, color_border_3;
    AlertDialog imageViewDialog;
    GestureDetector tapgestureListener;
    PDViewPagerImageAdapter1 pd_vpAdapter;
    dialogPDViewPagerImageAdapter dialog_pd_vpAdapter;
    LinearLayout pd_chat, pd_pay;

    LinearLayout layout_4Scroll, layout_4_Root;
    ConstraintLayout toolbar_top;

    TextView desc_text;
    TextView desc_view_more;
    ExpandableTextView exp_text;
    ArrayList<ProductClass> top_rated_items;
    ArrayList<ReviewClass> review_items;
    ArrayList<ReviewClass> review_items_shown;
    RecyclerView rcv_reviews_product_detail;
    RecyclerView rcv_top_rated_product_detail;

    TextView image_no;
    TextView product_detail_title;
    TextView product_detail_cost;
    TextView product_detail_rating_score;
    TextView product_detail_title_items_sold;
    ImageView pd_back_arrow, pd_search, pd_cart;
    ProductClass pc;
    boolean isTransitionReady;
    boolean product_exiting;
    boolean isDialogReady;
    static String transitionStringName;

    @Override
    public void onBackPressed() {
        product_exiting = true;
        pdmView.setCurrentItem(0);
        overridePendingTransition(0, 0);
        supportFinishAfterTransition();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainShopFragment.isSharedReady = 1;
                        MainShopFragment.isListSharedReady = 1;
                        MainShopFragment.is3ColSharedReady = 1;
                        MainShopFragment.is2ColSharedReady = 1;
                    }
                }, 400);
            }
        });

//        finish();
//        overridePendingTransition(0, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


//        toolbar_top = findViewById(R.id.toolbar_product_detail);
//        toolbar_top.setTransitionName(Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
        isTransitionReady = false;
        pdmView = findViewById(R.id.viewpager_product_detail);
        ActivityCompat.postponeEnterTransition(this);
//        View decorView = getWindow().getDecorView();
//        decorView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if (isTransitionReady) {
//                    ActivityCompat.startPostponedEnterTransition(ProductDETAILsActivity.this);
//                }
//            }
//        });


        layout_4Scroll = findViewById(R.id.layout_for_scroll_up);
        layout_4_Root = findViewById(R.id.layout_scroll_root);
        isDialogReady = false;
        card_viewpager = findViewById(R.id.card_viewpager1);
        product_exiting = false;
        transitionStringName = getIntent().getStringExtra("transitionName");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            card_viewpager.setTransitionName(transitionStringName);

//            TransitionManager.beginDelayedTransition(card_viewpager,new TransitionSet()
//                    .addTransition(new ChangeBounds()).setDuration(350));
//            card_viewpager.setRadius(0);

//            getWindow().setAllowEnterTransitionOverlap(false);
//            getWindow().setAllowReturnTransitionOverlap(false);
            TransitionArcMotion motion = new TransitionArcMotion();
            getWindow().setSharedElementEnterTransition(new ChangeBounds()
                    .setDuration(345).setInterpolator(new DecelerateInterpolator()));
            getWindow().getSharedElementEnterTransition()
                    .addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                            if (!product_exiting) {
                                ObjectAnimator animator = ObjectAnimator
                                        .ofFloat(card_viewpager, "radius", 40, 0);
                                animator.setDuration(245);
                                animator.start();
                            } else {
//                                toggleOut();
//                                Toast.makeText(ProductDETAILsActivity.this, "From Transition", Toast.LENGTH_SHORT).show();
                                card_viewpager.setTransitionName(transitionStringName);
                                card_viewpager.setRadius(40);
                                ObjectAnimator animator1 = ObjectAnimator
                                        .ofFloat(card_viewpager, "radius", 0, 40);
                                animator1.setDuration(245);
                                animator1.start();

////                                if (!isFinishing()) {
////
////                                    new Timer().schedule(new TimerTask() {
////                                        @Override
////                                        public void run() {
//////                                            MainShopFragment.isSharedReady = 1;
////                                            isDialogReady = true;
////                                        }
////                                    }, 400);
////                                    Toast.makeText(ProductDETAILsActivity.this, "ready", Toast.LENGTH_SHORT).show();
////                                }
                            }
                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            toggleInUp();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
//                                            MainShopFragment.isSharedReady = 1;
//                                            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                    pdmView.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            tapgestureListener.onTouchEvent(event);
                                            return false;
                                        }
                                    });
                                }
                            }, 1400);
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


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            postponeEnterTransition();
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.move));
//        }

//        Toast.makeText(this, "name: " + transitionStringName, Toast.LENGTH_SHORT).show();

        pd_chat = findViewById(R.id.product_detail_chat);
        pd_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDETAILsActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ProductDETAILsActivity.this, android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });

        pd_pay = findViewById(R.id.product_detail_pay_button);
        pd_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean already_in_cart = false;

                for (int i = 0; i < MainActivity.cart_items.size(); i++) {
                    if (pc.product_name.equals(MainActivity.cart_items.get(i).product_name)) {
                        already_in_cart = true;
                        break;
                    } else {
                        already_in_cart = false;
                    }
                }

                if (!already_in_cart) {
                    MainActivity.cart_items.add(pc);
                }

                Intent i = new Intent(ProductDETAILsActivity.this, DeliveryActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ProductDETAILsActivity.this, android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });


        tapgestureListener = new GestureDetector(this, new TapGestureListener());

        pd_cart = findViewById(R.id.product_detail_add_to_cart_view);
        pd_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean already_added_to_cart = false;
                for (int i = 0; i < MainActivity.cart_items.size(); i++) {
                    if (pc.product_name.equals(MainActivity.cart_items.get(i).product_name)) {
                        already_added_to_cart = true;
                        break;
                    }
                }

                if (!already_added_to_cart) {
                    MainActivity.cart_items.add(pc);
                    Toast.makeText(ProductDETAILsActivity.this, getString(R.string.item_added), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDETAILsActivity.this, getString(R.string.item_already_cart), Toast.LENGTH_SHORT).show();
                }
            }
        });

        pd_back_arrow = findViewById(R.id.product_detail_back_arrow);
        pd_search = findViewById(R.id.product_detail_search);

        pd_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pd_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDETAILsActivity.this, SearchActivity.class);
                i.putExtra("entry", "pd");
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ProductDETAILsActivity.this, android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });

        product_detail_title = findViewById(R.id.product_detail_title);
        product_detail_cost = findViewById(R.id.product_detail_cost);
        product_detail_rating_score = findViewById(R.id.product_detail_rating_score);
        product_detail_title_items_sold = findViewById(R.id.product_detail_items_sold);

        ProductClass pc1;

        int product_position = getIntent().getIntExtra("item_position", 0);

        pc = MainShopFragment.recommended_items.get(product_position);

        product_detail_title.setText(pc.product_name);
        product_detail_cost.setText("$" + pc.cost_in_$);
        product_detail_rating_score.setText(String.valueOf(pc.rating));
        product_detail_title_items_sold.setText(String.valueOf(pc.items_sold));

        top_rated_items = new ArrayList<>();
        populateTopRatedItems();
        rcv_top_rated_product_detail = findViewById(R.id.recycler_top_rated_product_detail);

        pd_vpAdapter = new PDViewPagerImageAdapter1(this, pc.product_bitmaps);
        pdmView.setAdapter(pd_vpAdapter);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            startPostponedEnterTransition();
//        }

        dialog_pd_vpAdapter = new dialogPDViewPagerImageAdapter(this, pc.product_bitmaps);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.image_zoomer, null);
        dialog_imagesView = dialogView.findViewById(R.id.product_images_viewpager1);
        dialogBuilder.setView(dialogView);
        image_no = dialogView.findViewById(R.id.image_number);
//
        imageViewDialog = dialogBuilder.create();
        imageViewDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        image_no.setText(1 + "/" + pc.product_bitmaps.size());
        dialog_imagesView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int i1 = i + 1;
                image_no.setText(i1 + "/" + pc.product_bitmaps.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        pdmView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                tapgestureListener.onTouchEvent(event);
//                return false;
//            }
//        });
//        pdmView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ProductDETAILsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//                dialog_imagesView.setAdapter(pd_vpAdapter);
//                imageViewDialog.show();
//            }
//        });

        CategoryPopularListAdapter adapter2 = new CategoryPopularListAdapter(this, top_rated_items);
        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcv_top_rated_product_detail.setLayoutManager(manager2);
        rcv_top_rated_product_detail.setAdapter(adapter2);

        rcv_reviews_product_detail = findViewById(R.id.recycler_reviews_product_detail);

        review_items = new ArrayList<>();
        review_items_shown = new ArrayList<>();
        String review_comments = "Item delivered in good condition. I will recommend to other buyers as well";
        String review_comments_1 = "Item delivered in bad condition. I will not recommend to other buyers";

        Bitmap image_1 = BitmapFactory.decodeResource(getResources(), R.drawable.reviewer_1);
        Bitmap image_2 = BitmapFactory.decodeResource(getResources(), R.drawable.reviewer_2);
        Bitmap image_3 = BitmapFactory.decodeResource(getResources(), R.drawable.reviewer_3);
        Bitmap image_4 = BitmapFactory.decodeResource(getResources(), R.drawable.reviewer_4);
        Bitmap image_5 = BitmapFactory.decodeResource(getResources(), R.drawable.reviewer_5);

        review_items.add(new ReviewClass(image_1, 4.5f, "16 Apr 2019", review_comments));
        review_items.add(new ReviewClass(image_2, 4.2f, "14 Apr 2019", review_comments));
        review_items.add(new ReviewClass(image_3, 3.5f, "12 Apr 2019", review_comments_1));
        review_items.add(new ReviewClass(image_4, 2.0f, "15 Apr 2019", review_comments_1));
        review_items.add(new ReviewClass(image_5, 5.0f, "7 Apr 2019", review_comments));

        if (review_items.size() > 3) {
            review_items_shown.add(review_items.get(0));
            review_items_shown.add(review_items.get(1));
            review_items_shown.add(review_items.get(2));
            REVIEWProductDetailAdapter adapter = new REVIEWProductDetailAdapter(this, review_items_shown);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            rcv_reviews_product_detail.setLayoutManager(manager);
            rcv_reviews_product_detail.setAdapter(adapter);
        } else {
            REVIEWProductDetailAdapter adapter1 = new REVIEWProductDetailAdapter(this, review_items);
            LinearLayoutManager manager1 = new LinearLayoutManager(this);
            rcv_reviews_product_detail.setLayoutManager(manager1);
            rcv_reviews_product_detail.setAdapter(adapter1);
        }

        exp_text = findViewById(R.id.expand_text_view);

        exp_text.setText(getString(R.string.lorem_ipsum_text));

        TextDrawable drawable_black = TextDrawable.builder().buildRound("", Color.parseColor("#000000"));
        TextDrawable drawable_white = TextDrawable.builder().buildRound("", Color.parseColor("#ffffff"));
        TextDrawable drawable_blue = TextDrawable.builder().buildRound("", Color.parseColor("#004fff"));

        black_selector = findViewById(R.id.black_selector);
        white_selector = findViewById(R.id.white_selector);
        blue_selector = findViewById(R.id.blue_selector);

        color_border_1 = findViewById(R.id.color_border_1);
        color_border_2 = findViewById(R.id.color_border_2);
        color_border_3 = findViewById(R.id.color_border_3);

        black_selector.setImageDrawable(drawable_black);
        white_selector.setImageDrawable(drawable_white);
        blue_selector.setImageDrawable(drawable_blue);

        size_back_1 = findViewById(R.id.size_back_1);
        size_back_2 = findViewById(R.id.size_back_2);
        size_back_3 = findViewById(R.id.size_back_3);
        size_back_4 = findViewById(R.id.size_back_4);

        size_text_1 = findViewById(R.id.size_text_1);
        size_text_2 = findViewById(R.id.size_text_2);
        size_text_3 = findViewById(R.id.size_text_3);
        size_text_4 = findViewById(R.id.size_text_4);

        //<editor-fold desc="size_back_1 click Listener">

        size_back_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size_back_1.setBackgroundResource(R.drawable.curved_view_sharp_theme_background);
                size_text_1.setTextColor(getResources().getColor(R.color.white_color));

                size_back_2.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_2.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_3.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_3.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_4.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_4.setTextColor(getResources().getColor(R.color.themeColor));

            }
        });
        //</editor-fold>

        //<editor-fold desc="size_back_2 clickListener">
        size_back_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size_back_2.setBackgroundResource(R.drawable.curved_view_sharp_theme_background);
                size_text_2.setTextColor(getResources().getColor(R.color.white_color));

                size_back_1.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_1.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_3.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_3.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_4.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_4.setTextColor(getResources().getColor(R.color.themeColor));

            }
        });
        //</editor-fold>

        //<editor-fold desc="size_back_3 clickListener">
        size_back_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size_back_3.setBackgroundResource(R.drawable.curved_view_sharp_theme_background);
                size_text_3.setTextColor(getResources().getColor(R.color.white_color));

                size_back_2.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_2.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_1.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_1.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_4.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_4.setTextColor(getResources().getColor(R.color.themeColor));

            }
        });
        //</editor-fold>

        //<editor-fold desc="size_back_4 clickListener">
        size_back_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size_back_4.setBackgroundResource(R.drawable.curved_view_sharp_theme_background);
                size_text_4.setTextColor(getResources().getColor(R.color.white_color));

                size_back_2.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_2.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_3.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_3.setTextColor(getResources().getColor(R.color.themeColor));

                size_back_1.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                size_text_1.setTextColor(getResources().getColor(R.color.themeColor));

            }
        });
        //</editor-fold>

        color_border_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_border_1.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                color_border_2.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);
                color_border_3.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);

            }
        });

        color_border_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_border_2.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                color_border_1.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);
                color_border_3.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);

            }
        });

        color_border_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_border_3.setBackgroundResource(R.drawable.curved_view_sharp_theme_border);
                color_border_2.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);
                color_border_1.setBackgroundResource(R.drawable.curved_view_sharp_grey_border);

            }
        });


    }

    private void toggleInUp() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition transition1 = new Slide(Gravity.BOTTOM);
            transition1.setDuration(600);
            transition1.setInterpolator(new DecelerateInterpolator());
            transition1.addTarget(R.id.layout_for_scroll_up);
            NestedScrollView nest = findViewById(R.id.product_scroll);
            TransitionManager.beginDelayedTransition(nest, transition1);
            layout_4Scroll.setVisibility(View.VISIBLE);
        }

    }

    private void populateTopRatedItems() {

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


            top_rated_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            top_rated_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            top_rated_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));

            top_rated_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            top_rated_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));

            top_rated_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));


        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        public boolean onSingleTapConfirmed(MotionEvent e) {


//            Toast.makeText(ProductDETAILsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//            int selected_page = mView.getCurrentItem();
//            MensItemDetails mid = list_mid.get(selected_page);
//            ProductFragment prd = new ProductFragment();
//            Bundle b = new Bundle();
//            b.putParcelable("object", mid);
//            prd.setArguments(b);
//            ProductFragment.isProductFragment = true;
//            isReturned = true;
//            FragmentTransaction ft = getActivity().getSupportFragmentManager()
//                    .beginTransaction();
////            ft.setCustomAnimations(0,R.anim.exit);
//            ft.replace(R.id.fragment_container, prd)
//                    .addToBackStack(null)
//                    .commit();


//            if (isDialogReady) {
            dialog_imagesView.setAdapter(dialog_pd_vpAdapter);
            int current = pdmView.getCurrentItem();
            int current1 = current + 1;
            dialog_imagesView.setCurrentItem(current);
            image_no.setText(current1 + "/" + pc.product_bitmaps.size());
            imageViewDialog.show();
//            }

            return true;
        }

    }

    public class PDViewPagerImageAdapter1 extends PagerAdapter {

        Context context;
        ArrayList<String> slide_images;

        public PDViewPagerImageAdapter1(Context context, ArrayList<String> slide_images) {
            this.context = context;
            this.slide_images = slide_images;
        }

        @Override
        public int getCount() {
            return slide_images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == ((ImageView) o);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setTransitionName(ProductDETAILsActivity.transitionStringName);
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(slide_images.get(position));

            Glide
                    .with(context)
                    .load(slide_images.get(position))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            isTransitionReady = true;
                            ActivityCompat.startPostponedEnterTransition(ProductDETAILsActivity.this);
                            return false;
                        }
                    })
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .into(imageView);


            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

}