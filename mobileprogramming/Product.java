package com.example.mobileprogramming;

import android.graphics.drawable.Drawable;

public class Product {
    private String name, price;
    private Drawable d;

    public void setName(String name){
        this.name = name;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setD(Drawable d){
        this.d = d;
    }

    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public Drawable getD(){
        return d;
    }
}
