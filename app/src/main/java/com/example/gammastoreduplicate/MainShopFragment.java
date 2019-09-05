package com.example.gammastoreduplicate;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.circularreveal.cardview.CircularRevealCardView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainShopFragment extends Fragment {

    ViewPager mView;
    int currentPage;
    int recommended_view_selected = 0;
    RecyclerView rc_promo;
    ArrayList<ClassPromotion> promo_list;
    ArrayList<String> promo_list2;
    ArrayList<ProductClass> sale_items;
    static ArrayList<ProductClass> recommended_items;
    TextView recommended_label;
    LinearLayout category_layout;
    CardView recommended_card_view, recommended_2_column_view, recommended_list_view, recommended_3_column_view;
    ImageView view_2col, view_3col, view_card, view_list_view;
    MyRecommendedAdapter adapter2;
    static CardView card_shared;

    private TextView countDownText;
    private CountDownTimer countDownTimer;
    private long timeInMilliSeconds = 86400000;
    static int isSharedReady = 1;
    static int isListSharedReady = 1;
    static int is3ColSharedReady = 1;
    static int is2ColSharedReady = 1;

    RecyclerView rcv_flash, rcv_recommended, rcv_item_TEMP;
    boolean pendingIntroAnimation;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = null;
        try {


            v = inflater.inflate(R.layout.fragment_shop_main, container, false);
            recommended_label = v.findViewById(R.id.recommended_label);
            rc_promo = v.findViewById(R.id.recycler_promotion);
            promo_list = new ArrayList<>();
            promo_list2 = new ArrayList<>();
            promoPopulate();
            mView = v.findViewById(R.id.viewpager);
            final ArrayList<Integer> slide_images_id = new ArrayList<>();
            slide_images_id.add(R.drawable.front_view9);
            slide_images_id.add(R.drawable.front_view1);
            slide_images_id.add(R.drawable.front_view29a);
            slide_images_id.add(R.drawable.front_view5c);

            category_layout = v.findViewById(R.id.category_section);
            category_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CategoryActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                    intent.putExtra("entry", "main");
                    startActivity(intent, bundle);
                }
            });

            recommended_card_view = v.findViewById(R.id.recommended_view_card);
            recommended_list_view = v.findViewById(R.id.recommended_view_listview);
            recommended_2_column_view = v.findViewById(R.id.recommended_view_2col);
            recommended_3_column_view = v.findViewById(R.id.recommended_view_3col);

            view_2col = v.findViewById(R.id.ic_2col_view);
            view_3col = v.findViewById(R.id.ic_3col_view);
            view_card = v.findViewById(R.id.ic_card_view);
            view_list_view = v.findViewById(R.id.ic_list_view);


            ViewPagerImageAdapter vpAdapter = new ViewPagerImageAdapter(getContext(), slide_images_id);
            mView.setAdapter(vpAdapter);

            currentPage = mView.getCurrentItem();
            final Handler handler = new Handler();

            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage == slide_images_id.size()) {
                        currentPage = 0;
                    }
                    mView.setCurrentItem(currentPage++, true);
                }
            };

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 0, 1500);

            mView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                    currentPage = mView.getCurrentItem();
                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

            MyPromoAdapter adapter0 = new MyPromoAdapter(getContext(), promo_list);
            adapter0.setOnClickListener(new MyPromoAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(), PromotionActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                            android.R.anim.fade_out).toBundle();
                    startActivity(intent, bundle);
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rc_promo.setLayoutManager(manager);
            rc_promo.setAdapter(adapter0);
//            startIntroAnimation(rc_promo);

            countDownText = v.findViewById(R.id.countdown_time);
            startTimer();
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
            sale_items = new ArrayList<>();
            populateSaleItems();
//                }
//            });
//            thread.start();
            rcv_flash = v.findViewById(R.id.recycler_flash_sale);

            MyFlashSaleAdapter adapter1 = new MyFlashSaleAdapter(getContext(), sale_items);
            adapter1.setOnClickListener(new MyFlashSaleAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent1 = new Intent(getActivity(), FlashSaleActivity.class);
                    Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                            android.R.anim.fade_out).toBundle();
                    startActivity(intent1, bundle1);
                }
            });
            LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rcv_flash.setLayoutManager(manager1);
            rcv_flash.setAdapter(adapter1);
//            Thread thread1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
            recommended_items = new ArrayList<>();


            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Products");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    recommended_items.clear();
//                    Product product = dataSnapshot.getValue(Product.class);
                    ArrayList<Product> products_firebase = new ArrayList<>();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        Product product = childSnapshot.getValue(Product.class);
                        products_firebase.add(product);
                        recommended_items.add(new ProductClass(product.productName, product.productImages,
                                (Integer.parseInt(product.productCost.substring(1))), Integer.parseInt(product.productPrevCost.substring(1)), Integer.parseInt(product.itemsSold),
                                Float.parseFloat(product.productRating), Integer.parseInt(product.reviewCount), Integer.parseInt(product.numberAvailable), product.location));
                        adapter2.notifyDataSetChanged();
                    }

//                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    ArrayList<String> produxt_image9 = new ArrayList<>();
//                    produxt_image9 = product.getProductImages();

//                    Toast.makeText(getActivity(),produxt_image9.get(0), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


//
//                }
//            });
//            thread.start();
            populateRecommendedItems();
            rcv_recommended = v.findViewById(R.id.recycler_recommended);
            recommended_2_column_view.setCardBackgroundColor(getResources().getColor(R.color.themeColor));
            view_2col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_2_col_white_24dp));
            recommended_view_selected = 0;

            recommended_2_column_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (recommended_view_selected != 0) {
                        recommended_2_column_view.setCardBackgroundColor(getResources().getColor(R.color.themeColor));
                        recommended_3_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_card_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_list_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));

                        view_2col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_2_col_white_24dp));
                        view_3col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_3_col_black_24dp));
                        view_card.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_card_black_24dp));
                        view_list_view.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_list_black_24dp));

                        adapter2 = new MyRecommendedAdapter(getContext(), recommended_items);
                        adapter2.setOnClickListener(new MyRecommendedAdapter.onItemClickListener() {
                            @Override
                            public void onViewClick(int position, ImageView shared_2col_image) {
                                if (is2ColSharedReady == 1) {
                                    is2ColSharedReady = 0;
                                    int item = (position * 2);
                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                    intent_rec.putExtra("item_position", item);
                                    String transition_name = "small_3col_img" + item;
                                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                                    Pair<View, String> pair = new Pair<>(((View) shared_2col_image), transition_name);
                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair).toBundle();
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_2col_image, transition_name).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                    startActivity(intent_rec, bundle);
                                }

                            }

                            @Override
                            public void onViewClick2(int position, ImageView shared_2col_image) {
                                if (is2ColSharedReady == 1) {
                                    is2ColSharedReady = 0;
                                    int item = (position * 2) + 1;
                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                    intent_rec.putExtra("item_position", item);
                                    String transition_name = "small_3col_img" + item;
                                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                                    Pair<View, String> pair1 = new Pair<>((View) shared_2col_image, transition_name);
                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair1).toBundle();
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_2col_image, transition_name).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                    startActivity(intent_rec, bundle);
                                }
                            }
                        });
                        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                        rcv_recommended.setLayoutManager(manager2);
                        rcv_recommended.setAdapter(adapter2);
                        startIntroAnimation(rcv_recommended);
                        recommended_view_selected = 0;
                    }
                }
            });

            recommended_3_column_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (recommended_view_selected != 1) {

                        recommended_2_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_3_column_view.setCardBackgroundColor(getResources().getColor(R.color.themeColor));
                        recommended_card_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_list_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));

                        view_2col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_2_col_black_24dp));
                        view_3col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_3_col_white_24dp));
                        view_card.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_card_black_24dp));
                        view_list_view.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_list_black_24dp));

                        MyRecommended3ColumnAdapter adapter2 = new MyRecommended3ColumnAdapter(getContext(), recommended_items);
                        adapter2.setOnClickListener(new MyRecommended3ColumnAdapter.onItemClickListener() {
                            @Override
                            public void onItemClick(int position, CardView shared_3col_image) {
                                if (is3ColSharedReady == 1) {
                                    is3ColSharedReady = 0;
                                    int item = (position * 3);
                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                    intent_rec.putExtra("item_position", item);
                                    String transition_name = "small_3col_img" + item;
                                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                                    Pair<View, String> pair31 = new Pair<>(((View) shared_3col_image), transition_name);
                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair31).toBundle();
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_3col_image, transition_name).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                    startActivity(intent_rec, bundle);
                                }
                            }

                            @Override
                            public void onItemClick2(int position, CardView shared_3col_image) {
                                if (is3ColSharedReady == 1) {
                                    is3ColSharedReady = 0;
                                    int item = (position * 3) + 1;
                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                    intent_rec.putExtra("item_position", item);
                                    String transition_name = "small_3col_img" + item;
                                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                                    Pair<View, String> pair32 = new Pair<>(((View) shared_3col_image), transition_name);
                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair32).toBundle();
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_3col_image, transition_name).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                    startActivity(intent_rec, bundle);
                                }
                            }

                            @Override
                            public void onItemClick3(int position, CardView shared_3col_image) {
                                if (is3ColSharedReady == 1) {
                                    is3ColSharedReady = 0;
                                    int item = (position * 3) + 2;
                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                    intent_rec.putExtra("item_position", item);
                                    String transition_name = "small_3col_img" + item;
                                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                                    Pair<View, String> pair33 = new Pair<>(((View) shared_3col_image), transition_name);
                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair33).toBundle();
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_3col_image, transition_name).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                    startActivity(intent_rec, bundle);
                                }
                            }
                        });
                        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                        rcv_recommended.setLayoutManager(manager2);
                        rcv_recommended.setAdapter(adapter2);
                        startIntroAnimation(rcv_recommended);
                        recommended_view_selected = 1;
                    }

                }
            });

            recommended_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recommended_view_selected != 2) {

                        recommended_2_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_3_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_card_view.setCardBackgroundColor(getResources().getColor(R.color.themeColor));
                        recommended_list_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));

                        view_2col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_2_col_black_24dp));
                        view_3col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_3_col_black_24dp));
                        view_card.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_card_white_24dp));
                        view_list_view.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_list_black_24dp));

                        MyRecommendedCardViewAdapter adapter2 = new MyRecommendedCardViewAdapter(getContext(), recommended_items);
                        if (!getActivity().isFinishing()) {
                            adapter2.setOnClickListener(new MyRecommendedCardViewAdapter.onItemClickListener() {
                                @Override
                                public void onItemClick(int position, CardView sharedImage) {
                                    if (isSharedReady == 1) {
                                        try {
                                            isSharedReady = 0;
                                            card_shared = sharedImage;
                                            Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                            intent_rec.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent_rec.putExtra("item_position", position);
                                            String transition_name = "small_img" + position;
                                            Pair<View, String> pair_card = new Pair<>(((View) sharedImage), transition_name);
                                            Pair<View, String> pair_card_tool = new Pair<>(((View) MainActivity.toolbar2share), "toolbarTransitionName");
                                            android.support.v4.util.Pair<CardView, String> p1 = android.support.v4.util.Pair.create(card_shared, transition_name);
                                            android.support.v4.util.Pair<Toolbar, String> p2 = android.support.v4.util.Pair.create(MainActivity.toolbar2share, "toolbarTransitionName");
                                            intent_rec.putExtra("transitionName", "small_img" + position);
//                                Toast.makeText(getContext(), "name: " + transition_name, Toast.LENGTH_SHORT).show();
                                            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair_card_tool, pair_card).toBundle();
//                                        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_shared, transition_name).toBundle();
//                                        View decorView = getActivity().getWindow().getDecorView();
//                                        View statusbar = decorView.findViewById(android.R.id.statusBarBackground);
//                                        View navigationBar = decorView.findViewById(android.R.id.navigationBarBackground);
//                                        View toolbar = decorView.findViewById(R.id.toolbar2);
//                                        List<android.support.v4.util.Pair<View,String>> pairs = new ArrayList<>();
//                                        pairs.add(android.support.v4.util.Pair.create(statusbar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
//                                        pairs.add(android.support.v4.util.Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
//                                        pairs.add(android.support.v4.util.Pair.create(toolbar, "toolbarTransitionName"));
//                                        pairs.add(android.support.v4.util.Pair.create((View)card_shared,transition_name));
//
//                                        android.support.v4.util.Pair<View, String>[] pairs_array = new Pair[]{pairs.get(0),pairs.get(1),pairs.get(2),pairs.get(3)};
//                                        Bundle bundle = ActivityOptionsCompat
//                                                .makeSceneTransitionAnimation(getActivity(), pairs_array)
//                                                .toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this,)
                                            startActivity(intent_rec, bundle);

                                        } catch (WindowManager.BadTokenException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                        rcv_recommended.setLayoutManager(manager2);
                        rcv_recommended.setAdapter(adapter2);
                        startIntroAnimation(rcv_recommended);
                        recommended_view_selected = 2;
                    }
                }
            });

            recommended_list_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (recommended_view_selected != 3) {

//                        Toast.makeText(getActivity(), "Entering body", Toast.LENGTH_SHORT).show();
                        recommended_2_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_3_column_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_card_view.setCardBackgroundColor(getResources().getColor(R.color.white_color));
                        recommended_list_view.setCardBackgroundColor(getResources().getColor(R.color.themeColor));

                        view_2col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_2_col_black_24dp));
                        view_3col.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_3_col_black_24dp));
                        view_card.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_card_black_24dp));
                        view_list_view.setImageDrawable(getResources().getDrawable(R.drawable.ic_view_list_white_24dp));

                        MyRecommendedListViewAdapter adapter2 = new MyRecommendedListViewAdapter(getContext(), recommended_items);
                        adapter2.setOnClickListener(new MyRecommendedListViewAdapter.onItemClickListener() {
                            @Override
                            public void onItemClick(final int position, final ImageView shared_image) {

                                if (isListSharedReady == 1) {

                                    rcv_recommended.smoothScrollToPosition(position);
                                    rcv_recommended.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                        @Override
                                        public boolean onPreDraw() {
                                            rcv_recommended.getViewTreeObserver().removeOnPreDrawListener(this);
                                            isListSharedReady = 0;
                                            ImageView image_share = shared_image;
                                            Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                                            intent_rec.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent_rec.putExtra("item_position", position);
                                            String transition_name = "small_list_img" + position;
                                            intent_rec.putExtra("transitionName", "small_list_img" + position);
                                            Pair<View, String> pair_list = new Pair<>(((View) shared_image), transition_name);
//                                            View navbar = ((View).findViewById(android.R.id.navigationBarBackground));
//                                            Pair<View,String> pair_nav = Pair.create(navbar,Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
//                                            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair_list,pair_nav).toBundle();
                                            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair_list).toBundle();
//                                Bundle bundle = ActivityOptionsCompat.makeCgetActivityustomAnimation(getContext(), android.R.anim.fade_in,
//                                        android.R.anim.fade_out).toBundle();
                                            startActivity(intent_rec, bundle);

                                            return true;
                                        }
                                    });
//                                    isListSharedReady = 0;
//                                    ImageView image_share = shared_image;
//                                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
//                                    intent_rec.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent_rec.putExtra("item_position", position);
//                                    String transition_name = "small_list_img" + position;
//                                    intent_rec.putExtra("transitionName", "small_list_img" + position);
//                                    Pair<View,String> pair_list = new Pair<>(((View) shared_image),transition_name);
//                                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), image_share, transition_name).toBundle();
////                                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
////                                        android.R.anim.fade_out).toBundle();
//                                    startActivity(intent_rec, bundle);
                                }
                            }
                        });
                        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                        rcv_recommended.setLayoutManager(manager2);
                        rcv_recommended.setAdapter(adapter2);
                        startIntroAnimation(rcv_recommended);
                        recommended_view_selected = 3;
                    }
                }
            });

            adapter2 = new MyRecommendedAdapter(getContext(), recommended_items);
            adapter2.setOnClickListener(new MyRecommendedAdapter.onItemClickListener() {
                @Override
                public void onViewClick(int position, ImageView shared_2col_image) {
                    int item = position * 2;
                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                    intent_rec.putExtra("item_position", item);
                    String transition_name = "small_3col_img" + item;
                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                    Pair<View, String> pair21 = new Pair<>(((View) shared_2col_image), transition_name);
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair21).toBundle();
//                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_2col_image, transition_name).toBundle();
//                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                            android.R.anim.fade_out).toBundle();
//                    Bundle bundle= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), )
                    startActivity(intent_rec, bundle);

                }

                @Override
                public void onViewClick2(int position, ImageView shared_2col_image) {

                    int item = (position * 2) + 1;
                    Intent intent_rec = new Intent(getActivity(), ProductDETAILsActivity.class);
                    intent_rec.putExtra("item_position", item);
                    String transition_name = "small_3col_img" + item;
                    intent_rec.putExtra("transitionName", "small_3col_img" + item);
                    Pair<View, String> pair22 = new Pair<>(((View) shared_2col_image), transition_name);
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair22).toBundle();
//                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), shared_2col_image, transition_name).toBundle();
//                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                            android.R.anim.fade_out).toBundle();
                    startActivity(intent_rec, bundle);

                }
            });
            LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
            rcv_recommended.setLayoutManager(manager2);
            rcv_recommended.setAdapter(adapter2);

            if (savedInstanceState == null) {
                pendingIntroAnimation = true;
            }

            if (pendingIntroAnimation) {
                pendingIntroAnimation = false;
                startIntroAnimation(rcv_recommended);
            }

//            recommended_label.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent2 = new Intent(getContext(), ProductDETAILsActivity.class);
//                    Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
//                            android.R.anim.fade_out).toBundle();
//                    startActivity(intent2, bundle1);
//                }
//            });

        } catch (Throwable e) {
            e.printStackTrace();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainShopFragment())
                    .commit();
        }

        return v;
    }


    private void startIntroAnimation(RecyclerView rcv_recommended1) {
        rcv_recommended1.setTranslationX(rcv_recommended1.getWidth());
        rcv_recommended1.setAlpha(1f);
        rcv_recommended1.animate()
                .translationX(0)
                .setDuration(650)
                .alpha(1f)
//                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }


    private void populateRecommendedItems() {

        try {
//            ArrayList<String> produxt_image1 = new ArrayList<>();
//
////            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
//            produxt_image1.add(new String("https://d15udtvdbbfasl.cloudfront.net/catalog/product/large_image/412671_sub1.jpg"));
//            produxt_image1.add(new String("https://d15udtvdbbfasl.cloudfront.net/catalog/product/large_image/412671_sub3.jpg"));
//            produxt_image1.add(new String("https://d15udtvdbbfasl.cloudfront.net/catalog/product/large_image/412671_sub2.jpg"));
////            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
////            produxt_image1.add(new String("http://pagani-co-nz.imgix.net/products/printed-midi-pleat-skirt-blackwhite-main-63823~1551389855.jpg?w=590&h=960&fit=crop&auto=format&bg=ffffff&s=2fa910a1296f5397a758f805b10d05d7"));
////            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_woman_skirt));
////            produxt_image1.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_woman_skirt));
//
//            ArrayList<String> produxt_image2 = new ArrayList<>();
//            produxt_image2.add(new String("https://www.hawesandcurtis.com/images/products/mens-dark-charcoal-twill-slim-fit-suit-JKPRAS01-F46V-116518-800px-1040px.jpg"));
//            produxt_image2.add(new String("https://www.hawesandcurtis.com/images/products/mens-dark-charcoal-twill-slim-fit-suit-JKPRAS01-F46V-116519-800px-1040px.jpg"));
//            produxt_image2.add(new String("https://www.hawesandcurtis.com/images/products/mens-dark-charcoal-twill-slim-fit-suit-JKPRAS01-F46V-116520-800px-1040px.jpg"));
////            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176.jpg"));
////            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176_3.jpg"));
////            produxt_image2.add(new String("http://www.fleetservicing.nz/images/Fashion%20City/Discount%20Special%20offer%20Mens%20fashion%20online%20gap%20slim%20fit%20-%20shirt%20-%20authentic%20medium%20Store%203176_1.jpg"));
//
//            ArrayList<String> produxt_image3 = new ArrayList<>();
//            produxt_image3.add(new String("https://www.dhresource.com/0x0s/f2-albu-g10-M00-E7-88-rBVaWVwFFWaAd4DOAAbikUX8CVo313.jpg/4g-lte-smart-watch-phone-android-7.1.1-quad-core-16gb1gb-5mp-camera-1.6-quot;-ltps-crystal-display-gps-nano-sim-wifi-bt4.0-mic.jpg"));
//            produxt_image3.add(new String("https://www.dhresource.com/0x0s/f2-albu-g9-M01-DF-78-rBVaVVwFFXeACizMAAapPZ6tU1k538.jpg/4g-lte-smart-watch-phone-android-7.1.1-quad-core-16gb1gb-5mp-camera-1.6-quot;-ltps-crystal-display-gps-nano-sim-wifi-bt4.0-mic.jpg"));
//            produxt_image3.add(new String("https://www.dhresource.com/0x0s/f2-albu-g10-M01-4E-C1-rBVaWVwFFVWAU0dwAAWQfr30V5Y519.jpg/4g-lte-smart-watch-phone-android-7.1.1-quad-core-16gb1gb-5mp-camera-1.6-quot;-ltps-crystal-display-gps-nano-sim-wifi-bt4.0-mic.jpg"));
////            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_gold_pendant));
////            produxt_image3.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_gold_pendant));
//
//            ArrayList<String> produxt_image4 = new ArrayList<>();
//            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
//            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
//            produxt_image4.add(new String("http://www.toolreviewcentre.com/wp-content/uploads/2018/05/Dewalt-Drill-300x300.jpg"));
////            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_hand_drill));
////            produxt_image4.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_hand_drill));
//
//            ArrayList<String> produxt_image5 = new ArrayList<>();
//            produxt_image5.add(new String("http://www.nikon.pk/tmp/Asia/4016499630/3857477713/365508689/3015334490/1054978028/1391493119/3353927964/3370915663.png"));
//            produxt_image5.add(new String("http://www.nikon.pk/tmp/Asia/4016499630/3857477713/365508689/3015334490/1054978028/1391493119/3353927964/4055612591.png"));
//            produxt_image5.add(new String("http://www.nikon.pk/tmp/Asia/4016499630/3857477713/365508689/3015334490/1054978028/1391493119/3353927964/196379084.png"));
////            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_camera));
////            produxt_image5.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_camera));
//
//            ArrayList<String> produxt_image6 = new ArrayList<>();
//            produxt_image6.add(new String("https://www.chanel.com/images//t_fashion//q_auto,f_jpg,fl_lossy,dpr_2/w_620/small-flap-bag-with-top-handle-pink-grained-calfskin-gold-tone-metal-grained-calfskin-gold-tone-metal-packshot-default-a57147y83381n0897-8817617895454.jpg"));
//            produxt_image6.add(new String("https://www.chanel.com/images//t_fashion//q_auto,f_jpg,fl_lossy,dpr_2/w_620/small-flap-bag-with-top-handle-pink-grained-calfskin-gold-tone-metal-grained-calfskin-gold-tone-metal-packshot-alternative-a57147y83381n0897-8817620844574.jpg"));
//            produxt_image6.add(new String("https://www.chanel.com/images//t_fashion//q_auto,f_jpg,fl_lossy,dpr_2/w_620/small-flap-bag-with-top-handle-pink-grained-calfskin-gold-tone-metal-grained-calfskin-gold-tone-metal-packshot-other-a57147y83381n0897-8817623924766.jpg"));
////            produxt_image6.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_apple_watch));
////            produxt_image6.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_apple_watch));
//
//            ArrayList<String> produxt_image7 = new ArrayList<>();
//            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
//            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
//            produxt_image7.add(new String("https://assets.jassets.com/w_248,h_338,q_80/assets/images/6974986/2018/8/14/067c0026-92f3-47f3-bbff-cbd7688886241534264038969-White-Sneakers-4691534264038057-1.jpg"));
////            produxt_image7.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_unisex_converse));
////            produxt_image7.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_unisex_converse));
//
//            ArrayList<String> produxt_image8 = new ArrayList<>();
//            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
//            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
//            produxt_image8.add(new String("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/flcpg7aw7blgqorniqry/elemental-kids-backpack-OnTrAEV8.jpg"));
////            produxt_image8.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_asics_backpack));
////            produxt_image8.add(BitmapFactory.decodeResource(getResources(), R.drawable.recommended_asics_backpack));
//
//
//            recommended_items.add(new ProductClass("Womens woolen skirt",
//                    produxt_image1, 300, 450, 203, (float) 4.5, 11, 25, "United Kingdom"));
//
//            recommended_items.add(new ProductClass("Mens regular-fit shirt",
//                    produxt_image2, 1200, 1450, 116, (float) 4.7, 16, 9, "France"));
//
//            recommended_items.add(new ProductClass("4G LTE Smart Watch Phone Android",
//                    produxt_image3, 400, 550, 58, (float) 5.0, 19, 14, "United Kingdom"));
//
//            recommended_items.add(new ProductClass("Multi-purpose hand drill",
//                    produxt_image4, 40, 60, 431, (float) 4.6, 1031, 45, "United Kingdom"));
//
//            recommended_items.add(new ProductClass("Nikon D3500 DLSR camera",
//                    produxt_image5, 170, 220, 136, (float) 4.6, 232, 74, "United Kingdom"));
//
//            recommended_items.add(new ProductClass("Chanel small flap handbag",
//                    produxt_image6, 150, 190, 736, (float) 4.1, 232, 74, "United Kingdom"));
//
//
//            recommended_items.add(new ProductClass("All-star Unisex Converse",
//                    produxt_image7, 35, 47, 948, (float) 4.8, 232, 74, "United Kingdom"));
//
//
//            recommended_items.add(new ProductClass("Asics camping backpack",
//                    produxt_image8, 25, 34, 423, (float) 4.3, 232, 74, "United Kingdom"));
        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

    private void promoPopulate() {

//        promo_list.add(new ClassPromotion(R.drawable.promo_desktop, "Discount", "10%"));
//        promo_list.add(new ClassPromotion(R.drawable.promo_phone, "Start from $50", "0"));
//        promo_list.add(new ClassPromotion(R.drawable.promo_jacket, "Discount", "20%"));
//        promo_list.add(new ClassPromotion(R.drawable.promo_cosmetics, "Discount", "40%"));
//        promo_list.add(new ClassPromotion(R.drawable.promo_food, "Discount", "70%"));
//        promo_list.add(new ClassPromotion(R.drawable.promo_laptops, "Discount", "30%"));

        promo_list.add(new ClassPromotion("https://img.pngio.com/comparerange-20181023png-mobile-phone-png-png-600_560.png", getString(R.string.discount12), "10%"));
        promo_list.add(new ClassPromotion("https://1.bp.blogspot.com/-cy6rKWsYNqE/WH3VOz6gsEI/AAAAAAAADGo/dWJfXQOMeb8HKuyh3SU62VXmWJTSBSgxwCLcB/s1600/Freebak_0130%2BFormal%2BShirts%2BLot%2B2.png", getString(R.string.start_from_fifty), "$50"));
        promo_list.add(new ClassPromotion("https://www.loeffler-shop.at/media/image/d9/6d/e5/21824968_600x600.png", getString(R.string.discount12), "20%"));
        promo_list.add(new ClassPromotion("https://officialpsds.com/imageview/ry/3z/ry3z3m_large.png?1529595431", getString(R.string.discount12), "40%"));
        promo_list.add(new ClassPromotion("http://spitacgt.com/wp-content/uploads/2018/11/best-price-laptop-Spitacgt.png", getString(R.string.discount12), "70%"));
        promo_list.add(new ClassPromotion("http://www.pngmart.com/files/7/Desktop-Computer-PNG-Pic.png", getString(R.string.discount12), "30%"));

        promo_list2.add("https://img.pngio.com/comparerange-20181023png-mobile-phone-png-png-600_560.png");
        promo_list2.add("https://www.bell.ca/Styles/RSX/shop/img/trade-in.png");
        promo_list2.add("https://www.loeffler-shop.at/media/image/d9/6d/e5/21824968_600x600.png");
        promo_list2.add("https://banner2.kisspng.com/20180128/vyw/kisspng-grocery-store-health-food-supermarket-vegetarian-c-shopping-basket-of-fruits-and-vegetables-5a6e013e94ba57.4233760915171587186092.jpg");
        promo_list2.add("https://3.imimg.com/data3/PI/JG/MY-15260994/laptops-sale-500x500.png");
//        promo_list2.add(new ClassPromotion(R.drawable.promo_laptops, "Discount", "30%"));
    }

    private void populateSaleItems() {
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

            sale_items.add(new ProductClass("Acer Helios Pro 13 with Touch Bar",
                    produxt_image1, 1500, 2100, 4, 11, 25, "United Kingdom"));

            sale_items.add(new ProductClass("Navy Blue sleeveless silk dress",
                    produxt_image2, 1200, 1450, 5, 16, 9, "France"));

            sale_items.add(new ProductClass("Cherry mobile Flare S7 mobile phone",
                    produxt_image3, 400, 550, 4, 19, 14, "United Kingdom"));

            sale_items.add(new ProductClass("The Godfather by Mario Puzo",
                    produxt_image4, 40, 60, 5, 1031, 45, "United Kingdom"));

            sale_items.add(new ProductClass("Metal Gear Solid V: The Phantom Pain",
                    produxt_image5, 70, 90, 4, 232, 74, "United Kingdom"));
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
