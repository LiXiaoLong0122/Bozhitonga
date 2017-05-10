package com.example.bozhitong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bozhitong.photo.adapter.GridAdapter;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.photo.util.FileUtils;
import com.example.bozhitong.photo.util.ImageItem;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.photo.util.Res;
import com.example.bozhitong.time.TimePopupWindow;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.PopupwindowPhoto;

public class ActwildlyTwoActivity extends Activity implements View.OnClickListener{
private TimePopupWindow timePopupWindow;
    private TextView starttime,endtime;

    private GridView noScrollgridview;
    private GridAdapter adapter;

    private PopupwindowPhoto pop;
    public static Bitmap bimap;

    private RadioGroup rg_actwildly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_actwildly_two);

        initView();
        initPhoto();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("发布活动");

        findViewById(R.id.rl_start_time).setOnClickListener(this);
        findViewById(R.id.rl_end_time).setOnClickListener(this);
        starttime =(TextView)findViewById(R.id.tv_start_date);
        endtime =(TextView)findViewById(R.id.tv_end_date);
        timePopupWindow = new TimePopupWindow(this);

        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {

                    pop.showpopPhotoAtLocation(Gravity.BOTTOM, 0, 0);

                } else {
                    Intent intent = new Intent(ActwildlyTwoActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });



        rg_actwildly = (RadioGroup) findViewById(R.id.rg_actwildly);
        rg_actwildly.check(R.id.but_find);
    }
    private void initPhoto() {
        pop = new PopupwindowPhoto(this);

        Res.init(this);
        bimap = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_start_time:
                timePopupWindow.showBottoPopupWindow("请选择时间",starttime);
                break;
            case R.id.rl_end_time:
                timePopupWindow.showBottoPopupWindow("请选择时间",endtime);
                break;
        }
    }

    public void onStart() {
        adapter.update();
        super.onStart();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case ContentValuse.TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {

                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);

                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.notifyDataSetChanged();
        Bimp.tempSelectBitmap.clear();
        Bimp.max = 0;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        pop.onRequestPermissionsResult(requestCode,permissions,grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
