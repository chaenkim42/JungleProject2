package com.example.seungeun.jungleproject2;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterCartListView extends BaseAdapter {
    private Context context;
    private List<Product> itemList;

    public AdapterCartListView(Context context, List<Product> itemList) {
        this.context = context;
        this.itemList = itemList;
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
        View view = View.inflate(context,R.layout.list_cart_item,null);

        //초기화
        ImageView image = (ImageView)view.findViewById(R.id.equip_image_cart);
        TextView nameText = (TextView)view.findViewById(R.id.equip_name_cart);
        TextView stockText = (TextView)view.findViewById(R.id.equip_stock_cart);
        Button deleteBtn = (Button)view.findViewById(R.id.deleteBtn);

        //image.setImageDrawable();
        nameText.setText(itemList.get(position).getEquip_name());
        stockText.setText(itemList.get(position).getStockNumStr());


        //버튼 이벤트
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"삭제 버튼 클릭",Toast.LENGTH_SHORT).show();
            }
        });


        view.setTag(itemList.get(position).getEquip_name());
        return view;
    }
}
