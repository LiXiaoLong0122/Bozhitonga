package com.example.bozhitong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.bozhitong.fragment.AllordersFragment;
import com.example.bozhitong.fragment.ForthegoodsFragment;
import com.example.bozhitong.fragment.ObligationFragment;
import com.example.bozhitong.fragment.OverhangFragment;
import com.example.bozhitong.fragment.adapter.OrderPagerAdapter;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class Order extends FragmentActivity implements OnCheckedChangeListener {
	
	private ViewPager pager;
	private List<Fragment> beans;
	private OrderPagerAdapter adapter;
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
		setContentView(R.layout.activity_order);
		initView();
		initData();
	}
	private void initData() {
		beans.clear();
		beans.add(new AllordersFragment());
		beans.add(new ObligationFragment());
		beans.add(new OverhangFragment());
		beans.add(new ForthegoodsFragment());
		adapter.setBeans(beans);
		adapter.notifyDataSetChanged();
		
	}
	private void initView() {
        mBack=(ImageView) findViewById(R.id.img_back);
        mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(Order.this, "返回事件", Toast.LENGTH_LONG).show();
//				FragmentManager manager = getChildFragmentManager();
//				FragmentTransaction transaction = manager.beginTransaction();
//				transaction.remove(new RecordsFragment());
//				transaction.addToBackStack(null); 
//				transaction.commit();
				finish();
			}
		});
		pager = (ViewPager) findViewById(R.id.view_pager);
		adapter = new OrderPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		group = (RadioGroup) findViewById(R.id.rg_group);
		group.setOnCheckedChangeListener(this);
		beans = new ArrayList<Fragment>();
		button=(Button) findViewById(R.id.but_xian);
		screenW = Tools.getScreenWidth(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenW/4, 6);//Button的宽与高
		button.setLayoutParams(params);
		valueAnimator(0, 0);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				RadioButton button = null;
				if(position==0){
					 button =(RadioButton) findViewById(R.id.tv_Allorders);
				}else if(position==1){
					 button =(RadioButton) findViewById(R.id.tv_obligation);
				}else if(position==2){
					 button =(RadioButton) findViewById(R.id.tv_Overhang);
				}else if(position==3){
					 button =(RadioButton) findViewById(R.id.tv_Forthegoods);
				}
				button.setChecked(true);
				if (mPosition == 0 && position == 1) {
					valueAnimator(0, screenW / 4);
				}  else if (mPosition == 1 && position == 2) {
					valueAnimator(screenW/4, screenW/4*2);
				}else if (mPosition == 2 && position == 3) {
					valueAnimator(screenW/4*2, screenW/4*3);
				}else if (mPosition == 3 && position == 4) {
					valueAnimator(screenW/4*3, screenW);
				}else if (mPosition == 4 && position == 3) {
					valueAnimator(screenW, screenW/4*3);
				}else if (mPosition == 3 && position == 2) {
					valueAnimator(screenW/4*3, screenW/4*2);
				}else if (mPosition == 2 && position == 1) {
					valueAnimator(screenW/4*2, screenW / 4);
				}else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW / 4, 0);
				}else if (mPosition == 0 && position == 2) {
					valueAnimator(0, screenW/4*2);
				}else if (mPosition == 0 && position == 3) {
					valueAnimator(0, screenW/4*3);
				}else if (mPosition == 0 && position == 4) {
					valueAnimator(0, screenW);
				}else if (mPosition == 1 && position == 4) {
					valueAnimator(screenW/4, screenW);
				}else if (mPosition == 1 && position == 3) {
					valueAnimator(screenW/4, screenW/4*3);
				}else if (mPosition == 4 && position == 1) {
					valueAnimator(screenW, screenW/4);
				}else if (mPosition == 3 && position == 1) {
					valueAnimator(screenW/4*3, screenW/4);
				}else if (mPosition == 4 && position == 0) {
					valueAnimator(screenW, 0);
				}else if (mPosition == 3 && position == 0) {
					valueAnimator(screenW/4*3, 0);
				}else if (mPosition == 2 && position == 0) {
					valueAnimator(screenW/4*2, 0);
				}else if (mPosition == 1 && position == 0) {
					valueAnimator(screenW/4, 0);
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
		button.startAnimation(Tools.getAnimation(start,end));
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.tv_Allorders:
			pager.setCurrentItem(0);
			break;
		case R.id.tv_obligation:
			pager.setCurrentItem(1);
			break;
		case R.id.tv_Overhang:
			pager.setCurrentItem(2);
			break;
		case R.id.tv_Forthegoods:
			pager.setCurrentItem(3);
			break;
		default:
			break;
		}
	};
}
