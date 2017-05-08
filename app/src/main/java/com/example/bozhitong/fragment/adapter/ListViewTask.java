package com.example.bozhitong.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.activity.TaskDetailedActivity;

import java.util.List;

public class ListViewTask extends BaseAdapter {

	private ViewHolder holder;
	private List<String> list;
	private Context context;

	public ListViewTask(Context context, List<String> list) {
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
					R.layout.listview_tasksitem, null);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.ll_task = (LinearLayout)convertView.findViewById(R.id.ll_task);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(list.get(position));
		holder.ll_task.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, TaskDetailedActivity.class);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	private static class ViewHolder {
		TextView text;
		LinearLayout ll_task;
	}

}
