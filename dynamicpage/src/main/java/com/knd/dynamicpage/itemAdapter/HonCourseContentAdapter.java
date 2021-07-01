package com.knd.dynamicpage.itemAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.knd.common.bean.ElementBean;
import com.knd.dynamicpage.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HonCourseContentAdapter extends BaseQuickAdapter<ElementBean,HonCourseContentAdapter.MyViewHolder> {

    public HonCourseContentAdapter(@Nullable List<ElementBean> data) {
        super(R.layout.dynamic_item_course, data);
    }

    @Override
    protected void convert(@NotNull MyViewHolder myViewHolder, ElementBean bean) {
        myViewHolder.tv_name.setText(bean.getElementName());

        myViewHolder.tv_flag.setText(bean.getElementDetail());

        Glide.with(getContext()).load(bean.getImageUrl()).into(myViewHolder.image);
    }

    static class MyViewHolder extends BaseViewHolder{
        public ImageView image;
        public TextView tv_name;
        public TextView tv_flag;
        public MyViewHolder(@NotNull View view) {
            super(view);
            image=view.findViewById(R.id.image);
            tv_name=view.findViewById(R.id.tv_name);
            tv_flag=view.findViewById(R.id.tv_flag);
        }
    }
}
