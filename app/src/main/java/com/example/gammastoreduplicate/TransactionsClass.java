package com.example.gammastoreduplicate;

public class TransactionsClass {

    String date_purchased;
    String product_purchased_name;

    String cost_in_$_purchased;

    public TransactionsClass(String date_purchased, String product_purchased_name, String cost_in_$_purchased) {
        this.date_purchased = date_purchased;
        this.product_purchased_name = product_purchased_name;
        this.cost_in_$_purchased = cost_in_$_purchased;
    }
}
