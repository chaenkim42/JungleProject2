package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// 위시리스트를 보여주는 화면

public class FragmentWishList extends BaseFragment {
    private View view;
    private Button rentalBtn;
    //private List<Product> itemList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wish_list_view, container, false);

        BtnOnClickListener btnOnClickListener = new BtnOnClickListener();

        //버튼 이벤트

        ExpandableListView elv = (ExpandableListView)view.findViewById(R.id.elv);

        final List<WishListFolder> position = getData();


        //create and bind to adatper
        AdapterWishExpandList adapter = new AdapterWishExpandList(getActivity(), position);
        elv.setAdapter(adapter);

        //set onclick listener
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), position.get(groupPosition).childList.get(childPosition).getEquip_name(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return view;
    }

    //add and get data for list
    private List<WishListFolder> getData() {

        WishListFolder p1 = new WishListFolder("분류1");
        Product temp1 = new Product("테스트제품명1","잔여재고3");
        p1.childList.add(temp1);
        p1.childList.add(temp1);
        p1.childList.add(temp1);

        WishListFolder p2 = new WishListFolder("분류2");
        Product temp2 = new Product("테스트제품명2","잔여재고3");
        p2.childList.add(temp2);

        WishListFolder p3 = new WishListFolder("분류3");
        Product temp3 = new Product("테스트제품명3","잔여재고3");
        p3.childList.add(temp3);

        WishListFolder p4 = new WishListFolder("분류4");
        Product temp4 = new Product("테스트제품명4","잔여재고3");
        p4.childList.add(temp4);


        List<WishListFolder> allposition = new ArrayList<>();
        allposition.add(p1);
        allposition.add(p2);
        allposition.add(p3);
        allposition.add(p4);

        return allposition;
    }

    //버튼 클릭 이벤트
    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch (id) {
            }
        }
    }
}
