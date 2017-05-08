package com.example.bozhitong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bozhitong.time.TimePopupWindow;

public class Pay extends Activity implements OnClickListener {

	private ImageView mBack;
	private RelativeLayout mNO;
	private Button mYear,mMonth,mQuery;
	private TimePopupWindow timepop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pay);
		initView();
	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		mNO = (RelativeLayout) findViewById(R.id.no);
		mYear=(Button) findViewById(R.id.year);
		mMonth=(Button) findViewById(R.id.month);
		mQuery=(Button) findViewById(R.id.query);
		mBack.setOnClickListener(this);
		mYear.setOnClickListener(this);
		mMonth.setOnClickListener(this);
		mQuery.setOnClickListener(this);
		timepop = new TimePopupWindow(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.year:
			timepop.showBottoPopupWindow("请选择时间",mYear);
//			new DatePickerDialog(Pay.this, new DatePickerDialog.OnDateSetListener() {
//				@Override
//				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//					mYear.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
//				}
//				},2000,1,2).show();
			break;
		case R.id.month:
			timepop.showBottoPopupWindow("请选择时间",mMonth);
//			new DatePickerDialog(Pay.this, new DatePickerDialog.OnDateSetListener() {
//				@Override
//				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//					mMonth.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
//				}
//				},2000,1,2).show();
			break;
		case R.id.query:
			mNO.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
		;
	}

}