package com.example.bozhitong;

import android.Manifest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.activity.AddHousingInformation;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.time.TimePopupWindow;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.DialogTool;
import com.example.bozhitong.view.RoundImageView;

import java.io.File;
import java.io.IOException;

public class Profile extends Activity implements OnClickListener {

    private final int CAMERA_REQUEST_CODE = 0;      //相机
    private final int PHOTE_REQUEST_CODE = 1;       //相册
    private final int REQUEST_CODE_CLIP_PHOTO = 2;  //图片裁剪

    private LinearLayout mHead, mNickname, mName, mGender, mBirthday,
            mPhonenumber, mHousinginformation, mShippingaddress, mFamily;
    private TextView tv, tv_gender;
    private ImageView mBack;

    String[] heads = new String[]{"相机", "相册"};//头像选择
    private PopupWindow mPopupWindow;

    private RoundImageView iv_head;

    private File mOutputFile;
private TimePopupWindow timeDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        initView();
        addListener();
    }

    private void addListener() {
        mBack.setOnClickListener(this);
        mHead.setOnClickListener(this);
        mNickname.setOnClickListener(this);
        mName.setOnClickListener(this);
        mGender.setOnClickListener(this);
        mBirthday.setOnClickListener(this);
        mPhonenumber.setOnClickListener(this);
        mHousinginformation.setOnClickListener(this);
        mShippingaddress.setOnClickListener(this);
        mFamily.setOnClickListener(this);

    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_MyProfile);
        mHead = (LinearLayout) findViewById(R.id.Head);
        mNickname = (LinearLayout) findViewById(R.id.nickname);
        mName = (LinearLayout) findViewById(R.id.name);
        mGender = (LinearLayout) findViewById(R.id.gender);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        mBirthday = (LinearLayout) findViewById(R.id.birthday);
        mPhonenumber = (LinearLayout) findViewById(R.id.phonenumber);
        mHousinginformation = (LinearLayout) findViewById(R.id.Housinginformation);
        mShippingaddress = (LinearLayout) findViewById(R.id.Shippingaddress);
        mFamily = (LinearLayout) findViewById(R.id.Family);

        iv_head = (RoundImageView) findViewById(R.id.iv_head);
        tv = (TextView) findViewById(R.id.tv);
        timeDialog = new TimePopupWindow(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_MyProfile:
                //   bitmapBack();
                finish();
                break;
            case R.id.Head:

                // showPopupWindow(mHead);

                showHeadDialog();

                Intent intent = new Intent();
        /*	intent.setClass(Profile.this, AddHousingInformation.class);
            startActivity(intent);*/
                break;
            case R.id.nickname:
            /*Toast.makeText(Profile.this, "", Toast.LENGTH_LONG).show();*/
                Intent intent1 = new Intent();
                intent1.setClass(Profile.this, Nickname.class);
                startActivity(intent1);
                break;
            case R.id.name:
                Intent intent2 = new Intent();
                intent2.setClass(Profile.this, Name.class);
                startActivity(intent2);
                break;
            case R.id.gender:
              //  showChooseDialog();
                new DialogTool(this).showChooseDialog(tv_gender);
                break;
            case R.id.birthday:

                timeDialog.showBottoPopupWindow("请选择时间",tv);

//                new DatePickerDialog(Profile.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        tv.setText(String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
//                    }
//                }, 2000, 1, 2).show();
                break;
            case R.id.phonenumber:
                Intent intent3 = new Intent();
                intent3.setClass(Profile.this, Phonenumber.class);
                startActivity(intent3);
                break;
            case R.id.Housinginformation:
                Intent intent4 = new Intent();
                intent4.setClass(Profile.this, AddHousingInformation.class);
                startActivity(intent4);
                break;
            case R.id.Shippingaddress:
                Intent intent5 = new Intent();
                intent5.setClass(Profile.this, Shippingaddress.class);
                startActivity(intent5);
                break;
            case R.id.Family:
                Intent intent6 = new Intent();
                intent6.setClass(Profile.this, Family.class);
                startActivity(intent6);
                break;
            default:
                break;
        }

    }

    ;



    private void showPopupWindow(View view) {
        if (mPopupWindow == null) {
            View popupView = getLayoutInflater().inflate(R.layout.pop_head, null);
            mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        }
        mPopupWindow.showAsDropDown(view);
    }

    /* 头像选择框 */
    private void showHeadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);// 自定义对话框
        builder.setSingleChoiceItems(heads, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                //  Toast.makeText(Profile.this, which + "", Toast.LENGTH_SHORT).show();
                dialog.dismiss();//随便点击一个item消失对话框，不用点击确认取消
                switch (which) {
                    case 0:
//                        //Android 6.0以上 不能只是在AndroidManifest.xml中进行配置 还要在代码中动态设置权限
//                        ActivityCompat.requestPermissions(Profile.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//
                        startCameraActivity();
                        break;
                    case 1:
                        //照片
                        startPhotoActivity();
                        break;
                }

            }
        });
        builder.show();// 让弹出框显示
    }


    //启动拍照
    private void startCameraActivity() {
        //获取当前File对象的绝对路径名的字符串形式

        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //声明new file并不会创建文件或文件夹
        mOutputFile = new File(sdPath, System.currentTimeMillis() + ".tmp");

        Uri uri = Uri.fromFile(mOutputFile);
       /*ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);*/
        //调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);

    }

    //启动系统相册
    private void startPhotoActivity() {
        //获取当前File对象的绝对路径名的字符串形式
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //声明new file并不会创建文件或文件夹
        mOutputFile = new File(sdPath, System.currentTimeMillis() + ".tmp");
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTE_REQUEST_CODE);

    }

    //启动相册裁剪
    private void startCropActivity(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //MediaStore.EXTRA_OUTPUT关联到一个Uri，此Uri是用来存放Bitmap
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mOutputFile.getAbsoluteFile() + "tmp")));
        startActivityForResult(intent, REQUEST_CODE_CLIP_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {//相册
            onSystemPhotoFinished(resultCode, data);
        } else if (requestCode == REQUEST_CODE_CLIP_PHOTO) {//裁剪页面
            onSystemClipPhotoFinished(resultCode, data);
        } else if (requestCode == PHOTE_REQUEST_CODE) {//相册
            onSystemPhotoAlbumFinished(resultCode, data);
        }
    }

    //系统相机界面关闭后
    private void onSystemPhotoFinished(int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {//点击取消
            Toast.makeText(this, "相册取消", Toast.LENGTH_LONG).show();
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "拍照失败", Toast.LENGTH_LONG).show();

            return;
        }
        startCropActivity(Uri.fromFile(mOutputFile));
    }


    //调用系统相册关闭后
    private void onSystemPhotoAlbumFinished(int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "相册取消", Toast.LENGTH_LONG).show();
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "选择图片异常", Toast.LENGTH_LONG).show();

            return;
        }

        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        try {
            Bitmap bitmap = Bimp.revitionImageSize(picturePath);
            setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//private void bitmapBack(){
//    if (mbitmap != null && !mbitmap.isRecycled()) {
//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//       bundle.putParcelable(ContentValuse.BITMAP_PROFILE, mbitmap);
//        intent.putExtra("b",bundle);
//        intent.putExtra(ContentValuse.TAG_COM,"111111111");
//        setResult(0, intent);
//   }
//}


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //bitmapBack();
        return super.onKeyDown(keyCode, event);
    }

    //系统裁剪页面关闭后
    private void onSystemClipPhotoFinished(int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {

            Toast.makeText(this, "裁剪取消", Toast.LENGTH_LONG).show();
            return;
        }
        if (resultCode != RESULT_OK) {

            Toast.makeText(this, "裁剪失败", Toast.LENGTH_LONG).show();
            return;
        }
        /**
         * 在真实项目中获取bitmap之后直接传给后台
         */
        Bitmap bitmap = BitmapFactory.decodeFile(mOutputFile.getAbsolutePath() + "tmp");
        setBitmap(bitmap);
    }


    private void setBitmap(Bitmap bitmap) {
        ContentValuse.ischange = true;
        ContentValuse.MBITMAP = bitmap;

        iv_head.setImageBitmap(bitmap);
        Toast.makeText(this, "更新头像成功", Toast.LENGTH_LONG).show();

    }

}
