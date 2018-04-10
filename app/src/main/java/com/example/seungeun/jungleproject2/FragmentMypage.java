package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentMypage extends Fragment {

    private View view;
    private List<Info_Mypage_CertificateInfo> itemList1, itemList2;
    private ListView listView1, listView2;

    //private Button searchBtn;
   // private EditText searchEditText;
    //private TextView warningText;

    //private String searchWord;

    //private final String urlName = ".php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mypage, container, false);

        listView1 = (ListView) view.findViewById(R.id.mypage_applying_list);
        listView1.setVisibility(view.VISIBLE); // 대여신청목록
        listView2 = (ListView) view.findViewById(R.id.mypage_applying_list_past);
        listView2.setVisibility(view.VISIBLE); // 이전대여목록

        itemList1 = new ArrayList<Info_Mypage_CertificateInfo>();
        itemList2 = new ArrayList<Info_Mypage_CertificateInfo>();

        addItem();

        return view;
    }

    public void addItem(){

        itemList1.clear();
        itemList2.clear();


        //아직 모바일 확인증 db가 없어서 임의로 집어넣음
        itemList1.add(new Info_Mypage_CertificateInfo(123, "영상연출 영화 제작", "5DMARK3/배터리 2개/MEGA LED/레코더/믹서" ,"승인대기"));
        itemList1.add(new Info_Mypage_CertificateInfo(112, "영상연출 영화 제작", "MEGA LED/레코더/믹서" ,"승인실패"));
        itemList1.add(new Info_Mypage_CertificateInfo(122, "영상미학 뮤직비디오 제작", "5DMARK3/삼각대 3개/믹서","승인완료"));
        itemList1.add(new Info_Mypage_CertificateInfo(131, "영상연출 영화 제작", "믹서" ,"대여중"));

        
        itemList2.add(new Info_Mypage_CertificateInfo(133, "UCC 공모전 출품", "5DMARK3/배터리 2개/MEGA LED/레코더/믹서","반납완료"));
        itemList2.add(new Info_Mypage_CertificateInfo(213, "17정글 뮤지컬 영화 제작", "5DMARK3/삼각대 3개/믹서","반납지연"));

        AdapterMyPage adapter1 = new AdapterMyPage(getActivity().getApplicationContext(), itemList1);
        listView1.setAdapter(adapter1);
        AdapterMyPage adapter2 = new AdapterMyPage(getActivity().getApplicationContext(), itemList2);
        listView2.setAdapter(adapter2);

        setListViewHeightBasedOnChildren(listView1);
        setListViewHeightBasedOnChildren(listView2);

    }

    // 리스트뷰의 목록 개수가 바뀔때마다 리스트뷰의 heignt를 갱신해서 업데이트 해주는 함수
    // 스크롤뷰가 우리가 원하는 방식으로 제대로 작동하게 기여함
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /*// onCreateView가 실행된 이후(view가 생성된 이후)에 자동으로 실행되는 함수
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //모바일 대여 확인증으로 가는 버튼 클릭시
        TextView textbtn = (TextView)view.findViewById(R.id.mypage_goto_certificate);
        textbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //insertNestedFragment();

            }
        });
    }*/

    /*// Embeds the child fragment dynamically
    public void insertNestedFragment() {
        Fragment childFragment = new FragmentMobileCertificate();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.mypage_mCertificate_container, childFragment).commit();
    }*/


}

