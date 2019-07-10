package com.example.gammastoreduplicate;

public class BrandProduct {

    String brand_product_name;
    int brand_product_image;
    int brand_product_cost_in_$;
    float brand_product_rating;
    int brand_product_items_sold;

    public BrandProduct(String brand_product_name, int brand_product_image, int brand_product_cost_in_$, float brand_product_rating, int brand_product_items_sold) {
        this.brand_product_name = brand_product_name;
        this.brand_product_image = brand_product_image;
        this.brand_product_cost_in_$ = brand_product_cost_in_$;
        this.brand_product_rating = brand_product_rating;
        this.brand_product_items_sold = brand_product_items_sold;
    }
}
