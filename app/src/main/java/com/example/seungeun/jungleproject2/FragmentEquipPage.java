package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

// 장비 상세페이지를 나타내는 페이지

public class FragmentEquipPage extends Fragment {
    private View view;
    private Product selectedProduct;
    private TextView equipName;
    private Button specBtn,useBtn,noticeBtn,commentBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_equip_page_view, container, false);

        BtnOnClickListener btnOnClickListener = new BtnOnClickListener();

        //버튼 이벤트
        specBtn = (Button)view.findViewById(R.id.equip_page_detail);
        specBtn.setOnClickListener(btnOnClickListener);
        useBtn = (Button)view.findViewById(R.id.equip_page_solution);
        useBtn.setOnClickListener(btnOnClickListener);
        noticeBtn = (Button)view.findViewById(R.id.equip_page_notice);
        noticeBtn.setOnClickListener(btnOnClickListener);
        commentBtn = (Button)view.findViewById(R.id.equip_page_comment);
        commentBtn.setOnClickListener(btnOnClickListener);

        //텍스트 뷰
        equipName = (TextView)view.findViewById(R.id.selected_equip_name);
        equipName.setText(selectedProduct.getEquip_name());

        //이미지 도 위와 마찬가지로 넣어주기.

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null && getActivity() instanceof Menu_MainHome) {
            selectedProduct = ((Menu_MainHome) getActivity()).getSelectedProductData();
        }
    }

    //버튼 클릭 이벤트
    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            switch (id) {
                case R.id.equip_page_detail:
                    break;
                case R.id.equip_page_solution:
                    break;
                case R.id.equip_page_notice:
                    break;
                case R.id.equip_page_comment:
                    break;
            }
        }
    }
}
