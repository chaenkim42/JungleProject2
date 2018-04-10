package com.example.seungeun.jungleproject2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Menu_MainHome extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomBar;
    private BackPressCloseHandler backPress;

    private EditText searchEditText;
    private RelativeLayout homeLayout, fragmentMenuLayout,fragmentETCLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //그리드 뷰
    private GridView gridView;
    private AdapterMainHomeGrid adapter;
    private List<Product> itemList;

    //카테고리
    private Button categoryBtn1, categoryBtn2, categoryBtn3, categoryBtn4, categoryBtn5, categoryBtn6;

    //검색창
    private ImageButton searchBtn;
    private EditText searchText;

    private Product selectedProduct; //선택한 상품 정보에 대한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //액션바 설정
        getSupportActionBar().setTitle("미디어학과 장비대여");

        //뒤로가기
        backPress = new BackPressCloseHandler(this);

        //프래그먼트
        homeLayout = (RelativeLayout) findViewById(R.id.home); //메인 홈
        fragmentMenuLayout = (RelativeLayout) findViewById(R.id.fragment_menu); // 교체되는 화면 부분_아래 하단바 일때
        fragmentETCLayout = (RelativeLayout)findViewById(R.id.fragment_etc);
        fragmentManager = getFragmentManager();

        //그리드 뷰
        gridView = (GridView) findViewById(R.id.customGridView);
        itemList = new ArrayList<Product>();
        String url = "equipment_all_select.php";
        addItem(url);
        gridView.setOnItemClickListener(this); //아이템 클릭 리스너
        //gridView.setSelectionFromTop(0, 0); //그리드뷰 상단으로 올리는 것.

        //하단바
        bottomBar = (BottomNavigationView) findViewById(R.id.bottom_nav);
        BottomNavigationViewHelper.disableShiftMode(bottomBar); //애니메이션 없애 주는 것
        bottomBar = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomBar.setOnNavigationItemSelectedListener(this);

        //버튼
        categoryBtn1 = (Button) findViewById(R.id.category_item1);
        categoryBtn1.setOnClickListener(this);
        categoryBtn2 = (Button) findViewById(R.id.category_item2);
        categoryBtn2.setOnClickListener(this);
        categoryBtn3 = (Button) findViewById(R.id.category_item3);
        categoryBtn3.setOnClickListener(this);
        categoryBtn4 = (Button) findViewById(R.id.category_item4);
        categoryBtn4.setOnClickListener(this);
        categoryBtn5 = (Button) findViewById(R.id.category_item5);
        categoryBtn5.setOnClickListener(this);
        categoryBtn6 = (Button) findViewById(R.id.category_item6);
        categoryBtn6.setOnClickListener(this);

        //검색 창
        searchText = (EditText) findViewById(R.id.search_editText_main);
        searchBtn = (ImageButton)findViewById(R.id.search_btn_main);
        searchBtn.setOnClickListener(this);



    }

    //그리드 뷰 아이템 클릭 이벤트
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this ,itemList.get(position).getEquip_name(),Toast.LENGTH_SHORT).show();
        selectedProduct = itemList.get(position);
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.fragment_etc, new FragmentEquipPage()).commit();
        homeLayout.setVisibility(View.GONE);
        fragmentETCLayout.setVisibility(View.VISIBLE);
        fragmentMenuLayout.setVisibility(View.GONE);
    }

    //아래 버튼 바 클릭 이벤트
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                homeLayout.setVisibility(View.VISIBLE);
                fragmentETCLayout.setVisibility(View.GONE);
                fragmentMenuLayout.setVisibility(View.GONE);
                //스택 모두 제거하기
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                return true;

            case R.id.action_mypage:
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_menu, new FragmentMypage()).commit();
                homeLayout.setVisibility(View.GONE);
                fragmentETCLayout.setVisibility(View.GONE);
                fragmentMenuLayout.setVisibility(View.VISIBLE);
                //스택 모두 제거하기
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                return true;

            case R.id.action_chat:
                homeLayout.setVisibility(View.GONE);
                fragmentETCLayout.setVisibility(View.GONE);
                fragmentMenuLayout.setVisibility(View.VISIBLE);
                //스택 모두 제거하기
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                return true;

            case R.id.action_board:
                homeLayout.setVisibility(View.GONE);
                fragmentETCLayout.setVisibility(View.GONE);
                fragmentMenuLayout.setVisibility(View.VISIBLE);
                //스택 모두 제거하기
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                return true;
        }
        return false;
    }

    //액션바에 메뉴 추가 하기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.cartBtn:
                //Toast.makeText(this, "장바구니 클릭", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction()
                        .addToBackStack("add_stack")
                        .add(R.id.fragment_etc, new Menu_Cart()).commit();
                homeLayout.setVisibility(View.GONE);
                fragmentETCLayout.setVisibility(View.VISIBLE);
                fragmentMenuLayout.setVisibility(View.GONE);
                return true;

            case R.id.settigBtn:
                Toast.makeText(this, "설정 클릭", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //뒤로가기 버튼
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
            Log.i("pop stack", "ok");
        }
        else if (getFragmentManager().getBackStackEntryCount() == 1) {
            getFragmentManager().popBackStack();
            homeLayout.setVisibility(View.VISIBLE);
        }else {
            backPress.onBackPressed();
        }

    }

    //버튼 클릭 이벤트
    @Override
    public void onClick(View v) {
        int id = v.getId();
        String url;

        switch (id) {
            case R.id.category_item1:
                url = "equipment_cam_select.php";
                addItem(url);
                break;
            case R.id.category_item2:
                url = "equipment_came_select.php";
                addItem(url);
                break;
            case R.id.category_item3:
                url = "equipment_etc_select.php";
                addItem(url);
                break;
            case R.id.category_item4:
                url = "equipment_lig_select.php";
                addItem(url);
                break;
            case R.id.category_item5:
                url = "equipment_lige_select.php";
                addItem(url);
                break;
            case R.id.category_item6:
                url = "equipment_sou_select.php";
                addItem(url);
                break;
            case R.id.search_btn_main:
                //Toast.makeText(Menu_MainHome.this,"검색버튼 클릭",Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction()
                        .addToBackStack("add_stack")
                        .add(R.id.fragment_etc, new FragmentSearch()).commit();
                homeLayout.setVisibility(View.GONE);
                fragmentETCLayout.setVisibility(View.VISIBLE);
                fragmentMenuLayout.setVisibility(View.GONE);
                break;
        }
    }

    //DB에서 제품 목록 받아와 보여주는 함수
    public void addItem(final String urlName) {
        runOnUiThread(new Runnable() {
            DataRead itemRead = new DataRead();
            Handler handler = new Handler();

            @Override
            public void run() {
                itemList.clear();
                itemRead.execute(urlName);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //로딩중 화면 보여주기.
                        try {
                            String resultPostResponse = itemRead.getPostResponseResult();
                            String[] spiltRow = resultPostResponse.split("<br>");

                            //행을 뽑아냄
                            for (int i = 0; i < spiltRow.length; i++) {
                                System.out.format("spiltRow[%d] = %s%n", i, spiltRow[i]);
                                //"!!"기준으로 변수들 분류
                                // [0]모델명, [1]이미지경로, [2]현재 가지고 있는 재고 수, [3]반납예정 재고
                                String[] spiltCol = spiltRow[i].split("!");
                                int stock =  Integer.parseInt(spiltCol[2]) + Integer.parseInt(spiltCol[3]);
                                Product temp = new Product(/*앞에 이미지,*/spiltCol[0], Integer.toString(stock) + "대");

                                itemList.add(temp);
                                System.out.format("spiltCol[%d] = %s%n", 0, spiltCol[0]);
                                System.out.format("spiltCol[%d] = %s%n", 1, spiltCol[1]);
                            }

                            adapter = new AdapterMainHomeGrid(getApplicationContext(), itemList);
                            gridView.setAdapter(adapter);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 800);
            }
        });
    }

    public String getSearchData(){
        Log.i("getSearchData ", "getSearchData ok!!!");
        return searchText.getText().toString().trim(); //trim이 스페이스도 있으면 안됨!
    }

    public Product getSelectedProductData(){
        return selectedProduct;
    }

}
