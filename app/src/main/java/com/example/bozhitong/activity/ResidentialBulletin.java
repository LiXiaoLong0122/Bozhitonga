package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.dialog.view.AutoListView.OnLoadListener;
import com.example.bozhitong.dialog.view.AutoListView.OnRefreshListener;
import com.example.bozhitong.fragment.adapter.ResiBullAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 小区公告
 * 
 * @author 12306
 * 
 */
public class ResidentialBulletin extends Activity implements OnRefreshListener,
		OnLoadListener, OnClickListener {

	private AutoListView lstv;
	private ResiBullAdapter adapter;
	private ImageView mBack;
	private List<String> list = new ArrayList<String>();
	private String[] starStrings = {
			"尊敬的各位业主（住户）：\n\u3000\u3000值此节日来临之际，物业管理处全体员工向您致以 诚挚的节日问候，同时也感谢您对物业工作一贯的支持与配合.....",
			"尊敬的各位业主（住户）：\n\u3000\u3000为引导广大业主文明规范停车，创造良好停车秩序，给您及他人提供停车便利，高科物业服务中心特提示广大车友朋友们规范、安全停车应注意以下事项：" };
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
		setContentView(R.layout.activty_residential_bulletin);
		lstv = (AutoListView) findViewById(R.id.Residential_lstv);
		mBack = (ImageView) findViewById(R.id.resigent_iv);
		mBack.setOnClickListener(this);
		adapter = new ResiBullAdapter(this, list, starStrings);
		lstv.setAdapter(adapter);
		lstv.setOnRefreshListener(this);
		lstv.setOnLoadListener(this);
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
		String[] CeshiAry = { "春节温馨提示", "春节停车提示" };
		for (int i = 0; i < CeshiAry.length; i++) {
			result.add(CeshiAry[i]);
		}
		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.resigent_iv:
			finish();
			break;

		default:
			break;
		}

	}
}
