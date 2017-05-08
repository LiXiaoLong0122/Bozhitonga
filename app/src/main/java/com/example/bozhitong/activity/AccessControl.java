package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

/**

 * nihao智慧门禁fdggdsd，，，，，，，，，，，，，，，
 * 从v哈个快快快看过asdasdas
 * @author 12306rthsrthyrthrthjtujrthahhahahhahah哈哈哈ZHPON中姑引吭高歌广告费和
 * 
 */
public class AccessControl extends Activity implements OnClickListener {
	private ImageView mBack;
	private TextView mAccesItem;
	private TextView mMonitoring;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_accesscontrol);
		initView();
	}

	private void initView() {
		mBack = (ImageView) findViewById(R.id.access_back);
		mBack.setOnClickListener(this);
		mAccesItem = (TextView) findViewById(R.id.accessitem_tv);
		mAccesItem.setOnClickListener(this);
		mMonitoring = (TextView) findViewById(R.id.monitoring_tv);
		mMonitoring.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.access_back:
			finish();
			break;
		case R.id.accessitem_tv:
			intent = new Intent(AccessControl.this, AccessItemControl.class);
			startActivity(intent);
			break;
		case R.id.monitoring_tv:
			intent = new Intent(AccessControl.this, MonitorActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
