package com.example.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class ShoppingCart extends AppCompatActivity {

    ListView listView;                      //상품 목록
    Button homeButton, purchaseButton;      //홈 버튼, 구매 버튼
    static int itemCount;                   //구매 제품 개수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        //각 위젯을 id값을 이용하여 받아오기
        listView = findViewById(R.id.listView);
        homeButton = findViewById(R.id.homeButton);
        purchaseButton = findViewById(R.id.purchaseButton);
        purchaseButton.setVisibility(View.INVISIBLE);


        //adapter 생성
        CheckProductList adapter = new CheckProductList();
        //ListView에 해당 adapter 적용
        listView.setAdapter(adapter);


        //장바구니 목록에 있는 객체들을, addItem 함수를 사용하여 adapter에 항목 추가
        for(int i=0; i<MainActivity.cartList.size(); i++){
            Product p = (Product) MainActivity.cartList.get(i);
            adapter.addItem(p.getName(), p.getPrice(), p.getD());
        }


        //ListView 눌렀을 때
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int idx, long id){

                //체크된 제품의 수 받아오기. =>구매할 제품의 수
                itemCount = listView.getCheckedItemCount();

                //구매 목록에 선택된 객체 추가
                Product selectedItem = (Product) parent.getItemAtPosition(idx);
                if(!MainActivity.shoppingList.contains(selectedItem)) MainActivity.shoppingList.add(selectedItem);
                else MainActivity.shoppingList.remove(selectedItem);


                //선택된 제품이 1개 이상일 때만, 구매 버튼이 보이도록 설정
                if(itemCount > 0){
                    purchaseButton.setVisibility(View.VISIBLE);
                }
                //선택된 제품이 0개일 때, 구매 버튼이 보이지 않도록 설정
                else{
                    purchaseButton.setVisibility(View.INVISIBLE);
                }
            }
        });


        //홈 버튼이 눌렀을 때
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //구매 버튼이 눌렀을 때
        purchaseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Intent 사용하여 Purchase.class로 이동
                Intent intent = new Intent(getApplicationContext(), Purchase.class);
                startActivity(intent);

                //체크된 수 0으로 초기화
                itemCount = 0;
            }
        });

    }

}