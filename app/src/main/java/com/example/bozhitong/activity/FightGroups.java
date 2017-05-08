package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.bozhitong.R;

/**
 * 拼团特惠
 * 
 * @author 12306
 * 
 */
public class FightGroups extends Activity implements OnClickListener {
	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_fightgroups);
		initview();
	}

	private void initview() {
		mBack = (ImageView) findViewById(R.id.fightgroups_iv);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fightgroups_iv:
			finish();
			break;

		default:
			break;
		}
	}
}
