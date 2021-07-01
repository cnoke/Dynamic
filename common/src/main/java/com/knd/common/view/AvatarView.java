package com.knd.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.knd.common.R;
import com.knd.common.utils.StringUtils;

public class AvatarView extends RelativeLayout {

    private TextView tvName;
    private ImageView ivIcon;
    private String url;
    private String name;
    private View vwBg;
    private boolean isModify = false;
    private float sizeText;

    public AvatarView(Context context) {
        super(context);
        init(context,null);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.common_AvatarView);
            sizeText = ta.getDimension(R.styleable.common_AvatarView_common_avatarView_text_size, 50);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.common_avatar_view,null,false);
        tvName = view.findViewById(R.id.tv_avatar_name);
        tvName.setTextSize(sizeText);
        ivIcon = view.findViewById(R.id.iv_avatar_icon);
        vwBg = view.findViewById(R.id.vw_avatar_bg);
        addView(view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for(int i = 0 , m = getChildCount() ; i < m ; i++){
            View view = getChildAt(i);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for(int i = 0 , m = getChildCount() ; i < m ; i++){
            View view = getChildAt(i);
            view.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
        vwBg.setVisibility(isModify ? VISIBLE : INVISIBLE);
        if(StringUtils.isEmpty(url) && !StringUtils.isEmpty(name)){
            tvName.setText(StringUtils.getAbbreviation(name));
            tvName.setVisibility(VISIBLE);
            ivIcon.setVisibility(INVISIBLE);
        }else if(!StringUtils.isEmpty(url)){
            Glide.with(this).load(url).transform(new CircleCrop())
                    .addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    tvName.setText(StringUtils.getAbbreviation(name));
                    tvName.setVisibility(VISIBLE);
                    ivIcon.setVisibility(INVISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    tvName.setVisibility(INVISIBLE);
                    ivIcon.setVisibility(VISIBLE);
                    return false;
                }
            }).into(ivIcon);
        }else{
            tvName.setVisibility(VISIBLE);
            ivIcon.setVisibility(INVISIBLE);
        }
    }

    public void setModify(boolean isModify) {
        this.isModify = isModify;
        requestLayout();
    }

    public void setImageUrl(String url) {
        this.url = url;
        requestLayout();
    }

    public void setName(String name) {
        this.name = name;
        requestLayout();
    }

    public void setData(String name,String url) {
        this.url = url;
        this.name = name;
        requestLayout();
    }
}
