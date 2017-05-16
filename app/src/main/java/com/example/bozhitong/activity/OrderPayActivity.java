package com.example.bozhitong.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bozhitong.R;
import com.example.bozhitong.dialog.view.SelectPicPopupWindow;
import com.example.bozhitong.fragment.adapter.OrderPayAdapter;

import java.util.ArrayList;

public class OrderPayActivity extends Activity implements View.OnClickListener{
private  TextView tv_orderid,tv_price,tv_status;
    private int orderId ,price,status;
private ListView lv_list;
    private OrderPayAdapter adapter;
    private ArrayList<Integer> priList = new ArrayList<Integer>();
    private SelectPicPopupWindow  picPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pay2);

      initView();

    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);

        TextView title =(TextView) findViewById(R.id.tv_title);
        title.setText("支付页面");
        tv_orderid = (TextView)findViewById(R.id.tv_orderid);
        tv_price = (TextView)findViewById(R.id.tv_price);
        tv_status = (TextView)findViewById(R.id.tv_status);
        lv_list =(ListView) findViewById(R.id.lv_list);
        price = 5;
        tv_orderid.setText("订单编号："+orderId);
        tv_price.setText(price+"元");
        tv_status.setText("未支付");

        priList.add(500);
        adapter = new OrderPayAdapter(this,priList);
        lv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_pay:
                if(picPop == null){
                    picPop = new SelectPicPopupWindow(this,this);
                }
                    picPop.showAtLocation(OrderPayActivity.this
                                    .findViewById(R.id.btn_pay),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                            0, 0);

                break;
            case R.id.btn_paybyalipay:
                //支付宝确定




                break;

            case R.id.btn_wechatpay:
                //微信确定




                break;

        }
    }
}
