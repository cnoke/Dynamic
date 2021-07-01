package com.knd.dynamicpage.inter;

import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

public interface OnItemClickListener {
    void onItemClick(@NonNull DelegateAdapter.Adapter<BaseDataBindingHolder> adapter, @NonNull View view, int position);
}
