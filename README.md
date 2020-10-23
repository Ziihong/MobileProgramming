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
구매하려는 제품이 하나일 때는 상품 페이지에서 원하는 제품 선택 후 구매 버튼을 눌러, 바로 구매 페이지로 이동할 수 있다. 구매하려는 제품이 2개 이상일 경우, 원하는 제품을 차례대로 장바구니에 담은 후 장바구니 페이지에서 
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

일부 뷰를 객체화 하기 위해 LayoutInflater.inflate 메소드를 사용용한다. 원하는 viewGroup에 직접 작성한 productlist_layout.xml을 적용시킨다. 

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
### 3.2 스크린샷
