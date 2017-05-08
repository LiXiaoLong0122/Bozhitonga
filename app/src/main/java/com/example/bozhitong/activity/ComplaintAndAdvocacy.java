package com.example.bozhitong.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.fragment.ComplaintFragment;
import com.example.bozhitong.fragment.ConsultationFragment;
import com.example.bozhitong.fragment.ProposalFragment;
import com.example.bozhitong.fragment.adapter.CoAndAdPagerAdapter;
import com.example.bozhitong.fragment.adapter.StringListAdapter;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.utils.DrawerLayoutUtils;
import com.example.bozhitong.utils.Utils;
/*import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;*/

import java.util.ArrayList;

/**
 * 投诉建议页面
 *
 * @author 12306
 */
public class ComplaintAndAdvocacy extends FragmentActivity implements
        OnClickListener {


    private ComplaintFragment btFragment;
    private ConsultationFragment secondFragment;
    private ProposalFragment thirdFragment;

    private ViewPager mPager;
    private ArrayList<Fragment> fragmentList;
    private TextView barText;
    private TextView view1, view2, view3;
    private int currIndex;// 当前页卡编号
    private ImageView mBack;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   /* private GoogleApiClient client;*/

    private DrawerLayout mDrawerLayout;
    private TextView tvtitle;
    public ListView timeList;
    public ArrayList<String> itemList = new ArrayList<String>();
    private String type1,type2,type3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏�ְ�
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_compandadvoc);

        InitTextView();
         InitTextBar();
        InitViewPager();
        initDrawerLayout();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/
    }

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);

        DrawerLayoutUtils.initEvents(mDrawerLayout);
        View fragmenM = DrawerLayoutUtils.getFragmentView(this);
        tvtitle = (TextView) fragmenM.findViewById(R.id.tv_title);
        timeList = (ListView) fragmenM.findViewById(R.id.lv);
        itemList.addAll(Utils.getList(this, R.array.type_complaints));

        StringListAdapter adapter = new StringListAdapter(this, itemList);
        timeList.setAdapter(adapter);

    }

    public void initFragmentView(String title, final TextView ttv) {

        tvtitle.setText(title);

        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type1 =  itemList.get(position);
                type2 =  itemList.get(position);
                type3 =  itemList.get(position);

            /*    Toast.makeText(getApplication(),type,Toast.LENGTH_LONG).show();*/

                switch (currIndex){
                    case 0:
                       // Toast.makeText(getApplication(),tag+"投诉",Toast.LENGTH_LONG).show();
                       btFragment.setFragmentTexta(type1);

                        break;
                    case 1:
                       secondFragment.setFragmentTextb(type2);
                        //Toast.makeText(getApplication(),tag+"建议",Toast.LENGTH_LONG).show();

                        break;
                    case 2:
                        thirdFragment.setFragmentTextc(type3);
                    //    Toast.makeText(getApplication(),tag+"咨询",Toast.LENGTH_LONG).show();

                        break;
                }
                closeDrawers();



            }
        });
    }

    public void OpenRightMenu(View view) {
        DrawerLayoutUtils.OpenRightMenu(mDrawerLayout);

    }
    public void closeDrawers() {
        mDrawerLayout.closeDrawers();
    }

    /*
     * 初始化标签名
     */
    public void InitTextView() {
        view1 = (TextView) findViewById(R.id.complaint_tv);
        view2 = (TextView) findViewById(R.id.proposal_tv);
        view3 = (TextView) findViewById(R.id.consultation_tv);
        view1.setOnClickListener(new txListener(0));
        view2.setOnClickListener(new txListener(1));
        view3.setOnClickListener(new txListener(2));
        mBack = (ImageView) findViewById(R.id.compan_iv);
        mBack.setOnClickListener(this);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
/*    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ComplaintAndAdvocacy Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    /*    client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
  /*      AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();*/
    }

    public class txListener implements OnClickListener {
        private int index = 0;

        public txListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }

    /*
     * 初始化图片的位移像素
     */
    public void InitTextBar() {
        barText = (TextView) super.findViewById(R.id.cursor);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // 1/3屏幕宽度
        int tabLineLength = metrics.widthPixels / 3;
        LayoutParams lp = (LayoutParams) barText.getLayoutParams();
        lp.width = tabLineLength;
        barText.setLayoutParams(lp);

    }
    private Handler mHandler;
    public  void  setmHandler(Handler mandler){
        mHandler = mandler;
    }
    /*
     * 初始化ViewPager
     */
    /*private ComplaintFragment btFragment;
    private ConsultationFragment secondFragment;
    private ProposalFragment thirdFragment;*/
    public void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();
         btFragment = new ComplaintFragment();

         secondFragment = new ConsultationFragment();
         thirdFragment = new ProposalFragment();

        fragmentList.add(btFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);


        // 给ViewPager设置适配器
        mPager.setAdapter(new CoAndAdPagerAdapter(getSupportFragmentManager(),
                fragmentList));
        mPager.setCurrentItem(0);// 设置当前显示标签页为第一页
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器


    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            // 取得该控件的实例
            LayoutParams ll = (LayoutParams) barText
                    .getLayoutParams();

            if (currIndex == arg0) {
                ll.leftMargin = (int) (currIndex * barText.getWidth() + arg1
                        * barText.getWidth());
            } else if (currIndex > arg0) {
                ll.leftMargin = (int) (currIndex * barText.getWidth() - (1 - arg1)
                        * barText.getWidth());
            }
            barText.setLayoutParams(ll);
        }

        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            currIndex = arg0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.compan_iv:
                finish();
                break;

            default:
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (int i = 0; i < PublicWay.activityList.size(); i++) {
                if (null != PublicWay.activityList.get(i)) {
                    PublicWay.activityList.get(i).finish();
                }
            }
        }
        return true;
    }
}
