package com.example.bozhitong.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bozhitong.R;

public class OtherUserMoreActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_more);

        initView();

    }

    private void initView() {
        findViewById(R.id.chacha).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chacha:
                finish();
                break;
        }
    }
}
