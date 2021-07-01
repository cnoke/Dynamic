package com.knd.dynamicpage.itemAdapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.knd.common.bean.ElementBean;
import com.knd.dynamicpage.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HonBtnWithStrContentAdapter extends BaseQuickAdapter<ElementBean, HonBtnWithStrContentAdapter.MyViewHolder> {

    public HonBtnWithStrContentAdapter(@Nullable List<ElementBean> data) {
        super(R.layout.dynamic_item_btn_with_str, data);
    }

    @Override
    protected void convert(@NotNull MyViewHolder myViewHolder, ElementBean bean) {
        myViewHolder.tv_name.setText(bean.getElementName());

        Glide.with(getContext()).load(bean.getImageUrl()).placeholder(R.drawable.common_ic_placeholder).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@androidx.annotation.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).into(myViewHolder.image);
    }

    static class MyViewHolder extends BaseViewHolder{
        public ImageView image;
        public TextView tv_name;
        public MyViewHolder(@NotNull View view) {
            super(view);
            image=view.findViewById(R.id.image);
            tv_name=view.findViewById(R.id.tv_name);
        }
    }
}
