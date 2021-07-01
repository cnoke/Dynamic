package com.knd.base.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.room.util.StringUtil;

import com.knd.base.R;


public class ToastUtils {
    private static Toast mToast;
    public static void show(Context context,String msg){
        if(mToast==null){
            mToast=new Toast(context);
            mToast.setGravity(Gravity.CENTER,0,0);
            View view= LayoutInflater.from(context).inflate(R.layout.base_view_toast,null);
            mToast.setView(view);
        }
        if(msg!=null && msg.length()>0){
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
    public static void dismiss(){
        if (mToast!=null){
            mToast.cancel();
            mToast = null;
        }
    }
}
