package com.example.mobileprogramming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Purchase extends AppCompatActivity {

    ListView listView;                      //상품 목록
    TextView price;                         //총 계산 금액
    Button orderButton;                     //구매 완료 버튼
    EditText inputPhone, inputAddress;      //연락처, 주소 입력
    int totalPrice;                         //총 금액


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        //각 위젯을 id값을 이용하여 받아오기
        listView = findViewById(R.id.listView);
        orderButton = findViewById(R.id.orderButton);
        price = findViewById(R.id.price);
        inputPhone = findViewById(R.id.inputPhone);
        inputAddress = findViewById(R.id.inputAdress);

        //adapter 생성
        ProductList adapter = new ProductList();
        //ListView에 해당 adapter 적용
        listView.setAdapter(adapter);


        //구매 목록에 있는 객체들을, addItem함수를 사용하여 adapter에 추가
        for(int i=0; i<MainActivity.shoppingList.size(); i++){
            Product p = MainActivity.shoppingList.get(i);
            adapter.addItem(p.getName(), p.getPrice(), p.getD());

            //금액 계산
            int pr = 0;
            if(p.getPrice().equals("75,000원")) pr=75000;
            else if(p.getPrice().equals("74,000원")) pr=74000;
            else if(p.getPrice().equals("62,000원")) pr=62000;
            totalPrice += pr;
        }
        //계산한 금액 setText 사용하여 지정
        price.setText("총 합: " + totalPrice/1000 + ",000원");


        //주문 버튼 눌렀을 때
        orderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //연락처와 주소 둘 중 하나라도 입력하지 않았을 때
                if(inputPhone.getText().length()==0 || inputAddress.getText().length() == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
                    builder.setTitle("🤔").setMessage("정보를 모두 입력해주세요!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                //정보를 모두 입력 하였을 때
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
                    builder.setTitle("😉").setMessage("주문이 완료되었습니다.");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    //구매 완료. Intent 사용하여 MainActivity.class로 이동
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    //구매한 제품들은 장바구니 목록에서 삭제
                    for(int i=0; i<MainActivity.shoppingList.size(); i++){
                        Product p = MainActivity.shoppingList.get(i);
                        for(int j=0; j<MainActivity.cartList.size(); j++){
                            if(MainActivity.cartList.get(j).getName().equals(p.getName())){
                                MainActivity.cartList.remove(MainActivity.cartList.get(j));
                            }
                        }
                    }

                    //구매를 완료하였으므로, 구매 목록 초기화
                    MainActivity.shoppingList.clear();
                }

            }
        });

    }

    //뒤로 가기 버튼 눌렀을 때
    public void onBackPressed(){
        //구매 목록 초기화 후, 상품 페이지로 이동
        MainActivity.shoppingList.clear();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}