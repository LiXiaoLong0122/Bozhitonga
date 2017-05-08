package com.example.bozhitong.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * @Author SunnyCoffee
 * @Date 2014-1-28
 * @version 1.0
 * @Desc 工具类
 */

public class Utils {

	public static String getCurrentTime(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	public static String getCurrentTime() {
		return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
	}


	public static ArrayList<String> getList(Context m, int id){
		ArrayList<String> mlist = new ArrayList<String>();
		String[] types = m.getResources().getStringArray(id);

		for (int i = 0; i < types.length; i++) {
			mlist.add(types[i]);
		}
		return mlist;
	}

}
