package com.example.gammastoreduplicate;

import android.graphics.Bitmap;

public class ProductCOMMENTSClass {

    Bitmap comment_image;
    String comment_name;
    Float comment_rating;
    String comment_date;
    String comment_text;

    public ProductCOMMENTSClass(Bitmap comment_image, String comment_name, Float comment_rating, String comment_date, String comment_text) {
        this.comment_image = comment_image;
        this.comment_name = comment_name;
        this.comment_rating = comment_rating;
        this.comment_date = comment_date;
        this.comment_text = comment_text;
    }
}
