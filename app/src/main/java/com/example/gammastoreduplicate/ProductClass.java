package com.example.gammastoreduplicate;

import java.util.ArrayList;

public class ProductClass {

    String product_name;
    ArrayList<String> product_bitmaps;
    int cost_in_$;
    int prev_cost_in_$;
    int items_sold;
    float rating;
    int review_count;
    int no_available;
    int quantity = 1;
    int discount = 10;
    String location;
    ArrayList<ColorClass> colors;
    ArrayList<String> sizes;
    ArrayList<ProductCOMMENTSClass> comments;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductClass(String product_name, ArrayList<String> product_bitmaps, int cost_in_$, int prev_cost_in_$, int items_sold, float rating, int review_count, int no_available, int quantity, String location, ArrayList<ColorClass> colors, ArrayList<String> sizes, ArrayList<ProductCOMMENTSClass> comments) {
        this.product_name = product_name;
        this.product_bitmaps = product_bitmaps;
        this.cost_in_$ = cost_in_$;
        this.prev_cost_in_$ = prev_cost_in_$;
        this.items_sold = items_sold;
        this.rating = rating;
        this.review_count = review_count;
        this.no_available = no_available;
        this.quantity = quantity;
        this.location = location;
        this.colors = colors;
        this.sizes = sizes;
        this.comments = comments;
    }

    public ProductClass(String product_name, ArrayList<String> product_bitmaps, int cost_in_$, int prev_cost_in_$, int items_sold, float rating, int review_count, int no_available, String location, ArrayList<ColorClass> colors, ArrayList<String> sizes, ArrayList<ProductCOMMENTSClass> comments) {
        this.product_name = product_name;
        this.product_bitmaps = product_bitmaps;
        this.cost_in_$ = cost_in_$;
        this.prev_cost_in_$ = prev_cost_in_$;
        this.items_sold = items_sold;
        this.rating = rating;
        this.review_count = review_count;
        this.no_available = no_available;
        this.location = location;
        this.colors = colors;
        this.sizes = sizes;
        this.comments = comments;
    }

    public ProductClass(String product_name, ArrayList<String> product_bitmaps, int cost_in_$, int prev_cost_in_$, float rating, int review_count, int no_available, String location) {
        this.product_name = product_name;
        this.product_bitmaps = product_bitmaps;
        this.cost_in_$ = cost_in_$;
        this.prev_cost_in_$ = prev_cost_in_$;
        this.rating = rating;
        this.review_count = review_count;
        this.no_available = no_available;
        this.location = location;
    }

    public ProductClass(String product_name, ArrayList<String> product_bitmaps, int cost_in_$, int prev_cost_in_$, int items_sold, float rating, int review_count, int no_available, String location) {
        this.product_name = product_name;
        this.product_bitmaps = product_bitmaps;
        this.cost_in_$ = cost_in_$;
        this.prev_cost_in_$ = prev_cost_in_$;
        this.items_sold = items_sold;
        this.rating = rating;
        this.review_count = review_count;
        this.no_available = no_available;
        this.location = location;
    }



}