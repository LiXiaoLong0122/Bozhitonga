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

import com.example.bozhitong.fragment.MyattentionFragment;
import com.example.bozhitong.fragment.MyfansFragment;
import com.example.bozhitong.fragment.adapter.MyattentionPagerAdapter;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class Myattention extends FragmentActivity implements OnCheckedChangeListener {
	private ViewPager pager;
	private List<Fragment> beans;
	private MyattentionPagerAdapter adapter;
	private RadioGroup group;// ����
	private Button button;// �߿�
	private int mPosition;// �洢position
	private int screenW = 0;
    private int start = 0;// ��ʼλ��
    private ImageView mBack;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_myattention);
		initView();
		initData();
	}
	private void initView() {

		mBack=(ImageView) findViewById(R.id.img_Myattentionback);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Myattention.this.finish();
			}
		});
		pager = (ViewPager) findViewById(R.id.view_pager);
		adapter = new MyattentionPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		group = (RadioGroup) findViewById(R.id.rg_group);
		group.setOnCheckedChangeListener(this);
		beans = new ArrayList<Fragment>();
		button=(Button) findViewById(R.id.but_xian);
		screenW = Tools.getScreenWidth(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenW/4, 6);
		button.setLayoutParams(params);
		valueAnimator(screenW /8, screenW /8);
		

		
		
		
		
		
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				RadioButton button;
				if(position==0){
					 button =(RadioButton) findViewById(R.id.tv_Myattention);
				}else{
					 button =(RadioButton) findViewById(R.id.tv_Myfans);
				}
				
				button.setChecked(true);
				
				// �ı��ߵ�λ��
				if (mPosition == 0 && position == 1) {
					valueAnimator(screenW/8, screenW / 8*5);
				} else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW / 8*5, screenW/8);
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
	
	
	
	/**
	 * ��ʼλ�ã�����
	 * @param start
	 * @param end
	 */
	private void valueAnimator(int start, int end) {
		// TODO Auto-generated method stub
		Animation animation = new TranslateAnimation(start, end, 0, 0);
		animation.setDuration(1);// ����ʱ��
		animation.setFillAfter(true);// ����λ��
		button.startAnimation(animation);

	}
	private void initData() {
		beans.clear();
	     beans.add(new MyattentionFragment());
	     beans.add(new MyfansFragment());
		adapter.setBeans(beans);
		adapter.notifyDataSetChanged();

	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.tv_Myattention:
			pager.setCurrentItem(0);
			break;
		case R.id.tv_Myfans:
			pager.setCurrentItem(1);
			break;
		default:
			break;
		}

	}
}
