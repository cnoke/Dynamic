package com.knd.common.utils;

import android.content.Context;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;

import com.knd.base.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyInputFilter extends DigitsKeyListener {
    private Context context;
    public MyInputFilter(Context context){
        super();
        this.context=context;
    }
    Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");
        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Matcher matcher=  pattern.matcher(charSequence);
            if(!matcher.find()){
                return null;
            }else{
                ToastUtils.show(context,"请输入字母、数字或者中文");
                return "";
            }

        }
}
