package com.example.gammastoreduplicate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.joooonho.SelectableRoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    ImageView profile_cover;
    RelativeLayout dp_name_layout;
    CollapsingToolbarLayout collapse_toolbar;
    AppBarLayout appBarLayout_profile;
    CircleImageView moving_image;
    LinearLayout notifications_row;
    LinearLayout card_details_row;
    LinearLayout orders_details_row;
    LinearLayout language_row;
    LinearLayout about_app_row;
    LinearLayout chat_row;
    SelectableRoundedImageView profile_image;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_image = v.findViewById(R.id.rounded_image_view_profile_image1);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ProfileEditActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        chat_row = v.findViewById(R.id.chat_profile_layout);
        chat_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        about_app_row = v.findViewById(R.id.about_app_profile_layout);
        about_app_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AboutAppActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        language_row = v.findViewById(R.id.language_profile_layout);
        language_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LanguageActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        orders_details_row = v.findViewById(R.id.orders_row_layout);
        orders_details_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TransactionsDetailsActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        card_details_row = v.findViewById(R.id.card_details_row_layout1);

        card_details_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CardDetailsActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
        notifications_row = v.findViewById(R.id.notifications_row);

        notifications_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),NotificationsActivity.class);
                Bundle bundle1 = ActivityOptionsCompat.makeCustomAnimation(getContext(), android.R.anim.fade_in,
                        android.R.anim.fade_out).toBundle();
                startActivity(intent, bundle1);
            }
        });
//        dp_name_layout = v.findViewById(R.id.layout_with_profile_dp_name);
//        appBarLayout_profile = v.findViewById(R.id.app_bar_layout_profile);

//        appBarLayout_profile.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = true;
//            int scrollRange = -1;

//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

//                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) dp_name_layout.getLayoutParams();
//
//                int marginLeft = params.leftMargin;
//                int marginRight = params.rightMargin;
//                int marginTop = params.topMargin;
//                int marginBottom = params.bottomMargin;
//
//
////                if(scrollRange == -1){
////                    scrollRange = appBarLayout_profile.getTotalScrollRange();
////                }
////                if(scrollRange + i == 0){
////                    isShow = true;
////                    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_down);
////                    dp_name_layout.setAnimation(animation);
////                    dp_name_layout.setVisibility(View.GONE);
////                }
////            else if(isShow){
////                    Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_up);
////                    dp_name_layout.setAnimation(animation);
////                    dp_name_layout.setVisibility(View.VISIBLE);
////                    isShow = false;
//            }
//            }
//        });

//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) moving_image.getLayoutParams();
//        params.setBehavior(new AvatarImageBehavior(getContext(), null));

        profile_cover = v.findViewById(R.id.collapsing_image);
//        String cover_uri = new String("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/FTC_Mosque_Karachi.png/1024px-FTC_Mosque_Karachi.png");
//
//        Glide
//                .with(getContext())
//                .load(cover_uri)
//                .into(profile_cover);

//        profile_cover.setBackground(getResources().getDrawable(R.drawable.trend_gradient_white_more));

        return v;
    }

}