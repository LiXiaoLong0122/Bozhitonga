package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bozhitong.R;

/**
 * 添加房屋信息 李瑞你好
 *
 * @author 12306 李瑞
 */
public class AddHousingInformation extends Activity implements OnClickListener {
    private ImageView mBack;
    private EditText et_name;
private TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_addhousing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        addListener();
    }

    private void addListener() {
        mBack.setOnClickListener(this);

    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_add_housing);
        findViewById(R.id.btn_chongzhi).setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        address = (TextView) findViewById(R.id.et_address);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.img_add_housing:
                Intent mintents = new Intent();
                mintents.putExtra("name", "");
                mintents.putExtra("address", "");
                setResult(0, mintents);
                finish();
                break;
            case R.id.btn_chongzhi:
                Intent mintent = new Intent();
                mintent.putExtra("name", et_name.getText().toString());
                mintent.putExtra("address", address.getText().toString());
                setResult(0, mintent);
                 finish();
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent mintent = new Intent();
        mintent.putExtra("name", "");
        mintent.putExtra("address", "");
        setResult(0, mintent);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
