package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

public class dialogPDViewPagerImageAdapter extends PagerAdapter {

    Context context;
    ArrayList<String> slide_images;

    public dialogPDViewPagerImageAdapter(Context context, ArrayList<String> slide_images) {
        this.context = context;
        this.slide_images = slide_images;
    }

    @Override
    public int getCount() {
        return slide_images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((SelectableRoundedImageView) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SelectableRoundedImageView imageView = new SelectableRoundedImageView(context);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setCornerRadiiDP(0,0,0,0);
//        imageView.setImageResource(slide_images.get(position));

        Glide
                .with(context)
                .load(slide_images.get(position))
                .into(imageView);

        ((ViewPager)container).addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((SelectableRoundedImageView)object);
    }
}