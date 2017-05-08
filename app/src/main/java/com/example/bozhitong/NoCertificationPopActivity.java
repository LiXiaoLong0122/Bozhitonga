package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.bozhitong.activity.MyHousingActivity;

public class NoCertificationPopActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_no_certification_pop);
        initView();
    }
    private void initView() {
  findViewById(R.id.chacha).setOnClickListener(this);
        findViewById(R.id.rL_apply).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chacha:
                finish();
                break;
            case  R.id.rL_apply:
                Intent mIntent = new Intent();
                mIntent.setClass(this, MyHousingActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
