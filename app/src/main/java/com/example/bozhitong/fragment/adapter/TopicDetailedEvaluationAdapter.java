package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017-05-10.
 */

public class TopicDetailedEvaluationAdapter extends BaseAdapter{
    private ArrayList<String> evlist;
    private Context mContent;
private ViewHolder mHolder;
    public TopicDetailedEvaluationAdapter(ArrayList<String> list,Context mCox){
        this.evlist = list;
        this.mContent = mCox;
    }


    @Override
    public int getCount() {
        return evlist.size();
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
        if (convertView == null)
        {
            mHolder = new ViewHolder();
            convertView = View.inflate(mContent, R.layout.item_layout_topic_detailed,null);
            mHolder.tv_ev = (TextView)convertView.findViewById(R.id.tv_ev);
            convertView.setTag(mHolder);
        }
        else
        {
            mHolder =(ViewHolder) convertView.getTag();
        }
        mHolder.tv_ev.setText(evlist.get(position));
        return convertView;
    }

class ViewHolder {
    TextView tv_ev;
}


}
