package com.example.bozhitong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bozhitong.GalleryActivity;
import com.example.bozhitong.R;
import com.example.bozhitong.activity.ComplaintAndAdvocacy;
import com.example.bozhitong.photo.adapter.GridAdapter;
import com.example.bozhitong.photo.util.Bimp;
import com.example.bozhitong.photo.util.FileUtils;
import com.example.bozhitong.photo.util.ImageItem;
import com.example.bozhitong.photo.util.PublicWay;
import com.example.bozhitong.photo.util.Res;
import com.example.bozhitong.utils.ContentValuse;
import com.example.bozhitong.utils.PopupwindowPhoto;

/**
 * 投诉建议(建议Fragment)
 *
 * @author 12306
 */
public class ProposalFragment extends Fragment {
    private View mView;
    public static final int RESULT_OK = -1;
    private RelativeLayout mcamera;

    private GridView noScrollgridview;
    private GridAdapter adapter;

    private Activity mActivity;
    private PopupwindowPhoto pop;
    public static Bitmap bimap;
    private ComplaintAndAdvocacy complaintAndAdvocacy;
    private TextView tvv;
    private LinearLayout ll_typed;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        complaintAndAdvocacy = (ComplaintAndAdvocacy) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_proposal, container,
                false);// 关联布局文件

        pop = new PopupwindowPhoto(mActivity, ProposalFragment.this);


        Res.init(mActivity);
        bimap = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(mActivity);
        initVew();

        return mView;

    }

    private void initVew() {
        mcamera = (RelativeLayout) mView.findViewById(R.id.camera);
        noScrollgridview = (GridView) mView.findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(mActivity);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {

                    pop.showpopPhotoAtLocation(Gravity.BOTTOM, 0, 0);

                } else {
                    Intent intent = new Intent(mActivity,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
        tvv = (TextView) mView.findViewById(R.id.tv_typea);
        tvv.setTag(ContentValuse.TAG_PRO);
        ll_typed = (LinearLayout) mView.findViewById(R.id.ll_typed);
        ll_typed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintAndAdvocacy.OpenRightMenu(ll_typed);
            }
        });
        complaintAndAdvocacy.initFragmentView("请选择建议类型",tvv);


//        complaintAndAdvocacy.timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                tvv.setText(complaintAndAdvocacy.itemList.get(position));
//                complaintAndAdvocacy.closeDrawers();
//            }
//        });
    }
    public void setFragmentTextc(String data){ tvv.setText(data); }

    public void onStart() {
        adapter.update();
        super.onStart();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //	Toast.makeText(mActivity,"sdas",Toast.LENGTH_LONG).show();
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
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        // 页面销毁Grdivew里边的数据
        adapter.notifyDataSetChanged();
        Bimp.tempSelectBitmap.clear();
        Bimp.max = 0;
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        adapter.notifyDataSetChanged();
        Bimp.tempSelectBitmap.clear();
        Bimp.max = 0;
    }

}
