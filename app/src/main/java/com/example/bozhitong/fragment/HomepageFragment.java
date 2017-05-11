package com.example.bozhitong.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.example.bozhitong.MainActivity;
import com.example.bozhitong.MipcaActivityCapture;
import com.example.bozhitong.MyContentHandler;
import com.example.bozhitong.MyNetwork;
import com.example.bozhitong.R;
import com.example.bozhitong.activity.AccessControl;
import com.example.bozhitong.activity.FightGroups;
import com.example.bozhitong.activity.Periphery;
import com.example.bozhitong.activity.Smarthome;
import com.example.bozhitong.activity.Supermarket;
import com.example.bozhitong.activity.VoiceOrder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.stevenhu.android.phone.bean.ADInfo;
import com.stevenhu.android.phone.utils.ViewFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

/**

 * 首页界面sdfsd

 * 首页界面

 * 
 * @author 12306
 * 
 */
@SuppressLint("SimpleDateFormat")
public class HomepageFragment extends Fragment implements OnClickListener {

	// 头部
	TextView tev_head_city, tev_head_weather;

	// 中间
	TextView tev_weather_week1, tev_week1_temperature, tev_week1_date;
	ImageView img_week1_weather;

	// 底部
	Button btn_addcity;
	// 设置数据相关
	MyNetwork myNetwork = new MyNetwork();
	MyContentHandler myContentHandler = new MyContentHandler();
	SimpleDateFormat dateformatter = new SimpleDateFormat("MM-dd");
	SimpleDateFormat weekformatter = new SimpleDateFormat("EEEE");
	ProgressDialog pd;
	List<String> list_weather;

	// 设置地图定位相关
	String location_city;
	MapView mapView;
	BaiduMap baiduMap;
	LocationClient locationClient;
	MyBDLocationListener myBDLocationListener = new MyBDLocationListener();







	private View mView;
	private Activity mContext;
    private FrameLayout mFlpager;
    private LinearLayout mScrollView;

	String a;

	/**
	 * 智慧门禁

	 */
	private LinearLayout mAccess;
	/**
	 * 邻里生活
	 */
	private LinearLayout mNeighborhood_tv;

	/**
	 * 语音下单
	 */
	private LinearLayout mVoice;
	/**
	 * 周边
	 */
	private LinearLayout mPeriphery;
	/**
	 * 商超
	 */
	private LinearLayout mSupermark;

	/**
	 * 二维码
	 */
	private int QR_WIDTH = 300;
	private int QR_HEIGHT = 300;
	private Button create;
	private Button cancel;
	private ImageView scan;
	private BufferedOutputStream outStream;
	private EditText content;
	private long time = Calendar.getInstance().getTimeInMillis();
	/**
	 * 邻里生活
	 */
	private LinearLayout mNeighborhood;
	/**
	 * 智慧家居
	 */
	private LinearLayout mSmarthome;
	private LinearLayout mFightGroups;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;

	private String[] imageUrls = {
			"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
			"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
			"http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
			"http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg" };

	private MainActivity mainActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.mContext = activity;
		mainActivity = (MainActivity)getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		if (mView != null) {
			ViewGroup parent = (ViewGroup) mView.getParent();
			if (parent != null) {
				parent.removeView(mView);
			}
		}
		try {
			mView = inflater.inflate(R.layout.activity_homepage, null);

			configImageLoader();
			initialize();
		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}

		initview();
		initdata();

		initMapLocation();
		list_weather = new ArrayList<>();

		setWeekData(dateformatter, weekformatter);
		checkNetworkState();
		return mView;
	}
	@Override
	public void onResume() {
		location();
		super.onResume();
	}

	private void initMapLocation() {
		mapView = new MapView(mContext);
		baiduMap = mapView.getMap();
	}

	@SuppressLint("NewApi")
	private void initialize() {
		// cycleViewPager = (CycleViewPager)mContext.getFragmentManager()
		// .findFragmentById(R.id.fragment_cycle_viewpager_content);
		cycleViewPager = (CycleViewPager) mContext.getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);

		for (int i = 0; i < imageUrls.length; i++) {
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("图片-->" + i);
			infos.add(info);
		}

		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(mContext, infos
				.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(mContext, infos.get(i).getUrl()));
		}
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(mContext, infos.get(0).getUrl()));

		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);

		// 在加载数据前设置是否循环
		cycleViewPager.setData(views, infos, mAdCycleViewListener);
		// 设置轮播
		cycleViewPager.setWheel(true);

		// 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		// 设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();
		mNeighborhood = (LinearLayout) mView.findViewById(R.id.Neighborhood_tv);
		mNeighborhood.setOnClickListener(this);
		mSmarthome = (LinearLayout) mView.findViewById(R.id.smarthome_ly);
		mSmarthome.setOnClickListener(this);

	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;

			}

		}

	};

	/**
	 * 配置ImageLoder
	 */

	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				mContext.getApplicationContext())
				.defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.Neighborhood_tv:
//			FragmentManager manager = getChildFragmentManager();
//			FragmentTransaction transaction = manager.beginTransaction();
//		/*	Intent intent1 = new Intent(mContext, Circle.class);
//			startActivity(intent1);*/
//			mScrollView.setVisibility(View.GONE);
//			mFlpager.setVisibility(View.VISIBLE);
//			transaction.replace(R.id.Fl_pager, new NeighbourhoodFragment());
//			transaction.commit();
			mainActivity.setCurrentItem(2);


			break;



		case R.id.smarthome_ly:// 智能家居

			intent = new Intent(mContext, Smarthome.class);
			startActivity(intent);
			break;
		case R.id.access_ly:// 智慧门禁，平安社区
			intent = new Intent();
			intent.setClass(mContext, AccessControl.class);
			startActivity(intent);
			break;
		case R.id.voice_ly:// 语音下单
			intent = new Intent();
			intent.setClass(mContext, VoiceOrder.class);
			startActivity(intent);
			break;
		case R.id.periphery_lv:// 周边
			intent = new Intent();
			intent.setClass(mContext, Supermarket.class);
			startActivity(intent);
			break;
		case R.id.supermarket_ly:// 商超
			intent = new Intent();
			intent.setClass(mContext, Periphery.class);
			startActivity(intent);
			break;
		case R.id.teihui_ly:// 特惠拼团
			intent = new Intent();
			intent.setClass(mContext, FightGroups.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	};

	private void initdata() {
		/*
		 * 生成二维码
		 */

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (content.getText().toString().equals("")) {

					Toast.makeText(mContext, "不可输入空的内容", Toast.LENGTH_LONG).show();
				} else {
					View view = LayoutInflater.from(mContext).inflate(
							R.layout.bitmapdialog, null);
					ImageView img = (ImageView) view
							.findViewById(R.id.dialog_bitmap);
					final String url = content.getText().toString();
					final Bitmap scanbitmap = createQRImage(url);
					img.setImageBitmap(scanbitmap);

					new AlertDialog.Builder(mContext)
							.setTitle("二维码")
							.setView(view)
							.setPositiveButton("存至本地",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											if (Environment
													.getExternalStorageState()
													.equals(Environment.MEDIA_MOUNTED)) {
												/*
												 * 将图片转换成一个byte[]；
												 */
												byte[] bitmaps = getbitmaptobytes(scanbitmap);

												/*
												 * 将Byte[]转换成long类型
												 */
												long longbitmaps = bytes2long(bitmaps);
												/*
												 * 判断SD卡是否有足够的空间供下载使用
												 */
												// boolean iscapacity =
												// isEnaleforDownload(longbitmaps);
												if (true) {

													try {
														File sdCardDir = Environment
																.getExternalStorageDirectory();
														// 防止出现重复名字
														String fileName = time
																+ ".png";
														File dir;
														dir = new File(
																sdCardDir
																		.getCanonicalPath()
																		+ "/myscan/");
														if (!dir.exists()) {
															dir.mkdirs();
														}

														File cacheFile = new File(
																dir, fileName);
														FileOutputStream fstream = new FileOutputStream(
																cacheFile);
														outStream = new BufferedOutputStream(
																fstream);
														System.out
																.println("1222222");
														outStream
																.write(bitmaps);

														Toast.makeText(
																mContext,
																"图片成功存至myscan目录下",
																Toast.LENGTH_SHORT).show();

													} catch (Exception e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
														Log.d("ydh",
																"保存本地图片异常！！！");
													} finally {

														if (outStream != null) {
															try {
																outStream
																		.close();
															} catch (IOException e) {
																// TODO
																// Auto-generated
																// catch block
																e.printStackTrace();
															}
														}
														// TODO
														dialog.dismiss();
														dialog = null;
													}

												}
											} else {
												Toast.makeText(mContext,
														"SDCard存储空间不足", Toast.LENGTH_SHORT)
														.show();
											}

										}

										private boolean isEnaleforDownload(
												long longbitmaps) {
											/*
											 * Statfs : 获取系统文件的类
											 * 
											 * @.getAbsolutePath()给一个抽象路径名的绝对路径字符串
											 */
											StatFs statfs = new StatFs(
													Environment
															.getExternalStorageDirectory()
															.getAbsolutePath());

											// 获得你的手机共有几个存储，即获得块的总量
											int blockCount = statfs
													.getBlockCount();

											// 该手机里可用的块的数量，即可用的存储。也可以说是剩余内存容量
											int availableBlocks = statfs
													.getAvailableBlocks();

											/*
											 * 获得每一个块的大小， 返回值用long接受，int可能达到上限
											 */
											long blockSize = statfs
													.getBlockSize();
											// 获得可用的存储空间

											long asavespace = availableBlocks
													* blockSize;

											if (asavespace > longbitmaps) {
												return true;
											}
											return false;
										}

										/*
										 * 将Byte[]转换成long类型
										 */
										private long bytes2long(byte[] bitmaps) {
											int num = 0;
											for (int ix = 0; ix < 8; ++ix) {
												num <<= 8;
												num |= (bitmaps[ix] & 0xff);
											}
											return num;
										}

										/*
										 * 将图片转换成Byte[]
										 */
										private byte[] getbitmaptobytes(
												Bitmap bitmap) {
											ByteArrayOutputStream out = new ByteArrayOutputStream();
											bitmap.compress(
													Bitmap.CompressFormat.PNG,
													100, out);
											return out.toByteArray();
										}
									}).show();

				}
			}
		});

		/*
		 * 跳转到扫描二维码页面
		 */
		scan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, MipcaActivityCapture.class);
				startActivity(intent);
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				content.setText("");
			}
		});
	}

	private void initview() {
		// head
		tev_head_city = (TextView) mView.findViewById(R.id.tev_main_city);
		tev_head_weather = (TextView) mView.findViewById(R.id.tev_main_currentWeather);

		// middle
		// week1
		tev_weather_week1 = (TextView) mView.findViewById(R.id.tev_weather_week1);
		tev_week1_temperature = (TextView) mView.findViewById(R.id.tev_weather_week1_temperature);
		tev_week1_date = (TextView) mView.findViewById(R.id.tev_weather_week1_date);
		img_week1_weather = (ImageView) mView.findViewById(R.id.img_weather_week1);





		mFlpager=(FrameLayout) mView.findViewById(R.id.Fl_pager);
		mScrollView=(LinearLayout) mView.findViewById(R.id.Rl_Layout);
		create = (Button) mView.findViewById(R.id.create);
		cancel = (Button) mView.findViewById(R.id.cancel);
		content = (EditText) mView.findViewById(R.id.input);
		scan = (ImageView) mView.findViewById(R.id.main_scan);
		mAccess = (LinearLayout) mView.findViewById(R.id.access_ly);
		mAccess.setOnClickListener(this);
		mNeighborhood_tv = (LinearLayout) mView
				.findViewById(R.id.Neighborhood_tv);
		mNeighborhood_tv.setOnClickListener(this);

		mVoice = (LinearLayout) mView.findViewById(R.id.voice_ly);
		mVoice.setOnClickListener(this);
		mPeriphery = (LinearLayout) mView.findViewById(R.id.periphery_lv);
		mPeriphery.setOnClickListener(this);
		mSupermark = (LinearLayout) mView.findViewById(R.id.supermarket_ly);
		mSupermark.setOnClickListener(this);
		mFightGroups = (LinearLayout) mView.findViewById(R.id.teihui_ly);
		mFightGroups.setOnClickListener(this);
	}

	public Bitmap createQRImage(String url) {
		try {
			// 判断URL合法性
			if (url == null || "".equals(url) || url.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 图像数据转换，使用了矩阵转换
			// BitMatrix bitMatrix = new MultiFormatWriter().encode(new
			// String(url.getBytes("GBK"),"ISO-8859-1"),BarcodeFormat.QR_CODE,
			// 300, 300);
			BitMatrix bitMatrix = new QRCodeWriter().encode(url,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			// 下面这里按照二维码的算法，逐个生成二维码的图片，
			// 两个for循环是图片横列扫描的结果
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			// 生成二维码图片的格式，使用ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

























	// 获取天气信息
	public void getWeather(String city_name, String day, final String key)
			throws UnsupportedEncodingException {
		city_name = URLEncoder.encode(city_name, "gbk");
		String urlStr = "http://php.weather.sina.com.cn/xml.php?city="
				+ city_name + "&password=DJOYnieT8234jlsK&day=" + day + "";
		Toast.makeText(mContext, urlStr, Toast.LENGTH_SHORT);
		myNetwork.getWeatherXml(urlStr, new MyNetwork.SuccessCallBack() {
			@Override
			public void onSuccess(String result) {

				if (result.equals("") && pd.isShowing()) {
					pd.dismiss();
				}

				MyContentHandler contentHandler;
				if (key.equals("head")) {
					try {
						contentHandler = analysis(result);
						setWeatherHeadView(analysis(result));
						pd.dismiss();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}, new MyNetwork.FailCallBack() {
			@Override
			public void onFail() {
				pd.dismiss();
				Toast.makeText(mContext, "服务器繁忙，获取天气信息失败！",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	// 解析xml
	public MyContentHandler analysis(String s) throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		XMLReader xmlReader = factory.newSAXParser().getXMLReader();
		xmlReader.setContentHandler(myContentHandler);
		xmlReader.parse(new InputSource(new StringReader(s)));

		return myContentHandler;
	}

	// head + week1
	public void setWeatherHeadView(MyContentHandler contentHandler) {
		String city = contentHandler.getCity();
		String status1 = contentHandler.getStatus1(), status2 = contentHandler
				.getStatus2();
		String temperature1 = contentHandler.getTemperature1(), temperature2 = contentHandler
				.getTemperature2();
		if (!TextUtils.isEmpty(city)) {
			tev_head_city.setText(city);
		}
		if (!(TextUtils.isEmpty(status1) && TextUtils.isEmpty(status2))) {
			if (status1.equals(status2)) {
				tev_head_weather.setText(status1);
				list_weather.add(status1);
			} else {
				tev_head_weather.setText(status1 + " 转 " + status2);
				list_weather.add(status1 + " 转 " + status2);
			}
		}
		if (!(TextUtils.isEmpty(temperature1) && TextUtils
				.isEmpty(temperature2))) {
			tev_week1_temperature.setText(temperature2+"℃" + "~" + temperature1+"℃");
		}

		// 设置天气图标
		if (tev_head_weather.getText().toString().contains("多云")) {

			img_week1_weather.setImageResource(R.drawable.img_weather_cloudy);
		} else if (tev_head_weather.getText().toString().contains("晴")) {

			img_week1_weather.setImageResource(R.drawable.img_weather_fine);
		} else if (tev_head_weather.getText().toString().contains("雷")) {

			img_week1_weather.setImageResource(R.drawable.img_weather_thunder);
		} else if (tev_head_weather.getText().toString().contains("雨")) {

			img_week1_weather.setImageResource(R.drawable.img_weather_rain);
		} else if (tev_head_weather.getText().toString().contains("雪")) {
			;
			img_week1_weather.setImageResource(R.drawable.img_weather_snow);
		} else {

			img_week1_weather.setImageResource(R.drawable.img_weather_cloudy);
		}

	}

	// 设置温度和天气图标
	public void setWeatherWeekView(TextView tev_temperature,
								   ImageView img_weather, MyContentHandler contentHandler) {
		String temperature1 = contentHandler.getTemperature1(), temperature2 = contentHandler
				.getTemperature2();
		String status1 = contentHandler.getStatus1(), status2 = contentHandler
				.getStatus2();
		if (!(TextUtils.isEmpty(temperature1) && TextUtils
				.isEmpty(temperature2))) {
			tev_temperature.setText(temperature2 + "/" + temperature1);
		}
		if (!(TextUtils.isEmpty(status1) && TextUtils.isEmpty(status2))) {
			if (status1.equals(status2)) {
				list_weather.add(status1);
			} else {
				list_weather.add(status1 + " 转 " + status2);
			}
		}

		// 设置天气图标
		if ((status1 + status2).contains("多云")) {
			img_weather.setImageResource(R.drawable.img_weather_cloudy);
		} else if ((status1 + status2).contains("晴")) {
			img_weather.setImageResource(R.drawable.img_weather_fine);
		} else if ((status1 + status2).contains("雷")) {
			img_weather.setImageResource(R.drawable.img_weather_thunder);
		} else if ((status1 + status2).contains("雨")) {
			img_weather.setImageResource(R.drawable.img_weather_rain);
		} else if ((status1 + status2).contains("雪")) {
			img_weather.setImageResource(R.drawable.img_weather_snow);
		} else {
			img_weather.setImageResource(R.drawable.img_weather_cloudy);
		}

	}

	// 设置星期和日期
	@SuppressWarnings("static-access")
	public void setWeekData(SimpleDateFormat dateFormat,
							SimpleDateFormat weekFormat) {

		Date date = new Date(System.currentTimeMillis());
		// 添加日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String week1_date = dateFormat.format(date);
		tev_weather_week1.setText(weekFormat.format(date));
		tev_week1_date.setText(week1_date);


	}

	// 判断是否有可用的网络
	private boolean checkNetworkState() {
		boolean flag = false;
		// 得到网络连接信息
		ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 去进行判断网络是否连接
		if (manager.getActiveNetworkInfo() != null) {
			flag = manager.getActiveNetworkInfo().isAvailable();
		}
		if (!flag) {
			View view = LayoutInflater.from(mContext).inflate(
					R.layout.mydialog, null);
			new AlertDialog.Builder(mContext)
					.setView(view)
					.setPositiveButton("设置",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
													int arg1) {
									Intent intent = new Intent(
											"android.settings.WIRELESS_SETTINGS");
									startActivity(intent);
								}
							}).setNegativeButton("取消", null).show();
		} else {
			pd = ProgressDialog.show(mContext, null, "正在查询......");
		}

		return flag;
	}

	// 定位设置
	private void location() {
		// 定位初始化
		locationClient = new LocationClient(mContext);
		// 注册监听函数：说明：当没有注册监听函数时，无法发起网络请求。
		locationClient.registerLocationListener(myBDLocationListener);
		LocationClientOption clientOption = new LocationClientOption();
		clientOption.setCoorType("bd09ll");
		clientOption.setScanSpan(1000);
		clientOption.setIsNeedAddress(true);
		locationClient.setLocOption(clientOption);
		locationClient.start();// 开启定位
	}

	// 定位监听相关
	class MyBDLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null || locationClient == null) {
				return;
			}
			new MyLocationData.Builder().accuracy(location.getRadius())
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			location_city = location.getCity();
			String[] city = location_city.split("市");
			System.out.println("定位到的城市：-------------------->" + city[0]);
			try {
				getWeather(city[0], "0", "head");
				getWeather(city[0], "1", "week1");
				getWeather(city[0], "2", "week2");
				getWeather(city[0], "3", "week3");
				getWeather(city[0], "4", "week4");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
