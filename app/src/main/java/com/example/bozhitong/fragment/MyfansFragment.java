package com.example.bozhitong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bozhitong.R;

public class MyfansFragment extends DialogFragment{
	private View mView;
	private Activity mContext;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mContext = activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.activity_myfans, null);
		return mView;
	};
	
	}
	
	
	

