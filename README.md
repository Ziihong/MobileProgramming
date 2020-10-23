# MobileProgramming
국민대학교 모바일프로그래밍 개인과제에 대한 프로젝트입니다. <br>
소프트웨어학부 20191572 김지홍<br><br>

## 목차
<ul>
<li> 1.프로젝트 소개
<ul>
  <li> 1.1 프로젝트 개요
  <li> 1.2 프로젝트 대상
  <li> 1.3 프로젝트 기능
  <li> 1.4 개발 환경
</ul>
<li> 2. 소스 파일
<ul>
  <li> 2.1 소스 파일 구성
  <li> 2.2 소스 파일 설명
</ul>
<li> 3. UI 및 스크린샷
<ul>
  <li> 3.1 UI 설계
  <li> 3.2 스크린샷
</ul>
<br><br>

## 1. 프로젝트 소개
### 1.1 프로젝트 개요
실제 상품을 구매하는 것과 같은 페이지를 만든다. 상품 페이지에서 2개 이상의 상품을 화면에 출력한다.
상품을 선택한 후에는 구매 혹은 장바구니 버튼을 선택하여 각 선택에 맞는 페이지로 이동한다. 
장바구니 페이지에서는 구매하려는 상품을 선택하여 구매페이지로 이동할 수 있다. 구매 페이지에서는
실제 구매가 이루어지는 페이지로서, 결제할 금액을 출력하고 연락처와 주소를 입력받는다. 결제가 완료된 후에는
다시 상품 페이지로 이동한다.<br>
### 1.2 프로젝트 대상
이 프로젝트의 대상은 가방을 구매하려는 소비자이다. 원하는 제품을 선택하여 구매할 수 있다.
### 1.3 프로젝트 기능
구매하려는 제품이 하나일 때는 상품 페이지에서 원하는 제품 선택 후 구매 버튼을 눌러, 바로 구매 페이지로 이동할 수 있다. 구매하려는 제품이 2개 이상일 경우, 원하는 제품을 차례대로 장바구니에 담은 후 장바구니 페이지에서 구매할 제품을 선택하여 구매 페이지로 이동할 수 있다.
### 1.4 개발환경
Language: java <br>
IDE: Android Studio <br><br>

## 2. 소스 파일
### 2.1 소스 파일 구성
|파일명|기능|
|:---:|:---:|
|MainActivity.java|기본 액티비티로서, 상품 페이지를 구성하는 액티비티|
|ShoppingCart.java|장바구니 페이지를 구성하는 액티비티 |
|Purchase.java|구매 페이지를 구성하는 액티비티|
|Product.java|상품 정보를 담고 있는 상품 클래스|
|CheckableLinearLayout.java|Checkable 인터페이스를 상속하여 새로 구성한 LinearLayout 클래스|
|ProductList.java|상품 페이지와 구매 페이지에서 상품 나열을 위한 adapter 클래스|
|CheckProductList.java|장바구니 페이지에서 상품 나열을 위한 adapter 클래스|

### 2.2 소스 파일 설명
#### -MainActivity.java

결제할 리스트와 장바구니에 담을 리스트를 static을 사용하여 생성하였다.


    //결제할 제품
    static ArrayList<Product> shoppingList = new ArrayList<>();
    //장바구니 제품
    static ArrayList<Product> cartList = new ArrayList<>();

 adapter을 생성하고 상품을 보여주는 ListView에 적용하였다. addItem 메소드를 사용하여 adapter에 상품을 추가하였다. 제품이 클릭되었을 때 해당 제품을 저장하고, 장바구니와 구매 중 선택하라는 메세지가 뜬다.

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

이때, 저장한 제품은 장바구니 버튼을 클릭하면 장바구니 리스트에 추가되고 구매하기 버튼을 클릭하면 구매 리스트에 추가된다.

          //장바구니 버튼을 눌렀을 때
          if(!cartList.contains(selectedItem)) cartList.add(selectedItem);


          //구매 버튼을 눌렀을 때
          shoppingList.add(selectedItem);




#### -ShoppingCart.java

adapter을 생성하고 장바구니 리스트에 담겨있는 객체들을, addItem 메소드를 사용하여 adapter에 추가한다.

        //adapter 생성
        CheckProductList adapter = new CheckProductList();
        //ListView에 해당 adapter 적용
        listView.setAdapter(adapter);


        //장바구니 목록에 있는 객체들을, addItem 함수를 사용하여 adapter에 항목 추가
        for(int i=0; i<MainActivity.cartList.size(); i++){
            Product p = (Product) MainActivity.cartList.get(i);
            adapter.addItem(p.getName(), p.getPrice(), p.getD());
        }
       
ListView가 눌렸을 때, 체크된 제품 수를 받아오고 선택된 제품은 구매 리스트에 추가한다. 이때, 선택된 제품이 1개 이상일 때만 구매 버튼이 보이도록 설정한다.

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

#### -Purchase.java

adapter을 생성하고 구매 리스트에 담겨 있는 객체들을, addItem 메소드를 사용하여 adaper에 추가한다. 이때, 총 결제 금액을 계산하기 위해 구매 리스트에 있는 제품의 값을 더해준다.

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

최종 주문 버튼을 눌렀을 때, 상품 페이지인 MainActivity.class로 이동하도록 한다. 구매한 제품들은 장바구니 리스트에서 삭제하고 구매 리스트는 초기화한다.

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
#### -Product.java
제품 정보를 담고 있는 클래스이다. get메소드와 set메소들 이용하여 제품의 정보를 관리하였다.

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


#### -CheckableLinearLayout.java
Checkable 인터페이스와 LinearLayout 클래스를 상속 받아 재구성하였다.

      public CheckableLinearLayout(Context context, AttributeSet attrs){
          super(context, attrs);
      }
      //checkable 인터페이스 abstract함수 override해야함

      public boolean isChecked(){ //현재 checked 상태 확인
          CheckBox checkBox = findViewById(R.id.checkbox);
          return checkBox.isChecked();
      }

      public void toggle(){ //현재 checked 상태 변경
          CheckBox checkBox = findViewById(R.id.checkbox);
          setChecked(!checkBox.isChecked());
      }

      public void setChecked(boolean checked){ //checked 상태를 checked 변수대로 설정
          CheckBox checkBox = findViewById(R.id.checkbox);
          if(checkBox.isChecked() != checked){
              checkBox.setChecked(checked);
          }
      }
#### -ProductList.java

제품을 담을 리스트를 ArrayList클래스를 사용하여 생성한다.

    ArrayList<Product> products = new ArrayList<>();
  
일부 뷰를 객체화 하기 위해 LayoutInflater.inflate 메소드를 사용한다. 원하는 viewGroup에 직접 작성한 product_layout.xml을 적용시킨다. 

    public View getView(int idx, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.product_layout, viewGroup, false); // false->root에 붙일것인지
        }
      
        ''''''
        return view;
    }
 addItem 메소드를 정의하여 adapter에 객체 추가를 용이하도록한다.
 
     public void addItem(String name, String price, Drawable d){
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setD(d);

        products.add(p);
    }
    
#### -CheckableProductList.java
제품을 담을 리스트를 ArrayList클래스를 사용하여 생성한다.

    ArrayList<Product> checkItemList = new ArrayList<>();

일부 뷰를 객체화 하기 위해 LayoutInflater.inflate 메소드를 사용한다. 원하는 viewGroup에 직접 작성한 productlist_layout.xml을 적용시킨다. 

    public View getView(int idx, View view, ViewGroup parent){
        Context context = parent.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.productlist_layout, parent, false);
        }
        
 addItem 메소드를 사용하여 adapter에 객체 추가를 용이하도록 한다.
 
     public void addItem(String name, String price, Drawable d){
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setD(d);

        checkItemList.add(p);
    }
 
## 3. UI 및 스크린샷
### 3.1 UI 설계
|파일명|기능|
|:---:|:---:|
|activity_main.xml|기본 액티비티. 상품 페이지 레이아웃|
|activity_shopping_cart.xml|장바구니 페이지 레이아웃|
|activity_purchase.xml|구매 페이지 레이아웃|
|product_layout.xml|뷰를 객체화 하기 위해 구성한 레이아웃. 상품 페이지, 구매 페이지에서 사용|
|productlist_layout.xml|뷰를 객체화 하기 위해 구성한 레이아웃. 장바구니 페이지에서 사용|
#### -activity_main.xml
최상위 레이아웃을 Relative Layout을 사용하였다. ListView에 상품을 보여주고, 상품을 선택하면 아래에 장바구니 혹은 구매 버튼을 선택할 수 있다. MainActivity.class와 연결되어 작동한다.

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
            android:id="@+id/textView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#C0C7ED"
            android:gravity="center"
            android:padding="10sp"
            android:text="상품 선택 페이지"
            android:textIsSelectable="false"
            android:textSize="23sp" />

        <Button
            android:id="@+id/goShoppingList"
            android:layout_width="60dp"
            android:layout_height="40dp"
            android:layout_alignParentTop="true"
            android:layout_alignParentEnd="true"
            android:layout_marginTop="5dp"
            android:layout_marginEnd="7dp"
            android:background="#CCB8F1"
            android:text="🛒"
            android:textSize="20sp"
            android:layout_marginRight="7dp"
            android:layout_alignParentRight="true" />

        <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/textView" />

        <androidx.constraintlayout.widget.ConstraintLayout
            android:id="@+id/menu"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_below="@id/listView"
            android:layout_alignParentStart="true"
            android:layout_alignParentLeft="true"
            android:layout_alignParentBottom="true"
            android:layout_marginStart="-1dp"
            android:layout_marginLeft="-1dp"
            android:layout_marginTop="2dp"
            android:layout_marginBottom="-2dp"
            android:background="#F6DBE4">

            <Button
                android:id="@+id/shoppingcartButton"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:background="#F4BFD1"
                android:text="장바구니"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toStartOf="@+id/purchaseButton"
                app:layout_constraintHorizontal_bias="0.488"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <Button
                android:id="@+id/purchaseButton"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginEnd="72dp"
                android:layout_marginRight="72dp"
                android:background="#F4BFD1"
                android:text="바로 구매"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
        </androidx.constraintlayout.widget.ConstraintLayout>
    </RelativeLayout>
#### -activity_shopping_cart.xml
최상위 레이아웃으로 LinearLayout을 사용하였다. ListView에 상품을 보여주고, 상품 별로 체크박스를 사용하여 제품을 구매 리스트에 담을 수 있다. ShoppingCart.java와 연결되어 작동한다.

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clickable="false"
        android:orientation="vertical"
        tools:context=".ShoppingCart">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:background="#C0C7ED">

            <TextView
                android:id="@+id/주문페이지"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:padding="10sp"
                android:text="장바구니"
                android:textSize="23sp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <Button
                android:id="@+id/homeButton"
                android:layout_width="50dp"
                android:layout_height="40dp"
                android:layout_marginStart="16dp"
                android:layout_marginLeft="16dp"
                android:background="#CCB8F1"
                android:text="🏠"
                android:textSize="20sp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toStartOf="@+id/주문페이지"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.454" />

        </androidx.constraintlayout.widget.ConstraintLayout>


        <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="10"
            android:choiceMode="multipleChoice" >

        </ListView>


        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1.5"
            android:background="#F6DBE4">

            <Button
                android:id="@+id/purchaseButton"
                android:layout_width="0dp"
                android:layout_height="0dp"
                android:background="#F4BFD1"
                android:text="구매하기"
                android:textSize="17sp"
                android:textStyle="bold"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
        </androidx.constraintlayout.widget.ConstraintLayout>

    </LinearLayout>

#### -activity_purchase.xml
최상위 레이아웃으로 TableLayout을 사용하였다. 주소와 연락처를 입력 받는 칸은 GridLayout을 2행 2열을 갖도록 하였다. ListView에 상품을 보여준다. 연락처를 입력 받는 곳에는 숫자만 입력이 가능하도록 설정하고, 주소를 입력 받는 곳에는 기본적으로 한글 자판이 뜨도록 설정하였다. Purchase.java와 연결되어 작동한다.

    <?xml version="1.0" encoding="utf-8"?>
    <TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#00FFFFFF"
        android:orientation="vertical"
        tools:context=".Purchase">

        <TextView
            android:id="@+id/주문페이지"
            android:layout_width="423dp"
            android:layout_height="wrap_content"
            android:background="#C0C7ED"
            android:gravity="center"
            android:padding="10sp"
            android:text="주문자 정보 입력"
            android:textSize="23sp" />

        <GridLayout
            android:layout_height="103dp"
            android:columnCount="2"
            android:orientation="horizontal">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_row="0"
                android:layout_column="0"
                android:padding="10sp"
                android:paddingLeft="30sp"
                android:text="번호: "
                android:textSize="17sp" />


            <EditText
                android:id="@+id/inputPhone"
                android:layout_width="351dp"
                android:layout_height="53dp"
                android:layout_row="0"
                android:layout_column="1"
                android:digits="0123456789"
                android:gravity="center"
                android:hint="' - '  없이  입력"
                android:inputType="phone"
                android:padding="10sp"
                android:textSize="17sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_row="1"
                android:layout_column="0"
                android:padding="10sp"
                android:paddingLeft="30sp"
                android:text="주소: "
                android:textSize="17sp" />

            <EditText
                android:id="@+id/inputAdress"
                android:layout_width="351dp"
                android:layout_height="wrap_content"
                android:layout_row="1"
                android:layout_column="1"
                android:gravity="center"
                android:hint="주소  입력"
                android:inputType="textLongMessage"
                android:privateImeOptions="defaultInputmode=korean"
                android:isScrollContainer="true"
                android:keepScreenOn="true"
                android:padding="10sp"
                android:textSize="17sp" />
        </GridLayout>

        <TextView
            android:id="@+id/textView7"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#C0C7ED"
            android:gravity="center"
            android:padding="10sp"
            android:text="결제 품목 확인"
            android:textSize="23sp" />

        <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="291dp">

        </ListView>
        <TextView
            android:id="@+id/price"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#00B60000"
            android:gravity="right"
            android:padding="10sp"
            android:textColor="#910000"
            android:textSize="20sp"
            android:textStyle="bold" />


        <Button
            android:id="@+id/orderButton"
            android:layout_width="match_parent"
            android:layout_height="62dp"
            android:background="#F8B34E"
            android:text="주문 하기" />
    </TableLayout>
    
#### -product_layout.xml
뷰를 객체화 하기 위한 레이아웃이다. 상품 페이지와 구매 페이지에 사용된다.

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/img"
            android:layout_width="150dp"
            android:layout_height="150dp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:id="@+id/name"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:textSize="17sp"
                android:textStyle="bold" />

            <TextView
                android:id="@+id/price"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:textSize="17sp"
                android:textStyle="italic" />
        </LinearLayout>
    </LinearLayout>
    
#### -productlist_layout.xml
뷰를 객체화 하기 위한 레이아웃이다. 장바구니 페이지에 사용된다. 앞에 있는, product_layout.xml과 차이점은 체크박스의 유무이다.

    <?xml version="1.0" encoding="utf-8"?>
    <com.example.mobileprogramming.CheckableLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/img"
            android:layout_width="150dp"
            android:layout_height="150dp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <CheckBox
                android:id="@id/checkbox"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:focusable="false" />

            <TextView
                android:id="@+id/name"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:textSize="17sp"
                android:textStyle="bold" />

            <TextView
                android:id="@+id/price"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:textSize="17sp"
                android:textStyle="italic" />

        </LinearLayout>
    </com.example.mobileprogramming.CheckableLinearLayout>

### 3.2 스크린샷
상품 선택 페이지에서 상품을 선택하여 장바구니 페이지로 이동하였을 때,<br>
<img src="https://user-images.githubusercontent.com/54922803/97002305-c3c83680-1574-11eb-8092-8c344ee8009f.png" width="30%">
<img src="https://user-images.githubusercontent.com/54922803/97002615-320cf900-1575-11eb-80f6-a562e96e3f34.png" width="30%">
<img src="https://user-images.githubusercontent.com/54922803/97002620-3507e980-1575-11eb-829d-ad826a22d0a1.png" width="30%"> <br><br>
장바구니 페이지에서 구매할 제품을 선택하고 구매 페이지로 이동하였을 때, <br>
<img src="https://user-images.githubusercontent.com/54922803/97002633-39cc9d80-1575-11eb-8924-2449e10737ea.png" width="30%">
<img src="https://user-images.githubusercontent.com/54922803/97002640-3afdca80-1575-11eb-810d-332950cbfea2.png" width="30%"> <br><br>
주문할 때 주소와 연락처를 입력하지 않은 경우와 모든 정보를 입력한 경우, <br>
<img src="https://user-images.githubusercontent.com/54922803/97002651-3e915180-1575-11eb-9097-9a698040078b.png" width="30%">
<img src="https://user-images.githubusercontent.com/54922803/97002655-40f3ab80-1575-11eb-97e7-0ba07569aaba.png" width="30%">
<img src="https://user-images.githubusercontent.com/54922803/97002679-44873280-1575-11eb-8fc0-9d06515c8b96.png" width="30%"> <br><br>
구매를 완료한 경우, 다시 상품 페이지로 돌아온다. <br>
<img src="https://user-images.githubusercontent.com/54922803/97002685-46e98c80-1575-11eb-8a37-9ad7c4bbac66.png" width="30%">
