package com.example.bozhitong.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bozhitong.R;

public class FaceActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        initView();
    }

    private void initView() {
   findViewById(R.id.iv_back).setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("人脸布控");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
