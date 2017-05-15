package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.activity.OtherUserDetailActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-12.
 */

public class TalKAdapter extends BaseAdapter{
    private ArrayList<String> itemList ;
    private Context mcontent;
    private ViewHolder  mholder;
    public  TalKAdapter(Context con,ArrayList<String> items){
        this.mcontent = con;
        this.itemList = items;
    }

    @Override
    public int getCount() {
        return itemList.size();
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
        if(convertView == null){
            mholder = new ViewHolder();
            convertView = View.inflate(mcontent, R.layout.item_layout_talk,null);
            mholder.text = (TextView) convertView.findViewById(R.id.text);
            mholder.iv_head = (ImageView)convertView.findViewById(R.id.iv_head);
            convertView.setTag(mholder);
        }else {
            mholder = (ViewHolder)convertView.getTag();
        }

        mholder.text.setText(itemList.get(position));
        mholder.iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mcontent, OtherUserDetailActivity.class);
                mcontent.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView text;
        ImageView iv_head;
    }

}
