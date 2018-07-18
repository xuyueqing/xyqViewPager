package com.example.xuyueqing.xyqviewpager.xyqviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xuyueqing.xyqviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyueqing on 2018/7/16.
 */

public class XyqPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<View> viewList;
    private int mPageNum;
    private List<String> mTestString;

    public XyqPagerAdapter(Context context){
        super();
        mContext = context;
        init();
    }

    private void init(){
        mTestString = new ArrayList<>();
        mTestString.add("ONE");
        mTestString.add("TWO");
        mTestString.add("THREE");
        mTestString.add("FOUR");
        mTestString.add("FIVE");
        mTestString.add("SIX");
        mTestString.add("SEVEN");
        mTestString.add("EIGHT");
        mTestString.add("NINE");
        mTestString.add("TEN");
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    public void setPage(int pageNum){
        mPageNum = pageNum;
        if(viewList == null){
            viewList = new ArrayList<>();
        }
        else
            viewList.clear();
        for(int i = 0;i< pageNum;i++) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            View view = mInflater.inflate(R.layout.my_view, null);
            TextView textView = (TextView) view.findViewById(R.id.my_text_view);
            textView.setText(mTestString.get(i));
            viewList.add(view);
        }
        notifyDataSetChanged();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
        //return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // return super.getPageTitle(position);
        return "title" + position;
    }
}

