package com.example.bozhitong.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.TopicDetailedEvaluationAdapter;
import com.example.bozhitong.view.BanSlidingListView;

import java.util.ArrayList;
import java.util.List;

public class TopicDetailedActivity extends Activity implements View.OnClickListener {

    private LinearLayout ll_like;
    private ImageView iv_like;
    private TextView tv_like;
    private int index = 11;
    private ArrayList<String> evlist = new ArrayList<String>();
    private Button but;
    private TopicDetailedEvaluationAdapter adapter;
    private BanSlidingListView list;
    private EditText et_ev;

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
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("话题详情");
        TextView tvCollect = (TextView) findViewById(R.id.tv_collect);
        tvCollect.setVisibility(View.VISIBLE);
        tvCollect.setOnClickListener(this);

        but = (Button) findViewById(R.id.but_fabu);
        but.setOnClickListener(this);
        list = (BanSlidingListView) findViewById(R.id.ll_list);
        adapter = new TopicDetailedEvaluationAdapter(evlist, this);
        list.setAdapter(adapter);

        et_ev = (EditText) findViewById(R.id.et_ev);

        ll_like = (LinearLayout) findViewById(R.id.ll_like);
        tv_like = (TextView) findViewById(R.id.tv_like);
        iv_like = (ImageView) findViewById(R.id.iv_like);

        iv_like.setOnClickListener(this);
        tv_like.setText(index+"");

        findViewById(R.id.tv_thank).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_collect:
                //收藏


                break;
            case R.id.tv_thank:
                Toast.makeText(this,"感谢成功",Toast.LENGTH_SHORT).show();

                break;

            case R.id.iv_like:
                if (iv_like.isSelected()) {
                    iv_like.setSelected(false);
                    index = index - 1;
                } else {
                    iv_like.setSelected(true);
                    index = index + 1;

                }

                tv_like.setText(index + "");
                break;
            case R.id.but_fabu:
                String text = et_ev.getText().toString();
                if (!text.isEmpty()){
                    evlist.add(text);
                    adapter.notifyDataSetChanged();
                    et_ev.setText("");
                }


                break;

        }


    }


}
