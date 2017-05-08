package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bozhitong.activity.FindActivity;

public class Set extends Activity implements OnClickListener {
	private ImageView mRetreat;
	private RelativeLayout mFind, mIdea, mService, mMy, mShare, mVersion;
	private Button mOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set);
		initView();
	}

	/**
	 * 
	 */
	private void initView() {
		mRetreat = (ImageView) findViewById(R.id.imge_set_retreat);
		mFind = (RelativeLayout) findViewById(R.id.rl_set_find);
		mIdea = (RelativeLayout) findViewById(R.id.rl_set_idea);
		mService = (RelativeLayout) findViewById(R.id.rl_set_service);
		mMy = (RelativeLayout) findViewById(R.id.rl_set_my);
		mShare = (RelativeLayout) findViewById(R.id.rl_set_share);
		mVersion = (RelativeLayout) findViewById(R.id.rl_set_version);
		mOut = (Button) findViewById(R.id.btn_set_out);
		mRetreat.setOnClickListener(this);
		mFind.setOnClickListener(this);
		mIdea.setOnClickListener(this);
		mService.setOnClickListener(this);
		mMy.setOnClickListener(this);
		mShare.setOnClickListener(this);
		mVersion.setOnClickListener(this);
		mOut.setOnClickListener(this);

	}

	/**
	 * ����¼�
	 */
	@Override
	public void onClick(View v) {
		mRetreat = (ImageView) findViewById(R.id.imge_set_retreat);
		mFind = (RelativeLayout) findViewById(R.id.rl_set_find);
		mIdea = (RelativeLayout) findViewById(R.id.rl_set_idea);
		mService = (RelativeLayout) findViewById(R.id.rl_set_service);
		mMy = (RelativeLayout) findViewById(R.id.rl_set_my);
		mShare = (RelativeLayout) findViewById(R.id.rl_set_share);
		mVersion = (RelativeLayout) findViewById(R.id.rl_set_version);
		mOut = (Button) findViewById(R.id.btn_set_out);
		switch (v.getId()) {
		case R.id.imge_set_retreat:// 返回事件图标
			finish();
			break;
		case R.id.rl_set_find:// 修改密码
			Intent intent = new Intent(this, FindActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_set_idea:// 意见反馈
			Intent couple = new Intent(this, CoupleBackActivity.class);
			startActivity(couple);
			break;
		case R.id.rl_set_service:// 联系客服

			break;
		case R.id.rl_set_my:// 关于我们
			Intent we = new Intent(this, WeActivity.class);
			startActivity(we);
			break;
		case R.id.rl_set_share:// 分享
			Intent share = new Intent(this, ShareActivity.class);
			startActivity(share);
			break;
		case R.id.rl_set_version:// 检测新版本
			Toast.makeText(this, "当前为最新版本", 0).show();
			break;
		case R.id.btn_set_out:// 退出当前帐号

			/*
			 * BmobUser.logOut(); BmobUser currentUser =
			 * BmobUser.getCurrentUser();
			 */
			
			
			
			
			
			
		/*	Intent intent2 = new Intent(Set.this, RegisterActivity.class);
			startActivity(intent2);
			Toast.makeText(this, "退出当前帐号", 0).show();*/
			finish();
			break;

		default:
			break;
		}
	}

}
