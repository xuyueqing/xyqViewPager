package com.example.xuyueqing.xyqviewpager;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuyueqing.xyqviewpager.xyqviewpager.XyqViewPagerView;

public class MainActivity extends AppCompatActivity {

    private XyqViewPagerView mXyqViewPagerView;
    private Button mButton;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mXyqViewPagerView = (XyqViewPagerView) findViewById(R.id.xyq_view_pager);
        mXyqViewPagerView.init();
        mButton = (Button)findViewById(R.id.change_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText = (EditText)findViewById(R.id.page_num_text);
                mXyqViewPagerView.setPageNum(Integer.parseInt(mEditText.getText().toString()));
            }
        });
    }
}
