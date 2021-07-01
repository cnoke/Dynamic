package com.knd.base.view;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;


import com.knd.base.R;

public class LoadingDialog {
    private static volatile LoadingDialog mLoadingDialog;
    private Dialog mDialog;
    private LoadingDialog(){
    }

    public static LoadingDialog getInstance(){
        if(mLoadingDialog==null){
            synchronized (LoadingDialog.class){
                if(mLoadingDialog==null){
                    mLoadingDialog=new LoadingDialog();
                }
            }
        }
        return mLoadingDialog;
    }

    public void show(Context context){
        if(mDialog==null){
            mDialog=new Dialog(context, R.style.transparent_window);
            mDialog.setContentView(R.layout.base_view_loading);
            mDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        }
        mDialog.show();
    }
//    public void show(String content){
//        if(mDialog!=null){
//            TextView tv_msg= mDialog.findViewById(R.id.tv_msg);
//            tv_msg.setText(content);
//            mDialog.show();
//        }
//    }

    public void dismiss(){
        if(mDialog!=null) {
            mDialog.dismiss();
            mDialog=null;
        }
    }
}
