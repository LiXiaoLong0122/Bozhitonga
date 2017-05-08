package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Release extends Activity implements OnClickListener {

	private ImageView mBack;
	private RelativeLayout mActwildly,mSecondhand,mHelp,mPublish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_release);
		initView();
		addListener();
	}


	private void addListener() {
		mBack.setOnClickListener(this);
		mActwildly.setOnClickListener(this);
		mSecondhand.setOnClickListener(this);
		mHelp.setOnClickListener(this);
		mPublish.setOnClickListener(this);
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_qq);
		mActwildly=(RelativeLayout) findViewById(R.id.Actwildly);
		mSecondhand=(RelativeLayout) findViewById(R.id.Secondhand);
		mHelp=(RelativeLayout) findViewById(R.id.Help);
		mPublish=(RelativeLayout) findViewById(R.id.Publish);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_qq:
			finish();
			break;
		case R.id.Actwildly:
			Intent intent = new Intent();
			intent.setClass(this, Actwildly.class);
			startActivity(intent);
			break;
		case R.id.Secondhand:
			Intent intent1 = new Intent();
			intent1.setClass(this, Secondhand.class);
			startActivity(intent1);
			break;
		case R.id.Help:
			Intent intent2 = new Intent();
			intent2.setClass(this, HelpMyActivity.class);
			startActivity(intent2);
			break;
		case R.id.Publish:
			Intent intent3 = new Intent();
			intent3.setClass(this, Publishes.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	
	}

}