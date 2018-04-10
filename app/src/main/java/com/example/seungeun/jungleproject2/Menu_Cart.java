package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//장바구니 화면을 보여주는 프래그 먼트

public class Menu_Cart extends BaseFragment {
    private View view;
    private Button addWishListBtn,wishListBtn,rentalBtn;
    private List<Product> itemList;

    private FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart_view, container, false);

        BtnOnClickListener btnOnClickListener = new BtnOnClickListener();

        //버튼 이벤트
        rentalBtn = (Button) view.findViewById(R.id.rentalBtn);
        rentalBtn.setOnClickListener(btnOnClickListener);
        addWishListBtn = (Button)view.findViewById(R.id.addWishListBtn);
        addWishListBtn.setOnClickListener(btnOnClickListener);
        wishListBtn = (Button)view.findViewById(R.id.showWishListBtn);
        wishListBtn.setOnClickListener(btnOnClickListener);

        //리스트뷰
        ListView listview = (ListView) view.findViewById(R.id.cart_listView);
        itemList = new ArrayList<Product>();
        itemList.add(new Product("카메라","잔여재고 2대"));
        itemList.add(new Product("조명","잔여재고 4대"));
        itemList.add(new Product("카메라","잔여재고 1대"));
        AdapterCartListView adapter = new AdapterCartListView(getActivity().getApplicationContext(),itemList);
        listview.setAdapter(adapter);
        return view;
    }

    //버튼 클릭 이벤트
    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch (id) {
                case R.id.rentalBtn:
                    Toast.makeText(getActivity(), "대여신청", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.addWishListBtn:
                    Toast.makeText(getActivity(), "위시리스트 추가", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.showWishListBtn:
                    //Toast.makeText(getActivity(), "위시리스트 보기", Toast.LENGTH_SHORT).show();
                    startFragment(getFragmentManager(), FragmentWishList.class);
                    break;
            }
        }
    }
}
