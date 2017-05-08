package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.List;

/**
 * Created by Administrator on 2017-04-28.
 */

public class StringListAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<String> list;
    private Context context;


    public StringListAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
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
            holder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(
                    R.layout.layout_item_tv, null);
            holder.text = (TextView)convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.text.setText(list.get(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView text;

    }
}
