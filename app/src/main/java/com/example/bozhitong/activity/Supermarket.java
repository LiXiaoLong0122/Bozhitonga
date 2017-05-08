package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.bozhitong.R;

public class Supermarket extends Activity implements OnClickListener {
	private ImageView mWeb;
	private ImageView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_supermarket);
		mWeb = (ImageView) findViewById(R.id.supermark_webview);
		mBack = (ImageView) findViewById(R.id.supermark_back);
		mBack.setOnClickListener(this);
		// // 监听webview
		// mWeb.setWebViewClient(new WebViewClient() {
		// // 网页加载完成
		// @Override
		// public void onPageFinished(WebView view, String url) {
		// // TODO Auto-generated method stub
		//
		// super.onPageFinished(view, url);
		// }
		//
		// // 网页加载失败，重新加载
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// // TODO Auto-generated method stub
		//
		// mWeb.loadUrl(url);// 重新加载
		// return super.shouldOverrideUrlLoading(view, url);
		// }
		// });
		// // 监听浏览器
		// mWeb.setWebChromeClient(new WebChromeClient() {
		// // 加载进度更新
		// @Override
		// public void onProgressChanged(WebView view, int newProgress) {
		// // TODO Auto-generated method stub
		//
		// super.onProgressChanged(view, newProgress);
		// }
		// });
		initView();
		initData();
	}

	private void initView() {

		// WebSettings webSettings = mWeb.getSettings();
		// webSettings.setJavaScriptEnabled(true); // 支持 js
		// // webSettings.setPluginsEnabled(true); //支持插件
		// webSettings.setUseWideViewPort(false); // 将图片调整到适合 webview 的大小
		// webSettings.setSupportZoom(true); // 支持缩放
		// webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); //
		// 支持内容重新布局
		// webSettings.supportMultipleWindows(); // 多窗口
		// webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 关闭
		// // webview
		// // 中缓存
		// webSettings.setAllowFileAccess(true); // 设置可以访问文件
		// webSettings.setNeedInitialFocus(true); // 当 webview 调用 requestFocus
		// 时为
		// // webview 设置节点
		// webSettings.setBuiltInZoomControls(true); // 设置支持缩放
		// webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过
		// JS
		// // 打开新窗口
		// webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
		// webSettings.setLoadsImagesAutomatically(true); // 支持自动加载图片
	}

	private void initData() {
		// http://c.b1za.com/h.2gVePi?cv=vbhD7Y9MMX&sm=2f1e0e
		// mWeb.loadUrl("https://m.taobao.com/?spm=0.0.0.0.QcYDXn#index");//
		// 加载网页.html文件

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (keyCode == KeyEvent.KEYCODE_BACK && mWeb.canGoBack()) {
		//
		// //mWeb.goBack();// 返回上一页面
		// finish();
		// return true;
		//
		// } else {
		//
		// }
		return super.onKeyDown(keyCode, event);

	}

	@Override
	protected void onDestroy() {
		// if (mWeb.canGoBack()) {// 网页中存在返回
		// mWeb.goBack();// 网页中的返回
		// }
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.supermark_back:
			finish();
			break;

		default:
			break;
		}

	}

}
