package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Seungeun on 2017-08-01.
 */

public class AdapterMyPage extends BaseAdapter {
    private Context context;
    private List<Info_Mypage_CertificateInfo> itemList;
    private Fragment parent;

    public AdapterMyPage(Context context, List<Info_Mypage_CertificateInfo> itemList/*Fragment parent*/) {
        this.context = context;
        this.itemList = itemList;
        //this.parent = parent;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context,R.layout.list_mypage_rental_applying,null);

        //초기화
        TextView applyformName = (TextView)view.findViewById(R.id.mypage_rental_applyform_name);
        TextView equiptList = (TextView)view.findViewById(R.id.mypage_rental_equip_list);
        TextView rentalState = (TextView)view.findViewById(R.id.mypage_rental_state);
        TextView gotoCertificate = (TextView)view.findViewById(R.id.mypage_goto_certificate);

        applyformName.setText(itemList.get(position).getTitle());
        rentalState.setText(itemList.get(position).getProgress_state());
        equiptList.setText(itemList.get(position).getEquip_list());
        String rentalStateText = rentalState.getText().toString();
        if(rentalStateText.equals("승인대기") || rentalStateText.equals("반납완료")  || rentalStateText.equals("반납지연") ){
            gotoCertificate.setVisibility(View.INVISIBLE);
            gotoCertificate.setClickable(false);
        }
        if(rentalStateText.equals("승인실패")){
            gotoCertificate.setText("미승인 사유");
        }

        /*//버튼 이벤트
        gotoCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            *//*FragmentMypage.insertNestedFragment
            Fragment childFragment = new FragmentMobileCertificate();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.mypage_mCertificate_container, childFragment).commit();*//*

            }
        });*/

        //view.setTag(itemList.get(position).getEquip_name());
        return view;
    }



    /*public void clearAdapter(){
        itemList.clear();
    }*/
}
