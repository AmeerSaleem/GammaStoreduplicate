package com.example.gammastoreduplicate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private static final long RIPPLE_DURATION = 250;

    FrameLayout fragment_container;
    SlidingRootNav srn;
    LinearLayout sliding_home, sliding_brands, sliding_cart, sliding_profile;
    BottomNavigationView bnv;
    int bnv_selected;
    ImageView hamburger_icon;
    int view_selected;
    ImageView search_icon;
    static ArrayList<ProductClass> cart_items;
    ImageView chat_icon;
    ImageView notifications_icon;
    static Toolbar toolbar2share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_selected = 1;

        toolbar2share = findViewById(R.id.toolbar2);
        notifications_icon = findViewById(R.id.toolbar_notifications);
        notifications_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        });

        chat_icon = findViewById(R.id.chat_main_activity);

        chat_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle);
            }
        });

        cart_items = new ArrayList<>();
        search_icon = findViewById(R.id.toolbar_search);
        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                intent.putExtra("entry", "main");
                startActivity(intent, bundle);
            }
        });
        hamburger_icon = findViewById(R.id.toolbar_menu_hamburger);
        bnv_selected = 1;
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout root = findViewById(R.id.root);
        bnv = findViewById(R.id.bottom_nav_panel);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.bottom_home:
                        if (bnv_selected != 1) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, new MainShopFragment())
                                    .commit();
                            bnv_selected = 1;
//                            bnv.setSelected(false);
//                            bnv.setSelectedItemId(R.id.bottom_home);
                        }
                        break;

                    case R.id.bottom_brand:
                        if (bnv_selected != 2) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, new BrandScreenWorkingFragment())
                                    .commit();
                            bnv_selected = 2;
//                            bnv.setSelectedItemId(R.id.bottom_brand);
                        }
                        break;

                    case R.id.bottom_cart:
                        if (bnv_selected != 3) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, new CartFragment())
                                    .commit();
                            bnv_selected = 3;
//                            bnv.setSelectedItemId(R.id.bottom_brand);
                        }
                        break;
                    case R.id.bottom_account:
                        if (bnv_selected != 4) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, new ProfileFragment())
                                    .commit();
                            bnv_selected = 4;
//                            bnv.setSelectedItemId(R.id.bottom_brand);
                        }
                        break;
                }
                return true;
            }
        });
        srn = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLocked(false)
                .withMenuLayout(R.layout.menu_sliding)
                .inject();

        LinearLayout linear_image_circle = findViewById(R.id.linear_circular_image);
        linear_image_circle.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        sliding_home = findViewById(R.id.sliding_menu_home);
        sliding_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnv_selected != 1) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new MainShopFragment())
                            .commit();
                    overridePendingTransition(0, 0);
                    srn.closeMenu(true);
                    bnv_selected = 1;
                    bnv.setSelectedItemId(R.id.bottom_home);
                } else {
                    srn.closeMenu(true);
                }
            }
        });

        sliding_brands = findViewById(R.id.sliding_menu_brands);
//        sliding_brands = v_slide_menu.findViewById(R.id.sliding_menu_brands);


        sliding_brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnv_selected != 2) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new BrandScreenWorkingFragment())
                            .commit();
                    overridePendingTransition(0, 0);
                    srn.closeMenu(true);
                    bnv_selected = 2;
                    bnv.setSelectedItemId(R.id.bottom_brand);
                } else {
                    srn.closeMenu(true);
                }
            }
        });

        sliding_cart = findViewById(R.id.sliding_menu_cart);
//        sliding_brands = v_slide_menu.findViewById(R.id.sliding_menu_brands);


        sliding_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnv_selected != 3) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new CartFragment())
                            .commit();
                    overridePendingTransition(0, 0);
                    srn.closeMenu(true);
                    bnv_selected = 3;
                    bnv.setSelectedItemId(R.id.bottom_cart);
                } else {
                    srn.closeMenu(true);
                }
            }
        });

        sliding_profile = findViewById(R.id.sliding_menu_profile);
//        sliding_brands = v_slide_menu.findViewById(R.id.sliding_menu_brands);


        sliding_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnv_selected != 4) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProfileFragment())
                            .commit();
                    overridePendingTransition(0, 0);
                    srn.closeMenu(true);
                    bnv_selected = 4;
                    bnv.setSelectedItemId(R.id.bottom_account);
                } else {
                    srn.closeMenu(true);
                }
            }
        });

        hamburger_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srn.openMenu(true);
            }
        });
//        View contentHamburger = findViewById(R.id.content_hamburger);

//        if(toolbar != null){
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setTitle(null);
//        }
//
//        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine_layout,null);
//        root.addView(guillotineMenu);
//
//
//        new GuillotineAnimation.GuillotineBuilder(guillotineMenu,guillotineMenu.findViewById(R.id.guilotine_hamburger),contentHamburger)
//                .setStartDelay(RIPPLE_DURATION)
//                .setActionBarViewForAnimation(toolbar)
//                .setClosedOnStart(true)
//                .build();

//        FlowingDrawer fd = findViewById(R.id.root);
//        fd.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
//        fd.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
//            @Override
//            public void onDrawerStateChange(int oldState, int newState) {
//
//            }
//
//            @Override
//            public void onDrawerSlide(float openRatio, int offsetPixels) {
//
//            }
//        });
        fragment_container = findViewById(R.id.fragment_container);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new MainShopFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
