package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.fragment.adapter.FamilyAdapter;

import java.util.ArrayList;

public class Family extends Activity implements OnClickListener, AutoListView.OnLoadListener, AutoListView.OnRefreshListener {

    private ImageView mBack;
    private AutoListView familylv;
    private FamilyAdapter adapter;
    private ArrayList<String> family = new ArrayList<String>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case AutoListView.REFRESH:
                    familylv.onRefreshComplete();
                    family.clear();
                    family.add("黄日成");
                    break;
                case AutoListView.LOAD:
                    familylv.onLoadComplete();
                    family.add("黄日成");
                    break;
            }
            familylv.setResultSize(family.size());
            adapter.notifyDataSetChanged();
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_family);
        initView();
        initData();
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_back);
        mBack.setOnClickListener(this);
        findViewById(R.id.but_add_family).setOnClickListener(this);
        adapter = new FamilyAdapter(this, family);
        familylv = (AutoListView) findViewById(R.id.family_listv);
        familylv.setAdapter(adapter);
        familylv.setOnRefreshListener(this);
        familylv.setOnLoadListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.but_add_family:
                Intent mintent = new Intent();
                mintent.setClass(this, AddFamilyActivity.class);
                startActivity(mintent);
                break;
            default:
                break;
        }
        ;
    }


    private void initData() {
        loadData(AutoListView.REFRESH);
    }

    private void loadData(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;

                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        loadData(AutoListView.REFRESH);
    }

    @Override
    public void onLoad() {
        loadData(AutoListView.LOAD);
    }
}