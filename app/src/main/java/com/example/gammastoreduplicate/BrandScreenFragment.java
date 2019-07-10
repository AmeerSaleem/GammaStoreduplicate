package com.example.gammastoreduplicate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joooonho.SelectableRoundedImageView;

public class BrandScreenFragment extends Fragment {

    SelectableRoundedImageView  card_calvin_klein, card_tommy, card_apple, card_samsung;
    ImageView card_nike,image1;
    int extra_passed;
    SelectableRoundedImageView image2,image3,image4,image5;
    CardView card1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_brands, container, false);

        card1 = v.findViewById(R.id.card_view_brand);
        image1 = v.findViewById(R.id.brand_nike);
        image2 = v.findViewById(R.id.brand_ck);
        image3 = v.findViewById(R.id.brand_tommy);
        image4 = v.findViewById(R.id.brand_apple);
        image5 = v.findViewById(R.id.brand_samsung);
        card_nike = v.findViewById(R.id.brand_nike);
        card_calvin_klein = v.findViewById(R.id.brand_ck);
        card_tommy = v.findViewById(R.id.brand_tommy);
        card_apple = v.findViewById(R.id.brand_apple);
        card_samsung = v.findViewById(R.id.brand_samsung);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_passed = 1;
                moveToBrandDetailActivity();

            }
        });

        card_calvin_klein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_passed = 2;
                moveToBrandDetailActivity();

            }
        });

        card_tommy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_passed = 3;
                moveToBrandDetailActivity();

            }
        });

        card_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_passed = 4;
                moveToBrandDetailActivity();

            }
        });

        card_samsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extra_passed = 5;
                moveToBrandDetailActivity();

            }
        });

        return v;
    }

    private void moveToBrandDetailActivity() {
        Intent intent = new Intent(getContext(),BRANDDetailActivty.class);
        intent.putExtra("brand_type",extra_passed);
        if(extra_passed == 1){

            Bundle bundle = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),card1,"card12").toBundle();
            }

            startActivity(intent,bundle);
            return;
        }
        if(extra_passed == 2){
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),image2,"nike_transition").toBundle();
            startActivity(intent,bundle);
            return;
        }
        if(extra_passed == 3){
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),image3,"nike_transition").toBundle();
            startActivity(intent,bundle);
            return;
        }
        if(extra_passed == 4){
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),image4,"nike_transition").toBundle();
            startActivity(intent,bundle);
            return;
        }
        if(extra_passed == 5){
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),image5,"nike_transition").toBundle();
            startActivity(intent,bundle);
            return;
        }
        startActivity(intent);
    }
}