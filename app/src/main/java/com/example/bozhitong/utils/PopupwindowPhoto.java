package com.example.bozhitong.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.bozhitong.AlbumActivity;
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

    public PopupwindowPhoto(Activity mActivity, Fragment mf) {
        this.mActivity = mActivity;
        this.mf = mf;
    }

    public void showpopPhotoAtLocation( int gravity, int x, int y) {

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
                 photo();
                 dismiss();

                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity,
                            AlbumActivity.class);
                    mActivity.startActivity(intent);
                    mActivity.overridePendingTransition(R.anim.activity_translate_in,
                            R.anim.activity_translate_out);
                    dismiss();
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

    public void dismiss() {
        if (mpop.isShowing()) {
            mpop.dismiss();
            ll_popup.clearAnimation();
        }

    }

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mf.startActivityForResult(openCameraIntent, ContentValuse.TAKE_PICTURE);
    }


}
