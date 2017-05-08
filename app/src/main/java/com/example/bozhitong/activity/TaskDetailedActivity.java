package com.example.bozhitong.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.bozhitong.R;

public class TaskDetailedActivity extends Activity implements OnClickListener {

    private TextView tv_starttime,tv_endtime,tv_address,tv_contacts,tv_applied,tv_detailed;
private Button but_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_task_detailed);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("活动详情");

        tv_starttime = (TextView)findViewById(R.id.tv_starttime);
        tv_endtime = (TextView)findViewById(R.id.tv_endtime);
        tv_address = (TextView)findViewById(R.id.tv_address);
        tv_applied = (TextView)findViewById(R.id.tv_apply);
        tv_contacts   = (TextView)findViewById(R.id.tv_contacts);
        tv_detailed = (TextView)findViewById(R.id.tv_detailed);

        tv_starttime.setText("开始时间：2017-5-12 12：00");
        tv_endtime.setText("结束时间：2017-5-12 18：00");
        tv_address.setText("大雁塔及其周边");
        tv_contacts.setText("麦迪");
        tv_applied.setText("已报名1人");
        tv_detailed.setText("嗨皮嗨皮嗨皮嗨皮嗨皮嗨皮");

        but_apply = (Button) findViewById(R.id.but_apply);
        but_apply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_apply:

                break;
        }
    }
}
