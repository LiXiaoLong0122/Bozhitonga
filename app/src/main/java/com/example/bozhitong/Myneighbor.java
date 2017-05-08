package com.example.bozhitong;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.fragment.adapter.MyneighborAdapter;

import java.util.ArrayList;
import java.util.List;

public class Myneighbor extends Activity implements OnClickListener, AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener {

    private ImageView mBack;
    private AutoListView alv;

    private ArrayList<String> neighborList = new ArrayList<String>();
private MyneighborAdapter adapter ;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<String> result = (List<String>) msg.obj;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    alv.onRefreshComplete();
                    neighborList.clear();
                    neighborList.addAll(result);
                    break;
                case AutoListView.LOAD:
                    alv.onLoadComplete();
                    neighborList.addAll(result);
                    break;
            }
           alv.setResultSize(result.size());
           adapter.notifyDataSetChanged();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_myneighbor);
        initView();
        initData();
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_back);
        mBack.setOnClickListener(this);
        adapter = new MyneighborAdapter(this,neighborList);
        alv = (AutoListView) findViewById(R.id.Residential_lstv);
        alv.setAdapter(adapter);
        alv.setOnRefreshListener(this);
        alv.setOnLoadListener(this);

    }
    private void initData() {
        loadData(AutoListView.REFRESH);
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
                android.os.Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();
    }

    private ArrayList<String> getData(){
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i <9 ; i++) {
            names.add("夏虎");
        }
        return names;
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