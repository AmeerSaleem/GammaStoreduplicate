package com.example.gammastoreduplicate;

import java.util.ArrayList;

public class Product {

    String productId;
    String productName;
    ArrayList<String> productImages;
    String productCost;
    String productPrevCost;
    String itemsSold;
    String productRating;
    String reviewCount;
    String numberAvailable;
    String location;

    public Product() {
    }

    public Product(String productId, String productName, ArrayList<String> productImages, String productCost, String productPrevCost, String itemsSold, String productRating, String reviewCount, String numberAvailable, String location) {
        this.productId = productId;
        this.productName = productName;
        this.productImages = productImages;
        this.productCost = productCost;
        this.productPrevCost = productPrevCost;
        this.itemsSold = itemsSold;
        this.productRating = productRating;
        this.reviewCount = reviewCount;
        this.numberAvailable = numberAvailable;
        this.location = location;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(ArrayList<String> productImages) {
        this.productImages = productImages;
    }

    public String getProductCost() {
        return productCost;
    }

    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }

    public String getProductPrevCost() {
        return productPrevCost;
    }

    public void setProductPrevCost(String productPrevCost) {
        this.productPrevCost = productPrevCost;
    }

    public String getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(String itemsSold) {
        this.itemsSold = itemsSold;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getNumberAvailable() {
        return numberAvailable;
    }

    public void setNumberAvailable(String numberAvailable) {
        this.numberAvailable = numberAvailable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}