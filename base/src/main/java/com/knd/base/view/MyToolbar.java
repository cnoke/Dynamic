package com.knd.base.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.knd.base.R;


public class MyToolbar extends RelativeLayout {
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_right;
    public static final String TAG="TOOLBAR";
    public MyToolbar(Context context) {
        this(context,null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setTag(TAG);
    }

    private void init(){
        iv_back=new ImageView(getContext());
        iv_back.setImageResource(R.mipmap.ic_back);
        iv_back.setVisibility(GONE);
        addView(iv_back);

        tv_title=new TextView(getContext());
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_title.setGravity(Gravity.CENTER);
        tv_title.setTextColor(Color.WHITE);
        tv_title.setTextSize(18);
        tv_title.setLayoutParams(lp);
        addView(tv_title);

        tv_right=new TextView(getContext());
        LayoutParams lp2=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(ALIGN_PARENT_RIGHT);
        tv_right.setTextColor(Color.WHITE);
        tv_right.setTextSize(16);
        tv_right.setLayoutParams(lp2);
        tv_right.setText("");
        tv_right.setVisibility(GONE);
        addView(tv_right);
    }

    public void setBack(OnClickListener listener){
        if(listener!=null){
            iv_back.setVisibility(VISIBLE);
            iv_back.setOnClickListener(listener);
        }
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public void setRightValue(String value,OnClickListener listener){
        tv_right.setVisibility(VISIBLE);
        tv_right.setText(value);
        tv_right.setOnClickListener(listener);
    }

}
