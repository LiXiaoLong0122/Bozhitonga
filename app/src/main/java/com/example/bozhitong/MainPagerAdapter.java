package com.example.bozhitong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter{
	
	
	/*������5����ť��Ƭ*/
	private List<Fragment> beans=new ArrayList<Fragment>();
	
	public void setBeans(List<Fragment> beans) {
		this.beans = beans;
	}
	
	
	
	
	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return beans.get(position);
	}

	@Override
	public int getCount() {
		return beans!=null?beans.size():0;
	}


}
