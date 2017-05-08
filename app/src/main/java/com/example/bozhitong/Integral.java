package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Integral extends Activity implements OnClickListener {

	private ImageView mBack;
	private LinearLayout mIntegralwithdrawal,mCreditsexchange,mMyoffline;
	private RelativeLayout mMypoints;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_integral);
		initView();
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mMypoints = (RelativeLayout) findViewById(R.id.Mypoints);
		mIntegralwithdrawal=(LinearLayout) findViewById(R.id.Integralwithdrawal);
		mCreditsexchange=(LinearLayout) findViewById(R.id.creditsexchange);
		mMyoffline=(LinearLayout) findViewById(R.id.myoffline);
		mBack.setOnClickListener(this);
		mMypoints.setOnClickListener(this);
		mIntegralwithdrawal.setOnClickListener(this);
		mCreditsexchange.setOnClickListener(this);
		mMyoffline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.Mypoints:
			//积分明细

			Intent intent = new Intent();
			intent.setClass(Integral.this, IntegralDetailsActivity.class);
			startActivity(intent);
			break;
		case R.id.Integralwithdrawal:
	//积分提现
			Intent intent1 = new Intent();
			intent1.setClass(Integral.this, IntegralWithdrawalActivity.class);
			startActivity(intent1);
			break;
		case R.id.creditsexchange:
	//积分兑换
			Intent intent2 = new Intent();
			intent2.setClass(Integral.this, IntegralExchangeActivity.class);
			startActivity(intent2);
			break;
		case R.id.myoffline:
			//我的圈子

			Intent intent3 = new Intent();
			intent3.setClass(Integral.this, MyofflineActivity.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
		;
	}

}