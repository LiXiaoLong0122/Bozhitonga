package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Goodscollection extends Activity implements OnClickListener {

	private ImageView mBack;
	private Button mShoppingimmediately;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goodscollection);
		initView();
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mShoppingimmediately = (Button) findViewById(R.id.Shoppingimmediately);
		mBack.setOnClickListener(this);
		mShoppingimmediately.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.Shoppingimmediately:
			Intent intent = new Intent();
			intent.setClass(Goodscollection.this, Shopping.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		;
	}

}