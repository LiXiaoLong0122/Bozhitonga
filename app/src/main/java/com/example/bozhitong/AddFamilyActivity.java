package com.example.bozhitong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bozhitong.time.TimePopupWindow;
import com.example.bozhitong.utils.DialogTool;

/*
*添加家人
 */
public class AddFamilyActivity extends Activity implements OnClickListener {

    private EditText name,phone;
    private TextView tvsex, tvbirthday;
    private Spinner sprelation;
    private String[] relations;
    private TimePopupWindow timeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_family);


        initView();

    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("添加家人");
        name = (EditText) findViewById(R.id.et_addfamily_name);
        phone = (EditText)findViewById(R.id.et_addfamily_phone);
        tvsex = (TextView) findViewById(R.id.tv_addfamily_sex);
        tvbirthday = (TextView) findViewById(R.id.tv_addfamily_birthday);
        sprelation = (Spinner) findViewById(R.id.s_addfamily_relation);


     //   relations = getResources().getStringArray(R.array.add_family_relation);

        findViewById(R.id.rl_addfamily_birthday).setOnClickListener(this);
        findViewById(R.id.rl_sex).setOnClickListener(this);
//        sprelation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        timeDialog = new TimePopupWindow(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_addfamily_birthday:
                //生日
                timeDialog.showBottoPopupWindow("请选择时间", tvbirthday);

                break;
            case R.id.rl_sex:
                //性别
                new DialogTool(this).showChooseDialog(tvsex);

                break;
        }
    }
}
