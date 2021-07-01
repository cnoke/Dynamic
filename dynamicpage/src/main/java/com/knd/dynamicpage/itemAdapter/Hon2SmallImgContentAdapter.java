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

public class Hon2SmallImgContentAdapter extends BaseQuickAdapter<ElementBean, Hon2SmallImgContentAdapter.MyViewHolder> {

    public Hon2SmallImgContentAdapter(@Nullable List<ElementBean> data) {
        super(R.layout.dynamic_item_my_plan, data);
    }

    @Override
    protected void convert(@NotNull MyViewHolder myViewHolder, ElementBean bean) {
        myViewHolder.tv_name.setText(bean.getElementName());

        myViewHolder.tv_flag.setText(bean.getElementDetail());

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
        public TextView tv_flag;
        public MyViewHolder(@NotNull View view) {
            super(view);
            image=view.findViewById(R.id.image);
            tv_name=view.findViewById(R.id.tv_name);
            tv_flag=view.findViewById(R.id.tv_flag);
        }
    }
}
