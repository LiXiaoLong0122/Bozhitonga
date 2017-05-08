package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

/**
 * 房屋租售详情
 * 
 * @author 12306
 * 
 */
public class HousingRentalDetails extends Activity implements OnClickListener {
	private ImageView mBack;
	private TextView mContactNumber;
	private ImageView mDialTelephone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏�ְ�
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_housingrentalde);
		initView();
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.htalde_back);
		mBack.setOnClickListener(this);
		mDialTelephone = (ImageView) findViewById(R.id.telephone_btn);
		mDialTelephone.setOnClickListener(this);
		mContactNumber = (TextView) findViewById(R.id.text_phone_tv);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.htalde_back:
			finish();
			break;
		case R.id.telephone_btn:
			intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			// url:统一资源定位符
			// uri:统一资源标示符（更广）
			intent.setData(Uri.parse("tel:" + mContactNumber.getText()));
			// 开启系统拨号器
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
