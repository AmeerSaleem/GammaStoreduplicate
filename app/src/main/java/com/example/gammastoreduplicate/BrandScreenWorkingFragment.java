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

public class BrandScreenWorkingFragment extends Fragment {

    CardView card_source1,card_source2,card_source3,card_source4,card_source5;
    ImageView image_source1, image_source2, image_source3, image_source4, image_source5;
    int extra_passed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_brand_screen_working,container,false);

        card_source1 = v.findViewById(R.id.card_source);
        card_source2 = v.findViewById(R.id.card_source2_ck);
        card_source3 = v.findViewById(R.id.card_source3_th);
        card_source4 = v.findViewById(R.id.card_source4_apple);
        card_source5 = v.findViewById(R.id.card_source5_samsung);
        image_source1 = v.findViewById(R.id.image_source);

        card_source1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BRANDDetailWorkingActivity.class);
                extra_passed = 1;
                intent.putExtra("brand_type",extra_passed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_source1, card_source1.getTransitionName()).toBundle();
                    startActivity(intent, bundle);
                    return;
                }
                startActivity(intent);
            }
        });

        card_source2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BRANDDetailWorkingActivity.class);
                extra_passed = 2;
                intent.putExtra("brand_type",extra_passed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_source2, card_source2.getTransitionName()).toBundle();
                    startActivity(intent, bundle);
                    return;
                }
                startActivity(intent);
            }
        });

        card_source3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BRANDDetailWorkingActivity.class);
                extra_passed = 3;
                intent.putExtra("brand_type",extra_passed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_source3, card_source3.getTransitionName()).toBundle();
                    startActivity(intent, bundle);
                    return;
                }
                startActivity(intent);
            }
        });

        card_source4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BRANDDetailWorkingActivity.class);
                extra_passed = 4;
                intent.putExtra("brand_type",extra_passed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_source4, card_source4.getTransitionName()).toBundle();
                    startActivity(intent, bundle);
                    return;
                }
                startActivity(intent);
            }
        });

        card_source5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BRANDDetailWorkingActivity.class);
                extra_passed = 5;
                intent.putExtra("brand_type",extra_passed);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), card_source5, card_source5.getTransitionName()).toBundle();
                    startActivity(intent, bundle);
                    return;
                }
                startActivity(intent);
            }
        });

        return v;
    }
}
