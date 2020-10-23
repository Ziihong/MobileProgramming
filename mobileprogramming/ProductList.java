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

public class ProductList extends BaseAdapter {
    ArrayList<Product> products = new ArrayList<>();

    @Override
    //데이터 개수
    public int getCount() {
        return products.size();
    }

    @Override
    //데이터 보내기
    public Object getItem(int idx) {
        return products.get(idx);
    }

    @Override
    //데이터 위치 찾기
    public long getItemId(int idx) {
        return idx;
    }

    @Override
    public View getView(int idx, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.product_layout, viewGroup, false); // false->root에 붙일것인지
        }

        TextView name = view.findViewById(R.id.name);
        TextView price = view.findViewById(R.id.price);
        ImageView img = view.findViewById(R.id.img);

        Product p = products.get(idx);
        name.setText(p.getName());
        price.setText(p.getPrice());
        img.setImageDrawable(p.getD());

        return view;
    }

    public void addItem(String name, String price, Drawable d){
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setD(d);

        products.add(p);
    }
}
