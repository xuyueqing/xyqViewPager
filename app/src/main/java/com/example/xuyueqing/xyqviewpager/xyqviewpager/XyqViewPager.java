package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.example.xuyueqing.xyqviewpager.xyqobserver.XyqObserver;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqViewPager extends ViewPager implements XyqObserver {

    private Context mContext;

    public XyqViewPager(Context context) {
        super(context);
        mContext = context;
    }

    public XyqViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public void actionChange(boolean isClickDown) {
    }

    @Override
    public void pageChange(int pagePosition) {
        this.setCurrentItem(pagePosition,false);
    }
}
