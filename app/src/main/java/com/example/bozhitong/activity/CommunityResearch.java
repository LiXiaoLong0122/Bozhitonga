package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.dialog.view.AutoListView.OnLoadListener;
import com.example.bozhitong.dialog.view.AutoListView.OnRefreshListener;
import com.example.bozhitong.fragment.adapter.CommunAdapter;

import java.util.HashMap;
import java.util.Random;

/**
 * 小区调研
 * 
 * @author 12306
 * 
 */
public class CommunityResearch extends Activity implements OnClickListener,
		OnRefreshListener, OnLoadListener, OnItemClickListener {

	private AutoListView lstv;
	private CommunAdapter adapter;
	/**
	 * 发布房屋出租
	 */
	private Button mRelHousing;
	private ImageView mBack;
	private HashMap<String, String> list = new HashMap<String, String>();
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			HashMap<String, String> result = (HashMap<String, String>) msg.obj;
			switch (msg.what) {
			case AutoListView.REFRESH:
				lstv.onRefreshComplete();
				list.clear();
				list.putAll(result);
				break;
			case AutoListView.LOAD:
				lstv.onLoadComplete();
				list.putAll(result);
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
		setContentView(R.layout.activity_conmmun_research);

		lstv = (AutoListView) findViewById(R.id.community_listv);
		mBack = (ImageView) findViewById(R.id.Coityback_iv);
		mBack.setOnClickListener(this);
		adapter = new CommunAdapter(this, list);
		lstv.setAdapter(adapter);
		lstv.setOnRefreshListener(this);
		lstv.setOnLoadListener(this);
		lstv.setOnItemClickListener(this);
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
	public HashMap<String, String> getData() {
		HashMap<String, String> result = new HashMap<String, String>();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			long l = random.nextInt(10000);
			result.put("id", "当前条目的ID：" + l);
			if (i == 0) {
				result.put("MingCheng", "植树活动：");
			} else if (i == 1) {
				result.put("MingCheng", "对环境的调研");

			} else if (i == 2) {
				result.put("MingCheng", "对管家服务的调研：");

			} else if (i == 3) {
				result.put("MingCheng", "1到知识点hihi：");
			}

			result.put("JieZhi", "截止时间：");

		}

		return result;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.Coityback_iv:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		Intent intent = null;
		switch (position) {

		case 1:
			Log.e("position1===", position + "");
			intent = new Intent(CommunityResearch.this, Housekeeper.class);
			startActivity(intent);
			break;
		case 2:
			Log.e("position2===", position + "");
			break;
		case 3:
			Log.e("position3===", position + "");
			break;
		case 4:
			Log.e("position4===", position + "");
			break;
		default:
			break;
		}

	}
}
