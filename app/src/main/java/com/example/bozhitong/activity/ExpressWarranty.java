package com.example.bozhitong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bozhitong.NoCertificationPopActivity;
import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.StringListAdapter;
import com.example.bozhitong.utils.DrawerLayoutUtils;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.express_iv:
                finish();
                break;

            default:
                break;
        }
    }
}
