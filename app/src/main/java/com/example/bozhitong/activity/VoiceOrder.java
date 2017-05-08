package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.bozhitong.R;

/**
 * 语音下单
 * 
 * @author 12306
 * 
 */
public class VoiceOrder extends Activity implements OnClickListener {
	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_voiceorder);
		initView();
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.voic_back);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.voic_back:
			finish();
			break;
		default:
			break;
		}
	}
}
