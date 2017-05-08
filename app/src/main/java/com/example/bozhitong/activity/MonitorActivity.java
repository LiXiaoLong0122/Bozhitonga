package com.example.bozhitong.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

/**
 * 公共监控
 * 
 * @author 12306
 * 
 */
public class MonitorActivity extends Activity implements OnClickListener {
	// 相册
	private Button btn_picture, btn_photo, btn_cancale;
	private ImageView mSmart_iv;
	private TextView keting_tv, chufang_tv, loudao_tv, yangtai_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉Activity上面的状态栏�ְ�
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_monitor);
		initView();
	}

	private void initView() {
		mSmart_iv = (ImageView) findViewById(R.id.monitor_iv);
		mSmart_iv.setOnClickListener(this);
		keting_tv = (TextView) findViewById(R.id.keting_tv);
		keting_tv.setOnClickListener(this);
		chufang_tv = (TextView) findViewById(R.id.chufang_tv);
		chufang_tv.setOnClickListener(this);
		loudao_tv = (TextView) findViewById(R.id.loudao_tv);
		loudao_tv.setOnClickListener(this);
		yangtai_tv = (TextView) findViewById(R.id.yangtai_tv);
		yangtai_tv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.monitor_iv:
			finish();
			break;
		case R.id.chufang_tv:
			showDialog();
			break;
		case R.id.loudao_tv:
			showDialog();
			break;
		case R.id.keting_tv:
			showDialog();
			break;
		case R.id.yangtai_tv:
			showDialog();
			break;

		default:
			break;
		}
	}

	// 弹框
	private void showDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
				null);
		final Dialog dialog = new Dialog(this,
				R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 一下两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

		btn_picture = (Button) window.findViewById(R.id.btn_picture);
		btn_photo = (Button) window.findViewById(R.id.btn_photo);
		btn_cancale = (Button) window.findViewById(R.id.btn_cancle);
		btn_picture.setText("实时预览");
		btn_photo.setText("回放");
		btn_picture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});

		btn_photo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.dismiss();

			}
		});

		btn_cancale.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();

			}
		});

	}

}
