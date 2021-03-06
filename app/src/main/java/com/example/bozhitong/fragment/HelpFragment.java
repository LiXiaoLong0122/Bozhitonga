package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.HelpAdapter;
import com.example.bozhitong.fragment.adapter.ListViewTask;

import java.util.ArrayList;
import java.util.List;

public class HelpFragment extends Fragment {
	private ListView lstv;
	private HelpAdapter adapter;
	private Context mContext;
	private View view;
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_help, null);
		lstv = (ListView) view.findViewById(R.id.tasks_lstv);
		list.addAll(initData());
		adapter = new HelpAdapter(mContext, list);
		lstv.setAdapter(adapter);
		return view;
	}

	private List<String> initData() {
		for (int i = 0; i < 10; i++) {
			list.add("丢狗了");
		}
		return list;
	}
}
