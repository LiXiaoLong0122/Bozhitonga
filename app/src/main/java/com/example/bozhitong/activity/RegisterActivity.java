package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bozhitong.R;

/**
 * 用户注册界面
 * 
 * @author Administrator
 * 
 */
public class RegisterActivity extends Activity implements OnClickListener {
	private Button mRegisterBtn, mResetBtn;// 声明注册，重置按钮
	// 用户名输入框，密码输入框，确认密码输入框
	private EditText mUserNameEt, mPassWordEt, mRePassWordEt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 去掉标题栏，一定在绑定布局之前加上这句代码
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 给Activity绑定一个布局
		setContentView(R.layout.activity_register);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mRegisterBtn = (Button) findViewById(R.id.btn_register);
		// 绑定监听器【写法2：实现接口的写法】
		mRegisterBtn.setOnClickListener(this);
		mResetBtn = (Button) findViewById(R.id.btn_reset);
		mResetBtn.setOnClickListener(this);
		mUserNameEt = (EditText) findViewById(R.id.et_username);
		mPassWordEt = (EditText) findViewById(R.id.et_password);
		mRePassWordEt = (EditText) findViewById(R.id.et_repassword);
		// 给注册按钮设置一个点击事件【写法1：匿名内部类】
		// mRegisterBtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:// 注册按钮的点击事件
			check();
			break;
		case R.id.btn_reset:// 重置
			reset();
		default:
			break;
		}

	}

	/**
	 * 重置
	 */
	private void reset() {
		mUserNameEt.setText("");// 给控件设置文本内容
		mPassWordEt.setText("");
		mRePassWordEt.setText("");

	}

	/**
	 * 检查登录
	 */
	private void check() {
		// 拿到输入框内的文本信息
		String username = mUserNameEt.getText().toString().trim();
		String password = mPassWordEt.getText().toString().trim();
		String repString = mRePassWordEt.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {// 用户名是否为空
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
			return;
		} else if (TextUtils.isEmpty(password)) {//
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
			return;
		} else if (!password.equals(repString)) {
			Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_LONG).show();
			return;
		}
		// 注册，保存数据到本地，后续课程学习
		SharedPreferences preferences = getSharedPreferences("hotel", 0);
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
		Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
		// 跳转界面
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();// 关闭自己
	}

}
