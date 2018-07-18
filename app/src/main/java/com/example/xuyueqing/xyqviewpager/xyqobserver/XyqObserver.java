package com.example.xuyueqing.xyqviewpager.xyqobserver;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public interface XyqObserver {
    void actionChange(boolean isClickDown);
    void pageChange(int pagePosition);
}
