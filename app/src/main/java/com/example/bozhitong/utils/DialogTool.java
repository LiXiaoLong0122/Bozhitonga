package com.example.bozhitong.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.example.bozhitong.dialog.view.CustomDialog;

/**
 * 显示对话框的工具类
 * 
 * @author 12306
 * 
 */
public class DialogTool {
	private Activity mContext;
	private String[] sexArry = new String[]{"男孩", "女孩"};//性别选择
	public DialogTool() {
		super();
	}

	public DialogTool(Activity mContext) {
		super();
		this.mContext = mContext;
	}

	public CustomDialog showeDialog(String message, String title,
									String positivebutton, String NegativeButton) {
		CustomDialog.Builder builder = new CustomDialog.Builder(mContext);
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setPositiveButton(positivebutton,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// 设置你的操作事项

					}
				});

		builder.setNegativeButton(NegativeButton,
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		CustomDialog daogin = builder.create();
		daogin.show();
		return daogin;
	}
	/* 性别选择框 */
	public   void showChooseDialog(final TextView tv) {

		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);// 自定义对话框
		builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

			@Override
			public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
				//showToast(which+"");
				tv.setText(sexArry[which]);
				dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
			}
		});
		builder.show();// 让弹出框显示
	}
}
