package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.bozhitong.photo.adapter.GridAdapter;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.photo.util.FileUtils;
import com.example.bozhitong.photo.util.ImageItem;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.photo.util.Res;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.PopupwindowPhoto;

//R.layout.activity_eagertoinform
public class Eagertoinform extends Activity implements OnClickListener {

	private ImageView mBack;

	private GridView noScrollgridview;
	private GridAdapter adapter;

	private PopupwindowPhoto pop;
	public static Bitmap bimap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_eagertoinform);
		initView();
		initPhoto();
		addListener();
	}
	private void initPhoto() {
		pop = new PopupwindowPhoto(this);

		Res.init(this);
		bimap = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_addpic_unfocused);
		PublicWay.activityList.add(this);

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

		pop.onRequestPermissionsResult(requestCode,permissions,grantResults);

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	private void addListener() {
		mBack.setOnClickListener(this);

	}


	private void initView() {
		mBack = (ImageView) findViewById(R.id.img_back);
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				if (arg2 == Bimp.tempSelectBitmap.size()) {

					pop.showpopPhotoAtLocation(Gravity.BOTTOM, 0, 0);

				} else {
					Intent intent = new Intent(Eagertoinform.this,
							GalleryActivity.class);
					intent.putExtra("position", "1");
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		default:
			break;
		}
		;
	}
	public void onStart() {
		adapter.update();
		super.onStart();
	}




	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {


		switch (requestCode) {
			case ContentValuse.TAKE_PICTURE:
				if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {

					String fileName = String.valueOf(System.currentTimeMillis());
					Bitmap bm = (Bitmap) data.getExtras().get("data");
					FileUtils.saveBitmap(bm, fileName);

					ImageItem takePhoto = new ImageItem();
					takePhoto.setBitmap(bm);
					Bimp.tempSelectBitmap.add(takePhoto);
				}
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		adapter.notifyDataSetChanged();
		Bimp.tempSelectBitmap.clear();
		Bimp.max = 0;
	}
}