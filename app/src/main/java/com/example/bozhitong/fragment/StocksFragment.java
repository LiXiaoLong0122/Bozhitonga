package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bozhitong.R;

public class StocksFragment extends Fragment{
	  private Context mContext;
	    private View view;
	    @Override
	    public void onAttach(Activity activity) {
	    	super.onAttach(activity);
	    	mContext=activity;
	    }
	    @Override
	    public View onCreateView(LayoutInflater inflater,
								 ViewGroup container, Bundle savedInstanceState) {
	    	view=inflater.inflate(R.layout.activity_stocks, null);
	    	return view;
	    }
}
