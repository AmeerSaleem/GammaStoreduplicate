package com.example.gammastoreduplicate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerStringAdapter extends PagerAdapter {

    Context context;
    ArrayList<Drawable> slide_images;

    public ViewPagerStringAdapter(Context context, ArrayList<Drawable> slide_images) {
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
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        ((ViewPager)container).removeView((ImageView)object);
    }
}