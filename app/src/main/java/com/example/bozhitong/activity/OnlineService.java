package com.example.bozhitong.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bozhitong.R;

/**
 * 在线客服
 * 
 * @author 12306
 * 
 */
public class OnlineService extends FragmentActivity implements OnClickListener {
	private ImageView mBack;
	private ImageView mVoice;
	private ImageView mPlus;
	private EditText mContent;
	private Button mContentBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏�ְ�
	/*	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		setContentView(R.layout.activity_onlineservice);
		mBack = (ImageView) findViewById(R.id.onlineservice_back_iv);
		mBack.setOnClickListener(this);
		mVoice = (ImageView) findViewById(R.id.voice_iv);
		mVoice.setOnClickListener(this);
		mPlus = (ImageView) findViewById(R.id.plus_iv);
		mPlus.setOnClickListener(this);
		mContent = (EditText) findViewById(R.id.content_et);
		mContent.setOnClickListener(this);
		mContentBt = (Button) findViewById(R.id.content_btn);
		mContent.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.onlineservice_back_iv:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onStart() {
		if (mContent.getText().length() != 0) {
			mPlus.setImageResource(R.drawable.voice);
		}
		super.onStart();
	}
}
