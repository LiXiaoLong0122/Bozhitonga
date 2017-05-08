package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bozhitong.R;

/**
 * 智能门禁子页面
 * 
 * @author 12306
 * 
 */
public class AccessItemControl extends Activity implements OnClickListener {
	private ImageView mBack;
	private FrameLayout mOpendoor;
	private FrameLayout mOpendoor1;
	private FrameLayout mOpendoor2;
	private FrameLayout mOpendoor3;
	private FrameLayout mOpendoor4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_accitcon);
		initView();
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.accessitem_back);
		mBack.setOnClickListener(this);
		mOpendoor = (FrameLayout) findViewById(R.id.Opendoor_fly);
		mOpendoor.setOnClickListener(this);
		mOpendoor1 = (FrameLayout) findViewById(R.id.Opendoor1_fly);
		mOpendoor1.setOnClickListener(this);
		mOpendoor2 = (FrameLayout) findViewById(R.id.Opendoor2_fly);
		mOpendoor2.setOnClickListener(this);
		mOpendoor3 = (FrameLayout) findViewById(R.id.Opendoor3_fly);
		mOpendoor3.setOnClickListener(this);
		mOpendoor4 = (FrameLayout) findViewById(R.id.Opendoor4_fly);
		mOpendoor4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.accessitem_back:
			finish();
			break;
		case R.id.Opendoor_fly:
			Toast.makeText(AccessItemControl.this, "门已经开了", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.Opendoor1_fly:
			Toast.makeText(AccessItemControl.this, "门已经开了", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.Opendoor2_fly:
			Toast.makeText(AccessItemControl.this, "门已经开了", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.Opendoor3_fly:
			Toast.makeText(AccessItemControl.this, "门已经开了", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.Opendoor4_fly:
			Toast.makeText(AccessItemControl.this, "门已经开了", Toast.LENGTH_LONG)
					.show();
			break;

		default:
			break;
		}

	}
}
