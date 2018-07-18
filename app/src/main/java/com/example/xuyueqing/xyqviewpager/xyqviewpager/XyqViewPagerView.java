package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.xuyueqing.xyqviewpager.R;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqViewPagerView extends RelativeLayout {

    private XyqDotListView mMyDotListView;
    private XyqPagerAdapter mViewPagerAdapter;
    private XyqTouchDotView mTouchDotView;
    private XyqViewPager mViewPager;
    private Context mContext;
    private int mPageNum;

    public XyqViewPagerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public XyqViewPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }


    public void init() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(R.layout.xyq_view_pager, null);
        mMyDotListView = (XyqDotListView) findViewById(R.id.my_dot_list_view);
        mTouchDotView = (XyqTouchDotView) findViewById(R.id.touch_dot);
        mViewPager = (XyqViewPager) findViewById(R.id.my_view_pager);
        mViewPagerAdapter = new XyqPagerAdapter(mContext);
        setPageNum(3);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(mTouchDotView);
        mTouchDotView.registerObserver(mMyDotListView);
        mTouchDotView.registerObserver(mViewPager);
        mMyDotListView.registerObserver(mViewPager);
        mMyDotListView.registerObserver(mTouchDotView);
    }

    public void setPageNum(int pageNum){
        mPageNum = pageNum;
        mViewPager.pageChange(0);
        mMyDotListView.setData(mPageNum);
        mViewPagerAdapter.setPage(mPageNum);
        mTouchDotView.setNum(mPageNum);


    }
}
