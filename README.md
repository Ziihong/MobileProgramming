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
|Product.java|상품 클래스. 상품 정보를 관리|
|CheckableLinearLayout.java|Checkable 인터페이스를 상속하여 새로 구성한 LinearLayout 클래스|
|ProductList.java|상품 페이지와 구매 페이지에서 상품 나열을 위한 adapter 클래스|
|CheckProductList.java|장바구니 페이지에서 상품 나열을 위한 adapter 클래스|

### 2.2 소스 파일 설명
#### -MainActivity.java
결제할 리스트와 장바구니에 담을 리스트를 static을 사용하여 생성하였다.
'''java

    //결제할 제품
    static ArrayList<Product> shoppingList = new ArrayList<>();
    //장바구니 제품
    static ArrayList<Product> cartList = new ArrayList<>();
 '''
 addItem함수를 사용하여 항목을 추가한 adapter을, 상품을 보여주는 ListView에 적용하였다. 제품이 클릭되었을 때 해당 제품을 저장하고, 장바구니와 구매 중 선택하라는 메세지가 뜬다.
'''java

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

'''
#### -ShoppingCart.java

## 3. UI 및 스크린샷
### 3.1 UI 설계
### 3.2 스크린샷
