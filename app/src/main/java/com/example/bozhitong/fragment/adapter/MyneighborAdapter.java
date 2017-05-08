package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-03.
 */

public class MyneighborAdapter extends BaseAdapter {
    private ArrayList<String> neighborList = new ArrayList<String>();
    private Context mcontext;
    private  ViewHolder mviewHolder;
    public  MyneighborAdapter(Context m, ArrayList<String> ne){
        this.neighborList = ne;
        this.mcontext = m;
    }

    @Override
    public int getCount() {
        return neighborList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            mviewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_layout_neighbor,null);
            mviewHolder.name =(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(mviewHolder);
        }else {
            mviewHolder= (ViewHolder)convertView.getTag();
        }
        mviewHolder.name.setText(neighborList.get(position));

        return convertView;
    }
    private static class ViewHolder {
        TextView name;

    }
}
