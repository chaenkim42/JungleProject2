package com.example.seungeun.jungleproject2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungeun on 2017-08-02.
 */

public class FragmentSearch extends Fragment {
    private View view;
    private List<Product> itemList;
    private ListView listView;

    private Button searchBtn;
    private EditText searchEditText;
    private TextView warningText;

    private String searchWord;

    private final String urlName = "equipment_searching.php";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_view, container, false);

        searchEditText = (EditText) view.findViewById(R.id.search_editText);
        searchBtn = (Button) view.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord = searchEditText.getText().toString();
                if (searchWord.equals("")) {
                    listView.setVisibility(view.GONE);
                    warningText.setVisibility(view.VISIBLE);
                    warningText.setText("검색어를 입력하세요.");
                } else {
                    listView.setVisibility(view.VISIBLE);
                    warningText.setVisibility(view.GONE);
                    addItem(urlName);
                }
            }
        });

        warningText = (TextView) view.findViewById(R.id.search_message);

        listView = (ListView) view.findViewById(R.id.searchListView);
        itemList = new ArrayList<Product>();

        if (searchWord.equals("")) {
            listView.setVisibility(view.GONE);
            warningText.setVisibility(view.VISIBLE);
            warningText.setText("검색어를 입력하세요.");
        } else {
            addItem(urlName);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null && getActivity() instanceof Menu_MainHome) {
            searchWord = ((Menu_MainHome) getActivity()).getSearchData();
            //Log.i("메인에서 검색창 정보",searchWord);
        }

    }

    public void addItem(final String urlName) {
        final DataRead itemRead = new DataRead();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                itemList.clear();
                itemRead.execute(urlName, "search_word", searchWord);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //로딩중 화면 보여주기.
                        try {
                            String resultPostResponse = itemRead.getPostResponseResult();
                            if (resultPostResponse.equals(null)) {
                                listView.setVisibility(view.GONE);
                                warningText.setVisibility(view.VISIBLE);
                                warningText.setText("검색 결과가 없습니다.");
                            } else {
                                listView.setVisibility(view.VISIBLE);
                                warningText.setVisibility(view.GONE);

                                String[] spiltRow = resultPostResponse.split("<br>");

                                //행을 뽑아냄
                                //0부터 해야 하는데 맨 앞에 이상한 값 들어가서..!
                                for (int i = 2; i < spiltRow.length; i++) {
                                    System.out.format("spiltRow[%d] = %s%n", i, spiltRow[i]);
                                    //"!"기준으로 변수들 분류
                                    String[] spiltCol = spiltRow[i].split("!");
                                    Product temp = new Product(/*앞에 이미지,*/spiltCol[0], spiltCol[2] + "대");

                                    itemList.add(temp);
                                    System.out.format("spiltCol[%d] = %s%n", 0, spiltCol[0]);//모델명
                                    System.out.format("spiltCol[%d] = %s%n", 1, spiltCol[1]);//이미지경로
                                    System.out.format("spiltCol[%d] = %s%n", 2, spiltCol[2]);//잔여재고
                                    System.out.format("spiltCol[%d] = %s%n", 3, spiltCol[3]);//반납예정일에 잔여재고
                                }

                                AdapterSearchList adapter = new AdapterSearchList(getActivity().getApplicationContext(), itemList);
                                listView.setAdapter(adapter);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 700);
            }
        });
    }

}



