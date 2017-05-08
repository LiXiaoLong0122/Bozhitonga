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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
 * 投诉建议(投诉Fragment)
 *
 * @author 12306
 */
public class ComplaintFragment extends Fragment {
    private View mView;
    private Activity mActivity;
    public static final int RESULT_OK = -1;
    private RelativeLayout mcamera;

    private GridView noScrollgridview;
    private GridAdapter adapter;
    //	private View view;
//	private PopupWindow pop = null;
//	private LinearLayout ll_popup;
    public static Bitmap bimap;
    private PopupwindowPhoto popupwindowType;
    private ComplaintAndAdvocacy complaintAndAdvocacy;
    private TextView tv;
    private LinearLayout ll_typea;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
        complaintAndAdvocacy = (ComplaintAndAdvocacy) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_complaint, null);
//		view = inflater.inflate(R.layout.item_popupwindows,
//				null);

        popupwindowType = new PopupwindowPhoto(mActivity, ComplaintFragment.this);


        Res.init(mActivity);
        bimap = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon_addpic_unfocused);
        PublicWay.activityList.add(mActivity);
        Init();
        return mView;
    }

    ;

    public void Init() {

//		pop = new PopupWindow(mActivity);
//
//		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
//
//		pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//		pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//		pop.setBackgroundDrawable(new BitmapDrawable());
//		pop.setFocusable(true);
//		pop.setOutsideTouchable(true);
//		pop.setContentView(view);
//
//		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
//		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
//		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
//		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
//		parent.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				pop.dismiss();
//			ll_popup.clearAnimation();
//
//
//			}
//		});
//		bt1.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				photo();
//				pop.dismiss();
//			ll_popup.clearAnimation();
//
//
//			}
//		});
//		bt2.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				Intent intent = new Intent(mActivity,
//						AlbumActivity.class);
//				startActivity(intent);
//				mActivity.overridePendingTransition(R.anim.activity_translate_in,
//						R.anim.activity_translate_out);
//				pop.dismiss();
//				ll_popup.clearAnimation();
//
//
//			}
//		});
//		bt3.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				pop.dismiss();
//				ll_popup.clearAnimation();
//
//			}
//		});
        tv = (TextView) mView.findViewById(R.id.tv_type);
        tv.setTag(ContentValuse.TAG_COM);
        ll_typea = (LinearLayout) mView.findViewById(R.id.ll_typea);
        ll_typea.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintAndAdvocacy.OpenRightMenu(ll_typea);
            }
        });


        mcamera = (RelativeLayout) mView.findViewById(R.id.camera);
        noScrollgridview = (GridView) mView.findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(mActivity);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
//
//					ll_popup.startAnimation(AnimationUtils.loadAnimation(
//							mActivity, R.anim.activity_translate_in));
//					pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                    popupwindowType.showpopPhotoAtLocation(Gravity.BOTTOM, 0, 0);

                } else {
                    Intent intent = new Intent(mActivity,
                            GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    complaintAndAdvocacy.initFragmentView("请选择投诉类型",tv);
//complaintAndAdvocacy.timeList.setOnItemClickListener(new OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        tv.setText(complaintAndAdvocacy.itemList.get(position));
//        complaintAndAdvocacy.closeDrawers();
//    }
//});
    }

    public void setFragmentTexta(String data){ tv.setText(data); }


//	@SuppressLint("HandlerLeak")
//	public class GridAdapter extends BaseAdapter {
//		private LayoutInflater inflater;
//		private int selectedPosition = -1;
//		private boolean shape;
//
//		public boolean isShape() {
//			return shape;
//		}
//
//		public void setShape(boolean shape) {
//			this.shape = shape;
//		}
//
//		public GridAdapter(Context context) {
//			inflater = LayoutInflater.from(context);
//		}
//
//		public void update() {
//			loading();
//		}
//
//		public int getCount() {
//			if (Bimp.tempSelectBitmap.size() == 9) {
//				return 9;
//			}
//			return (Bimp.tempSelectBitmap.size() + 1);
//		}
//
//		public Object getItem(int arg0) {
//			return null;
//		}
//
//		public long getItemId(int arg0) {
//			return 0;
//		}
//
//		public void setSelectedPosition(int position) {
//			selectedPosition = position;
//		}
//
//		public int getSelectedPosition() {
//			return selectedPosition;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder holder = null;
//			if (convertView == null) {
//				convertView = inflater.inflate(R.layout.item_published_grida,
//						parent, false);
//				holder = new ViewHolder();
//				holder.image = (ImageView) convertView
//						.findViewById(R.id.item_grida_image);
//				convertView.setTag(holder);
//			} else {
//				holder = (ViewHolder) convertView.getTag();
//			}
//
//			if (position == Bimp.tempSelectBitmap.size()) {
//				holder.image.setImageBitmap(BitmapFactory.decodeResource(
//						getResources(), R.drawable.icon_addpic_unfocused));
//				if (position == 9) {
//					holder.image.setVisibility(View.GONE);
//				}
//			} else {
//				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position)
//						.getBitmap());
//			}
//
//			return convertView;
//		}
//
//		public class ViewHolder {
//			public ImageView image;
//		}
//
//		Handler handler = new Handler() {
//			public void handleMessage(Message msg) {
//				switch (msg.what) {
//				case 1:
//					adapter.notifyDataSetChanged();
//					break;
//				}
//				super.handleMessage(msg);
//			}
//		};
//
//		public void loading() {
//			new Thread(new Runnable() {
//				public void run() {
//					while (true) {
//						if (Bimp.max == Bimp.tempSelectBitmap.size()) {
//							Message message = new Message();
//							message.what = 1;
//							handler.sendMessage(message);
//							break;
//						} else {
//							Bimp.max += 1;
//							Message message = new Message();
//							message.what = 1;
//							handler.sendMessage(message);
//						}
//					}
//				}
//			}).start();
//		}
//	}


    public void onStart() {
        adapter.update();
        super.onStart();
    }


//    public void photo() {
//        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(openCameraIntent, ContentValuse.TAKE_PICTURE);
//    }

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
