package com.knd.dynamicpage.inter;

import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

public interface OnItemChildClickListener {
    /**
     * callback method to be invoked when an item child in this view has been click
     *
     * @param adapter  DelegateAdapter.Adapter
     * @param view     The view whihin the ItemView that was clicked
     * @param position The position of the view int the adapter
     */
    void onItemChildClick(@NonNull DelegateAdapter.Adapter<BaseDataBindingHolder> adapter, @NonNull View view, int position);
}
