package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bozhitong.R;
import com.example.bozhitong.activity.TopicDetailedActivity;
import com.example.bozhitong.fragment.adapter.ListViewTask;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动
 * 
 * @author 12306
 * 
 */
public class TasksFragment extends Fragment {
	private ListView lstv;
	private ListViewTask adapter;
	private Context mContext;
	private View view;
	private List<String> list = new ArrayList<String>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_tasks, null);
		lstv = (ListView) view.findViewById(R.id.tasks_lstv);

		initData();
		adapter = new ListViewTask(mContext, list);
		lstv.setAdapter(adapter);
		lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				intent.setClass(mContext,TopicDetailedActivity.class);
				startActivity(intent);
			}
		});



		return view;
	}

	private List<String> initData() {
		for (int i = 0; i < 10; i++) {
			list.add("这是第" + i + "条数据");
		}
		return list;
	}



}
