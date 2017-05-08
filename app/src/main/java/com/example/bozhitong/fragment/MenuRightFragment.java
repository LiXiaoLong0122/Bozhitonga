package com.example.bozhitong.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bozhitong.R;

/**
 * 家政预约右侧侧滑
 * 
 * @author 12306
 * 
 */
public class MenuRightFragment extends Fragment {
private View nview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		nview = inflater.inflate(R.layout.menu_layout_right, container, false);
		return nview;
	}


}
