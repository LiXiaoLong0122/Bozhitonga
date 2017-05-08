package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-05.
 */

public class FamilyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> mfamily = new ArrayList<String>();
    private HolderView mholder;

    public FamilyAdapter(Context con, ArrayList<String> mfamily) {
        this.context = con;
        this.mfamily = mfamily;
    }

    @Override
    public int getCount() {
        return mfamily.size();
    }

    @Override
    public Object getItem(int position) {
        return mfamily.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mholder = new HolderView();
            convertView = LinearLayout.inflate(context, R.layout.item_layout_family, null);
            mholder.name = (TextView) convertView.findViewById(R.id.tv_name);
            mholder.relations = (TextView) convertView.findViewById(R.id.tv_relations);
            convertView.setTag(mholder);
        } else {
            mholder = (HolderView) convertView.getTag();
        }

        mholder.name.setText(mfamily.get(position));
        mholder.relations.setText("弟弟");

        return convertView;
    }

    class HolderView {
        TextView name;
        TextView relations;
    }

}
