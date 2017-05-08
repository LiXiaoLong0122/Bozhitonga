package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

import java.util.List;

/**
 * @author SunnyCoffee
 * @date 2014-2-2
 * @version 1.0
 * @desc 适配器
 * 
 */
public class ListViewAdapter extends BaseAdapter {

	private ViewHolder holder;
	private List<String> list;
	private Context context;

	public ListViewAdapter(Context context, List<String> list) {
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
					R.layout.listview_item, null);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.text2 = (TextView) convertView
					.findViewById(R.id.tfanghao_iv);
			holder.tfangmg = (TextView) convertView
					.findViewById(R.id.tfangm_iv);
			holder.imagev = (ImageView) convertView
					.findViewById(R.id.housing_iv);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(list.get(position));
		String[] strArray = { "房号：高科尚都摩卡二栋209", "房号：高科尚都摩卡二栋210",
				"房号：高科尚都摩卡四栋210", "房号：高科尚都摩卡四栋216" };
		String[] strtfangm = { "1000.00元/月", "1400000.00元/套", "1600.00元/月",
				"1700000.00元/套" };

		holder.text2.setText(strArray[position]);
		holder.tfangmg.setText(strtfangm[position]);

		if (list.indexOf(list.get(position)) % 2 == 0) {

			holder.imagev.setBackgroundResource(R.drawable.rent);

		} else {
			holder.imagev.setBackgroundResource(R.drawable.sell);
		}

		return convertView;
	}

	private static class ViewHolder {
		TextView text;
		TextView text2;
		TextView tfangmg;
		ImageView imagev;
	}

}
