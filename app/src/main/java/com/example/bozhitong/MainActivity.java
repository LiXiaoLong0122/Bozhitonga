package com.example.bozhitong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.bozhitong.activity.AccessItemControl;
import com.example.bozhitong.activity.OnlineService;
import com.example.bozhitong.activity.VoiceOrder;
import com.example.bozhitong.dialog.view.ShortcutKey;
import com.example.bozhitong.fragment.HomepageFragment;
import com.example.bozhitong.fragment.MyFragment;
import com.example.bozhitong.fragment.NeighbourhoodFragment;
import com.example.bozhitong.fragment.StewardFragment;
import com.example.bozhitong.utils.Tools;
import com.example.bozhitong.utils.UpdateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面Actrivity 力推HAk迪斯科拉夫第三离开粉撒代理费
 * 
 * @author 20161217
 * 
 */
public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener, OnClickListener {
	private ViewPager pager;
	private List<Fragment> beans;
	private MainPagerAdapter adapter;
	private RadioGroup group;
	private String name;
	private int mPosition;
	private int screenW = 0;
	private int start = 0;
	private ShortcutKey menuWindow;
	private ImageView mCenterFrgment;
	/**
	 * 判断是否点击小于1秒
	 */

	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// initFragment();
		initView();
		initData();
		//判断版本更新
		UpdateManager upd = new UpdateManager(MainActivity.this);
		upd.checkUpdate();

	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	private void initData() {

		beans.clear();
		beans.add(new HomepageFragment());
		beans.add(new StewardFragment());
		/* beans.add(new CenterFragment()); */
		beans.add(new NeighbourhoodFragment());
		beans.add(new MyFragment());
		adapter.setBeans(beans);
		adapter.notifyDataSetChanged();
	}

	private void initView() {
		pager = (ViewPager) findViewById(R.id.ll_fragment_main);
		adapter = new MainPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		group = (RadioGroup) findViewById(R.id.rg_fragment_main);
		group.setOnCheckedChangeListener(this);
		beans = new ArrayList<Fragment>();
		screenW = Tools.getScreenWidth(this);

		valueAnimator(0, 0);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int position) {
				RadioButton button = null;
				if (position == 0) {
					button = (RadioButton) findViewById(R.id.rb_home_fragment);
				} else if (position == 1) {
					button = (RadioButton) findViewById(R.id.rb_circle_fragment);
				} /*
				 * else if (position == 2) { button = (RadioButton)
				 * findViewById(R.id.rb_center_fragment); }
				 */else if (position == 2) {
					button = (RadioButton) findViewById(R.id.rb_message_fragment);
				} else if (position == 3) {
					button = (RadioButton) findViewById(R.id.rb_my_fragment);
				}
				button.setChecked(true);

				if (mPosition == 0 && position == 1) {
					valueAnimator(0, screenW / 5);
				} else if (mPosition == 1 && position == 2) {
					valueAnimator(screenW / 5, screenW / 5 * 2);
				} /*
				 * else if (mPosition == 2 && position == 3) {
				 * valueAnimator(screenW / 5 * 2, screenW / 5 * 3); } else if
				 * (mPosition == 3 && position == 4) { valueAnimator(screenW / 5
				 * * 3, screenW / 5 * 4); }
				 */else if (mPosition == 2 && position == 3) {
					valueAnimator(screenW / 5 * 2, screenW / 5 * 4);
				} else if (mPosition == 4 && position == 5) {
					valueAnimator(screenW / 5 * 4, screenW);
				} else if (mPosition == 5 && position == 4) {
					valueAnimator(screenW, screenW / 5 * 4);
				} /*
				 * else if (mPosition == 4 && position == 3) {
				 * valueAnimator(screenW / 5 * 4, screenW / 5 * 3); } else if
				 * (mPosition == 3 && position == 2) { valueAnimator(screenW / 5
				 * * 3, screenW / 5 * 2); }
				 */else if (mPosition == 4 && position == 3) {
					valueAnimator(screenW / 5 * 4, screenW / 5 * 2);
				} else if (mPosition == 2 && position == 1) {
					valueAnimator(screenW / 5 * 2, screenW / 5);
				} else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW / 5, 0);
				} else if (mPosition == 0 && position == 2) {
					valueAnimator(0, screenW / 5 * 2);
				} /*
				 * else if (mPosition == 0 && position == 3) { valueAnimator(0,
				 * screenW / 5 * 3); }
				 */else if (mPosition == 0 && position == 4) {
					valueAnimator(0, screenW / 5 * 4);
				} else if (mPosition == 0 && position == 5) {
					valueAnimator(0, screenW);
				} /*
				 * else if (mPosition == 1 && position == 3) {
				 * valueAnimator(screenW / 5, screenW / 5 * 3); }
				 */else if (mPosition == 1 && position == 4) {
					valueAnimator(screenW / 5, screenW / 5 * 4);
				} else if (mPosition == 1 && position == 5) {
					valueAnimator(screenW / 5, screenW);
				} else if (mPosition == 2 && position == 4) {
					valueAnimator(screenW / 5 * 2, screenW / 5 * 4);
				} else if (mPosition == 2 && position == 5) {
					valueAnimator(screenW / 5 * 2, screenW);
				} /*
				 * else if (mPosition == 3 && position == 5) {
				 * valueAnimator(screenW / 5 * 3, screenW); }
				 *//*
					 * else if (mPosition == 5 && position == 3) {
					 * valueAnimator(screenW, screenW / 5 * 3); }
					 */else if (mPosition == 5 && position == 2) {
					valueAnimator(screenW, screenW / 5 * 2);
				} else if (mPosition == 4 && position == 2) {
					valueAnimator(screenW / 5 * 4, screenW / 5 * 2);
				} else if (mPosition == 5 && position == 1) {
					valueAnimator(screenW, screenW / 5);
				} else if (mPosition == 4 && position == 1) {
					valueAnimator(screenW / 5 * 4, screenW / 5);
				} /*
				 * else if (mPosition == 3 && position == 1) {
				 * valueAnimator(screenW / 5 * 3, screenW / 5); }
				 */else if (mPosition == 5 && position == 0) {
					valueAnimator(screenW, 0);
				} else if (mPosition == 4 && position == 0) {
					valueAnimator(screenW / 5 * 4, 0);
				}/*
				 * else if (mPosition == 3 && position == 0) {
				 * valueAnimator(screenW / 5 * 3, 0); }
				 */else if (mPosition == 2 && position == 0) {
					valueAnimator(screenW / 5 * 2, 0);
				}
				mPosition = position;

			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		mCenterFrgment = (ImageView) findViewById(R.id.im_center_add_main);
		mCenterFrgment.setOnClickListener(this);
	}

	private void valueAnimator(int start, int end) {
		Animation animation = new TranslateAnimation(start, end, 0, 0);
		animation.setDuration(300);
		animation.setFillAfter(true);
	}

	public void setCurrentItem(int itemid){
		pager.setCurrentItem(itemid);
	}


	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_home_fragment:

			pager.setCurrentItem(0);
			break;
		case R.id.rb_circle_fragment:

			pager.setCurrentItem(1);
			break;
		/*
		 * case R.id.rb_center_fragment:
		 * 
		 * pager.setCurrentItem(2); break;
		 */
		case R.id.rb_message_fragment:

			pager.setCurrentItem(2);
			break;
		case R.id.rb_my_fragment:

			pager.setCurrentItem(3);
			break;

		default:
			break;
		}
	}

	// 弹出气球对话框升起
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.btn_yjkm:// 一键开门
				intent = new Intent(MainActivity.this, AccessItemControl.class);
				startActivity(intent);
				menuWindow.dismiss();
				break;
			case R.id.btn_yyxd:// 语音下单
				intent = new Intent(MainActivity.this, VoiceOrder.class);
				startActivity(intent);
				menuWindow.dismiss();
				break;
			case R.id.btn_Service:// 在线客服
				intent = new Intent(MainActivity.this, OnlineService.class);
				startActivity(intent);
				menuWindow.dismiss();
				break;
			case R.id.btn_hjgj:// 呼叫管家
				intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ "029-81022300"));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				menuWindow.dismiss();
				break;
			default:
				break;
			}

		}

	};

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.im_center_add_main:
			menuWindow = new ShortcutKey(MainActivity.this, itemsOnClick);

			menuWindow.showAtLocation(
					MainActivity.this.findViewById(R.id.main), Gravity.BOTTOM
							| Gravity.CENTER_HORIZONTAL, 0, 0); // ����layout��PopupWindow����ʾ��λ��
			break;

		default:
			break;
		}
	}

	/*
	 * @Override public void onBackPressed() { // TODO Auto-generated method
	 * stub super.onBackPressed(); long mNowTime = System.currentTimeMillis();//
	 * 获取第一次按键时间 if ((mNowTime - mPressedTime) > 5000) {// 比较两次按键时间差
	 * Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show(); mPressedTime
	 * = mNowTime;
	 * 
	 * } else { ActivityManager manager = (ActivityManager)
	 * getSystemService(Context.ACTIVITY_SERVICE);
	 * manager.killBackgroundProcesses(getPackageName());
	 * 
	 * // android.os.Process.killProcess(android.os.Process.myPid());
	 * this.finish(); System.exit(0); } }
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
