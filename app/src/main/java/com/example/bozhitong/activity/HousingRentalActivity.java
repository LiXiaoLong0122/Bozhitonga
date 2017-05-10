package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.dialog.view.AutoListView.OnLoadListener;
import com.example.bozhitong.dialog.view.AutoListView.OnRefreshListener;
import com.example.bozhitong.fragment.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author SunnyCoffee
 * @date 2014-1-28
 * @version 1.0
 * @desc listview下拉刷新，上拉自动加载更多。 http：//blog.csdn.com/limb99
 */

public class HousingRentalActivity extends Activity implements
		OnRefreshListener, OnLoadListener, OnClickListener, OnItemClickListener {

	private AutoListView lstv;
	private ListViewAdapter adapter;
	/**
	 * 发布房屋出租
	 */
	private Button mRelHousing;
	private ImageView mBack;
	private List<String> list = new ArrayList<String>();
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			List<String> result = (List<String>) msg.obj;
			switch (msg.what) {
			case AutoListView.REFRESH:
				lstv.onRefreshComplete();
				list.clear();
				list.addAll(result);
				break;
			case AutoListView.LOAD:
				lstv.onLoadComplete();
				list.addAll(result);
				break;
			}
			lstv.setResultSize(result.size());
			adapter.notifyDataSetChanged();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏�ְ�
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_housingrental);

		lstv = (AutoListView) findViewById(R.id.lstv);
		mRelHousing = (Button) findViewById(R.id.RHousingRental_tv);
		mBack = (ImageView) findViewById(R.id.housing_iv);
		mBack.setOnClickListener(this);
		mRelHousing.setOnClickListener(HousingRentalActivity.this);
		adapter = new ListViewAdapter(this, list);
		lstv.setAdapter(adapter);
		lstv.setOnRefreshListener(this);
		lstv.setOnLoadListener(this);
		lstv.setOnItemClickListener(this);

		RadioGroup rb_all = (RadioGroup)findViewById(R.id.rb_all);
		rb_all.check(R.id.rbut_all);




		initData();
	}

	private void initData() {
		loadData(AutoListView.REFRESH);
	}

	private void loadData(final int what) {
		// 这里模拟从服务器获取数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = handler.obtainMessage();
				msg.what = what;
				msg.obj = getData();
				handler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public void onRefresh() {
		loadData(AutoListView.REFRESH);
	}

	@Override
	public void onLoad() {
		loadData(AutoListView.LOAD);
	}

	// 测试数据
	public List<String> getData() {
		List<String> result = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			long l = random.nextInt(10000);
			if (i % 2 == 0) {
				result.add("出租");
			} else {
				result.add("出售");
			}
		}
		return result;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.RHousingRental_tv:
			intent = new Intent();
			intent.setClass(HousingRentalActivity.this, RelHousingRental.class);
			startActivity(intent);
			break;
		case R.id.housing_iv:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(HousingRentalActivity.this,
				HousingRentalDetails.class);
		startActivity(intent);

	}
}
