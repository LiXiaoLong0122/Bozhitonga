package com.example.bozhitong.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;


import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bozhitong.AlbumActivity;
import com.example.bozhitong.Profile;
import com.example.bozhitong.R;

/**
 * Created by Administrator on 2017-04-17.
 */

public class PopupwindowPhoto {
    private PopupWindow mpop;
    private LinearLayout ll_popup;
    private Activity mActivity;
    private RelativeLayout parent;
    private View view;
    private Fragment mf;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 1;
    private static  int CAMER_PHOTO ;

    public PopupwindowPhoto(Activity mActivity, Fragment mf) {
        this.mActivity = mActivity;
        this.mf = mf;
    }

    public PopupwindowPhoto(Activity mActivity) {
        this.mActivity = mActivity;

    }


    public void showpopPhotoAtLocation(int gravity, int x, int y) {

        if (mpop == null) {
            mpop = new PopupWindow();
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_popupwindows,
                    null);

            ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

            mpop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mpop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mpop.setBackgroundDrawable(new BitmapDrawable());
            mpop.setFocusable(true);
            mpop.setOutsideTouchable(true);
            mpop.setContentView(view);


            parent = (RelativeLayout) view.findViewById(R.id.parent);
            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
            parent.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    dismiss();

                }
            });
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CAMER_PHOTO = 1;
                    if (Integer.parseInt(Build.VERSION.SDK) >= 23) {

                        if (ContextCompat.checkSelfPermission(mActivity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //Android 6.0以上 不能只是在AndroidManifest.xml中进行配置 还要在代码中动态设置权限

                            //权限还没有授予，需要在这里写申请权限的代码

                            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                        } else {
                            photo();
                            dismiss();
                        }
                    } else {
                        photo();
                        dismiss();

                    }


                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CAMER_PHOTO =2;
                    if (Integer.parseInt(Build.VERSION.SDK) >= 23) {

//                        if (ContextCompat.checkSelfPermission(mActivity,
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                                != PackageManager.PERMISSION_GRANTED) {
//                            //权限还没有授予，需要在这里写申请权限的代码
//
//                            ActivityCompat.requestPermissions(mActivity,
//                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
//                        } else {
                        if (ContextCompat.checkSelfPermission(mActivity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //Android 6.0以上 不能只是在AndroidManifest.xml中进行配置 还要在代码中动态设置权限

                            //权限还没有授予，需要在这里写申请权限的代码

                            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                        } else {
                            intentAlbumActivity();
                        }
                    } else {
                        intentAlbumActivity();
                    }


                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
        ll_popup.startAnimation(AnimationUtils.loadAnimation(
                mActivity, R.anim.activity_translate_in));
        mpop.showAtLocation(view, gravity, x, y);
    }


    private void intentAlbumActivity() {
        Intent intent = new Intent(mActivity,
                AlbumActivity.class);
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_translate_in,
                R.anim.activity_translate_out);
        dismiss();
    }

    public void dismiss() {
        if (mpop.isShowing()) {
            mpop.dismiss();
            ll_popup.clearAnimation();
        }

    }

    public void photo() {

        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (mf != null) {
            mf.startActivityForResult(openCameraIntent, ContentValuse.TAKE_PICTURE);
        } else {
            mActivity.startActivityForResult(openCameraIntent, ContentValuse.TAKE_PICTURE);
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
switch (CAMER_PHOTO){
    case 1:

        photo();
        dismiss();
        break;
    case 2:
        intentAlbumActivity();
        break;


}

            } else {

                Toast.makeText(mActivity, "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }
//        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                intentAlbumActivity();
//            } else {
//                // Permission Denied
//                Toast.makeText(mActivity, "Permission Denied", Toast.LENGTH_SHORT).show();
//            }
//        }

    }


}
