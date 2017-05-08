package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.AutoListView;
import com.example.bozhitong.entity.HousingInformation;
import com.example.bozhitong.fragment.adapter.AddHouseAdapter;

import java.util.ArrayList;

public class MyHousingActivity extends Activity implements View.OnClickListener ,AutoListView.OnLoadListener,AutoListView.OnRefreshListener{

    private Button butAddHouse;
       private AutoListView lvAdd;
    private ArrayList<HousingInformation> houseList = new ArrayList<HousingInformation>();
    private AddHouseAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            ArrayList<HousingInformation> result =(ArrayList<HousingInformation>) msg.obj;
            switch (msg.what) {
                case AutoListView.REFRESH:
                    lvAdd.onRefreshComplete();
                    houseList.clear();
                    houseList.addAll(result);
                    break;
                case AutoListView.LOAD:
                    lvAdd.onLoadComplete();
                    houseList.addAll(result);
                    break;
            }
            lvAdd.setResultSize(result.size());
            adapter.notifyDataSetChanged();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_housing);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("我的房屋信息");

        butAddHouse = (Button)findViewById(R.id.but_addhouse);
        butAddHouse.setOnClickListener(this);
        adapter = new AddHouseAdapter(this,houseList);

        lvAdd =(AutoListView)findViewById(R.id.lv_myhousing);
        lvAdd.setAdapter(adapter);
        lvAdd.setOnRefreshListener(this);
        lvAdd.setOnLoadListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_addhouse:

                Intent mIntent = new Intent();
                mIntent.setClass(this,AddHousingInformation.class);
                startActivity(mIntent);

                break;
        }
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
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();
    }

    private ArrayList<HousingInformation> getData(){
        ArrayList<HousingInformation> ff = new ArrayList<HousingInformation>();
        HousingInformation h = new HousingInformation();
        h.setCommunity("朝阳小区");
        h.setAddress("2期3栋5层101");
        h.setIdentity("租客");
        ff.add(h);
        return  ff;
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
