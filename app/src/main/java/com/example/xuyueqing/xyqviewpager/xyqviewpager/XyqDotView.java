package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqDotView extends View {
    public int mIndex;

    public XyqDotView(Context context) {
        super(context);
    }

    public XyqDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XyqDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIndex(int index){
        mIndex = index;
    }

    public int getIndex(){
        return mIndex;
    }
}
