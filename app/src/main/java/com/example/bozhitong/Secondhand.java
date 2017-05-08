package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Secondhand extends Activity implements OnClickListener {

	private ImageView mBack;
	private LinearLayout mTransferofidle,mPackaway,mAsktobuy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_secondhand);
		initView();
		addListener();
	}


	private void addListener() {
		mBack.setOnClickListener(this);
		mTransferofidle.setOnClickListener(this);
		mPackaway.setOnClickListener(this);
		mAsktobuy.setOnClickListener(this);
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mTransferofidle=(LinearLayout) findViewById(R.id.Transferofidle);
		mPackaway=(LinearLayout) findViewById(R.id.Packaway);
		mAsktobuy=(LinearLayout) findViewById(R.id.Asktobuy);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
			case R.id.Transferofidle://转让闲置
			Intent intent = new Intent();
			intent.setClass(this, Transferofidle.class);
			startActivity(intent);
			break;
		case R.id.Packaway://打包送人
			Intent intent1 = new Intent();
			intent1.setClass(this, Packaway.class);
			startActivity(intent1);
			break;
		case R.id.Asktobuy://求购闲置
			Intent intent2 = new Intent();
			intent2.setClass(this, Asktobuy.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
	
	}

}