package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.bozhitong.R;

/**
 * 智能家居
 * 
 * @author 12306
 * 
 */
public class Smarthome extends Activity implements OnClickListener {
	private ImageView mSmart_iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏�ְ�
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_smarthome);
		initView();
	}

	private void initView() {
		mSmart_iv = (ImageView) findViewById(R.id.smart_iv);
		mSmart_iv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.smart_iv:
			finish();
			break;

		default:
			break;
		}
	}
}
