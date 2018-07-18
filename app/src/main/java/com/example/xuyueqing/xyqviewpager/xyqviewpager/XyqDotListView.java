package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xuyueqing.xyqviewpager.R;
import com.example.xuyueqing.xyqviewpager.xyqobserver.XyqObservable;
import com.example.xuyueqing.xyqviewpager.xyqobserver.XyqObserver;
import com.example.xuyueqing.xyqviewpager.xyqutil.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqDotListView extends LinearLayout implements XyqObserver{
    public int DOT_SIZE;
    private List<XyqDotView> mDotList;
    private int mNum;
    private Context mContext;
    private XyqObservable mObservable = new XyqObservable();

    public void registerObserver(XyqObserver observer) {
        mObservable.registerObserver(observer);
    }

    public void unregisterObserver(XyqObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    public XyqDotListView(Context context) {
        super(context);
        mContext = context;
        mDotList = new ArrayList<>();
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    public XyqDotListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDotList = new ArrayList<>();
        this.setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setData(int num){
        removeAllViews();
        mDotList.clear();
        mNum = num;
        //setVisibility(View.VISIBLE);
        for(int i = 0;i<num;i++) {
            XyqDotView view = new XyqDotView(mContext);
            view.setIndex(i);
            view.setBackgroundResource(R.drawable.circle_light_gray);
            DOT_SIZE = UIUtils.dp2px(mContext,8);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DOT_SIZE,DOT_SIZE);
            lp.leftMargin = UIUtils.dp2px(mContext,4);
            lp.rightMargin = UIUtils.dp2px(mContext,4);
            view.setOnTouchListener(mOnTouchListener);
            this.addView(view,lp);
            mDotList.add(view);
        }
        requestLayout();
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v instanceof XyqDotView){
                XyqDotView view = (XyqDotView) v;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        colorChange(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        colorChange(false);
                        int position = view.getIndex();
                        mObservable.notifyPositionChange(position);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        colorChange(false);
                        break;
                    default:
                        break;
                }
                return true;
            }
            return false;
        }
    };

    public void colorChange(boolean isClickDown){
        if(isClickDown){
            for(XyqDotView view:mDotList)
                view.setBackgroundResource(R.drawable.circle_gray);
        }
        else{
            for(XyqDotView view:mDotList)
                view.setBackgroundResource(R.drawable.circle_light_gray);
        }
    }

    @Override
    public void actionChange(boolean isClickDown) {
        colorChange(isClickDown);
    }

    @Override
    public void pageChange(int pagePosition) {

    }
}

