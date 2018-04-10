package com.example.seungeun.jungleproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Seungeun on 2017-08-07.
 */

public class AdapterWishExpandList extends BaseExpandableListAdapter {

    private Context mContext;
    private List<WishListFolder> folderList;
    private LayoutInflater inflater;

    //class Constructor
    public AdapterWishExpandList (Context mContext, List<WishListFolder> folderList) {

        this.mContext = mContext;
        this.folderList = folderList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return folderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return folderList.get(groupPosition).childList.size();
    }

    //get position
    @Override
    public Object getGroup(int groupPosition) {
        return folderList.get(groupPosition);
    }

    //this is where we get the information of player
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return folderList.get(groupPosition).childList.get(childPosition);
    }

    //position ID
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    //where to get player's id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //get parent row
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.wish_list_parent, null);
        }

        return convertView;
    }

    //get child_list.xml (View)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //inflate the layout
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.wish_list_child, null);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

