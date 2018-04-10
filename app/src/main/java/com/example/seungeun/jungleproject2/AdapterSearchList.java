package com.example.seungeun.jungleproject2;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Seungeun on 2017-08-01.
 */

public class AdapterSearchList extends BaseAdapter {
    private Context context;
    private List<Product> itemList;
    private Fragment parent;

    public AdapterSearchList(Context context, List<Product> itemList/*Fragment parent*/) {
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
        View view = View.inflate(context,R.layout.list_search_item,null);

        //초기화
        ImageView image = (ImageView)view.findViewById(R.id.equip_image_search);
        TextView nameText = (TextView)view.findViewById(R.id.equip_name_search);
        TextView stockText = (TextView)view.findViewById(R.id.equip_stock_search);
        Button addCartButton = (Button)view.findViewById(R.id.addCartBtn_search);

        //image.setImageDrawable();
        nameText.setText(itemList.get(position).getEquip_name());

        //버튼 이벤트
        addCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"장바구니 추가 버튼",Toast.LENGTH_SHORT).show();
            }
        });

        view.setTag(itemList.get(position).getEquip_name());
        return view;
    }

    /*public void clearAdapter(){
        itemList.clear();
    }*/
}
