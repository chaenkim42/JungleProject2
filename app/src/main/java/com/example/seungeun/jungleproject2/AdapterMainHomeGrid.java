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

public class AdapterMainHomeGrid extends BaseAdapter {
    private Context context;
    private List<Product> itemList;
    private Fragment parent;

    public AdapterMainHomeGrid(Context context, List<Product> itemList/*Fragment parent*/) {
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
        View view = View.inflate(context,R.layout.gridview_home_item,null);

        //초기화
        ImageView image = (ImageView)view.findViewById(R.id.equip_image);
        TextView nameText = (TextView)view.findViewById(R.id.equip_name);
        TextView stockText = (TextView)view.findViewById(R.id.equip_stock);
        TextView stockNumText = (TextView)view.findViewById(R.id.equip_stock_num);

        //image.setImageDrawable();
        nameText.setText(itemList.get(position).getEquip_name());
        stockNumText.setText(itemList.get(position).getStockNumStr());


        view.setTag(itemList.get(position).getEquip_name());
        return view;
    }

    /*public void clearAdapter(){
        itemList.clear();
    }*/
}
