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
 * @author SunnyCoffee
 * @date 2014-2-2
 * @version 1.0
 * @desc 适配器
 * 
 */
public class ListViewAService extends BaseAdapter {

	private ViewHolder holder;
	private List<String> list;
	private Context context;
	private List<String> strayy = null;

	public ListViewAService(Context context, List<String> list) {
		this.list = list;
		this.context = context;
	}

	public ListViewAService(Context context, List<String> list,
							List<String> list1) {
		this.list = list;
		this.context = context;
		this.strayy = list1;
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
					R.layout.listlaunch_item, null);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.text1 = (TextView) convertView.findViewById(R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text
				.setText("\u3000\u3000婴幼儿服务中心主要经营：等产品。公司尊崇“踏实、拼搏、责任”的企业精神，并以诚信、共赢、开创经营理念，创造良好的企业环境，以全新的管理模式，完善的技术，周到的服务，卓越的品质为生存根本，我们始终坚持用户至上 用心服务于客户，坚持用自己的服务去打动客户。");
		holder.text1.setText(list.get(position));
		return convertView;
	}

	private static class ViewHolder {
		TextView text;
		TextView text1;
	}

}
