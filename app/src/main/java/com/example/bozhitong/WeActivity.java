package com.example.bozhitong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class WeActivity extends Activity {
	private ImageView mRetreat;
	private TextView mContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_we);
		
		initView();
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		mRetreat = (ImageView) findViewById(R.id.imge_we_retreat);
		mContent = (TextView) findViewById(R.id.text_we_content);
		mContent.setText(new MyString().MyContent());
		
		mRetreat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	
}
