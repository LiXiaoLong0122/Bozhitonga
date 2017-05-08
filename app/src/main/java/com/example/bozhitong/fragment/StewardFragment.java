package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.R;
import com.example.bozhitong.activity.CommonQuery;
import com.example.bozhitong.activity.ComplaintAndAdvocacy;
import com.example.bozhitong.activity.ExpressWarranty;
import com.example.bozhitong.activity.HomeReservation;
import com.example.bozhitong.activity.Housekeeper;
import com.example.bozhitong.activity.HousingRentalActivity;
import com.example.bozhitong.activity.PayCostActivity;
import com.example.bozhitong.activity.PropertyDescription;
import com.example.bozhitong.activity.ResidentialBulletin;
import com.example.bozhitong.activity.ServiceGuideActivity;

/**
 * 管家界面 李瑞
 * 
 * @author 12306
 * 
 */
public class StewardFragment extends Fragment implements OnClickListener {
	private View mView;
	private Activity mContext;
	private TextView mHousing;
	/**
	 * 常用查询
	 */
	private TextView mCommonQuery;
	/**
	 * 小区公告
	 */
	private TextView mResidentialBulletin;
	/**
	 * 快捷报修
	 */
	private TextView mExpressWarr;
	/**
	 * 投诉建议
	 */
	private TextView mSuggestions;
	/**
	 * 物业缴费
	 */
	private TextView mProperPayment;
	/**
	 * 家政预约
	 */
	private TextView mHomeReservation;
	/**
	 * 办事指南
	 */
	private TextView mServiceGuide;
	/**
	 * 在线客服
	 */
	private TextView mOnlineService;
	/**
	 * 小区调研
	 */
	private TextView mComityResea;
	/**
	 * 物业介绍
	 */
	private TextView mProduction;
	/**
	 * 快递服务
	 */
	private TextView mMailService;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.activity_steward, null);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		mHousing = (TextView) getActivity().findViewById(R.id.housing_tv);
		mHousing.setOnClickListener(this);
		mCommonQuery = (TextView) getActivity().findViewById(R.id.Common_query);
		mCommonQuery.setOnClickListener(this);
		mResidentialBulletin = (TextView) getActivity().findViewById(
				R.id.Residential_tv);
		mResidentialBulletin.setOnClickListener(this);
		mExpressWarr = (TextView) getActivity().findViewById(
				R.id.ExpressWarr_tv);
		mExpressWarr.setOnClickListener(this);
		mSuggestions = (TextView) getActivity().findViewById(
				R.id.suggestions_tv);
		mSuggestions.setOnClickListener(this);
		mProperPayment = (TextView) getActivity().findViewById(
				R.id.ProperPayment_tv);
		mProperPayment.setOnClickListener(this);
		mHomeReservation = (TextView) getActivity().findViewById(
				R.id.HomeReon_tv);
		mHomeReservation.setOnClickListener(this);
		mServiceGuide = (TextView) getActivity().findViewById(
				R.id.ServiceGuide_tv);
		mServiceGuide.setOnClickListener(this);
		mOnlineService = (TextView) getActivity().findViewById(
				R.id.OnlineService_tv);
		mOnlineService.setOnClickListener(this);
		mComityResea = (TextView) getActivity().findViewById(
				R.id.comityResea_tv);
		mComityResea.setOnClickListener(this);
		mProduction = (TextView) getActivity().findViewById(
				R.id.Property_introduction_tv);
		mProduction.setOnClickListener(this);
		mMailService = (TextView) getActivity().findViewById(R.id.mail_service);
		mMailService.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.housing_tv:// 房屋出租
			intent = new Intent();
			intent.setClass(mContext, HousingRentalActivity.class);
			startActivity(intent);
			break;
		case R.id.Residential_tv:// 小区公告
			intent = new Intent();
			intent.setClass(mContext, ResidentialBulletin.class);
			startActivity(intent);
			break;
		case R.id.ExpressWarr_tv:// 快捷报修
			intent = new Intent();
			intent.setClass(mContext, ExpressWarranty.class);
			startActivity(intent);
			break;
		case R.id.suggestions_tv:// 投诉建议
			intent = new Intent();
			intent.setClass(mContext, ComplaintAndAdvocacy.class);
			startActivity(intent);
			break;
		case R.id.ProperPayment_tv:// 物业缴费
			intent = new Intent();
			//intent.setClass(mContext, PropertyPayment.class);
			intent.setClass(mContext, PayCostActivity.class);
			startActivity(intent);
			break;
		case R.id.HomeReon_tv:// 家政预约
			intent = new Intent();
			intent.setClass(mContext, HomeReservation.class);
			startActivity(intent);
			break;
		case R.id.ServiceGuide_tv:// 办事指南
			intent = new Intent();
			intent.setClass(mContext, ServiceGuideActivity.class);
			startActivity(intent);
			break;
		case R.id.OnlineService_tv:// 呼叫管家
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
					+ "029-81022300"));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		case R.id.comityResea_tv:// 调研问卷
			intent = new Intent();
			/* intent.setClass(mContext, CommunityResearch.class); */
			intent.setClass(mContext, Housekeeper.class);
			startActivity(intent);
			break;
		case R.id.Property_introduction_tv:// 物业介绍
			intent = new Intent();
			intent.setClass(mContext, PropertyDescription.class);
			startActivity(intent);
			break;
		case R.id.mail_service:// 快递服务
			Toast.makeText(mContext, "暂未开发，请耐心等待", Toast.LENGTH_LONG).show();
			break;
		case R.id.Common_query:
			intent = new Intent();
			intent.setClass(mContext, CommonQuery.class);
			startActivity(intent);
			// DialogTool dialogTool = new DialogTool(mContext);
			// CustomDialog daogin = dialogTool.showeDialog("您还没有绑定房屋信息", "提示",
			// "立即绑定", "以后在说");
			// daogin.findViewById(R.id.positiveButton).setOnClickListener(
			// new OnClickListener() {
			//
			// @Override
			// public void onClick(View arg0) {
			// Intent intent = new Intent();
			// intent.setClass(mContext,
			// AddHousingInformation.class);
			// startActivity(intent);
			// }
			// });
			// daogin.findViewById(R.id.negativeButton).setOnClickListener(
			// new OnClickListener() {
			//
			// @Override
			// public void onClick(View arg0) {
			// Intent intent = new Intent();
			// intent.setClass(mContext, CommonQuery.class);
			// startActivity(intent);
			// }
			// });
			break;
		default:
			break;
		}
	};

}
