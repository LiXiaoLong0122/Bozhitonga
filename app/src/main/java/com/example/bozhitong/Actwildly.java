package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class Actwildly extends Activity implements OnClickListener {


	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_actwildly);
		initView();

	}




	private void initView() {
	    findViewById(R.id.img_back).setOnClickListener(this);
		findViewById(R.id.nextstep).setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
			case R.id.nextstep:
				Intent intent = new Intent();
				intent.setClass(this,ActwildlyTwoActivity.class);
				startActivity(intent);
			break;
		default:
			break;
		}
		;
	}

}