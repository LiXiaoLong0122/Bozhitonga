package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Shippingaddress extends Activity implements OnClickListener {

	private ImageView mBack;
	private TextView mXzTV;//新建地址
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shippingaddress);
		initView();
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mXzTV=(TextView) findViewById(R.id.tv_xz);
		mBack.setOnClickListener(this);
		mXzTV.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.tv_xz:
			Intent intent=new Intent(this,Newconstruction.class);
		    startActivity(intent);
			break;

		default:
			break;
		}
		;
	}

}