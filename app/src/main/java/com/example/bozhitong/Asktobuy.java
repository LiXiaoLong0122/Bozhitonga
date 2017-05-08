package com.example.bozhitong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class Asktobuy extends Activity implements OnClickListener {

	private ImageView mBack;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_asktobuy);
		initView();
		addListener();
	}


	private void addListener() {
		mBack.setOnClickListener(this);
		
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		default:
			break;
		}
		;
	}

}