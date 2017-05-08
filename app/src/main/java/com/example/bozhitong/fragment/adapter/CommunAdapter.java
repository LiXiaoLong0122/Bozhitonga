package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.HashMap;

/**
 * 小区调研适配器
 * 
 * @author 12306
 * 
 */
public class CommunAdapter extends BaseAdapter {

	private ViewHolder holder;
	private HashMap<String, String> list;
	private Context context;

	public CommunAdapter(Context context, HashMap<String, String> list) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		if(list == null){
			return 0;
		}else {
			return list.size();
		}

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
					R.layout.communadapter_item, null);
			holder.text = (TextView) convertView.findViewById(R.id.commun_lstv);
			holder.text1 = (TextView) convertView
					.findViewById(R.id.commun1_lstv);
			holder.text2 = (TextView) convertView
					.findViewById(R.id.commun2_lstv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(list.get("id"));
		holder.text1.setText(list.get("MingCheng"));
		holder.text2.setText(list.get("JieZhi"));
		return convertView;
	}

	private static class ViewHolder {
		TextView text;
		TextView text1;
		TextView text2;
	}

}
