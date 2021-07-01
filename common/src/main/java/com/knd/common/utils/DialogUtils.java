package com.knd.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.knd.common.R;

public class DialogUtils {
    public static void showConfirm(Context context, String message, final View.OnClickListener listener){
        View view= LayoutInflater.from(context).inflate(R.layout.common_view_dialog_confirm,null);
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(view);
        TextView tv_msg= view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams lp= dialog.getWindow().getAttributes();
        lp.width=ScreenUtils.getScreenWidth(context)/2;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.common_bg_equip_warning));
        dialog.show();

    }

    // 系统层对话弹窗
    public static void showConfirmSystem(Context context, String message, final View.OnClickListener listener){
        View view= LayoutInflater.from(context).inflate(R.layout.common_view_dialog_confirm,null);
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(view);
        TextView tv_msg= view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.onClick(v);
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams lp= dialog.getWindow().getAttributes();
        lp.width=ScreenUtils.getScreenWidth(context)/2;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.common_bg_equip_warning));
        dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
        dialog.show();

    }
}
