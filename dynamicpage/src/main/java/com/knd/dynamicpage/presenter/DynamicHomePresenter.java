package com.knd.dynamicpage.presenter;

import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.common.bean.FloorBean;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.adapter.DynamicBind;
import com.knd.dynamicpage.inter.DynamicHomeContract;
import com.knd.dynamicpage.inter.OnItemChildClickListener;
import com.knd.dynamicpage.model.DynamicHomeModel;
import com.knd.dynamicpage.model.DynamicNetWork;
import com.knd.dynamicpage.utils.DelegateViewType;

import java.util.ArrayList;

public class DynamicHomePresenter extends DynamicBasePresenter<DynamicHomeContract.IView, DynamicHomeModel>
        implements DynamicHomeContract.IPresenter {

    @Override
    protected boolean getNetWorkData(String searchUrl, BaseDelegateAdapter adapter) {
        return DynamicNetWork.DynamicHomeModel(searchUrl,adapter,getModel());
    }

    @Override
    public void init(String pageId) {
        mAdapters = new ArrayList<>();
        urlList = new ArrayList<>();
        BaseDelegateAdapter adapter = DynamicBind.initDynamic(DelegateViewType.POWER_VIEW);
        adapter.addData(new FloorBean());
        mAdapters.add(adapter);
        getView().addData(true,adapter);
        getModel().getInitDate(pageId);
    }

    @Override
    public void netError() {
        BaseDelegateAdapter adapter = DynamicBind.initDynamic(DelegateViewType.NET_ERROR);
        adapter.addData(new FloorBean());
        adapter.addChildClickViewIds(R.id.tv_reload);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            getView().onRetryBtnClick();
        });
        mAdapters.add(adapter);
        getView().addData(false,adapter);
    }

    @Override
    protected boolean needLoadMore() {
        return true;
    }
}
