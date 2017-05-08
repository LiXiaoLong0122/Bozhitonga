package com.example.bozhitong.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.ListViewAService;

import java.util.ArrayList;
import java.util.List;

public class CommonUIFragment extends Fragment {
	private ListView lstv;
	private ListViewAService adapter;
	private Context mContext;
	private View view;
	private List<String> list = new ArrayList<String>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_selection_common, container,
				false);

		lstv = (ListView) view.findViewById(R.id.lsit_selv);
		initData();
		adapter = new ListViewAService(mContext, list);
		lstv.setAdapter(adapter);

		return view;
	}

	private List<String> initData() {
		for (int i = 0; i < 1; i++) {
			list.add("《关于婴幼儿服务介绍》");
		}
		return list;
	}

}
