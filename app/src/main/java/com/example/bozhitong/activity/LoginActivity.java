package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.MainActivity;
import com.example.bozhitong.R;
/*import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;*/

/**
 * 登录界面
 *
 * @author Administrator
 */
public class LoginActivity extends Activity implements OnClickListener {
    private TextView mRegisterTv;
    private EditText mUserNameEt, mPasswordEt;// 用户名，密码
    private CheckBox mRememberPwdCb;// 记住密码框
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*private GoogleApiClient client;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();*/
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRegisterTv = (TextView) findViewById(R.id.tv_register);
        mRegisterTv.setOnClickListener(this);
        mRegisterTv.setText(Html
                .fromHtml("<font color='blue'><u>用户注册</u></font>"));
        mUserNameEt = (EditText) findViewById(R.id.et_username);
        mPasswordEt = (EditText) findViewById(R.id.et_password);
        mRememberPwdCb = (CheckBox) findViewById(R.id.cb_rb_pwd);
        // 使用记住的密码
        getRememberPwd();

    }

    /**
     * 获取记住的用户名密码
     */
    private void getRememberPwd() {
        SharedPreferences preferences = getSharedPreferences("rb_pwd", 0);
        String uname = preferences.getString("uname", null);
        String upwd = preferences.getString("upwd", null);
        boolean isChecked = preferences.getBoolean("checked", false);
        mUserNameEt.setText(uname);
        mPasswordEt.setText(upwd);
        mRememberPwdCb.setChecked(isChecked);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:// 跳转到注册界面
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /**
     * 登录事件
     *
     * @param v
     */
    public void login(View v) {
        String username = mUserNameEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();
        // 非空验证
        if (TextUtils.isEmpty(username)) {// 用户名为空
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password)) {// 密码不能为空
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        // 用户名密码验证
        SharedPreferences preferences = getSharedPreferences("hotel", 0);
        String uname = preferences.getString("username", null);
        String upwd = preferences.getString("password", null);

        uname = "tykj";

        upwd = "tykj";

        if (uname.equals(username) && upwd.equals(password)) {// 登录成功
            // 记住密码
            rememberPwd(uname, upwd);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "恭喜，登录成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "亲，登录失败", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 记住密码功能实现
     */
    private void rememberPwd(String uname, String upwd) {
        // 记住密码框是否选中
        boolean isChecked = mRememberPwdCb.isChecked();
        SharedPreferences preferences = getSharedPreferences("rb_pwd", 0);
        Editor editor = preferences.edit();
        if (isChecked) {// 记住密码框选中
            editor.putString("uname", uname);// 用户名
            editor.putString("upwd", upwd);// 密码
        } else {// 记住密码框未选中
            editor.putString("uname", "");// 用户名
            editor.putString("upwd", "");// 密码
        }
        editor.putBoolean("checked", isChecked);
        editor.commit();

    }


}
