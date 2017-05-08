package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.bozhitong.Apply;
import com.example.bozhitong.MainActivity;
import com.example.bozhitong.Message;
import com.example.bozhitong.R;
import com.example.bozhitong.Release;
import com.example.bozhitong.Search;
import com.example.bozhitong.fragment.adapter.NeighbourhoodPagerAdapter;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class NeighbourhoodFragment extends Fragment implements OnClickListener,
		OnCheckedChangeListener {
	private View mView;
	private Activity mContext;
	private LinearLayout mBack;

	private ViewPager pager;
	private List<Fragment> beans;
	private NeighbourhoodPagerAdapter adapter;
	private RadioGroup group;
	private Button button;
	private int mPosition;
	private int screenW = 0;
	private int start = 0;

	private LinearLayout mLl_release, mLl_key_word_search_btn, mLl_message,
			mApply;

	private MainActivity mainActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mContext = activity;
		mainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.activity_neighbourhood, null);
		initView();
		initData();
		addListener();
		return mView;
	}

	private void addListener() {
		mBack.setOnClickListener(this);
		mLl_release.setOnClickListener(this);
		mLl_key_word_search_btn.setOnClickListener(this);
		mLl_message.setOnClickListener(this);
		mApply.setOnClickListener(this);
	}

	private void initData() {
		beans.clear();
		beans.add(new TasksFragment());
		/* beans.add(new IdleFragment()); */
		beans.add(new HelpFragment());
		beans.add(new TalkFragment());
		// beans.add(new StocksFragment());
		adapter.setBeans(beans);
		adapter.notifyDataSetChanged();

	}

	private void initView() {
		mBack = (LinearLayout) mView.findViewById(R.id.ll_left);

		mApply = (LinearLayout) mView.findViewById(R.id.Apply);
		mLl_message = (LinearLayout) mView.findViewById(R.id.ll_message);
		mLl_key_word_search_btn = (LinearLayout) mView
				.findViewById(R.id.ll_key_word_search_btn);
		mLl_release = (LinearLayout) mView.findViewById(R.id.ll_release);
		pager = (ViewPager) mView.findViewById(R.id.view_pager);
		adapter = new NeighbourhoodPagerAdapter(getChildFragmentManager());
		pager.setAdapter(adapter);
		group = (RadioGroup) mView.findViewById(R.id.rg_group);
		group.setOnCheckedChangeListener(this);
		beans = new ArrayList<Fragment>();
		// screenW = Tools.getScreenWidth(this);
		screenW = Tools.getScreenWidth(mContext);
		valueAnimator(0, 0);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				RadioButton button = null;
				if (position == 0) {
					button = (RadioButton) mView.findViewById(R.id.tv_tasks);
				}/*
				 * else if(position==1){ button =(RadioButton)
				 * mView.findViewById(R.id.tv_review); }
				 */else if (position == 1) {
					button = (RadioButton) mView
							.findViewById(R.id.tv_scheduling);
				} else if (position == 2) {
					button = (RadioButton) mView.findViewById(R.id.tv_work);
				}/*
				 * else if(position==4){ button =(RadioButton)
				 * mView.findViewById(R.id.tv_stocks); }
				 */
				button.setChecked(true);

				if (mPosition == 0 && position == 1) {
					valueAnimator(0, screenW / 3);
				} else if (mPosition == 1 && position == 2) {
					valueAnimator(screenW / 3, screenW / 3 * 2);
				} else if (mPosition == 2 && position == 3) {
					valueAnimator(screenW / 3 * 2, screenW);
				} else if (mPosition == 3 && position == 2) {
					valueAnimator(screenW, screenW / 3 * 2);
				} else if (mPosition == 2 && position == 1) {
					valueAnimator(screenW / 3 * 2, screenW / 3);
				} else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW / 3, 0);
				} else if (mPosition == 0 && position == 2) {
					valueAnimator(0, screenW / 3 * 2);
				} else if (mPosition == 0 && position == 3) {
					valueAnimator(0, screenW);
				} else if (mPosition == 1 && position == 3) {
					valueAnimator(screenW / 3, screenW);
				} else if (mPosition == 3 && position == 1) {
					valueAnimator(screenW, screenW / 3);
				} else if (mPosition == 3 && position == 0) {
					valueAnimator(screenW, 0);
				} else if (mPosition == 2 && position == 0) {
					valueAnimator(screenW / 3 * 2, 0);
				}
				mPosition = position;

			}

			/*
			 * if (mPosition == 0 && position == 1) { valueAnimator(0, screenW /
			 * 5); } else if (mPosition == 1 && position == 2) {
			 * valueAnimator(screenW/5, screenW/5*2); }else if (mPosition == 2
			 * && position == 3) { valueAnimator(screenW/5*2, screenW/5*3);
			 * }else if (mPosition == 3 && position == 4) {
			 * valueAnimator(screenW/5*3, screenW/5*4); }else if (mPosition == 4
			 * && position == 5) { valueAnimator(screenW/5*4, screenW); }else if
			 * (mPosition == 5 && position == 4) { valueAnimator(screenW,
			 * screenW/5*4); }else if (mPosition == 4 && position == 3) {
			 * valueAnimator(screenW/5*4, screenW/5*3); }else if (mPosition == 3
			 * && position == 2) { valueAnimator(screenW/5*3, screenW/5*2);
			 * }else if (mPosition == 2 && position == 1) {
			 * valueAnimator(screenW/5*2, screenW / 5); }else if (mPosition == 1
			 * && position == 0) { valueAnimator(screenW / 5, 0); }else if
			 * (mPosition == 0 && position == 2) { valueAnimator(0,
			 * screenW/5*2); }else if (mPosition == 0 && position == 3) {
			 * valueAnimator(0, screenW/5*3); }else if (mPosition == 0 &&
			 * position == 4) { valueAnimator(0, screenW/5*4); }else if
			 * (mPosition == 0 && position == 5) { valueAnimator(0, screenW);
			 * }else if (mPosition == 1 && position == 3) {
			 * valueAnimator(screenW/5, screenW/5*3); }else if (mPosition == 1
			 * && position == 4) { valueAnimator(screenW/5, screenW/5*4); }else
			 * if (mPosition == 1 && position == 5) { valueAnimator(screenW/5,
			 * screenW); }else if (mPosition == 2 && position == 4) {
			 * valueAnimator(screenW/5*2, screenW/5*4); }else if (mPosition == 2
			 * && position == 5) { valueAnimator(screenW/5*2, screenW); }else if
			 * (mPosition == 3 && position == 5) { valueAnimator(screenW/5*3,
			 * screenW); }else if (mPosition == 5 && position == 3) {
			 * valueAnimator(screenW, screenW/5*3); }else if (mPosition == 5 &&
			 * position == 2) { valueAnimator(screenW, screenW/5*2); }else if
			 * (mPosition == 4 && position == 2) { valueAnimator(screenW/5*4,
			 * screenW/5*2); }else if (mPosition == 5 && position == 1) {
			 * valueAnimator(screenW, screenW/5); }else if (mPosition == 4 &&
			 * position == 1) { valueAnimator(screenW/5*4, screenW/5); }else if
			 * (mPosition == 3 && position == 1) { valueAnimator(screenW/5*3,
			 * screenW/5); }else if (mPosition == 5 && position == 0) {
			 * valueAnimator(screenW, 0); }else if (mPosition == 4 && position
			 * == 0) { valueAnimator(screenW/5*4, 0); }else if (mPosition == 3
			 * && position == 0) { valueAnimator(screenW/5*3, 0); }else if
			 * (mPosition == 2 && position == 0) { valueAnimator(screenW/5*2,
			 * 0); } mPosition = position;
			 * 
			 * }
			 */

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void valueAnimator(int start, int end) {
		Animation animation = new TranslateAnimation(start, end, 0, 0);
		animation.setDuration(1);
		animation.setFillAfter(true);

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.tv_tasks:
			pager.setCurrentItem(0);

			break;
		/*
		 * case R.id.tv_review: pager.setCurrentItem(1); break;
		 */
		case R.id.tv_scheduling:
			pager.setCurrentItem(1);
			break;
		case R.id.tv_work:
			pager.setCurrentItem(2);
			break;
		/*
		 * case R.id.tv_stocks: pager.setCurrentItem(4); break;
		 */
		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_left:
//			Intent intent10 = new Intent();
//			intent10.setClass(mContext, MainActivity.class);
//			startActivity(intent10);
			mainActivity.setCurrentItem(0);

			break;
		case R.id.ll_release:
			Intent intent = new Intent();
			intent.setClass(mContext, Release.class);
			startActivity(intent);
			break;
		case R.id.ll_key_word_search_btn:
			Intent intent1 = new Intent();
			intent1.setClass(mContext, Search.class);
			startActivity(intent1);
			break;
		case R.id.ll_message:
			Intent intent2 = new Intent();
			intent2.setClass(mContext, Message.class);
			startActivity(intent2);
			break;
		case R.id.Apply:
			Intent intent3 = new Intent();
			intent3.setClass(mContext, Apply.class);
			startActivity(intent3);
			break;

		default:
			break;
		}

	};
}
