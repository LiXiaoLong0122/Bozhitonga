package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.entity.HousingInformation;

import java.util.List;

/**
 * Created by Administrator on 2017-05-03.
 */

public class PayCostAdapter extends BaseAdapter {
    private  ViewHolder holder;
    private List<HousingInformation> list;
    private Context context;

    public PayCostAdapter(Context context, List<HousingInformation> list) {
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_layout_paycost, null);

            holder.name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.phone = (TextView)convertView.findViewById(R.id.tv_phone);
            holder.address = (TextView)convertView.findViewById(R.id.tv_address);
            holder.status = (TextView)convertView.findViewById(R.id.tv_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText("业主姓名:"+list.get(position).getName());
        holder.phone.setText("电话:"+list.get(position).getPhone());

        holder.address.setText("地址:"+list.get(position).getAddress());




        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView phone;
        TextView address;
        TextView status;
    }
}
