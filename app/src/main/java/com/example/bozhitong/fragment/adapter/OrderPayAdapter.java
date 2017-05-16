package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-16.
 */

public class OrderPayAdapter extends BaseAdapter {
    private ArrayList<Integer> list;
    private Context mContent;
private ViewHolder mholder;


    public OrderPayAdapter(Context mContent,ArrayList<Integer> list) {
       this.list = list;
        this.mContent = mContent;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
               mholder = new ViewHolder();
            convertView = View.inflate(mContent, R.layout.item_layout_orderpay,null);
            mholder.tv_ordername =(TextView) convertView.findViewById(R.id.tv_ordername);
            mholder.tv_orderprice = (TextView)convertView.findViewById(R.id.tv_orderprice);
            convertView.setTag(mholder);
        }else {
            mholder =(ViewHolder) convertView.getTag();
        }
        mholder.tv_ordername.setText("积分充值 "+list.get(position)+"分 X 1");
        mholder.tv_orderprice.setText("5.0"+"元");
        return convertView;
    }
    class ViewHolder{
        TextView tv_ordername,tv_orderprice;
    }
}
