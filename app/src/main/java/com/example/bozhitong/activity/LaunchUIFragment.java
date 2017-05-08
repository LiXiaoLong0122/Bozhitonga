package com.example.bozhitong.activity;

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

public class LaunchUIFragment extends Fragment {
	private ListView lstv;
	private ListViewAService adapter;
	private Context mContext;
	private View view;
	private List<String> list = new ArrayList<String>();
	private List<String> list11 = new ArrayList<String>();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_selection_launch, null);
		lstv = (ListView) view.findViewById(R.id.lsit_selv);
		initData();
		initData11();
		adapter = new ListViewAService(mContext, list, list11);
		lstv.setAdapter(adapter);
		return view;
	}

	private List<String> initData() {
		for (int i = 0; i < 1; i++) {
			list.add("《关于婴幼儿服务介绍》");
			list11.add("\u3000\u3000婴幼儿服务中心主要经营：等产品。公司尊崇“踏实、拼搏、责任”的企业精神，并以诚信、共赢、开创经营理念，创造良好的企业环境，以全新的管理模式，完善的技术，周到的服务，卓越的品质为生存根本，我们始终坚持用户至上 用心服务于客户，坚持用自己的服务去打动客户。");
		}
		return list;
	}

	private List<String> initData11() {
		for (int i = 0; i < 1; i++) {

			list11.add("\u3000\u3000婴幼儿服务中心主要经营：等产品。公司尊崇“踏实、拼搏、责任”的企业精神，并以诚信、共赢、开创经营理念，创造良好的企业环境，以全新的管理模式，完善的技术，周到的服务，卓越的品质为生存根本，我们始终坚持用户至上 用心服务于客户，坚持用自己的服务去打动客户。");
		}
		return list11;
	}
}
