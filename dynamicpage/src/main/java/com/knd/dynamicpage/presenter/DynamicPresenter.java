package com.knd.dynamicpage.presenter;

import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.inter.DynamicContract;
import com.knd.dynamicpage.model.DynamicModel;
import com.knd.dynamicpage.model.DynamicNetWork;

public class DynamicPresenter extends DynamicBasePresenter<DynamicContract.IView, DynamicModel>
        implements DynamicContract.IPresenter {

    @Override
    protected boolean needLoadMore() {
        return true;
    }

    @Override
    protected boolean getNetWorkData(String searchUrl, BaseDelegateAdapter adapter) {
        return DynamicNetWork.DynamicModel(searchUrl,adapter,getModel());
    }

    @Override
    public void netError() {
        getView().netError();
    }
}
