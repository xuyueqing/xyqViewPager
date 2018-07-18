package com.example.xuyueqing.xyqviewpager.xyqobserver;

import android.database.Observable;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqObservable extends Observable<XyqObserver> {
    @Override
    public void registerObserver(XyqObserver observer) {
        super.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(XyqObserver observer) {
        super.unregisterObserver(observer);
    }

    @Override
    public void unregisterAll() {
        super.unregisterAll();
    }

    public void notifyActionChange(boolean isClickDown){
        for(XyqObserver observer:mObservers){
            observer.actionChange(isClickDown);
        }
    }

    public void notifyPositionChange(int position){
        for(XyqObserver observer:mObservers){
            observer.pageChange(position);
        }
    }
}
