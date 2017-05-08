package com.example.bozhitong.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Tools {
    public static int getScreenWidth(Activity context) {
        // TODO Auto-generated method stub
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity context) {
        // TODO Auto-generated method stub
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 返回当前系统时间
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getDate() {
        return new SimpleDateFormat("MM月dd日").format(new Date());
    }

    /*
    *apm=0 表示上午，apm=1表示下午。
    * 判断上午，下午
     */
    public static boolean isAM() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);

        int apm = mCalendar.get(Calendar.AM_PM);
        if (apm == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static void Removethetitle(Activity activity) {

    }

    /**
     * Animatio动画
     *
     * @return
     */
    public static Animation getAnimation(int start, int end) {
        Animation animation = new TranslateAnimation(start, end, 0, 0);
        animation.setDuration(1);
        animation.setFillAfter(true);
        return animation;
    }

    /*获取日期*/
    public static int getDay(String date) {
        String h;
        String[] day = date.split("-");
        h = day[2];
        return Integer.parseInt(h);
    }

    /*获取月份*/
    public static int getMonth(String date) {
        String m;
        String[] day = date.split("-");
        m = day[1];
        return Integer.parseInt(m);
    }

    /*获取年份*/
    public static int getYear(String date) {
        String y;
        String[] day = date.split("-");
        y = day[0];
        return Integer.parseInt(y);
    }


    /**
     * 获取今天往后一周的日期（年月日）
     */
    public static List<String> get7date() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        for (int i = 0; i < 7; i++) {
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            String date = mYear + "-" + mMonth + "-" + mDay;

            if (isAM() && i == 0) {
                dates.add(date);
                dates.add(date);
            } else if (!isAM() && i == 0) {
                dates.add(date);
            } else {
                dates.add(date);
                dates.add(date);
            }
        }
        return dates;
    }


    public static ArrayList<String> getTimeList() {
        ArrayList<String> item = new ArrayList<String>();
        List<String> m7date = get7date();

        for (int i = 0; i < m7date.size(); i++) {
            String iten;
            String week = getWeek(m7date.get(i));
            if (isAM()) {

                iten = getisAMItem(i, week, m7date);
            } else {
                iten = getNoIsAMItem(i, week, m7date);
            }

            item.add(iten);
        }

        return item;
    }

    public static String getNoIsAMItem(int i, String week, List<String> m7date) {
        switch (i) {

            case 0:
                return "今天(" + week + ")下午";

            case 1:
                return "明天(" + week + ")上午";

            case 2:
                return "明天(" + week + ")下午";

            case 3:
                return "后天(" + week + ")上午";

            case 4:
                return "后天(" + week + ")下午";

            case 5:
                return "后天(" + week + ")上午";

            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                if (i % 2 == 0) {
                    return getMonth(m7date.get(i)) + "月" + getDay(m7date.get(i)) + "日(" + week + ")下午";
                } else {
                    return getMonth(m7date.get(i)) + "月" + getDay(m7date.get(i)) + "日(" + week + ")上午";
                }

        }
        return null;
    }

    public static String getisAMItem(int i, String week, List<String> m7date) {

        switch (i) {
            case 0:
                return "今天(" + week + ")上午";

            case 1:
                return "今天(" + week + ")下午";

            case 2:
                return "明天(" + week + ")上午";

            case 3:
                return "明天(" + week + ")下午";

            case 4:
                return "后天(" + week + ")上午";

            case 5:
                return "后天(" + week + ")下午";

            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                if (i % 2 == 0) {
                    return getMonth(m7date.get(i)) + "月" + getDay(m7date.get(i)) + "日(" + week + ")上午";
                } else {
                    return getMonth(m7date.get(i)) + "月" + getDay(m7date.get(i)) + "日(" + week + ")下午";
                }

        }
        return null;
    }


    /**
     * 判断当前日期是星期几
     *
     * @param pTime 设置的需要判断的时间  //格式如2012-09-08
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */

//  String pTime = "2012-03-12";
    public static String getWeek(String pTime) {

        String Week = "周";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }


        return Week;
    }
}
