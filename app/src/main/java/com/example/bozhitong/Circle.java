package com.example.bozhitong;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.view.RoundImageView;

public class Circle extends Activity implements OnClickListener {

	private ImageView mBack;
	//我的关注,关注我的,私信互动
	private RelativeLayout layout,mMyattention,mAttentionmy,mDirectmessagesinteract;
	//个性签名signature,发布话题,参与话题,收藏话题,不看Ta发布话题,黑名单
	private LinearLayout mPersonalizedsignature,
	        mReleasetopic,mParticipateinthesubject,mCollecttopic,
	        mNoReleasetopic,mBlacklist;
	
	
	private RoundImageView rim;
	
	
	//相册
	private Button btn_picture, btn_photo, btn_cancale;
	private ImageView ivHead;
	//调用系统相册-选择图片
    private static final int IMAGE = 1;
    private static final int CAMERA_REQUEST = 2; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_circle);
		initView();
		addListener();
	}
	private void addListener() {
		ivHead.setOnClickListener(this);
		layout.setOnClickListener(this);
		mBack.setOnClickListener(this);
		mMyattention.setOnClickListener(this);
		mAttentionmy.setOnClickListener(this);
		mDirectmessagesinteract.setOnClickListener(this);
		mPersonalizedsignature.setOnClickListener(this);
        mReleasetopic.setOnClickListener(this);
        mParticipateinthesubject.setOnClickListener(this);
        mCollecttopic.setOnClickListener(this);
        mNoReleasetopic.setOnClickListener(this);
        mBlacklist.setOnClickListener(this);
		
	}
	private void initView() {
		ivHead = (ImageView) findViewById(R.id.iv_head);
		layout = (RelativeLayout) findViewById(R.id.ll_images);
		mBack = (ImageView) findViewById(R.id.img_MyBack);
		mMyattention=(RelativeLayout) findViewById(R.id.Rl_myattention);
		mAttentionmy=(RelativeLayout) findViewById(R.id.Rl_attentionmy);
		mDirectmessagesinteract=(RelativeLayout) findViewById(R.id.Rl_Directmessagesinteract);
		mPersonalizedsignature=(LinearLayout) findViewById(R.id.personalizedsignature);
        mReleasetopic=(LinearLayout) findViewById(R.id.Releasetopic);
        mParticipateinthesubject=(LinearLayout) findViewById(R.id.Participateinthesubject);
        mCollecttopic=(LinearLayout) findViewById(R.id.Collecttopic);
        mNoReleasetopic=(LinearLayout) findViewById(R.id.NoReleasetopic);
        mBlacklist=(LinearLayout) findViewById(R.id.blacklist);
		rim = (RoundImageView)findViewById(R.id.im_center);
		if (ContentValuse.MBITMAP != null){
			rim.setImageBitmap(ContentValuse.MBITMAP);
		}

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_MyBack:
			finish();
			break;
		case R.id.Rl_myattention:
			Intent intent = new Intent();
			intent.setClass(Circle.this, Myattention.class);
			startActivity(intent);
			break;
		case R.id.Rl_attentionmy:
			Intent intent1 = new Intent();
			intent1.setClass(Circle.this, AttentionMy.class);
			startActivity(intent1);
			break;
		case R.id.Rl_Directmessagesinteract:
			Intent intent2 = new Intent();
			intent2.setClass(Circle.this, PrivateLetter.class);
			startActivity(intent2);
			break;
		case R.id.personalizedsignature:
			Intent intent3 = new Intent();
			intent3.setClass(Circle.this, Signature.class);
			startActivity(intent3);
			break;
		case R.id.Releasetopic:
			Intent intent4 = new Intent();
			intent4.setClass(Circle.this, Releasetopic.class);
			startActivity(intent4);
			break;
		case R.id.Participateinthesubject:
			Intent intent5 = new Intent();
			intent5.setClass(Circle.this, Participateinthesubject.class);
			startActivity(intent5);
			break;
		case R.id.Collecttopic:
			Intent intent6 = new Intent();
			intent6.setClass(Circle.this, Collecttopic.class);
			startActivity(intent6);
			break;
		case R.id.NoReleasetopic:
			Intent intent7 = new Intent();
			intent7.setClass(Circle.this, NoReleasetopic.class);
			startActivity(intent7);
			break;
		case R.id.blacklist:
			Intent intent8 = new Intent();
			intent8.setClass(Circle.this, Blacklist.class);
			startActivity(intent8);
			break;
		case R.id.iv_head:
			showDialog();
			break;
		default:
			break;
		}
		
	}


	
	
	
	//弹框
	private void showDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog,
				null);
		final Dialog dialog = new Dialog(this,
				R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();
		// 一下两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

		btn_picture = (Button) window.findViewById(R.id.btn_picture);
		btn_photo = (Button) window.findViewById(R.id.btn_photo);
		btn_cancale = (Button) window.findViewById(R.id.btn_cancle);

		btn_picture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(Intent.ACTION_PICK, null);
				intent1.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				 startActivityForResult(intent1, IMAGE);
				dialog.dismiss();
			}
		});

		btn_photo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent2, CAMERA_REQUEST);// 采用ForResult打开
				dialog.dismiss();

			}
		});

		btn_cancale.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();

			}
		});

	}
	
	
	
//	//获取图片路径
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Log.e("dsakjdlkjdlkdjgl", photo.toString());
            ((ImageView)findViewById(R.id.iv_head)).setImageBitmap(photo);
        }  
		 super.onActivityResult(requestCode, resultCode, data);
	}

	private void showImage(String imagePath) {
		 Bitmap bm = BitmapFactory.decodeFile(imagePath);
	        ((ImageView)findViewById(R.id.iv_head)).setImageBitmap(bm);
	}
}