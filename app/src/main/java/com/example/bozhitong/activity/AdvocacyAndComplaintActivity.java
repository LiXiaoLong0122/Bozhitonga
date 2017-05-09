package com.example.bozhitong.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
/*import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bozhitong.GalleryActivity;
import com.example.bozhitong.R;
import com.example.bozhitong.fragment.adapter.StringListAdapter;
import com.example.bozhitong.photo.adapter.GridAdapter;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.photo.util.FileUtils;
import com.example.bozhitong.photo.util.ImageItem;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.photo.util.Res;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.DrawerLayoutUtils;
import com.example.bozhitong.utils.PopupwindowPhoto;
import com.example.bozhitong.utils.Utils;

import java.util.ArrayList;

public class AdvocacyAndComplaintActivity extends FragmentActivity implements View.OnClickListener{
    private int ONCLICK;

private TextView tv_purpose,tv_type;
    private DrawerLayout mDrawerLayout;
    private TextView tvtitle;
    private ListView timeList;
    private ArrayList<String> itemList = new ArrayList<String>();
    private  StringListAdapter adapter;


    private GridView noScrollgridview;
    private GridAdapter gadapter;

    private PopupwindowPhoto pop;
    public static Bitmap bimap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_advocacy_and_complaint);
        initPhoto();
        initView();

        initDrawerLayout();
    }


    private void initPhoto() {
        pop = new PopupwindowPhoto(this);

        Res.init(this);
        bimap = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_addpic_unfocused);


        PublicWay.activityList.add(this);

    }

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);
        View fragmenM = DrawerLayoutUtils.getFragmentView(this);
        tvtitle = (TextView) fragmenM.findViewById(R.id.tv_title);
        timeList =(ListView) fragmenM.findViewById(R.id.lv);
        DrawerLayoutUtils.initEvents(mDrawerLayout);
        adapter = new StringListAdapter(this, itemList);
        timeList.setAdapter(adapter);
        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             switch (ONCLICK){
                 case 1:
                     tv_purpose.setText(itemList.get(position));
                     mDrawerLayout.closeDrawers();
                     break;
                 case 2:
                     tv_type.setText(itemList.get(position));
                     mDrawerLayout.closeDrawers();
                     break;
             }


            }
        });

    }

    private void setlistData(String title,int itArray){
        tvtitle.setText(title);
        itemList.clear();
        itemList.addAll(Utils.getList(this, itArray));
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView title = (TextView)findViewById(R.id.tv_title);
        title.setText("投诉与建议");

        tv_purpose =(TextView) findViewById(R.id.tv_purpose);
        tv_type =(TextView) findViewById(R.id.tv_type);


        findViewById(R.id.ll_purpose).setOnClickListener(this);
        findViewById(R.id.ll_typea).setOnClickListener(this);



        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gadapter = new GridAdapter(this);
        gadapter.update();
        noScrollgridview.setAdapter(gadapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {

                    pop.showpopPhotoAtLocation(Gravity.BOTTOM, 0, 0);

                } else {
                    Intent intent = new Intent(AdvocacyAndComplaintActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        pop.onRequestPermissionsResult(requestCode,permissions,grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void OpenRightMenu() {
        DrawerLayoutUtils.OpenRightMenu(mDrawerLayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_purpose://目的
                ONCLICK = 1;
                setlistData("请选择目的",R.array.purpose);

                OpenRightMenu();
                break;
            case R.id.ll_typea://类型
                ONCLICK =2;
                setlistData("请选择类型",R.array.type_complaints);
                OpenRightMenu();
                break;
        }
    }

    public void onStart() {
        gadapter.update();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for(int i=0;i<PublicWay.activityList.size();i++){
                if (null != PublicWay.activityList.get(i)) {
                    PublicWay.activityList.get(i).finish();
                }
            }
            //System.exit(0);
        }
        return true;
    }
}
