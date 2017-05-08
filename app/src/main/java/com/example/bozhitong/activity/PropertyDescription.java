package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bozhitong.R;

/**
 * 物业介绍界面
 * 
 * @author 12306
 * 
 */
public class PropertyDescription extends Activity implements OnClickListener {
	private static final int VIDEO_CONTENT_DESC_MAX_LINE = 5;// 默认展示最大行数3行
	private static final int SHOW_CONTENT_NONE_STATE = 0;// 扩充
	private static final int SHRINK_UP_STATE = 1;// 收起状态
	private static final int SPREAD_STATE = 2;// 展开状态
	private static int mState = SHRINK_UP_STATE;// 默认收起状态

	private TextView mContentText;// 展示文本内容
	private RelativeLayout mShowMore;// 展示更多
	private ImageView mImageSpread;// 展开
	private ImageView mImageShrinkUp;// 收起
	private ImageView mBack;// 返回键

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_proption);

		initView();
		initData();
	}

	private void initView() {
		mContentText = (TextView) findViewById(R.id.text_content);
		mShowMore = (RelativeLayout) findViewById(R.id.show_more);
		mImageSpread = (ImageView) findViewById(R.id.spread);
		mImageShrinkUp = (ImageView) findViewById(R.id.shrink_up);
		mBack = (ImageView) findViewById(R.id.proption_iv);
		mBack.setOnClickListener(this);
		mShowMore.setOnClickListener(this);
	}

	private void initData() {
		mContentText.setText(R.string.proption_info);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.show_more: {
			if (mState == SPREAD_STATE) {
				mContentText.setMaxLines(VIDEO_CONTENT_DESC_MAX_LINE);
				mContentText.requestLayout();
				mImageShrinkUp.setVisibility(View.GONE);
				mImageSpread.setVisibility(View.VISIBLE);
				mState = SHRINK_UP_STATE;
			} else if (mState == SHRINK_UP_STATE) {
				mContentText.setMaxLines(Integer.MAX_VALUE);
				mContentText.requestLayout();
				mImageShrinkUp.setVisibility(View.VISIBLE);
				mImageSpread.setVisibility(View.GONE);
				mState = SPREAD_STATE;
			}
			break;
		}
		case R.id.proption_iv:
			finish();
			break;
		default: {
			break;
		}
		}
	}
}
