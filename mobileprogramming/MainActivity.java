package com.example.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listView;                                  //상품 목록
    ConstraintLayout menu;                              //장바구니 또는 구매 버튼 선택하는 메뉴
    Button shoppingCart, purchase, goShoppingList;      //메뉴의 장바구니 버튼과 구매하기 버튼, 장바구니 이동 버튼
    Product selectedItem;                               //선택된 제품

    //결제할 제품
    static ArrayList<Product> shoppingList = new ArrayList<>();
    //장바구니 제품
    static ArrayList<Product> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //각 위젯을 id값을 이용하여 받아오기
        listView = findViewById(R.id.listView);
        menu = findViewById(R.id.menu);
        shoppingCart = findViewById(R.id.shoppingcartButton);
        purchase = findViewById(R.id.purchaseButton);
        goShoppingList = findViewById(R.id.goShoppingList);


        //처음 아무 제품을 선택하지 않았을 때, 메뉴 버튼 보이지 않게 설정
        menu.setVisibility(View.INVISIBLE);


        //제품을 구매한 후, 다시 상품 페이지로 이동하였을 때를 고려하여 shoppingList 초기화
        shoppingList.clear();


        //adapter 생성. ListView와 ArrayList 사이에서 중간 역할
        ProductList adapter = new ProductList();
        //ListView에 해당 adapter적용
        listView.setAdapter(adapter);


        //addItem함수 사용하여 adapter에 항목 추가
        adapter.addItem("칼하트\nD89 메신저백 블랙", "75,000원", ContextCompat.getDrawable(this, R.drawable.product1));
        adapter.addItem("커버낫\nCORDURA AUTHENTIC LOGO RUCK SACK 블랙", "74,000원", ContextCompat.getDrawable(this, R.drawable.product2));
        adapter.addItem("캉골\nPoodle Bucket Bag 아이보리", "62,000원", ContextCompat.getDrawable(this,R.drawable.product3));


        //ListView 눌렀을 때
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int idx, long id){
                //안내 문구
                Toast toast = Toast.makeText(getApplicationContext(), "이동할 페이지를 선택하세요😀", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM,0, 300);
                toast.show();

                //메뉴 보이게 설정
                menu.setVisibility(View.VISIBLE);
                //선택한 객체 저장
                selectedItem = (Product)parent.getItemAtPosition(idx);

            }
        });


        //장바구니 버튼 눌렀을 때
        shoppingCart.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //장바구니 목록에 선택한 객체 추가
                if(!cartList.contains(selectedItem)) cartList.add(selectedItem);

                //Intent 사용하여 ShoppingCart.class로 이동
                Intent intent = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent);

                //메뉴 보이지 않게 설정
                menu.setVisibility(View.INVISIBLE);
            }
        });


        //구매 버튼 눌렀을 때
        purchase.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //구매 목록에 선택한 객체 추가
                shoppingList.add(selectedItem);

                //Intent 사용하여 Purchase.class로 이동
                Intent intent = new Intent(getApplicationContext(), Purchase.class);
                startActivity(intent);

                //메뉴 보이지 않게 설정
                menu.setVisibility(View.INVISIBLE);
            }
        });


        //제품 선택 없이 장바구니 페이지로 이동할 때
        goShoppingList.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //Intent 사용하여 ShoppingCart.class로 이동
                Intent intent = new Intent(getApplicationContext(), ShoppingCart.class);
                startActivity(intent);

                //메뉴 보이지 않게 설정
                menu.setVisibility(View.INVISIBLE);
            }
        });


    }

}