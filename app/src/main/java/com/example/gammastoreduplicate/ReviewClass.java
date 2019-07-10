package com.example.gammastoreduplicate;

import android.graphics.Bitmap;

public class ReviewClass {

    Bitmap review_image;
    Float review_rating;
    String date;
    String review_comment;

    public ReviewClass(Bitmap review_image, Float review_rating, String date, String review_comment) {
        this.review_image = review_image;
        this.review_rating = review_rating;
        this.date = date;
        this.review_comment = review_comment;
    }
}
