package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.entity.HousingInformation;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-04.
 */

public class AddHouseAdapter extends BaseAdapter {
    private ArrayList<HousingInformation> houseList ;
    private Context mContent;
    private ViewHolder mHolder;
    private int index;

    public AddHouseAdapter(Context mCon, ArrayList<HousingInformation> hoList) {
        this.houseList = hoList;
        this.mContent = mCon;
    }

    @Override
    public int getCount() {
        return houseList.size();
    }

    @Override
    public Object getItem(int position) {
        return houseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContent).inflate( R.layout.item_addhouse, null);
            mHolder.tv_community = (TextView) convertView.findViewById(R.id.tv_community);
            mHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            mHolder.tv_identity = (TextView) convertView.findViewById(R.id.tv_identity);
            mHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
          //  mHolder.cb_isdefault = (CheckBox) convertView.findViewById(R.id.cb_isdefault);
            mHolder.ll_delete = (LinearLayout) convertView.findViewById(R.id.ll_delete);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        HousingInformation house = houseList.get(position);
        index = position;
        mHolder.tv_community.setText(house.getCommunity());
        mHolder.tv_address.setText(house.getAddress());
        mHolder.tv_identity.setText(house.getIdentity());
        mHolder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseList.remove(index);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    class ViewHolder {
        TextView tv_community;
        TextView tv_address;
        TextView tv_identity;
        TextView tv_status;
        CheckBox cb_isdefault;
        LinearLayout ll_delete;
    }

}
