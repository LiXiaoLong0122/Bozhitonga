package com.example.bozhitong.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 投诉建议适配Fragment器
 * 
 * @author 12306
 * 
 */
public class CoAndAdPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> list;

	public CoAndAdPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list = list;

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}






}
