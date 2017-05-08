package com.example.bozhitong.utils;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.example.bozhitong.R;
import com.example.bozhitong.fragment.MenuRightFragment;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Administrator on 2017-04-27.
 */

public class DrawerLayoutUtils {
    public static void initEvents(final DrawerLayout mDrawerLayout){
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            public void onDrawerStateChanged(int newState) {
            }

            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 1f;

                ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth()
                        * slideOffset);
                ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);

            }

            public void onDrawerOpened(View drawerView) {
            }

            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });
    }
    public static View getFragmentView(FragmentActivity fragmentActivity){
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        // 2.根据FragmentManager对象的findFragmentById方法来获取指定的fragment
        MenuRightFragment fragment2 =(MenuRightFragment) manager.findFragmentById(R.id.id_right_menu);
        // 3.获取Fragment中的布局文件
        return fragment2.getView();
    }
public static void OpenRightMenu(DrawerLayout mDrawerLayout){
    mDrawerLayout.openDrawer(Gravity.RIGHT);
    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
            Gravity.RIGHT);
}


}
