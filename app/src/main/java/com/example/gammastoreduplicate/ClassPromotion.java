package com.example.gammastoreduplicate;

public class ClassPromotion {

    String image_source;
    String text, discount;

    public ClassPromotion(String image_source, String text, String discount) {
        this.image_source = image_source;
        this.text = text;
        this.discount = discount;
    }

    public ClassPromotion(String image_source, String text) {
        this.image_source = image_source;
        this.text = text;
        discount = "0";
    }


}