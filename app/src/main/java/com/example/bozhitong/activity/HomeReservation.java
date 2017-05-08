package com.example.bozhitong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bozhitong.NoCertificationPopActivity;
import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.StringListAdapter;
import com.example.bozhitong.utils.DrawerLayoutUtils;
import com.example.bozhitong.utils.Tools;
import com.example.bozhitong.utils.Utils;

import java.util.ArrayList;

/**
 * 家政预约
 * 
 * @author 12306
 * 
 */
public class HomeReservation extends FragmentActivity implements
		OnClickListener {
private int INRFF = -1;
	private DrawerLayout mDrawerLayout;
	private ImageView mBack;
	private TextView tv,tv_time_service,tv_type_service;
	private ListView mList;
	private 	StringListAdapter adapter;
	private ArrayList<String> itemList=new ArrayList<String>();
private LinearLayout ll_type_service, ll_time_service,ll_house;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hreservation);

		initView();
		DrawerLayoutUtils.initEvents(mDrawerLayout);
		initFragmentView();



		Intent intent = new Intent();
		intent.setClass(this, NoCertificationPopActivity.class);
		startActivity(intent);
	}

	private void initFragmentView() {
		View fragmenM = DrawerLayoutUtils.getFragmentView(this);
		tv = (TextView) fragmenM.findViewById(R.id.tv_title);
		mList =(ListView) fragmenM.findViewById(R.id.lv);
		adapter = new StringListAdapter(this,itemList);
		mList.setAdapter(adapter);
		mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (INRFF == 1){
					tv_time_service.setText(itemList.get(position));
				}else {
					tv_type_service.setText(itemList.get(position));
				}


				mDrawerLayout.closeDrawers();
			}
		});
	}

	private void initDrawerData(String title, ArrayList<String> list){

		tv.setText(title);
		itemList.clear();
		itemList.addAll(list);
		adapter.notifyDataSetChanged();
	}





//	private void initEvents() {
//		mDrawerLayout.setDrawerListener(new DrawerListener() {
//			public void onDrawerStateChanged(int newState) {
//			}
//
//			public void onDrawerSlide(View drawerView, float slideOffset) {
//				View mContent = mDrawerLayout.getChildAt(0);
//				View mMenu = drawerView;
//				float scale = 1 - slideOffset;
//				float rightScale = 1f;
//
//				ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth()
//						* slideOffset);
//				ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
//				ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
//				mContent.invalidate();
//				ViewHelper.setScaleX(mContent, rightScale);
//				ViewHelper.setScaleY(mContent, rightScale);
//
//			}
//
//			public void onDrawerOpened(View drawerView) {
//			}
//
//			public void onDrawerClosed(View drawerView) {
//				mDrawerLayout.setDrawerLockMode(
//						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
//			}
//		});
//	}

	private void initView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
		mBack = (ImageView) findViewById(R.id.homeres_iv);
		ll_type_service = (LinearLayout) findViewById(R.id.ll_type_service);
		ll_type_service.setOnClickListener(this);
		ll_time_service = (LinearLayout) findViewById(R.id.ll_time_service);
		ll_time_service.setOnClickListener(this);
		ll_house = (LinearLayout) findViewById(R.id.ll_house);
		ll_house.setOnClickListener(this);

		tv_time_service  = (TextView)findViewById(R.id.tv_time_service);
		tv_type_service= (TextView)findViewById(R.id.tv_type_service);

		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.homeres_iv:
			finish();
			break;
			case R.id.ll_type_service:
				INRFF = 0;
				DrawerLayoutUtils.OpenRightMenu(mDrawerLayout);
				initDrawerData("家政服务类型", Utils.getList(this, R.array.type_service));
				break;
			case  R.id.ll_time_service:
				INRFF = 1;
				DrawerLayoutUtils.OpenRightMenu(mDrawerLayout);
				initDrawerData("请选择服务时间", Tools.getTimeList());
				break;
			case R.id.ll_house:
				Intent mIntent = new Intent();
				mIntent.setClass(this, MyHousingActivity.class);
				startActivity(mIntent);
				break;
		default:
			break;
		}
	}

}
