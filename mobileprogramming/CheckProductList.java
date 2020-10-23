package com.example.mobileprogramming;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CheckProductList extends BaseAdapter {
    ArrayList<Product> checkItemList = new ArrayList<>();

    public CheckProductList(){}

    public int getCount(){
        return checkItemList.size();
    }

    public View getView(int idx, View view, ViewGroup parent){
        Context context = parent.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.productlist_layout, parent, false);
        }

        TextView name = view.findViewById(R.id.name);
        TextView price = view.findViewById(R.id.price);
        ImageView img = view.findViewById(R.id.img);

        Product p = checkItemList.get(idx);
        name.setText(p.getName());
        price.setText(p.getPrice());
        img.setImageDrawable(p.getD());
        return view;
    }

    public long getItemId(int id){
        return id;
    }

    @Override
    public Object getItem(int idx) {
        return checkItemList.get(idx);
    }

    public void addItem(String name, String price, Drawable d){
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setD(d);

        checkItemList.add(p);
    }


}
