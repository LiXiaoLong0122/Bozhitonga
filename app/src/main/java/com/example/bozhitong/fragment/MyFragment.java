package com.example.bozhitong.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.bozhitong.Circle;
import com.example.bozhitong.Complaintrecord;
import com.example.bozhitong.Coupons;
import com.example.bozhitong.Goodscollection;
import com.example.bozhitong.House;
import com.example.bozhitong.Integral;
import com.example.bozhitong.MainActivity;
import com.example.bozhitong.Order;
import com.example.bozhitong.Pay;
import com.example.bozhitong.Profile;
import com.example.bozhitong.R;
import com.example.bozhitong.Records;
import com.example.bozhitong.Regularmembers;
import com.example.bozhitong.Rental;
import com.example.bozhitong.Research;
import com.example.bozhitong.Set;
import com.example.bozhitong.activity.AddHousingInformation;
import com.example.bozhitong.utils.ContentValuse;

public class MyFragment extends Fragment implements OnClickListener {
    private Context mContext;
    private View view;
    
    private ImageView mMoon,im_center;
    private RelativeLayout mUnauthorized,mRegularmembers,mIntegral,
    mCoupons,mGoodscollection,mRecords,mComplain,mPay,mResearch,
    mRental,mHouse,mOrder,mCircle,mSet;
    private FrameLayout mFlpager;
    private ScrollView mScrollView;
    private MainActivity mainActivity;
    ProgressDialog progressDialog;
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	mContext=activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
    	view=inflater.inflate(R.layout.activity_my, null);
    	initView();
    	addListener();
    	return view;
    }
	private void addListener() {
	im_center.setOnClickListener(this);
    mMoon.setOnClickListener(this);
    mUnauthorized.setOnClickListener(this);
	mRegularmembers.setOnClickListener(this);
	mIntegral.setOnClickListener(this);
	mCoupons.setOnClickListener(this);
	mGoodscollection.setOnClickListener(this);
	mRecords.setOnClickListener(this);
	mComplain.setOnClickListener(this);
	mPay.setOnClickListener(this);
	mResearch.setOnClickListener(this);
    mRental.setOnClickListener(this);
    mHouse.setOnClickListener(this);
    mOrder.setOnClickListener(this);
    mCircle.setOnClickListener(this);
    mSet.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		if (ContentValuse.ischange){
			ContentValuse.ischange = false;
			im_center.setImageBitmap(ContentValuse.MBITMAP);
		}
		super.onResume();
	}

	private void initView() {
		im_center=(ImageView) view.findViewById(R.id.im_center);
		mMoon=(ImageView) view.findViewById(R.id.img_moon);
		mUnauthorized=(RelativeLayout) view.findViewById(R.id.Rl_unauthorized);
		mScrollView=(ScrollView) view.findViewById(R.id.scrollView);
		
		mRegularmembers=(RelativeLayout) view.findViewById(R.id.Rl_Regularmembers);
		mIntegral=(RelativeLayout) view.findViewById(R.id.Rl_integral);
		mCoupons=(RelativeLayout) view.findViewById(R.id.Rl_coupons);
		mGoodscollection=(RelativeLayout) view.findViewById(R.id.Rl_Goodscollection);
		mRecords=(RelativeLayout) view.findViewById(R.id.Rl_records);
		mComplain=(RelativeLayout) view.findViewById(R.id.Rl_complain);
		mPay=(RelativeLayout) view.findViewById(R.id.Rl_Pay);
		mResearch=(RelativeLayout) view.findViewById(R.id.Rl_research);
	    mRental=(RelativeLayout) view.findViewById(R.id.Rl_rental);
	    mHouse=(RelativeLayout) view.findViewById(R.id.Rl_house);
	    mOrder=(RelativeLayout) view.findViewById(R.id.Rl_order);
	    mCircle=(RelativeLayout) view.findViewById(R.id.Rl_circle);
	    mSet=(RelativeLayout) view.findViewById(R.id.Rl_set);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		   case R.id.im_center:
			case R.id.img_moon:
//			/*	Toast.makeText(mContext, "图片", Toast.LENGTH_LONG).show();*/
//		//	onClick2();
//			Intent intent0 = new Intent();
//			intent0.setClass(mContext, Profile.class);
//		//	startActivity(intent0);
//			startActivityForResult(intent0,ContentValuse.BITMAP_BACK);
//			break;
		/*	Toast.makeText(mContext, "图片", Toast.LENGTH_LONG).show();*/
		//	onClick2();
			Intent intent = new Intent();
			intent.setClass(mContext, Profile.class);
			startActivity(intent);
	   // startActivityForResult(intent,ContentValuse.BITMAP_BACK);
			break;
		case R.id.Rl_unauthorized:
			//Toast.makeText(mContext, "点击未认证", Toast.LENGTH_LONG).show();
			Intent intent1 = new Intent();
			intent1.setClass(mContext, AddHousingInformation.class);
			startActivity(intent1);
			break;
		case R.id.Rl_Regularmembers:
			Intent intent2 = new Intent();
			intent2.setClass(mContext, Regularmembers.class);
			startActivity(intent2);
			break;
		case R.id.Rl_integral:
			Intent intent3 = new Intent();
			intent3.setClass(mContext, Integral.class);
			startActivity(intent3);
			break;
		case R.id.Rl_coupons:
			Intent intent4 = new Intent();
			intent4.setClass(mContext, Coupons.class);
			startActivity(intent4);
			break;
		case R.id.Rl_Goodscollection:
			Intent intent5 = new Intent();
			intent5.setClass(mContext, Goodscollection.class);
			startActivity(intent5);
			break;
		case R.id.Rl_records:
			Intent intent6 = new Intent();
			intent6.setClass(mContext, Records.class);
			startActivity(intent6);
			break;
		case R.id.Rl_complain:
			Intent intent7 = new Intent();
			intent7.setClass(mContext, Complaintrecord.class);
			startActivity(intent7);
			break;
		case R.id.Rl_Pay:
			Intent intent8 = new Intent();
			intent8.setClass(mContext, Pay.class);
			startActivity(intent8);
			break;
		case R.id.Rl_research:
			Intent intent9 = new Intent();
			intent9.setClass(mContext, Research.class);
			startActivity(intent9);
			break;
		case R.id.Rl_rental:
			Intent intent10 = new Intent();
			intent10.setClass(mContext, Rental.class);
			startActivity(intent10);
			break;
		case R.id.Rl_house:
			Intent intent11 = new Intent();
			intent11.setClass(mContext, House.class);
			startActivity(intent11);
			break;
		case R.id.Rl_order:
			Intent intent12 = new Intent();
			intent12.setClass(mContext, Order.class);
			startActivity(intent12);
			break;
		case R.id.Rl_circle:
			Intent intent13 = new Intent();
			intent13.setClass(mContext, Circle.class);
			startActivity(intent13);
			break;
		case R.id.Rl_set:
			/*Toast.makeText(mContext, "设置", Toast.LENGTH_LONG).show();*/
			Intent intent14 = new Intent();
			intent14.setClass(mContext, Set.class);
			startActivity(intent14);
			break;
		default:
			break;
		}
		
	}
	  public void onClick2() {  
	        // 显示的对话框 
	        final ProgressDialog dialog = ProgressDialog.show(mContext,
	                "", "", true);
	   
//	        final ProgressDialog dialog = ProgressDialog.show(mContext,  
//	        		"           正在加载数据", "   请等待...", true);  
	        new Thread(new Runnable() {
	            public void run() {  
	                try {  
	                    //模拟做一些冗长的  
	                    Thread.sleep(500);
	                    // 关闭对话框
	                    dialog.dismiss();  
	                } catch (InterruptedException e) {
	                    e.printStackTrace();  
	                }  
	            }  
	        }).start();  
	    }


}
