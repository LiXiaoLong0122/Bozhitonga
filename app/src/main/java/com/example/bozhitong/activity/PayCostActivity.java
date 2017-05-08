package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.entity.HousingInformation;
import com.example.bozhitong.fragment.adapter.PayCostAdapter;

import java.util.ArrayList;

/*
*缴费
 */
public class PayCostActivity extends Activity implements View.OnClickListener{
private int BACK = 100;
    private ListView lvPay;
private ArrayList<HousingInformation> mPayList = new ArrayList<HousingInformation>();
private PayCostAdapter adapter;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pay_cost);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("缴费");
        findViewById(R.id.rl_payment_records).setOnClickListener(this);
       but = (Button)findViewById(R.id.but);
        but.setOnClickListener(this);
        if (mPayList.size() > 0){
            but.setVisibility(View.GONE);
        }else {
            but.setVisibility(View.VISIBLE);
        }


        lvPay =(ListView) findViewById(R.id.lv_paycost);
        adapter = new PayCostAdapter(this,mPayList);
        lvPay.setAdapter(adapter);
        lvPay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mINtemt();
            }
        });
    }

    private void mINtemt(){
        Intent intent = new Intent();
        intent.setClass(PayCostActivity.this, AddHousingInformation.class);
        startActivityForResult(intent,BACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_payment_records:
                Intent intent = new Intent();
                intent.setClass(this, PropertyPayment.class);
                startActivity(intent);
                break;
            case R.id.but:
                mINtemt();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BACK){
            HousingInformation p = new HousingInformation();
            p.setName(data.getStringExtra("name"));
            if(!data.getStringExtra("name").isEmpty()) {
                p.setAddress(data.getStringExtra("address"));
                p.setPhone("12312312312");
                mPayList.add(p);
                adapter.notifyDataSetChanged();
                but.setVisibility(View.GONE);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
