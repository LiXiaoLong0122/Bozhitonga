package com.example.bozhitong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.bozhitong.fragment.AdviceFragment;
import com.example.bozhitong.fragment.ComplainsFragment;
import com.example.bozhitong.fragment.PraiseFragment;
import com.example.bozhitong.fragment.adapter.ComplaintrecordPagerAdapter;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class Complaintrecord extends FragmentActivity implements OnCheckedChangeListener {
	
	private ViewPager pager;
	private List<Fragment> beans;
	private ComplaintrecordPagerAdapter adapter;
	private RadioGroup group;
	private Button button;
	private int mPosition;
	private int screenW = 0;
    private int start = 0;
    private ImageView mBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_complainrecord);
		initView();
		initData();
	}
	private void initData() {
		beans.clear();
		beans.add(new ComplainsFragment());
		beans.add(new AdviceFragment());
		beans.add(new PraiseFragment());
		adapter.setBeans(beans);
		adapter.notifyDataSetChanged();
	}
	private void initView() {
      mBack=(ImageView)findViewById(R.id.img_back);
      mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		pager = (ViewPager) findViewById(R.id.view_pager);
		adapter = new ComplaintrecordPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		group = (RadioGroup) findViewById(R.id.rg_group);
		group.setOnCheckedChangeListener(this);
		beans = new ArrayList<Fragment>();
		button=(Button) findViewById(R.id.but_xian);
		screenW = Tools.getScreenWidth(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenW/3, 6);
		button.setLayoutParams(params);
		valueAnimator(0, 0);
		
		
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				RadioButton button = null;
				if(position==0){
					 button =(RadioButton) findViewById(R.id.tv_Complains);
				}else if(position==1){
					 button =(RadioButton) findViewById(R.id.tv_Advice);
				}else if(position==2){
					 button =(RadioButton) findViewById(R.id.tv_Praise);
				}
				button.setChecked(true);
				
				
				if (mPosition == 0 && position == 1) {
					valueAnimator(0, screenW / 3);
				}  else if (mPosition == 1 && position == 2) {
					valueAnimator(screenW/3, screenW/3*2);
				}else if (mPosition == 2 && position == 3) {
					valueAnimator(screenW/3*2, screenW);
				}else if (mPosition == 3 && position == 2) {
					valueAnimator(screenW, screenW/3*2);
				}else if (mPosition == 2 && position == 1) {
					valueAnimator(screenW/3*2, screenW / 3);
				}else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW / 3, 0);
				}else if (mPosition == 0 && position == 2) {
					valueAnimator(0, screenW/3*2);
				}else if (mPosition == 0 && position == 3) {
					valueAnimator(0, screenW);
				}else if (mPosition == 1 && position == 3) {
					valueAnimator(screenW/3, screenW);
				}else if (mPosition == 3 && position == 1) {
					valueAnimator(screenW, screenW/3);
				}else if (mPosition == 3 && position == 0) {
					valueAnimator(screenW, 0);
				}else if (mPosition == 2 && position == 0) {
					valueAnimator(screenW/3*2, 0);
				}
				mPosition = position;
				
			}
			
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
		button.startAnimation(animation);
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.tv_Complains:
			pager.setCurrentItem(0);
			break;
		case R.id.tv_Advice:
			pager.setCurrentItem(1);
			break;
		case R.id.tv_Praise:
			pager.setCurrentItem(2);
			break;
		default:
			break;
		}
		
	};
}
