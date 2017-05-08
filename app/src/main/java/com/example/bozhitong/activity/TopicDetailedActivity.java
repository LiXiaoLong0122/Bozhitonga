package com.example.bozhitong.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bozhitong.R;

public class TopicDetailedActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_topic_detailed);
         initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tvTitle =(TextView) findViewById(R.id.tv_title);
        tvTitle.setText("话题详情");
        TextView tvCollect = (TextView) findViewById(R.id.tv_collect);
        tvCollect.setVisibility(View.VISIBLE);
        tvCollect.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.iv_back:
               finish();
               break;
           case R.id.tv_collect:
               //收藏



               break;
       }


    }


}
