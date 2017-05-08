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
 * 小区公告
 * 
 * @author 12306
 * 
 */
public class ResiBullAdapter extends BaseAdapter {

	private ViewHolder holder;
	private List<String> list;
	private Context context;
	private String[] strearry;

	public ResiBullAdapter(Context context, List<String> list) {
		this.list = list;
		this.context = context;

	}

	public ResiBullAdapter(Context context, List<String> list, String[] arry) {
		this.list = list;
		this.context = context;
		this.strearry = arry;
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
					R.layout.resibulladapter_item, null);
			holder.text = (TextView) convertView
					.findViewById(R.id.service_news_tv);
			holder.text2 = (TextView) convertView
					.findViewById(R.id.message_content_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(list.get(position));

		holder.text2.setText(strearry[position]);

		return convertView;
	}

	private static class ViewHolder {
		TextView text;
		TextView text2;
	}

}
