package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.xuyueqing.xyqviewpager.xyqobserver.XyqObservable;
import com.example.xuyueqing.xyqviewpager.xyqobserver.XyqObserver;
import com.example.xuyueqing.xyqviewpager.xyqutil.UIUtils;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqTouchDotView extends android.support.v7.widget.AppCompatTextView implements ViewPager.OnPageChangeListener ,XyqObserver{
    private static final String TAG = "MyTouchCircleView";
    private Context mContext;
    private boolean mIsDrag = false;
    private float mDownX;
    private float mDownY;
    private int mNum;
    private int mStartPosition;
    private int mEndPosition;
    private int mSubDistance;
    private int mPosition;
    private XyqObservable mObservable = new XyqObservable();

    public void registerObserver(XyqObserver observer) {
        mObservable.registerObserver(observer);
    }

    public void unregisterObserver(XyqObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    public XyqTouchDotView(Context context) {
        super(context);
        mContext = context;
    }

    public XyqTouchDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mSubDistance = UIUtils.dp2px(mContext,16);
        mPosition = 0;
    }

    public XyqTouchDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setNum(int num){
        mNum = num;
        int width = UIUtils.dp2px(mContext,16*mNum);
        int margin = UIUtils.dp2px(mContext,2);
        mStartPosition = margin;
        mEndPosition = width;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int l = getLeft();
        Log.d(TAG, "onDraw: " + l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isEnabled()) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    setPressed(true);
                    mIsDrag = false;
                    mDownX = event.getX();
                    mDownY = event.getY();
                    mObservable.notifyActionChange(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d(TAG, "onTouchEvent: ");
                    final float xDistance = event.getX() - mDownX;
                    final float yDistance = event.getY() - mDownY;
                    if (Math.abs(xDistance) > 10 || Math.abs(yDistance) > 10) {
                        int l, r;
                        int width = getWidth();
                        mIsDrag = true;
                        l = (int) (getLeft() + xDistance);
                        r = l + width;
                        if (l < mStartPosition) {
                            l = mStartPosition;
                            r = l + width;
                        }
                        if (r > mEndPosition) {
                            r = mEndPosition;
                            l = r - width;
                        }
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
                        layoutParams.leftMargin = l;
                        layoutParams.width = getWidth();
                        layoutParams.height = getHeight();
                        this.setLayoutParams(layoutParams);
                        int position = (int)Math.round((double)(l-mStartPosition)/(double)mSubDistance);
                        if(position != mPosition){
                            mPosition = position;
                            mObservable.notifyPositionChange(mPosition);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    setPressed(false);
                    setLocation(mPosition);
                    mObservable.notifyActionChange(false);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setPressed(false);
                    mObservable.notifyActionChange(false);
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled: ");
        if(!isPressed()) {
            if (mPosition != position) {
                mPosition = position;
            }
            setLocation(position + positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected: ");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged: ");
    }

    public void setPosition(int position){
        if(mPosition != position){
            mPosition = position;
            setLocation(position);
        }
    }

    public void setLocation(double position){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
        layoutParams.leftMargin = (int)(position*mSubDistance + mStartPosition);
        layoutParams.width = getWidth();
        layoutParams.height = getHeight();
        this.setLayoutParams(layoutParams);
    }

    @Override
    public void actionChange(boolean isClickDown) { }

    @Override
    public void pageChange(int pagePosition) {
        setPosition(pagePosition);
    }
}
