package com.example.bozhitong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.R;

import java.lang.reflect.Field;

public class BuyPointsActivity extends Activity implements View.OnClickListener {
    private RadioGroup rgbut;
    private TextView tv_buy, tv_hide;

    private EditText et_inputpoints;
    private RadioButton rb_other;

    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_buy_points);
        intitView();

    }

    private void intitView() {
        findViewById(R.id.iv_back).setOnClickListener(this);


        final TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("积分充值");

        tv_buy = (TextView) findViewById(R.id.tv_buy);
        tv_hide = (TextView) findViewById(R.id.tv_hide);

        rgbut = (RadioGroup) findViewById(R.id.rg_group);
        rgbut.check(R.id.rb_one);
        rb_other = (RadioButton) findViewById(R.id.rb_other);
        final View vi = View.inflate(BuyPointsActivity.this, R.layout.dialog_layout_editview, null);
        et_inputpoints = (EditText) vi.findViewById(R.id.et_inputpoints);

        rgbut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_one:
                        setData("5.0", 500);

                        break;
                    case R.id.rb_two:
                        setData("10.0", 1000);
                        break;
                    case R.id.rb_three:
                        setData("50.0", 5000);
                        break;
                    case R.id.rb_other:
                        setRb_other(vi);
                        break;
                }
            }
        });

        findViewById(R.id.btn_buypoints).setOnClickListener(this);

    }

    private void setRb_other(View vi) {
        if (alertDialog == null) {
            alertDialog = setAlertDialog(vi);
        }
        alertDialog.show();
    }

    private AlertDialog setAlertDialog(View vi) {
        return new AlertDialog.Builder(BuyPointsActivity.this).setTitle("请输入积分数")
                .setView(vi)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        String point = et_inputpoints.getText().toString();
                        if (point.isEmpty()) {
                            setToastAndAlertDialogclose(dialog, " 请输入积分");
                        } else {
                            int points = Integer.valueOf(point);

                                alertDialogclose(dialog, true);
                                rb_other.setText(points + "积分");
                                int mo = points / 100 + points % 100;
                                setData(mo + "", points);

                        }

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .create();
    }


    private void setToastAndAlertDialogclose(DialogInterface dialog, String text) {
        alertDialogclose(dialog, false);
        Toast.makeText(BuyPointsActivity.this, text, Toast.LENGTH_SHORT).show();
    }


    private void alertDialogclose(DialogInterface dialog, boolean isclose) {
        try {//下面三句控制弹框的关闭

            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");

            field.setAccessible(true);

            field.set(dialog, isclose);//true表示要关闭

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void setData(String money, int points) {
        tv_buy.setText(money);
        tv_hide.setText("(可获" + points + "积分)");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_buypoints:
                Intent intent = new Intent();
                intent.setClass(this,OrderPayActivity.class);
                startActivity(intent);
                break;

        }
    }
}
