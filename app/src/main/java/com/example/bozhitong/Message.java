package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Message extends Activity implements OnClickListener {

	private ImageView mBack;
	private LinearLayout mDirectmessagesmy,mCommentsonthethumbup;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_message);
		initView();
		addListener();
	}


	private void addListener() {
		mBack.setOnClickListener(this);
		mDirectmessagesmy.setOnClickListener(this);
		mCommentsonthethumbup.setOnClickListener(this);
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mDirectmessagesmy=(LinearLayout)findViewById(R.id.Directmessagesmy);
		mCommentsonthethumbup=(LinearLayout)findViewById(R.id.Commentsonthethumbup);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.Directmessagesmy:
			//共享我的邻里私信 互动 的
			Intent intent = new Intent();
			intent.setClass(this, PrivateLetter.class);
			startActivity(intent);
			break;
		case R.id.Commentsonthethumbup:
			Intent intent1 = new Intent();
			intent1.setClass(this, Like.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
		;
	}

}