package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bozhitong.activity.OtherUserDetailActivity;

public class Apply extends Activity implements OnClickListener {
	private RelativeLayout mRL_neighbours,mRL_apply;
	private ImageView mChacha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_apply);
		initView();
	}
	private void initView() {
		mChacha=(ImageView) findViewById(R.id.chacha);
		mRL_neighbours=(RelativeLayout) findViewById(R.id.rL_neighbours);
		mRL_neighbours.setOnClickListener(this);
		mRL_apply=(RelativeLayout) findViewById(R.id.rL_apply);
		mRL_apply.setOnClickListener(this);
		mChacha.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chacha:
		     finish();
			break;
		case R.id.rL_neighbours:
			Intent intent = new Intent();
			intent.setClass(this, Myneighbor.class);
			startActivity(intent);
			break;
		case R.id.rL_apply:
			finish();
			Intent intent1 = new Intent();
			intent1.setClass(this, OtherUserDetailActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
	}

	
}
