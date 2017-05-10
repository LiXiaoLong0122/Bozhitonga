package com.example.bozhitong.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bozhitong.ActwildlyTwoActivity;
import com.example.bozhitong.GalleryActivity;
import com.example.bozhitong.NoCertificationPopActivity;
import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.StringListAdapter;
import com.example.bozhitong.photo.adapter.GridAdapter;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.photo.util.FileUtils;
import com.example.bozhitong.photo.util.ImageItem;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.photo.util.Res;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.DrawerLayoutUtils;
import com.example.bozhitong.utils.PopupwindowPhoto;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;

/**
 * 快捷报修
 *
 * @author 12306
 */
public class ExpressWarranty extends FragmentActivity implements OnClickListener {
    private ImageView mBack;
    private LinearLayout ll_public_areas;
    private RadioButton rb_private, rb_public;
    LinearLayout express_adrress_ly;
    LinearLayout ll_time;
    private TextView tv_time;
    private DrawerLayout mDrawerLayout;
    private TextView tv;
    private ListView timeList;
private ArrayList<String> itemList=new ArrayList<String>();


    private GridView noScrollgridview;
    private GridAdapter adapter;

    private PopupwindowPhoto pop;
    public static Bitmap bimap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏�ְ�
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exp_warty);
        initview();
        DrawerLayoutUtils.initEvents(mDrawerLayout);
        initFragmentView();

        initPhoto();

        Intent intent = new Intent();
        intent.setClass(this, NoCertificationPopActivity.class);
        startActivity(intent);
    }


//    private void initEvents() {
//        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
//            public void onDrawerStateChanged(int newState) {
//            }
//
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                View mContent = mDrawerLayout.getChildAt(0);
//                View mMenu = drawerView;
//                float scale = 1 - slideOffset;
//                float rightScale = 1f;
//
//                ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth()
//                        * slideOffset);
//                ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
//                ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
//                mContent.invalidate();
//                ViewHelper.setScaleX(mContent, rightScale);
//                ViewHelper.setScaleY(mContent, rightScale);
//
//            }
//
//            public void onDrawerOpened(View drawerView) {
//            }
//
//            public void onDrawerClosed(View drawerView) {
//                mDrawerLayout.setDrawerLockMode(
//                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
//            }
//        });
//    }

    public void OpenRightMenu(View view) {
        DrawerLayoutUtils.OpenRightMenu(mDrawerLayout);
//        mDrawerLayout.openDrawer(Gravity.RIGHT);
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
//                Gravity.RIGHT);
    }

    private void initview() {
        mBack = (ImageView) findViewById(R.id.express_iv);
        mBack.setOnClickListener(this);
        rb_private = (RadioButton) findViewById(R.id.rb_private);
        rb_public = (RadioButton) findViewById(R.id.rb_public);
        ll_public_areas = (LinearLayout) findViewById(R.id.ll_public_areas);
        express_adrress_ly = (LinearLayout) findViewById(R.id.express_adrress_ly);
        ll_time = (LinearLayout) findViewById(R.id.ll_time);
        tv_time = (TextView) findViewById(R.id.tv_time);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);


        RadioGroup rg_group = (RadioGroup) findViewById(R.id.rg_group);
        rg_group.check(R.id.rb_private);
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.rb_private://私人住宅
                        setRb_private();
                        break;
                    case R.id.rb_public://公共区域
                        setRb_public();

                        break;
                }


            }
        });






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
                    Intent intent = new Intent(ExpressWarranty.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });


    }
    private void initPhoto() {
        pop = new PopupwindowPhoto(this);

        Res.init(this);
        bimap = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(this);

    }

    private void initFragmentView() {
        View fragmenM = DrawerLayoutUtils.getFragmentView(this);
        tv = (TextView) fragmenM.findViewById(R.id.tv_title);
        tv.setText("请选择时间");
        timeList =(ListView) fragmenM.findViewById(R.id.lv);
        itemList.addAll(Tools.getTimeList());
        StringListAdapter adapter = new StringListAdapter(this,itemList);
        timeList.setAdapter(adapter);
        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_time.setText(itemList.get(position));
                mDrawerLayout.closeDrawers();
            }
        });
    }




    private void setRb_private() {
        ll_public_areas.setVisibility(View.GONE);
        express_adrress_ly.setVisibility(View.VISIBLE);
        ll_time.setVisibility(View.VISIBLE);
    }

    private void setRb_public() {
        ll_public_areas.setVisibility(View.VISIBLE);
        express_adrress_ly.setVisibility(View.GONE);
        ll_time.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        pop.onRequestPermissionsResult(requestCode,permissions,grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.express_iv:
                finish();
                break;

            default:
                break;
        }
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
