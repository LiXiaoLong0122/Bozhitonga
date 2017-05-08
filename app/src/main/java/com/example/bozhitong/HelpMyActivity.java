package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HelpMyActivity extends Activity implements OnClickListener {

	private ImageView mBack;
	private LinearLayout mEmergencycalling,mEagertoinform;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_help_my);
		initView();
		addListener();
	}


	private void addListener() {
		mBack.setOnClickListener(this);
		mEmergencycalling.setOnClickListener(this);
		mEagertoinform.setOnClickListener(this);
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mEmergencycalling=(LinearLayout) findViewById(R.id.Emergencycalling);
		mEagertoinform=(LinearLayout) findViewById(R.id.Eagertoinform);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.Emergencycalling:
			Intent intent = new Intent();
			intent.setClass(this, Emergencycalling.class);
			startActivity(intent);
			break;
		case R.id.Eagertoinform:
			Intent intent1 = new Intent();
			intent1.setClass(this, Eagertoinform.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
	
	}

}