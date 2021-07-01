package com.knd.common.view;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.knd.common.R;

import java.lang.reflect.Method;

public class MyKeyboardView extends KeyboardView {
    private EditText mCurrentEditText;
    private int mMax=-1;
    public MyKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        setKeyboard(new Keyboard(getContext(), R.xml.numberkbd));
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        setOnKeyboardActionListener(new OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {

            }

            @Override
            public void onRelease(int i) {

            }

            @Override
            public void onKey(int i, int[] ints) {
                if(mCurrentEditText!=null){
                    for (Keyboard.Key key: getKeyboard().getKeys()){
                        if(key.codes[0]==i){
                            if(i!=-5){
                                if(i==46) return;
                                int index= mCurrentEditText.getSelectionStart();
                                if(mMax==-1 || mMax>mCurrentEditText.getText().length()){
                                    mCurrentEditText.setText(mCurrentEditText.getText().insert(index,key.label));
                                    mCurrentEditText.setSelection(index+1);
                                }
                            }else{
                                int index= mCurrentEditText.getSelectionStart();
                                if(index==0){
                                    return;
                                }
                                mCurrentEditText.getText().delete(index-1,index);
//                                   mCurrentEditText.setSelection(index-1);
                            }
                            return;
                        }
                    }
                }
            }

            @Override
            public void onText(CharSequence charSequence) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });
    }

    public void register(EditText editText){
        mCurrentEditText=editText;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCurrentEditText.setShowSoftInputOnFocus(false);
        } else {
            //For sdk versions [14-20]
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(mCurrentEditText, false);
            } catch (Exception e) {
                // ignore
            }
        }

        for(InputFilter filter:mCurrentEditText.getFilters()){
            if(filter instanceof InputFilter.LengthFilter){
                mMax=  ((InputFilter.LengthFilter)filter).getMax();
                return;
            }
        }


    }

    public void resetPosition(){
        int top=mCurrentEditText.getTop();
        int height=getMeasuredHeight(); //键盘高度
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams lp= (ViewGroup.MarginLayoutParams) getLayoutParams();
            if(top>=height){
                lp.topMargin=top-height;
            }else{
                lp.topMargin=mCurrentEditText.getBottom();
            }
            lp.leftMargin=mCurrentEditText.getLeft();
            setLayoutParams(lp);
        }
//        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(getMeasuredWidth(),getMeasuredHeight());
//        if(top>=height){
//            lp.topMargin=top-height;
//        }else{
//            lp.topMargin=mCurrentEditText.getBottom();
//        }
//        lp.leftMargin=mCurrentEditText.getLeft();
//        setLayoutParams(lp);
    }
}
