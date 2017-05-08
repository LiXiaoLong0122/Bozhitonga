package com.example.bozhitong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bozhitong.fragment.AllIntegralFragment;
import com.example.bozhitong.fragment.adapter.OrderPagerAdapter;
import com.example.bozhitong.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class IntegralDetailsActivity extends FragmentActivity {
    private ViewPager vp;
    private OrderPagerAdapter adapter;
    private Button button;
    private int mPosition;
    private int screenW = 0;
    private int start = 0;
    private List<Fragment> fragmentList;

    private RadioGroup group;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_income_details);
        initView();
        initData();
    }

    private void initData() {
        fragmentList.clear();
        fragmentList.add(new AllIntegralFragment());
        fragmentList.add(new AllIntegralFragment());
        fragmentList.add(new AllIntegralFragment());
        adapter.setBeans(fragmentList);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("积分明细");

        group = (RadioGroup) findViewById(R.id.rg_group);
        vp = (ViewPager) findViewById(R.id.view_pager);
        adapter = new OrderPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        fragmentList = new ArrayList<Fragment>();
        button = (Button) findViewById(R.id.but_xian);
        screenW = Tools.getScreenWidth(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenW / 3, 6);//Button的宽与高
        button.setLayoutParams(params);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                RadioButton button = null;
                if (position == 0) {
                    button = (RadioButton) findViewById(R.id.rb_all);
                } else if (position == 1) {
                    button = (RadioButton) findViewById(R.id.rb_Income);
                } else if (position == 2) {
                    button = (RadioButton) findViewById(R.id.rb_spending);
                }
                button.setChecked(true);
                if (mPosition == 0 && position == 1) {
                    valueAnimator(0, screenW / 3);
                } else if (mPosition == 1 && position == 2) {
                    valueAnimator(screenW / 3, screenW / 3 * 2);
                } else if (mPosition == 2 && position == 3) {
                    valueAnimator(screenW / 3 * 2, screenW);

                } else if (mPosition == 3 && position == 2) {
                    valueAnimator(screenW, screenW / 3 * 2);
                } else if (mPosition == 2 && position == 1) {
                    valueAnimator(screenW / 3 * 2, screenW / 3);
                } else if (mPosition == 1 && position == 0) {
                    valueAnimator(screenW / 3, 0);
                } else if (mPosition == 0 && position == 2) {
                    valueAnimator(0, screenW / 3 * 2);
                } else if (mPosition == 0 && position == 3) {
                    valueAnimator(0, screenW);

                } else if (mPosition == 1 && position == 3) {
                    valueAnimator(screenW / 3, screenW);

                } else if (mPosition == 3 && position == 1) {
                    valueAnimator(screenW, screenW / 3);

                } else if (mPosition == 3 && position == 0) {
                    valueAnimator(screenW, 0);
                } else if (mPosition == 2 && position == 0) {
                    valueAnimator(screenW / 3 * 2, 0);
                } else if (mPosition == 1 && position == 0) {
                    valueAnimator(screenW / 3, 0);
                }

                mPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb_Income:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb_spending:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void valueAnimator(int start, int end) {
        button.startAnimation(Tools.getAnimation(start, end));
    }


}
