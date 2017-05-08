package com.example.bozhitong;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CoupleBackActivity extends Activity implements OnClickListener {
	private ImageView mReView;//返回上一层
	private EditText mEditText,mName;//文字输入框
	private Button mCouple;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_couple_back);
		initView();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		mCouple = (Button) findViewById(R.id.btn_couple);
		mEditText = (EditText) findViewById(R.id.et_couple_text);
		mName = (EditText) findViewById(R.id.et_couple_name);
		mReView =(ImageView) findViewById(R.id.imge_couple_retreat);
		mReView.setOnClickListener(this);
		mCouple.setOnClickListener(this);
	}
	/**
	 * 点击事件
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imge_couple_retreat:
			finish();
			break;
		case R.id.btn_couple:
			Toast.makeText(this, "提交成功，谢谢您的支持！", 0).show();
			finish();
			break;

		default:
			break;
		}
		
	}

}
