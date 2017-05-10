package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017-05-10.
 */

public class HelpAdapter extends android.widget.BaseAdapter {
    private ArrayList<String> list ;
    private Context cen;
    private ViewHolder mholder;

    public HelpAdapter(Context cen, ArrayList<String> list){
        this.list = list;
        this.cen = cen;
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
        if (convertView == null)
        {
            mholder = new ViewHolder();
            convertView = View.inflate(cen, R.layout.item_layout_help,null);
            mholder.text = (TextView) convertView.findViewById(R.id.text);
            mholder.tvaddress = (TextView)convertView.findViewById(R.id.tv_address);
            mholder.iv = (ImageView)convertView.findViewById(R.id.iv_tup);
            convertView.setTag(mholder);
        }else
        {
            mholder = (ViewHolder)convertView.getTag();
        }

        mholder.text.setText(list.get(position));
        mholder.tvaddress.setText("发生地点：明溪花园");
        mholder.iv.setImageResource(R.drawable.tyzj);

        return convertView;
    }

    class ViewHolder {
        TextView tvname,text,tvaddress;
        ImageView iv;

    }

}
